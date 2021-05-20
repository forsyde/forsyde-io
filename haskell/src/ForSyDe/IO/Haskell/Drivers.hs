module ForSyDe.IO.Haskell.Drivers () where

import Data.List.Split
import Data.Maybe (fromJust)
import ForSyDe.IO.Haskell
import Text.XML.HXT.Core
import Text.XML.HXT.XPath

modelFromForSyDeMLTree ::
  XmlTree ->
  ForSyDeModel
modelFromForSyDeMLTree root = m
  where
    vElems = getXPath "/graphml/graph//node" root
    mWithVertex = foldr addVertexFromForSyDeMLTree emptyForSyDeModel vElems
    eElems = getXPath "/graphml/graph//edge" root
    m = foldr addEdgeFromForSyDeMLTree mWithVertex eElems

addEdgeFromForSyDeMLTree ::
  XmlTree ->
  ForSyDeModel ->
  ForSyDeModel
addEdgeFromForSyDeMLTree edgeElement m = modelAddEdge m e
  where
    sId = read . head $ (runLA $ getAttrValue "source") edgeElement
    tId = read . head $ (runLA $ getAttrValue "target") edgeElement
    pSourceId = read . head $ (runLA $ getAttrValue "sourceport") edgeElement
    pTargetId = read . head $ (runLA $ getAttrValue "targetport") edgeElement
    tString = head $ (runLA $ getAttrValue "traits") edgeElement
    source = fromJust $ modelGetVertex m sId
    target = fromJust $ modelGetVertex m tId
    sourcePort = case pSourceId of
      "" -> Nothing
      a -> Just a
    targetPort = case pTargetId of
      "" -> Nothing
      a -> Just a
    ts = map read $ splitOn ";" tString
    e = Edge source target sourcePort targetPort ts

addVertexFromForSyDeMLTree ::
  XmlTree ->
  ForSyDeModel ->
  ForSyDeModel
addVertexFromForSyDeMLTree vertexElement m = modelAddVertex m v
  where
    vidString = head $ (runLA $ getAttrValue "id") vertexElement
    tString = head $ (runLA $ getAttrValue "traits") vertexElement
    portsElems = (runLA $ getChildren >>> isElem >>> hasName "port") vertexElement
    vid = read vidString
    ts = map read $ splitOn ";" tString
    ports = map parsePortFromXML portsElems
    properties = []
    v = Vertex vid ports properties ts

parsePortFromXML ::
  XmlTree ->
  String
parsePortFromXML portElement = head $ (runLA $ getAttrValue "name") portElement

-- propertiesFromForSyDeMLTree ::
--   XmlTree ->
--   MapItem
-- propertiesFromForSyDeMLTree propElement = undefined

-- modelFromXMLTree ::
--   XmlTree ->
--   ForSyDeModel
-- modelFromXMLTree root = m
--   where
--     vElems = getXPath "/ForSyDeModel/Vertex" root
--     mWithVertex = foldr addVertexFromXMLTree emptyForSyDeModel vElems
--     eElems = getXPath "/ForSyDeModel/Edge" root
--     m = foldr addEdgeFromXMLTree mWithVertex eElems

-- addEdgeFromXMLTree ::
--   XmlTree ->
--   ForSyDeModel ->
--   ForSyDeModel
-- addEdgeFromXMLTree edgeElement m = modelAddEdge m e
--   where
--     sId = read . head $ (runLA $ getAttrValue "source_id") edgeElement
--     tId = read . head $ (runLA $ getAttrValue "target_id") edgeElement
--     pSourceId = read . head $ (runLA $ getAttrValue "source_port_id") edgeElement
--     pTargetId = read . head $ (runLA $ getAttrValue "target_port_id") edgeElement
--     tString = head $ (runLA $ getAttrValue "type") edgeElement
--     source = fromJust $ modelGetVertex m sId
--     target = fromJust $ modelGetVertex m tId
--     sourcePort = find (\p -> pSourceId == portIdentifier p) (vertexPorts source)
--     targetPort = find (\p -> pTargetId == portIdentifier p) (vertexPorts target)
--     t = (makeTypeFromName . read) tString
--     e = Edge source target sourcePort targetPort t

-- addVertexFromXMLTree ::
--   XmlTree ->
--   ForSyDeModel ->
--   ForSyDeModel
-- addVertexFromXMLTree vertexElement m = modelAddVertex m v
--   where
--     vidString = head $ (runLA $ getAttrValue "id") vertexElement
--     tString = head $ (runLA $ getAttrValue "type") vertexElement
--     vid = read vidString
--     t = (makeTypeFromName . read) tString
--     ports = map parsePortFromXML portsElems
--     properties = []
--     portsElems = (runLA $ getChildren >>> isElem >>> hasName "Port") vertexElement
--     v = Vertex vid ports properties t

-- parsePortFromXML ::
--   XmlTree ->
--   String
-- parsePortFromXML portElement = pid
--   where
--     pidString = head $ (runLA $ getAttrValue "id") portElement
--     tString = head $ (runLA $ getAttrValue "type") portElement
--     pid = read pidString
--     t = (makeTypeFromName . read) tString
--     p = Port pid t

-- propertiesFromXMLTree ::
--   XmlTree ->
--   MapItem
-- propertiesFromXMLTree propElement = undefined
