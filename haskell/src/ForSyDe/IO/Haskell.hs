{-# LANGUAGE OverloadedStrings #-}

module ForSyDe.IO.Haskell
  ( Vertex (..),
    Edge (..),
    MapItem (..),
    ForSyDeModel (..),
    emptyForSyDeModel,
    modelAddEdge,
    modelAddVertex,
    modelGetEdges,
    modelGetVertex,
    vertexFromStrings,
  )
where

-- Base libraries
import Data.Dynamic
-- External libraries
import Data.List
import qualified Data.Map
import Data.Maybe
-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( EdgeTrait (..),
    VertexTrait (..),
  )

data Vertex = Vertex
  { vertexId :: String,
    vertexPorts :: [String],
    vertexProperties :: [MapItem],
    vertexTraits :: [VertexTrait]
  }

instance Eq Vertex where
  (==) v other = vertexId v == vertexId other

data Edge = Edge
  { edgeSource :: Vertex,
    edgeTarget :: Vertex,
    edgeSourcePort :: Maybe String,
    edgeTargetPort :: Maybe String,
    edgeTraits :: [EdgeTrait]
  }
  deriving (Eq)

data MapItem
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem]
  | DictMapItem (Data.Map.Map String MapItem)
  deriving (Eq)

data ForSyDeModel = ForSyDeModel
  { vertexes :: [Vertex],
    edges :: [Edge]
  }

-- | Return an empty 'ForSyDeModel' with no 'Vertex' or 'Edge'
emptyForSyDeModel :: ForSyDeModel
emptyForSyDeModel = ForSyDeModel [] []

vertexFromStrings ::
  String ->
  Vertex
vertexFromStrings idV = Vertex idV [] [] []

-- -- | Queries a 'Vertex' in the model by its Id
-- -- returns 'Nothing' if nones exists.
modelGetVertex ::
  ForSyDeModel ->
  String ->
  Maybe Vertex
modelGetVertex (ForSyDeModel vs _) vId = find (\v -> vId == vertexId v) vs

-- -- | Add a new vertex to the model if it does not exist there yet
-- -- Otherwise returns the same model silently.
modelAddVertex ::
  ForSyDeModel ->
  Vertex ->
  ForSyDeModel
modelAddVertex (ForSyDeModel vs es) v
  | v `elem` vs = ForSyDeModel vs es
  | otherwise = ForSyDeModel (v : vs) es

modelGetEdges ::
  ForSyDeModel ->
  Vertex ->
  Vertex ->
  [Edge]
modelGetEdges (ForSyDeModel _ es) s t = filter (\e -> (edgeSource e == s) && (edgeTarget e == t)) es

modelAddEdge ::
  ForSyDeModel ->
  Edge ->
  ForSyDeModel
modelAddEdge m@(ForSyDeModel vs es) e@(Edge s t _ _ _)
  | e `elem` es = ForSyDeModel vs' es
  | otherwise = ForSyDeModel vs' (e : es)
  where
    vs' = vertexes $ modelAddVertex (modelAddVertex m s) t