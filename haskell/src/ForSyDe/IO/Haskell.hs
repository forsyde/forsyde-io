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
import Data.Maybe
-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( ModelType (Unknown),
    getTypeDefaultProperties,
    makeTypeFromName,
  )
import Text.Regex.Base
import Text.Regex.TDFA
import Text.XML.HXT.Core

data MapItem keyType
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem keyType]
  | DictMapItem [(keyType, MapItem keyType)]

data Port idType = Port
  { portIdentifier :: idType,
    portType :: ModelType
  }
  deriving (Eq)

instance (Hashable idType) => Hashable (Port idType) where
  hashWithSalt salt (Port id _) = hashWithSalt salt id

data Vertex idType = Vertex
  { vertexIdentifier :: idType,
    vertexPorts :: [Port idType],
    vertexProperties :: [(idType, MapItem idType)],
    vertexType :: ModelType
  }

instance (Eq idType) => Eq (Vertex idType) where
  (==) (Vertex id ports props vtype) (Vertex oid oports oprops ovtype)
    | id == oid && vtype == ovtype && ports == oports = True
    | otherwise = False

instance (Hashable idType) => Hashable (Vertex idType) where
  hashWithSalt salt (Vertex id _ _ _) = hashWithSalt salt id

data Edge idType = Edge
  { sourceVertex :: Vertex idType,
    targetVertex :: Vertex idType,
    sourceVertexPort :: Maybe (Port idType),
    targetVertexPort :: Maybe (Port idType),
    edgeType :: ModelType
  }
  deriving (Eq)

instance (Hashable vIdType) => Hashable (Edge vIdType) where
  hashWithSalt salt (Edge sId tId _ _ _) =
    hashWithSalt salt sId + hashWithSalt salt tId

data ForSyDeModel idType = ForSyDeModel
  { vertexes :: [Vertex idType],
    edges :: [Edge idType]
  }
  deriving (Eq)

-- | Return an empty 'ForSyDeModel' with no 'Vertex' or 'Edge'
emptyForSyDeModel :: ForSyDeModel idType
emptyForSyDeModel = ForSyDeModel [] []

vertexFromStrings ::
  idType ->
  String ->
  Vertex idType
vertexFromStrings idV t = Vertex idV [] [] (makeTypeFromName t)

-- | Queries a 'Vertex' in the model by its Id
-- returns 'Nothing' if nones exists.
modelGetVertex ::
  (Eq idType) =>
  ForSyDeModel idType ->
  idType ->
  Maybe (Vertex idType)
modelGetVertex (ForSyDeModel vs _) vId = find (\v -> vId == vertexIdentifier v) vs

-- | Add a new vertex to the model if it does not exist there yet
-- Otherwise returns the same model silently.
modelAddVertex ::
  (Eq idType) =>
  ForSyDeModel idType ->
  Vertex idType ->
  ForSyDeModel idType
modelAddVertex (ForSyDeModel vs es) v
  | v `elem` vs = ForSyDeModel vs es
  | otherwise = ForSyDeModel (v : vs) es

modelGetEdges ::
  (Eq idType) =>
  ForSyDeModel idType ->
  Vertex idType ->
  Vertex idType ->
  [Edge idType]
modelGetEdges (ForSyDeModel _ es) s t = filter (\e -> (sourceVertex e == s) && (targetVertex e == t)) es

modelAddEdge ::
  (Eq idType) =>
  ForSyDeModel idType ->
  Edge idType ->
  ForSyDeModel idType
modelAddEdge m@(ForSyDeModel vs es) e@(Edge s t _ _ _)
  | e `elem` es = ForSyDeModel vs' es
  | otherwise = ForSyDeModel vs' (e : es)
  where
    vs' = vertexes $ modelAddVertex (modelAddVertex m s) t

modelFromXML ::
  (Read idType, Eq idType) =>
  XmlTree ->
  ForSyDeModel idType
modelFromXML = undefined

propertiesFromXML ::
  (Read keyType) =>
  XmlTree ->
  [MapItem keyType]
propertiesFromXML = undefined

addVertexFromXML ::
  (Read idType, Eq idType) =>
  XmlTree ->
  ForSyDeModel idType ->
  ForSyDeModel idType
addVertexFromXML vertexElement m =
  undefined

-- let id = read $ head . runLA $ getAttrValue "id" vertexElement
--     t = makeTypeFromName (getAttrValue "type" vertexElement)
--     portsElems = deep (isElem >>> hasName "Port")
--     ports = []
--     properties = []
--  in modelAddVertex m (Vertex id ports properties t)

parseVertexFromXML ::
  (Read idType, Eq idType) =>
  XmlTree ->
  Vertex idType
parseVertexFromXML vertexElement =
  let vid = read vidString
      t = (makeTypeFromName . read) tString
      ports = map parsePortFromXML portsElems
      properties = []
   in Vertex vid ports properties t
  where
    vidString = head $ (runLA $ getAttrValue "id") vertexElement
    tString = head $ (runLA $ getAttrValue "type") vertexElement
    portsElems = (runLA $ getChildren >>> isElem >>> hasName "Port") vertexElement

parsePortFromXML ::
  (Read idType, Eq idType) =>
  XmlTree ->
  Port idType
parsePortFromXML portElement =
  let pid = read pidString
      t = (makeTypeFromName . read) tString
   in Port pid t
  where
    pidString = head $ (runLA $ getAttrValue "id") portElement
    tString = head $ (runLA $ getAttrValue "type") portElement

addEdgeFromXML ::
  (Read idType, Eq idType) =>
  XmlTree ->
  ForSyDeModel idType ->
  ForSyDeModel idType
addEdgeFromXML edgeElement m =
  let source = fromJust (modelGetVertex m sId)
      target = fromJust (modelGetVertex m tId)
      t = (makeTypeFromName . read) tString
   in modelAddEdge m (Edge source target Nothing Nothing t)
  where
    sId = read . head $ (runLA $ getAttrValue "source_id") edgeElement
    tId = read . head $ (runLA $ getAttrValue "target_id") edgeElement
    tString = head $ (runLA $ getAttrValue "type") edgeElement
