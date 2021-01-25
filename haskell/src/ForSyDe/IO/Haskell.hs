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

data MapItem keyType
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem keyType]
  | DictMapItem (Map.Map keyType (MapItem keyType))

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
    sourceVertexPort :: Maybe Port idType,
    targetVertexPort :: Maybe Port idTye,
    edgeType :: ModelType
  }
  deriving (Eq)

instance (Hashable vIdType, Hashable pIdType) => Hashable (Edge vIdType pIdType) where
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

-- | Queries a 'Vertex' in the model by its Id
-- returns 'Nothing' if nones exists.
modelGetVertex ::
  (Eq idType) =>
  ForSyDeModel idType ->
  idType ->
  Maybe (Vertex idType)
modelGetVertex cId (ForSyDeModel vs _) = find (\v -> vId == vertexIdentifier v) vs

-- | Add a new vertex to the model if it does not exist there yet
-- Otherwise returns the same model silently.
modelAddVertex ::
  (Eq idType) =>
  ForSyDeModel idType ->
  Vertex idType ->
  ForSyDeModel idType
modelAddVertex v (ForSyDeModel vs _)
  | v `elem` vs = vs
  | otherwise = v : vs

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
modelAddEdge (ForSyDeModel vs es) e@(Edge s t _ _ _)
  | e `elem` es = modelWithVertexes es
  | otherwise = modelWithVertexes (e : es)
  where
    modelWithVertexes = ForSyDeModel (modelAddVertex s $ modelAddVertex t vs)

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

vertexesFromXML ::
  (Hashable idType) =>
  XmlTree ->
  [(Vertex idType)]
vertexesFromXML (NTree _ []) = []
-- found a vertex tag and therefore parse it
vertexesFromXML (NTree (XTag "Vertex" attrs) cs) = newVertex : vertexesFromXML cs
-- Did not find vertex so we recurse
vertexesFromXML (NTree (XTag _ _) cs) = vertexesFromXML cs

addVertexFromXML ::
  (Eq idType) =>
  XmlTree ->
  ForSyDeModel idType ->
  ForSyDeModel idType
addVertexFromXML vertexElement m =
  let id = getAttrValue "id" vertexElement
      t = makeTypeFromName (getAttrValue "type" vertexElement)
      -- TODO: continue here
      -- portsElems = deep undefined
      ports = []
      properties = []
   in modelAddVertex m (Vertex id ports properties t)

parsePortFromXML ::
  (Hashable idType) =>
  XmlTree ->
  Port idType
parsePortFromXML portElement =
  let id = getAttrValue "id" portElement
      t = makeTypeFromName (getAttrValue "type" portElement)
   in Port id t

addEdgeFromXML ::
  (Eq idType) =>
  XmlTree ->
  ForSyDeModel idType ->
  ForSyDeModel idType
addEdgeFromXML = undefined
