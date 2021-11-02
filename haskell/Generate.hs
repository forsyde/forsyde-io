{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}
import Data.Aeson
import Data.Aeson.Types
import qualified Data.ByteString.Lazy as B
import qualified Data.HashMap.Strict as HM
import Data.Maybe (fromJust)
import qualified Data.Text as T
import GHC.Generics

import System.IO
import Data.Aeson.Types (FromJSON)

data VertexTraitSpec = VertexTraitSpec {
  vertex_name :: String,
  vertex_refinedTraits :: [String]
} deriving (Generic, Eq, Show)

instance ToJSON VertexTraitSpec where
  toJSON = genericToJSON defaultOptions {
             fieldLabelModifier = drop 7 }

instance FromJSON VertexTraitSpec where
  parseJSON = withObject "VertexTrait" $ \o -> VertexTraitSpec
    <$> o .: "name"
    <*> o .:? "refinedTraits" .!= []

data EdgeTraitSpec = EdgeTraitSpec {
  edge_name :: String,
  edge_refinedTraits :: [String]
} deriving (Generic, Eq, Show)

instance ToJSON EdgeTraitSpec where
  toJSON = genericToJSON defaultOptions {
             fieldLabelModifier = drop 5 }

instance FromJSON EdgeTraitSpec where
  parseJSON = withObject "EdgeTrait" $ \o -> EdgeTraitSpec
    <$> o .: "name"
    <*> o .:? "refinedTraits" .!= []
    
data MetaModel = MetaModel {
  vertexTraits :: [VertexTraitSpec],
  edgeTraits :: [EdgeTraitSpec]
} deriving (Generic, Eq, Show)

instance ToJSON MetaModel
instance FromJSON MetaModel
   

genVertexTrait :: [VertexTraitSpec] -> String
genVertexTrait ts = top ++ firstTrait ++ restTraits ++ footer ++ "\n\n"
  where
    traitsNames = map vertex_name ts
    top = "data VertexTrait\n" :: String
    firstTrait = "  = " ++ head traitsNames ++ "\n"
    restTraits = concatMap (\v -> "  | " ++ v ++ "\n") . drop 1 $ traitsNames :: String
    footer = "  deriving (Show, Read, Eq)"

genEdgeTrait :: [EdgeTraitSpec] -> String
genEdgeTrait ts = top ++ firstTrait ++ restTraits ++ footer ++ "\n\n"
  where
    edgeNames = map edge_name ts
    top = "data EdgeTrait\n" :: String
    firstTrait = "  = " ++ head edgeNames ++ "\n"
    restTraits = concatMap (\v -> "  | " ++ v ++ "\n") . drop 1 $ edgeNames :: String
    footer = "  deriving (Show, Read, Eq)"

main :: IO ()
main = do
  metaModelStr <- B.readFile "meta.json"
  let metaModel = fromJust $ decode metaModelStr :: MetaModel
  let vertexTraitsStr = genVertexTrait . vertexTraits $ metaModel
  let edgeTraitsStr = genEdgeTrait . edgeTraits $ metaModel
  writeFile "src/ForSyDe/IO/Haskell/Types.hs" $ header ++ vertexTraitsStr ++ edgeTraitsStr
  where
    header = "module ForSyDe.IO.Haskell.Types (VertexTrait (..), EdgeTrait (..)) where\n\n"
