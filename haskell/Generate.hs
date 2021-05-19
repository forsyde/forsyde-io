import Data.Aeson
import Data.Aeson.Types
import Data.Map

parseVertexTraits :: Value -> Parser [String]
parseVertexTraits (Object o) = return $ keys o
parseVertexTraits _ = fail "expected vertex traits as object"

main :: IO ()
main = putStrLn "hi"