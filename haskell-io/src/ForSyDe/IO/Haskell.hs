{-# LANGUAGE OverloadedStrings #-}
module ForSyDe.IO.Haskell ( Port
                          , Vertex
                          , Edge
                          , ForSyDeModel
                          )
                            where
-- Base libraries
import Data.List
import Data.Dynamic

-- External libraries
import Text.Regex.Base
import Text.Regex.TDFA
import Data.Hashable
import qualified Data.Map as Map

-- Internal libraries
import ForSyDe.IO.Haskell.Types ( Type(Unknown)
                                , makeTypeFromName
                                , getTypeStandardProperties
                                )

data Port idType = Port { portIdentifier :: idType
                        , portType :: Type
                        } deriving (Show, Eq)

instance (Hashable idType) => Hashable (Port idType) where
  hashWithSalt salt (Port id _) = hashWithSalt salt id

data Vertex idType = Vertex { vertexIdentifier :: idType
                            , vertexPorts :: Map.Map idType (Port idType)
                            , vertexProperties :: Map.Map idType Dynamic
                            , vertexType :: Type
                            } deriving (Show)

instance (Hashable idType) => Hashable (Vertex idType) where
  hashWithSalt salt (Vertex id _ _ _) = hashWithSalt salt id

data Edge vIdType pIdType = Edge { sourceVertex :: vIdType
                                 , targetVertex :: vIdType
                                 , sourceVertexPort :: pIdType
                                 , targetVertexPort :: pIdType
                                 , edgeType :: Type
                                 } deriving (Show, Eq)

instance (Hashable vIdType, Hashable pIdType) => Hashable (Edge vIdType pIdType) where
  hashWithSalt salt (Edge sId tId _ _ _) = 
    hashWithSalt salt sId + hashWithSalt salt tId

data ForSyDeModel idType = ForSyDeModel { vertexes :: Map.Map idType (Vertex idType)
                                        , edges :: Map.Map (idType, idType) [(Edge idType idType)]
                                        } deriving (Show)

emptyForSyDeModel :: ForSyDeModel idType
emptyForSyDeModel = ForSyDeModel Map.empty Map.empty

forSyDeModelGetNode 
  :: (Ord idType, Eq idType, Hashable idType) 
  => ForSyDeModel idType
  -> idType
  -> Maybe (Vertex idType)
forSyDeModelGetNode (ForSyDeModel vSet eSet) id = Map.lookup id vSet


forSyDeModelAddNode 
  :: (Ord idType, Eq idType, Hashable idType)
  => Vertex idType
  -> ForSyDeModel idType
  -> ForSyDeModel idType
forSyDeModelAddNode vertex@(Vertex id _ _ _) (ForSyDeModel vSet eSet) = 
  let newVSet = Map.insert id vertex vSet
  in ForSyDeModel newVSet eSet
  
forSyDeModelAddEdge 
  :: (Ord idType, Eq idType, Hashable idType, Show idType) 
  => Edge idType idType
  -> ForSyDeModel idType
  -> ForSyDeModel idType
forSyDeModelAddEdge edge@(Edge sid tid _ _ _) (ForSyDeModel vSet eSet)
  | Map.member (sid, tid) eSet = ForSyDeModel vSet $ Map.adjust (edge:) (sid, tid) eSet
  | otherwise                  = ForSyDeModel vSet $ Map.insert (sid, tid) [edge] eSet

forSyDeModelFromString 
  :: String 
  -> Either String (ForSyDeModel String)
forSyDeModelFromString wholeString = forSyDeModelFromTokens tokens $ Right emptyForSyDeModel
  where
    tokens = lines wholeString

forSyDeModelFromTokens :: [String] -> Either String (ForSyDeModel String) -> Either String (ForSyDeModel String)
forSyDeModelFromTokens (token:tokens) (Right currentModel)
  | isInfixOf "%" token = forSyDeModelFromTokens tokens $ Right currentModel

  | isInfixOf "vertex" token = 
    let [vId, vTypeName] = filteredMatches
        vType = read vTypeName
        newVertex = Vertex vId Map.empty Map.empty vType :: Vertex String
        newModel = forSyDeModelAddNode newVertex currentModel
    in forSyDeModelFromTokens tokens $ Right newModel

  | isInfixOf "edge" token = 
    let [sId, tId, sPortId, tPortId, eTypeName] = filteredMatches
        eType = read eTypeName
        newEdge = Edge sId tId sPortId tPortId eType :: Edge String String
        newModel = forSyDeModelAddEdge newEdge currentModel
    in forSyDeModelFromTokens tokens $ Right newModel

  | isInfixOf "port" token = 
    let [vId, pId, pTypeName] = filteredMatches
        pType = read pTypeName
        newModel = case forSyDeModelGetNode currentModel vId of
          Just (Vertex _ ports props vType) -> 
            forSyDeModelAddNode (Vertex vId (Map.insert pId (Port pId pType) ports) props vType) currentModel
          Nothing ->
            forSyDeModelAddNode (Vertex vId (Map.singleton pId (Port pId pType)) Map.empty Unknown) currentModel
    in forSyDeModelFromTokens tokens $ Right newModel
  -- TODO: Implement property parsing
  | isInfixOf "prop" token = forSyDeModelFromTokens tokens $ Right currentModel

  | otherwise = Left ""
  where
    regexpat = "'([a-zA-Z0-9_]+)'[,)]" :: String
    matches = getAllTextMatches (token =~ regexpat) :: [String]
    -- drop the first ' and the last ', or ')
    filteredMatches = map (tail . (\s -> take ((length s) -2) s)) matches :: [String]
    
forSyDeModelFromTokens _ (Left err) = Left err
forSyDeModelFromTokens [] (Right model) = Right model

listToRelation
  :: (Show e)
  => String
  -> [e]
  -> String
listToRelation relName le = relName ++ "(" ++ listOfAtoms ++ ")."
  where
    listOfAtoms = intercalate ", " $ map (\s -> "'" ++ (show s) ++ "'") le

forSyDeModelToTokens
  :: (Eq idType, Ord idType, Hashable idType, Show idType)
  => ForSyDeModel idType
  -> [String]
forSyDeModelToTokens model@(ForSyDeModel vSet eSet) =
  let vStrings = Map.elems $ Map.map vToStr vSet
      pStrings = [] :: [String]
      eStrings = [] :: [String]
  in vStrings ++ pStrings ++ eStrings
    where
      vToStr (Vertex vId _ _ t) = listToRelation "vertex" [vId, t]
      pToStr (Vertex vId _ _ _) (Port pId t) = listToRelation  "port" [vId, pId, t]

forSyDeModelToString
  :: (Eq idType, Ord idType, Hashable idType, Show idType)
  => ForSyDeModel idType
  -> String
forSyDeModelToString model = intercalate "\n" $ forSyDeModelToTokens model
