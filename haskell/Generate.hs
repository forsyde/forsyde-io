import Data.Aeson
import Data.Aeson.Types
import qualified Data.HashMap.Strict as HM
import qualified Data.Text.Lazy as TL
import qualified Data.Text.Lazy.Encoding as T

parseVertexTraits :: Value -> Parser String
parseVertexTraits v = (++) <$> firstTrait <*> restTraits
  where
    traitsStrings = map (\(k, v) -> k) . HM.toList <$> (parseJSON v :: Parser (HM.HashMap String Value))
    top = pure "data VertexTrait\n" :: Parser String
    firstTrait = (((++) "  = ") . head) <$> traitsStrings :: Parser String
    restTraits = (concatMap (\v -> "  | " ++ v)) . (drop 1) <$> traitsStrings :: Parser String

main :: IO ()
main = do
  metaModelStr <- readFile "meta.json"
  metaModel <- decode <$> (T.encodeUtf8 . TL.pack) metaModelStr
  --   putStr show parseJSON (metaModel .: "vertexTraits")
  writeFile "src/ForSyDe/IO/Haskell/Types.hs" contentString
  where
    header = "module ForSyDe.IO.Haskell.Types (VertexTrait(..), EdgeTrait(..)) where\n\n"
    vertexTraits = "data VertexTrait = VertexTrait\n\n"
    edgeTraits = "data EdgeTrait = EdgeTrait\n\n"
    contentString = header ++ vertexTraits ++ edgeTraits