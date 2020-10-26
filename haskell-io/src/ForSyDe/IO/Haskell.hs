{-# LANGUAGE OverloadedStrings #-}
module ForSyDe.IO.Haskell (
  Port,
  Property,
  Vertex,
  Edge,
  ForSyDeModel
) where

-- Base libraries
import Data.List
import Data.Dynamic

-- External libraries
import Text.Regex.Base
import Text.Regex.TDFA
import Data.Hashable
import qualified Data.HashMap as HashMap

-- Internal libraries
import ForSyDe.IO.Haskell.Types (Type, typeFromName)

data Port idType = Port { portIdentifier :: idType
                        , portType :: Type
                        } deriving (Show, Eq)

instance (Hashable idType) => Hashable (Port idType) where
  hashWithSalt salt (Port id _) = hashWithSalt salt id

data Vertex idType = Vertex { vertexIdentifier :: idType
                            , vertexPorts :: HashMap.HashMap idType (Port idType)
                            , vertexProperties :: HashMap.HashMap idType Dynamic
                            , vertexType :: Type
                            } deriving (Show, Eq)

instance (Hashable idType) => Hashable (Vertex idType) where
  hashWithSalt salt (Vertex id _ _ _) = hashWithSalt salt id

data Edge vIdType pIdType = Edge { sourceVertex :: vIdType
                                 , targetVertex :: vIdType
                                 , sourceVertexPort :: pIdType
                                 , targetVertexPort :: pIdType
                                 , edgeType :: Type
                                 } deriving (Show, Eq)

instance (Hashable vIdType, Hashable pIdType) => Hashable (Edge vIdType pIdType) where
  hashWithSalt salt (Edge sId tId _ _ _) = 
    hashWithSalt salt sId + hashWithSalt salt tId

data ForSyDeModel idType = ForSyDeModel { vertexes :: HashMap.HashMap idTtype (Vertex idType)
                                        , edges :: HashMap.HashMap idType (Edge idType idType)
                                        } deriving (Show, Eq)

emptyForSyDeModel :: ForSyDeModel idType
emptyForSyDeModel = ForSyDeModel HashMap.empty HashMap.empty

forSyDeModelAddNode :: (Eq idType, Hashable idType) => 
  Vertex idType -> 
  ForSyDeModel idType -> 
  ForSyDeModel idType
forSyDeModelAddNode vertex@(Vertex id _ _ _) (ForSyDeModel vSet eSet) = let
  newVSet = HashMap.insert id vertex vSet
  in
    ForSyDeModel newVSet eSet
  
forSyDeModelAddEdge :: (Eq idType, Hashable idType) =>
  Edge idType idType ->
  ForSyDeModel idType ->
  ForSyDeModel idType
forSyDeModelAddEdge edge@(Edge sid tid _ _ _) (ForSyDeModel vSet eSet) = let
  newESet = HashMap.insert (sid ++ "-" ++ tid) edge eSet
  in
    ForSyDeModel vSet newESet

forSyDeModelFromString :: String -> Either String (ForSyDeModel String)
forSyDeModelFromString wholeString = forSyDeModelFromTokens tokens $ Right emptyForSyDeModel
  where
    tokens = lines wholeString

forSyDeModelFromTokens :: [String] -> Either String (ForSyDeModel String) -> Either String (ForSyDeModel String)
forSyDeModelFromTokens (token:tokens) (Right currentModel)
  | isInfixOf "%" token = forSyDeModelFromTokens tokens $ Right currentModel
  | isInfixOf "vertex" token = let
      [vId, vTypeName] = filteredMatches
      vType = typeFromName vTypeName
      newVertex = Vertex vId HashMap.empty HashMap.empty vType :: Vertex String
      newModel = forSyDeModelAddNode newVertex currentModel
    in
      forSyDeModelFromTokens tokens $ Right newModel
  | isInfixOf "edge" token = forSyDeModelFromTokens tokens $ Right currentModel
  | isInfixOf "port" token = forSyDeModelFromTokens tokens $ Right currentModel
  | isInfixOf "prop" token = forSyDeModelFromTokens tokens $ Right currentModel
  | otherwise = Left ""
  where
    regexpat = "'([a-zA-Z0-9_]+)'[,)]" :: String
    matches = getAllTextMatches (token =~ regexpat) :: [String]
    -- drop the first ' and the last ', or ')
    filteredMatches = map (tail . (\s -> take ((length s) -2) s)) matches :: [String]
    
forSyDeModelFromTokens _ (Left err) = Left err
forSyDeModelFromTokens [] (Right model) = Right model

