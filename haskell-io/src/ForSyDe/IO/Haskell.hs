module ForSyDe.IO.Haskell (
  Port,
  Property,
  Vertex,
  Edge
) where

import Data.Hashable
import qualified Data.HashSet as HashSet

import ForSyDe.IO.Haskell.Types (Type)

data Port idType = Port { portIdentifier :: idType
                        , portType :: Type
                        } deriving (Show, Eq)

instance (Hashable idType) => Hashable (Port idType) where
  hashWithSalt salt (Port id _) = hashWithSalt salt id

data Property = Property {} deriving (Show, Eq)

data Vertex idType = Vertex { vertexIdentifier :: idType
                            , vertexPorts :: HashSet.HashSet (Port idType)
                            , vertexProperties :: HashSet.HashSet (Property)
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

data ForSyDeModel idType = ForSyDeModel { vertexes :: HashSet.HashSet (Vertex idType)
                                        , edges :: HashSet.HashSet (Edge idType idType)
                                        } deriving (Show, Eq)

forSyDeModelAddNode :: (Eq idType, Hashable idType) => 
  Vertex idType -> 
  ForSyDeModel idType -> 
  ForSyDeModel idType
forSyDeModelAddNode vertex (ForSyDeModel vSet eSet) = let
  newVSet = HashSet.insert vertex vSet
  in
    ForSyDeModel newVSet eSet
  
forSyDeModelAddEdge :: (Eq idType, Hashable idType) =>
  Edge idType idType ->
  ForSyDeModel idType ->
  ForSyDeModel idType
forSyDeModelAddEdge edge (ForSyDeModel vSet eSet) = let
  newESet = HashSet.insert edge eSet
  in
    ForSyDeModel vSet newESet
