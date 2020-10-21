module Haskell (
  Port,
  Vertex,
  Edge
) where

import ForSyDe.IO.Haskell.Types (Type)

data Port idType = Port { identifier :: idType
                        , portType :: Type
                        } deriving (Show, Eq)

data Property = Property {} deriving (Show, Eq)

data Vertex idType = Vertex { identifier :: idType
                            , vertexPorts :: HashSet (Port idType)
                            , vertexProperties :: HashSet (Property)
                            , vertexType :: Type
                            } deriving (Show, Eq)

data Edge vIdType pIdType = Edge { sourceVertex :: vIdType,
                                 , targetVertex :: vIdType,
                                 , sourceVertexPort :: pIdType,
                                 , targetVertexPort :: pIdType,
                                 , edgeType :: Type
                                 } deriving (Show, Eq)

data ForSyDeModel idType = ForSyDeModel { vertexes :: HashSet (Vertex idType)
                                        , edges :: HashSet (Edge idType idType)
                                        } deriving (Show, Eq)
