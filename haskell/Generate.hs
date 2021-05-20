{-# LANGUAGE OverloadedStrings #-}

import Data.Aeson (FromJSON (parseJSON), Object, Value, decode)
import Data.Aeson.Types
import qualified Data.ByteString.Lazy as B
import qualified Data.HashMap.Strict as HM
import Data.Maybe (fromJust)
import qualified Data.Text as T
import System.IO

parseVertexTraits :: Object -> Parser String
parseVertexTraits o = return $ top ++ firstTrait ++ restTraits ++ footer ++ "\n\n"
  where
    traitsStrings = map fst . HM.toList $ o
    top = "data VertexTrait\n" :: String
    firstTrait = "  = " ++ (T.unpack . head $ traitsStrings) ++ "\n"
    restTraits = concatMap (\v -> "  | " ++ T.unpack v ++ "\n") . drop 1 $ traitsStrings :: String
    footer = "  deriving (Show, Read, Eq)"

parseEdgeTraits :: Object -> Parser String
parseEdgeTraits o = return $ top ++ firstTrait ++ restTraits ++ footer ++ "\n\n"
  where
    traitsStrings = map fst . HM.toList $ o
    top = "data EdgeTrait\n" :: String
    firstTrait = "  = " ++ (T.unpack . head $ traitsStrings) ++ "\n"
    restTraits = concatMap (\v -> "  | " ++ T.unpack v ++ "\n") . drop 1 $ traitsStrings :: String
    footer = "  deriving (Show, Read, Eq)"

main :: IO ()
main = do
  metaModelStr <- B.readFile "meta.json"
  let metaModel = fromJust $ decode metaModelStr :: Object
  let vertexTraits = fromJust $ parseMaybe (.: "vertexTraits") metaModel
  let vertexTraitsStr = fromJust $ parseMaybe parseVertexTraits vertexTraits
  let edgeTraits = fromJust $ parseMaybe (.: "edgeTraits") metaModel
  let edgeTraitsStr = fromJust $ parseMaybe parseEdgeTraits edgeTraits
  writeFile "src/ForSyDe/IO/Haskell/Types.hs" $ header ++ vertexTraitsStr ++ edgeTraitsStr
  where
    header = "module ForSyDe.IO.Haskell.Types (VertexTrait (..), EdgeTrait (..)) where\n\n"