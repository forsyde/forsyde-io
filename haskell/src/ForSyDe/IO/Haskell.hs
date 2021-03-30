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
import Data.List
import Data.Maybe
import qualified Data.Map

-- External libraries
import Data.Hashable
import Text.XML.HXT.Core
import Text.XML.HXT.XPath

-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( Vertex(..),
    Edge(..),
    Port(..),
    MapItem(..)
  )

data ForSyDeModel v e = ForSyDeModel
  { vertexes :: [v],
    edges :: [e]
  }

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

modelFromXMLTree ::
  (Read idType, Eq idType) =>
  XmlTree ->
  ForSyDeModel idType
modelFromXMLTree root = m
  where
    vElems = getXPath "/ForSyDeModel/Vertex" root
    mWithVertex = foldr addVertexFromXMLTree emptyForSyDeModel vElems
    eElems = getXPath "/ForSyDeModel/Edge" root
    m = foldr addEdgeFromXMLTree mWithVertex eElems

addEdgeFromXMLTree ::
  (Read idType, Eq idType) =>
  XmlTree ->
  ForSyDeModel idType ->
  ForSyDeModel idType
addEdgeFromXMLTree edgeElement m = modelAddEdge m e
  where
    sId = read . head $ (runLA $ getAttrValue "source_id") edgeElement
    tId = read . head $ (runLA $ getAttrValue "target_id") edgeElement
    pSourceId = read . head $ (runLA $ getAttrValue "source_port_id") edgeElement
    pTargetId = read . head $ (runLA $ getAttrValue "target_port_id") edgeElement
    tString = head $ (runLA $ getAttrValue "type") edgeElement
    source = fromJust $ modelGetVertex m sId
    target = fromJust $ modelGetVertex m tId
    sourcePort = find (\p -> pSourceId == portIdentifier p) (vertexPorts source)
    targetPort = find (\p -> pTargetId == portIdentifier p) (vertexPorts target)
    t = (makeTypeFromName . read) tString
    e = Edge source target sourcePort targetPort t

addVertexFromXMLTree ::
  (Read idType, Eq idType) =>
  XmlTree ->
  ForSyDeModel idType ->
  ForSyDeModel idType
addVertexFromXMLTree vertexElement m = modelAddVertex m v
  where
    vidString = head $ (runLA $ getAttrValue "id") vertexElement
    tString = head $ (runLA $ getAttrValue "type") vertexElement
    vid = read vidString
    t = (makeTypeFromName . read) tString
    ports = map parsePortFromXML portsElems
    properties = []
    portsElems = (runLA $ getChildren >>> isElem >>> hasName "Port") vertexElement
    v = Vertex vid ports properties t

parsePortFromXML ::
  (Read idType, Eq idType) =>
  XmlTree ->
  Port idType
parsePortFromXML portElement = p
  where
    pidString = head $ (runLA $ getAttrValue "id") portElement
    tString = head $ (runLA $ getAttrValue "type") portElement
    pid = read pidString
    t = (makeTypeFromName . read) tString
    p = Port pid t

propertiesFromXMLTree ::
  (Read keyType) =>
  XmlTree ->
  MapItem keyType
propertiesFromXMLTree propElement = undefined
