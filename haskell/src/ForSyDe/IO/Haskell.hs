{-# LANGUAGE GADTs #-}
{-# LANGUAGE OverloadedStrings #-}

module ForSyDe.IO.Haskell
  ( Vertex,
    Edge,
    ForSyDeModel,
  )
where

-- Base libraries
import Data.Dynamic
-- External libraries
import Data.Hashable
import Data.List
import qualified Data.Map
import Data.Maybe
-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( EdgeTrait (..),
    VertexTrait (..),
  )
import Text.XML.HXT.Core
import Text.XML.HXT.XPath

data Vertex = Vertex
  { vertexId :: String,
    vertexPorts :: [String],
    vertexProperties :: [MapItem],
    vertexTraits :: [VertexTrait]
  }

data Edge = Edge
  { edgeSource :: Vertex,
    edgeTarget :: Vertex,
    edgeSourcePort :: String,
    edgeTargetPort :: String,
    edgeTraits :: [EdgeTrait]
  }

data MapItem
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem]
  | DictMapItem (Data.Map.Map String MapItem)

data ForSyDeModel = ForSyDeModel
  { vertexes :: [Vertex],
    edges :: [Edge]
  }

-- | Return an empty 'ForSyDeModel' with no 'Vertex' or 'Edge'
emptyForSyDeModel :: ForSyDeModel
emptyForSyDeModel = ForSyDeModel [] []

-- vertexFromStrings ::
--   String ->
--   Vertex
-- vertexFromStrings idV = Vertex idV [] [] (makeTypeFromName t)

-- -- | Queries a 'Vertex' in the model by its Id
-- -- returns 'Nothing' if nones exists.
-- modelGetVertex ::
--   ForSyDeModel ->
--   String ->
--   Maybe Vertex
-- modelGetVertex (ForSyDeModel vs _) vId = find (\v -> vId == vertexIdentifier v) vs

-- -- | Add a new vertex to the model if it does not exist there yet
-- -- Otherwise returns the same model silently.
-- modelAddVertex ::
--   ForSyDeModel ->
--   Vertex ->
--   ForSyDeModel
-- modelAddVertex (ForSyDeModel vs es) v
--   | v `elem` vs = ForSyDeModel vs es
--   | otherwise = ForSyDeModel (v : vs) es

-- modelGetEdges ::
--   ForSyDeModel ->
--   Vertex ->
--   Vertex ->
--   [Edge]
-- modelGetEdges (ForSyDeModel _ es) s t = filter (\e -> (sourceVertex e == s) && (targetVertex e == t)) es

-- modelAddEdge ::
--   ForSyDeModel ->
--   Edge ->
--   ForSyDeModel
-- modelAddEdge m@(ForSyDeModel vs es) e@(Edge s t _ _ _)
--   | e `elem` es = ForSyDeModel vs' es
--   | otherwise = ForSyDeModel vs' (e : es)
--   where
--     vs' = vertexes $ modelAddVertex (modelAddVertex m s) t

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
