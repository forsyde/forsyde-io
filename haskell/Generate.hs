{-# LANGUAGE OverloadedStrings #-}

import System.IO
import qualified Data.Text as T
import Data.Maybe (fromJust)
import Data.Aeson ( decode, FromJSON(parseJSON), Object, Value )
import Data.Aeson.Types
import qualified Data.ByteString.Lazy as B
import qualified Data.HashMap.Strict as HM

parseVertexTraits :: Value -> Parser String
parseVertexTraits (Object o) = return $ top ++ firstTrait ++ restTraits
  where
    traitsStrings = map fst . HM.toList $ o
    top = "data VertexTrait\n" :: String
    firstTrait = "  = " ++ (T.unpack . head $ traitsStrings) ++ "\n"
    restTraits = concatMap (\v -> "  | " ++ (T.unpack v) ++ "\n") . drop 1 $ traitsStrings :: String
parseVertexTraits _ = fail "Only objects!"

main :: IO ()
main = do
  metaModelStr <- B.readFile "meta.json"
  -- let metaModel = fromJust $ decode metaModelStr :: Value
  let vertexTraitsStr = fromJust $ parseMaybe parseVertexTraits $ "vertexTraits" .: metaModelStr
  writeFile "src/ForSyDe/IO/Haskell/Types.hs" $ header ++ vertexTraitsStr ++ edgeTraits
  where 
    header = "module ForSyDe.IO.Haskell.Types (VertexTrait(..), EdgeTrait(..)) where\n\n"
    edgeTraits = "data EdgeTrait = EdgeTrait\n\n" 
    