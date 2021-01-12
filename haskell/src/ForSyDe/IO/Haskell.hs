{-# LANGUAGE GADTs #-}
{-# LANGUAGE OverloadedStrings #-}

module ForSyDe.IO.Haskell
  ( Port,
    Vertex,
    Edge,
    ForSyDeModel,
  )
where

-- Base libraries

import Data.Dynamic
-- External libraries
import Data.Hashable
import Data.List
import qualified Data.Map as Map
-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( ModelType (Unknown),
    getTypeDefaultProperties,
    makeTypeFromName,
  )
import Text.Regex.Base
import Text.Regex.TDFA
import Text.XML.HXT.Core

-- not super optimal, but does the trick in a very standard
-- Haskell way
-- data PropertyMap k = IntegerProperty k Integer (PropertyMap k)
--                    | FloatProperty k Float (PropertyMap k)
--                    | DoubleProperty k Double (PropertyMap k)
--                    | StringProperty k String (PropertyMap k)
--                    | ChildrenProperty k [PropertyMap k] (PropertyMap k)
--                    | NilPropertyMap

-- failed GADT attempt
-- data PropertyMap k v where
--   IntPropertyMap :: (Integral a) => k -> a -> PropertyMap k a
--   FloatPropertyMap :: (Floating a) => k -> a -> PropertyMap k a
--   TextPropertyMap :: k -> String -> PropertyMap k String
--   ChildPropertyMap :: k -> [PropertyMap k v] -> PropertyMap k [PropertyMap k v]
--   NilPropertyMap :: PropertyMap k v -- ??

-- instance Foldable PropertyMap where
--   foldMap f NilPropertyMap = mempty
--   foldMap f (IntegerProperty k v others) = f v `mappend` foldMap f others
--   foldMap f (FloatProperty k v others) = foldMap f others
--   foldMap f (DoubleProperty k v others) = foldMap f others
--   foldMap f (StringProperty k v others) = foldMap f others
--   foldMap f (ChildrenProperty k children others) = (foldr (foldMap f) children) `mappend` foldMap f others

-- instance (Integral a) => Foldable (PropertyMap keyType) where
--   foldMap f NilPropertyMap = mempty
--   foldMap f (IntPropertyMap k v) = f v
--   foldMap f (ChildPropertyMap k v)

-- instance (Eq keyType) => Eq (PropertyMap keyType) where
--   (==) pMap otherPMap |
--     NilPropertyMap _ = False
--     _ NilPropertyMap = False
--     NilPropertyMap NilPropertyMap = True
--     (IntegerProperty k v others) (

data MapItem keyType
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem keyType]
  | DictMapItem (Map.Map keyType (MapItem keyType))

data Port idType = Port
  { portIdentifier :: idType,
    portType :: Type
  }
  deriving (Eq)

instance (Hashable idType) => Hashable (Port idType) where
  hashWithSalt salt (Port id _) = hashWithSalt salt id

data Vertex idType = Vertex
  { vertexIdentifier :: idType,
    vertexPorts :: Map.Map idType (Port idType),
    vertexProperties :: Map.Map idType (MapItem idType),
    vertexType :: Type
  }

instance (Eq idType) => Eq (Vertex idType) where
  (==) (Vertex id ports props vtype) (Vertex oid oports oprops ovtype)
    | id == oid && vtype == ovtype && ports == oports = True
    | otherwise = False

instance (Hashable idType) => Hashable (Vertex idType) where
  hashWithSalt salt (Vertex id _ _ _) = hashWithSalt salt id

data Edge vIdType pIdType = Edge
  { sourceVertex :: vIdType,
    targetVertex :: vIdType,
    sourceVertexPort :: pIdType,
    targetVertexPort :: pIdType,
    edgeType :: Type
  }
  deriving (Eq)

instance (Hashable vIdType, Hashable pIdType) => Hashable (Edge vIdType pIdType) where
  hashWithSalt salt (Edge sId tId _ _ _) =
    hashWithSalt salt sId + hashWithSalt salt tId

data ForSyDeModel idType = ForSyDeModel
  { vertexes :: Map.Map idType (Vertex idType),
    edges :: Map.Map (idType, idType) [(Edge idType idType)]
  }
  deriving (Eq)

emptyForSyDeModel :: ForSyDeModel idType
emptyForSyDeModel = ForSyDeModel Map.empty Map.empty

modelGetVertex ::
  (Ord idType, Eq idType, Hashable idType) =>
  ForSyDeModel idType ->
  idType ->
  Maybe (Vertex idType)
modelGetVertex (ForSyDeModel vSet eSet) id = Map.lookup id vSet

modelAddVertex ::
  (Ord idType, Eq idType, Hashable idType) =>
  Vertex idType ->
  ForSyDeModel idType ->
  ForSyDeModel idType
modelAddVertex vertex@(Vertex id _ _ _) (ForSyDeModel vSet eSet) =
  let newVSet = Map.insert id vertex vSet
   in ForSyDeModel newVSet eSet

modelAddEdge ::
  (Ord idType, Eq idType, Hashable idType, Show idType) =>
  Edge idType idType ->
  ForSyDeModel idType ->
  ForSyDeModel idType
modelAddEdge edge@(Edge sid tid _ _ _) (ForSyDeModel vSet eSet)
  | Map.member (sid, tid) eSet = ForSyDeModel vSet $ Map.adjust (edge :) (sid, tid) eSet
  | otherwise = ForSyDeModel vSet $ Map.insert (sid, tid) [edge] eSet

-- modelFromString
--   :: String
--   -> Either String (ForSyDeModel String)
-- modelFromString wholeString = modelFromTokens tokens $ Right emptyForSyDeModel
--   where
--     tokens = lines wholeString
--
-- modelFromTokens :: [String] -> Either String (ForSyDeModel String) -> Either String (ForSyDeModel String)
-- modelFromTokens (token:tokens) (Right currentModel)
--   | isInfixOf "%" token = modelFromTokens tokens $ Right currentModel
--
--   | isInfixOf "vertex" token =
--     let [vId, vTypeName] = filteredMatches
--         vType = read vTypeName
--         newVertex = Vertex vId Map.empty Map.empty vType :: Vertex String
--         newModel = modelAddVertex newVertex currentModel
--     in modelFromTokens tokens $ Right newModel
--
--   | isInfixOf "edge" token =
--     let [sId, tId, sPortId, tPortId, eTypeName] = filteredMatches
--         eType = read eTypeName
--         newEdge = Edge sId tId sPortId tPortId eType :: Edge String String
--         newModel = modelAddEdge newEdge currentModel
--     in modelFromTokens tokens $ Right newModel
--
--   | isInfixOf "port" token =
--     let [vId, pId, pTypeName] = filteredMatches
--         pType = read pTypeName
--         newModel = case modelGetVertex currentModel vId of
--           Just (Vertex _ ports props vType) ->
--             modelAddVertex (Vertex vId (Map.insert pId (Port pId pType) ports) props vType) currentModel
--           Nothing ->
--             modelAddVertex (Vertex vId (Map.singleton pId (Port pId pType)) Map.empty Unknown) currentModel
--     in modelFromTokens tokens $ Right newModel
--   -- TODO: Implement property parsing
--   | isInfixOf "prop" token = modelFromTokens tokens $ Right currentModel
--
--   | otherwise = Left ""
--   where
--     regexpat = "'([a-zA-Z0-9_]+)'[,)]" :: String
--     matches = getAllTextMatches (token =~ regexpat) :: [String]
--     -- drop the first ' and the last ', or ')
--     filteredMatches = map (tail . (\s -> take ((length s) -2) s)) matches :: [String]
--
-- modelFromTokens _ (Left err) = Left err
-- modelFromTokens [] (Right model) = Right model

-- listToRelation
--   :: (Show e)
--   => String
--   -> [e]
--   -> String
-- listToRelation relName le = relName ++ "(" ++ listOfAtoms ++ ")."
--   where
--     listOfAtoms = intercalate ", " $ map (\s -> "'" ++ (show s) ++ "'") le
--
-- modelToTokens
--   :: (Eq idType, Ord idType, Hashable idType, Show idType)
--   => ForSyDeModel idType
--   -> [String]
-- modelToTokens model@(ForSyDeModel vSet eSet) =
--   let vStrings = Map.elems $ Map.map vToStr vSet
--       pStrings = [] :: [String]
--       eStrings = [] :: [String]
--   in vStrings ++ pStrings ++ eStrings
--     where
--       vToStr (Vertex vId _ _ t) = listToRelation "vertex" [vId, t]
--       pToStr (Vertex vId _ _ _) (Port pId t) = listToRelation  "port" [vId, pId, t]
--
-- modelToString
--   :: (Eq idType, Ord idType, Hashable idType, Show idType)
--   => ForSyDeModel idType
--   -> String
-- modelToString model = intercalate "\n" $ modelToTokens model

modelFromXML ::
  (Read idType, Hashable idType) =>
  XmlTree ->
  ForSyDeModel idType
modelFromXML = undefined

propertiesFromXML ::
  (Read keyType) =>
  XmlTree ->
  Map.Map keyType (MapItem keyType)
propertiesFromXML = undefined

vertexesFromXML ::
  (Hashable idType) =>
  XmlTree ->
  [(Vertex idType)]
vertexesFromXML (NTree _ []) = []
-- found a vertex tag and therefore parse it
vertexesFromXML (NTree (XTag "Vertex" attrs) cs) = newVertex : vertexesFromXML cs
-- Did not find vertex so we recurse
vertexesFromXML (NTree (XTag _ _) cs) = vertexesFromXML cs

vertexFromXML ::
  (Hashable idType) =>
  XmlTree ->
  (Vertex idType)
vertexFromXML vertexElement =
  let id = getAttrValue "id" vertexElement
      t = makeTypeFromName (getAttrValue "type" vertexElement)
      -- TODO: continue here
      -- portsElems = deep undefined
      ports = Map.Map.empty
      properties = Map.Map.empty
   in Vertex id ports properties t

portFromXML ::
  (Hashable idType) =>
  XmlTree ->
  (Port idType)
portFromXML portElement =
  let id = getAttrValue "id" vertexElement
      t = makeTypeFromName (getAttrValue "type" vertexElement)
   in Port id t

edgesFromXML ::
  (Hashable idType) =>
  XmlTree ->
  [(Edge idType idType)]
edgesFromXML = undefined
