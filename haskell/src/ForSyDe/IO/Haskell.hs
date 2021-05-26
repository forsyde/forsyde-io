{-# LANGUAGE OverloadedStrings #-}

module ForSyDe.IO.Haskell
  ( Vertex (..),
    -- Edge (..),
    MapItem (..),
    ForSyDeModel (..),
    -- emptyForSyDeModel,
    -- modelAddEdge,
    -- modelAddVertex,
    -- modelGetEdges,
    -- modelGetVertex,
    -- vertexFromStrings,
  )
where

-- Base libraries
import Data.Dynamic
-- External libraries
import Data.List
import qualified Data.Map
import Data.Maybe
import Algebra.Graph.Labelled
-- Internal libraries
import ForSyDe.IO.Haskell.Types
  ( EdgeTrait (..),
    VertexTrait (..),
  )

data MapItem
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem]
  | DictMapItem (Data.Map.Map String MapItem)
  deriving (Eq)

data Vertex = Vertex
  { vertexId :: String,
    vertexPorts :: [String],
    vertexProperties :: Data.Map.Map String MapItem,
    vertexTraits :: [VertexTrait]
  }

instance Eq Vertex where
  (==) v other = vertexId v == vertexId other

-- data EdgeLabel = EdgeLabel
--   {
--     -- edgeSource :: Vertex,
--     -- edgeTarget :: Vertex,
--     edgeSourcePort :: Maybe String,
--     edgeTargetPort :: Maybe String,
--     edgeTraits :: [EdgeTrait]
--   }
--   deriving (Eq)

newtype EdgeLabelEntry = EdgeLabelEntry (String, String, [EdgeTrait]) deriving (Eq)

get2 (EdgeLabelEntry (a, b, c)) = (a, b)

newtype EdgeLabel = EdgeLabel [EdgeLabelEntry] deriving (Eq)

instance Semigroup EdgeLabel where
    (<>) (EdgeLabel l1) (EdgeLabel l2) = unified
        where
            l1ports = map get2 l1
            l2minusl1 = nub $ filter ((flip notElem l1ports) . get2) l2
            intersect = nub $ filter ((flip elem l1ports) . get2) l2
            -- join all the label entries, maintaing the most refined ones
            unified = EdgeLabel $ (nub l1) ++ l2minusl1

instance Monoid EdgeLabel where
    mempty = EdgeLabel []
    mappend (EdgeLabel l1) (EdgeLabel l2) = EdgeLabel $ mappend l1 l2
    mconcat ls = foldl (<>) mempty ls

type ForSyDeModel = Graph EdgeLabel Vertex

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
