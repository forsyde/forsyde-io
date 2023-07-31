import Algebra.Graph.Labelled (Graph, empty, overlay, (-<), (>-))
import qualified Data.Map
import ForSyDe.IO.Haskell (EdgeLabel (..), ForSyDeModel, MultiEdgeLabel (..), Vertex (Vertex), linkVertexes)

main :: IO ()
main = do
  print testSimpleGraph

testSimpleGraph :: ForSyDeModel
testSimpleGraph = m
  where
    v1 = Vertex "v1" ["p1", "p2"] Data.Map.empty []
    v2 = Vertex "v2" ["p1", "p2"] Data.Map.empty []
    --n = v1 -< EdgeLabels [EdgeLabelEntry ("p1", "p1", [])] >- v2
    s = empty
    m = linkVertexes s v1 v2 "p1" "p1" []

-- e = edge