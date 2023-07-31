{-# LANGUAGE OverloadedStrings #-}

module ForSyDe.IO.Haskell
  ( Vertex (..),
    -- Edge (..),
    MapItem (..),
    ForSyDeModel,
    MultiEdgeLabel (..),
    EdgeLabel (..),
    -- emptyForSyDeModel,
    -- modelAddEdge,
    -- modelAddVertex,
    -- modelGetEdges,
    -- modelGetVertex,
    -- vertexFromStrings,
  )
where

-- Base libraries

-- External libraries

import Data.Graph.DMultiGraph
-- import Algebra.Graph.Labelled
import Data.Dynamic
import Data.List
import qualified Data.Map
import Data.Maybe
-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( EdgeTrait (..),
    VertexTrait (..),
  )
import Data.Graph.DMultiGraph (DMultiGraph)

data MapItem
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem]
  | StringDictMapItem (Data.Map.Map String MapItem)
  | IntDictMapItem (Data.Map.Map Integer MapItem)
  deriving (Eq, Show)

data Vertex = Vertex
  { vertexId :: String,
    vertexPorts :: [String],
    vertexProperties :: Data.Map.Map String MapItem,
    vertexTraits :: [VertexTrait]
  }
  deriving (Show)

newtype VertexRef = VertexRef String deriving (Eq, Show)

newtype Edge = Edge (VertexRef, VertexRef, Maybe String, Maybe String, [EdgeTrait]) deriving (Eq, Show)

instance Eq Vertex where
  (==) v other = vertexId v == vertexId other

-- data MultiEdgeLabel = MultiEdgeLabel
--   {
--     -- edgeSource :: Vertex,
--     -- edgeTarget :: Vertex,
--     edgeSourcePort :: Maybe String,
--     edgeTargetPort :: Maybe String,
--     edgeTraits :: [EdgeTrait]
--   }
--   deriving (Eq)

newtype EdgeLabel = EdgeLabel (Maybe String, Maybe String, [EdgeTrait]) deriving (Eq, Show)

get2 (EdgeLabel (a, b, c)) = (a, b)

newtype MultiEdgeLabel = MultiEdgeLabel [EdgeLabel] deriving (Eq, Show)

instance Semigroup MultiEdgeLabel where
  (<>) (MultiEdgeLabel l1) (MultiEdgeLabel l2) = unified
    where
      -- join all the label entries, maintaing the most refined ones
      justL1 = nub [EdgeLabel (sp, tp, traits1) | EdgeLabel (sp, tp, traits1) <- l1, EdgeLabel (s2, t2, traits2) <- l2, sp /= s2 && tp /= t2]
      justL2 = nub [EdgeLabel (s2, t2, traits2) | EdgeLabel (sp, tp, traits1) <- l1, EdgeLabel (s2, t2, traits2) <- l2, sp /= s2 && tp /= t2]
      uniList = nub [EdgeLabel (sp, tp, traits1 ++ traits2) | EdgeLabel (sp, tp, traits1) <- l1, EdgeLabel (s2, t2, traits2) <- l2, sp == s2 && tp == t2]
      unified = MultiEdgeLabel $ nub $ justL1 ++ uniList ++ justL2

instance Monoid MultiEdgeLabel where
  mempty = MultiEdgeLabel []
  mappend (MultiEdgeLabel l1) (MultiEdgeLabel l2) = MultiEdgeLabel $ mappend l1 l2
  mconcat ls = foldl (<>) mempty ls

type ForSyDeModel = DMultiGraph Vertex Edge

-- linkVertexes' :: ForSyDeModel -> Vertex -> Vertex -> Maybe String -> Maybe String -> [EdgeTrait] -> ForSyDeModel
-- linkVertexes' m v1 v2 p1 p2 ts = overlayed
--   where
--     eGraph = edge (MultiEdgeLabel [EdgeLabel (p1, p2, ts)]) v1 v2
--     overlayed = m `overlay` eGraph

-- linkVertexes :: ForSyDeModel -> Vertex -> Vertex -> String -> String -> [EdgeTrait] -> ForSyDeModel
-- linkVertexes m v1 v2 p1 p2 ts = overlayed
--   where
--     eGraph = edge (MultiEdgeLabel [EdgeLabel (Just p1, Just p2, ts)]) v1 v2
--     overlayed = m `overlay` eGraph

-- linkVertexesNoPorts :: ForSyDeModel -> Vertex -> Vertex -> [EdgeTrait] -> ForSyDeModel
-- linkVertexesNoPorts m v1 v2 = linkVertexes' m v1 v2 Nothing Nothing

-- linkVertexesDirect :: ForSyDeModel -> Vertex -> Vertex -> ForSyDeModel
-- linkVertexesDirect m v1 v2 = linkVertexes' m v1 v2 Nothing Nothing []

-- data ForSyDeModel = ForSyDeModel
--   { vertexes :: [Vertex],
--     edges :: [Edge]
--   }

-- | Return an empty 'ForSyDeModel' with no 'Vertex' or 'Edge'
-- empty :: ForSyDeModel
-- empty = ForSyDeModel [] []

-- vertexFromStrings ::
--   String ->
--   Vertex
-- vertexFromStrings idV = Vertex idV [] [] []

-- -- -- | Queries a 'Vertex' in the model by its Id
-- -- -- returns 'Nothing' if nones exists.
-- modelGetVertex ::
--   ForSyDeModel ->
--   String ->
--   Maybe Vertex
-- modelGetVertex (ForSyDeModel vs _) vId = find (\v -> vId == vertexId v) vs

-- -- -- | Add a new vertex to the model if it does not exist there yet
-- -- -- Otherwise returns the same model silently.
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
-- modelGetEdges (ForSyDeModel _ es) s t = filter (\e -> (edgeSource e == s) && (edgeTarget e == t)) es

-- modelAddEdge ::
--   ForSyDeModel ->
--   Edge ->
--   ForSyDeModel
-- modelAddEdge m@(ForSyDeModel vs es) e@(Edge s t _ _ _)
--   | e `elem` es = ForSyDeModel vs' es
--   | otherwise = ForSyDeModel vs' (e : es)
--   where
--     vs' = vertexes $ modelAddVertex (modelAddVertex m s) t
