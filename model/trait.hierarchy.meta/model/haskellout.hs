{-# LANGUAGE MultiParamTypeClasses, FunctionalDependencies #-}
{-# OPTIONS_GHC -fno-warn-duplicate-exports #-}
module HierarchyXML'xsd
  ( module HierarchyXML'xsd
  ) where
 
import Text.XML.HaXml.Schema.Schema (SchemaType(..),SimpleType(..),Extension(..),Restricts(..))
import Text.XML.HaXml.Schema.Schema as Schema
import Text.XML.HaXml.OneOfN
import qualified Text.XML.HaXml.Schema.PrimitiveTypes as Xsd
 
-- Some hs-boot imports are required, for fwd-declaring types.
 
data PortDirectionEnum
    = PortDirectionEnum_FORWARD
    | PortDirectionEnum_BACKWARD
    | PortDirectionEnum_BIDIRECTIONAL
    deriving (Eq,Show,Enum)
instance SchemaType PortDirectionEnum where
    parseSchemaType s = do
        e <- element [s]
        commit $ interior e $ parseSimpleType
    schemaTypeToXML s x = 
        toXMLElement s [] [toXMLText (simpleTypeText x)]
instance SimpleType PortDirectionEnum where
    acceptingParser =  do literal "FORWARD"; return PortDirectionEnum_FORWARD
                      `onFail` do literal "BACKWARD"; return PortDirectionEnum_BACKWARD
                      `onFail` do literal "BIDIRECTIONAL"; return PortDirectionEnum_BIDIRECTIONAL
                      
    simpleTypeText PortDirectionEnum_FORWARD = "FORWARD"
    simpleTypeText PortDirectionEnum_BACKWARD = "BACKWARD"
    simpleTypeText PortDirectionEnum_BIDIRECTIONAL = "BIDIRECTIONAL"
 
data PortSpec = PortSpec
        { portSpec_name :: Maybe Xsd.XsdString
        , portSpec_direction :: Maybe PortDirectionEnum
        , portSpec_connectedVertexTrait :: Maybe Xsd.XsdString
        , portSpec_connectedEdgeTrait :: Maybe Xsd.XsdString
        }
        deriving (Eq,Show)
instance SchemaType PortSpec where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "name" e pos
        a1 <- optional $ getAttribute "direction" e pos
        a2 <- optional $ getAttribute "connectedVertexTrait" e pos
        a3 <- optional $ getAttribute "connectedEdgeTrait" e pos
        commit $ interior e $ return (PortSpec a0 a1 a2 a3)
    schemaTypeToXML s x@PortSpec{} =
        toXMLElement s [ maybe [] (toXMLAttribute "name") $ portSpec_name x
                       , maybe [] (toXMLAttribute "direction") $ portSpec_direction x
                       , maybe [] (toXMLAttribute "connectedVertexTrait") $ portSpec_connectedVertexTrait x
                       , maybe [] (toXMLAttribute "connectedEdgeTrait") $ portSpec_connectedEdgeTrait x
                       ]
            []
 
elementPortSpec :: XMLParser PortSpec
elementPortSpec = parseSchemaType "PortSpec"
elementToXMLPortSpec :: PortSpec -> [Content ()]
elementToXMLPortSpec = schemaTypeToXML "PortSpec"
 
data VertexPropertyType
        = VertexPropertyType_IntMapVertexPropertyType IntMapVertexPropertyType
        | VertexPropertyType_ArrayVertexPropertyType ArrayVertexPropertyType
        | VertexPropertyType_LongVertexPropertyType LongVertexPropertyType
        | VertexPropertyType_DoubleVertexPropertyType DoubleVertexPropertyType
        | VertexPropertyType_FloatVertexPropertyType FloatVertexPropertyType
        | VertexPropertyType_BooleanVertexPropertyType BooleanVertexPropertyType
        | VertexPropertyType_IntVertexPropertyType IntVertexPropertyType
        | VertexPropertyType_StringVertexPropertyType StringVertexPropertyType
        
        deriving (Eq,Show)
instance SchemaType VertexPropertyType where
    parseSchemaType s = do
        (fmap VertexPropertyType_IntMapVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_ArrayVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_LongVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_DoubleVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_FloatVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_BooleanVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_IntVertexPropertyType $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyType_StringVertexPropertyType $ parseSchemaType s)
        `onFail` fail "Parse failed when expecting an extension type of VertexPropertyType,\n\
\  namely one of:\n\
\IntMapVertexPropertyType,ArrayVertexPropertyType,LongVertexPropertyType,DoubleVertexPropertyType,FloatVertexPropertyType,BooleanVertexPropertyType,IntVertexPropertyType,StringVertexPropertyType"
    schemaTypeToXML _s (VertexPropertyType_IntMapVertexPropertyType x) = schemaTypeToXML "intMapVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_ArrayVertexPropertyType x) = schemaTypeToXML "arrayVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_LongVertexPropertyType x) = schemaTypeToXML "longVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_DoubleVertexPropertyType x) = schemaTypeToXML "doubleVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_FloatVertexPropertyType x) = schemaTypeToXML "floatVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_BooleanVertexPropertyType x) = schemaTypeToXML "booleanVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_IntVertexPropertyType x) = schemaTypeToXML "intVertexPropertyType" x
    schemaTypeToXML _s (VertexPropertyType_StringVertexPropertyType x) = schemaTypeToXML "stringVertexPropertyType" x
 
data StringVertexPropertyType = StringVertexPropertyType
        deriving (Eq,Show)
instance SchemaType StringVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return StringVertexPropertyType
    schemaTypeToXML s x@StringVertexPropertyType{} =
        toXMLElement s []
            []
instance Extension StringVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_StringVertexPropertyType v
 
elementStringVertexPropertyType :: XMLParser StringVertexPropertyType
elementStringVertexPropertyType = parseSchemaType "StringVertexPropertyType"
elementToXMLStringVertexPropertyType :: StringVertexPropertyType -> [Content ()]
elementToXMLStringVertexPropertyType = schemaTypeToXML "StringVertexPropertyType"
 
data IntVertexPropertyType = IntVertexPropertyType
        deriving (Eq,Show)
instance SchemaType IntVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return IntVertexPropertyType
    schemaTypeToXML s x@IntVertexPropertyType{} =
        toXMLElement s []
            []
instance Extension IntVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_IntVertexPropertyType v
 
elementIntVertexPropertyType :: XMLParser IntVertexPropertyType
elementIntVertexPropertyType = parseSchemaType "IntVertexPropertyType"
elementToXMLIntVertexPropertyType :: IntVertexPropertyType -> [Content ()]
elementToXMLIntVertexPropertyType = schemaTypeToXML "IntVertexPropertyType"
 
data BooleanVertexPropertyType = BooleanVertexPropertyType
        deriving (Eq,Show)
instance SchemaType BooleanVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return BooleanVertexPropertyType
    schemaTypeToXML s x@BooleanVertexPropertyType{} =
        toXMLElement s []
            []
instance Extension BooleanVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_BooleanVertexPropertyType v
 
elementBooleanVertexPropertyType :: XMLParser BooleanVertexPropertyType
elementBooleanVertexPropertyType = parseSchemaType "BooleanVertexPropertyType"
elementToXMLBooleanVertexPropertyType :: BooleanVertexPropertyType -> [Content ()]
elementToXMLBooleanVertexPropertyType = schemaTypeToXML "BooleanVertexPropertyType"
 
data FloatVertexPropertyType = FloatVertexPropertyType
        deriving (Eq,Show)
instance SchemaType FloatVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return FloatVertexPropertyType
    schemaTypeToXML s x@FloatVertexPropertyType{} =
        toXMLElement s []
            []
instance Extension FloatVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_FloatVertexPropertyType v
 
elementFloatVertexPropertyType :: XMLParser FloatVertexPropertyType
elementFloatVertexPropertyType = parseSchemaType "FloatVertexPropertyType"
elementToXMLFloatVertexPropertyType :: FloatVertexPropertyType -> [Content ()]
elementToXMLFloatVertexPropertyType = schemaTypeToXML "FloatVertexPropertyType"
 
data DoubleVertexPropertyType = DoubleVertexPropertyType
        deriving (Eq,Show)
instance SchemaType DoubleVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return DoubleVertexPropertyType
    schemaTypeToXML s x@DoubleVertexPropertyType{} =
        toXMLElement s []
            []
instance Extension DoubleVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_DoubleVertexPropertyType v
 
elementDoubleVertexPropertyType :: XMLParser DoubleVertexPropertyType
elementDoubleVertexPropertyType = parseSchemaType "DoubleVertexPropertyType"
elementToXMLDoubleVertexPropertyType :: DoubleVertexPropertyType -> [Content ()]
elementToXMLDoubleVertexPropertyType = schemaTypeToXML "DoubleVertexPropertyType"
 
data LongVertexPropertyType = LongVertexPropertyType
        deriving (Eq,Show)
instance SchemaType LongVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return LongVertexPropertyType
    schemaTypeToXML s x@LongVertexPropertyType{} =
        toXMLElement s []
            []
instance Extension LongVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_LongVertexPropertyType v
 
elementLongVertexPropertyType :: XMLParser LongVertexPropertyType
elementLongVertexPropertyType = parseSchemaType "LongVertexPropertyType"
elementToXMLLongVertexPropertyType :: LongVertexPropertyType -> [Content ()]
elementToXMLLongVertexPropertyType = schemaTypeToXML "LongVertexPropertyType"
 
data ArrayVertexPropertyType = ArrayVertexPropertyType
        { arrayVertexPropertyType_valueTypes :: Maybe VertexPropertyType
        }
        deriving (Eq,Show)
instance SchemaType ArrayVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return ArrayVertexPropertyType
            `apply` optional (parseSchemaType "valueTypes")
    schemaTypeToXML s x@ArrayVertexPropertyType{} =
        toXMLElement s []
            [ maybe [] (schemaTypeToXML "valueTypes") $ arrayVertexPropertyType_valueTypes x
            ]
instance Extension ArrayVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_ArrayVertexPropertyType v
 
elementArrayVertexPropertyType :: XMLParser ArrayVertexPropertyType
elementArrayVertexPropertyType = parseSchemaType "ArrayVertexPropertyType"
elementToXMLArrayVertexPropertyType :: ArrayVertexPropertyType -> [Content ()]
elementToXMLArrayVertexPropertyType = schemaTypeToXML "ArrayVertexPropertyType"
 
data IntMapVertexPropertyType = IntMapVertexPropertyType
        { intMapVertexPropertyType_valueType :: Maybe VertexPropertyType
        }
        deriving (Eq,Show)
instance SchemaType IntMapVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return IntMapVertexPropertyType
            `apply` optional (parseSchemaType "valueType")
    schemaTypeToXML s x@IntMapVertexPropertyType{} =
        toXMLElement s []
            [ maybe [] (schemaTypeToXML "valueType") $ intMapVertexPropertyType_valueType x
            ]
instance Extension IntMapVertexPropertyType VertexPropertyType where
    supertype v = VertexPropertyType_IntMapVertexPropertyType v
 
elementIntMapVertexPropertyType :: XMLParser IntMapVertexPropertyType
elementIntMapVertexPropertyType = parseSchemaType "IntMapVertexPropertyType"
elementToXMLIntMapVertexPropertyType :: IntMapVertexPropertyType -> [Content ()]
elementToXMLIntMapVertexPropertyType = schemaTypeToXML "IntMapVertexPropertyType"
 
data StringMapVertexPropertyType = StringMapVertexPropertyType
        { stringMapVertexPropertyType_valueType :: Maybe IntVertexPropertyType
        }
        deriving (Eq,Show)
instance SchemaType StringMapVertexPropertyType where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return StringMapVertexPropertyType
            `apply` optional (parseSchemaType "valueType")
    schemaTypeToXML s x@StringMapVertexPropertyType{} =
        toXMLElement s []
            [ maybe [] (schemaTypeToXML "valueType") $ stringMapVertexPropertyType_valueType x
            ]
instance Extension StringMapVertexPropertyType IntVertexPropertyType where
    supertype (StringMapVertexPropertyType e0) =
               IntVertexPropertyType
instance Extension StringMapVertexPropertyType VertexPropertyType where
    supertype = (supertype :: IntVertexPropertyType -> VertexPropertyType)
              . (supertype :: StringMapVertexPropertyType -> IntVertexPropertyType)
              
 
elementStringMapVertexPropertyType :: XMLParser StringMapVertexPropertyType
elementStringMapVertexPropertyType = parseSchemaType "StringMapVertexPropertyType"
elementToXMLStringMapVertexPropertyType :: StringMapVertexPropertyType -> [Content ()]
elementToXMLStringMapVertexPropertyType = schemaTypeToXML "StringMapVertexPropertyType"
 
data VertexPropertyDefault
        = VertexPropertyDefault_IntMapVertexPropertyDefault IntMapVertexPropertyDefault
        | VertexPropertyDefault_ArrayVertexPropertyDefault ArrayVertexPropertyDefault
        | VertexPropertyDefault_LongVertexPropertyDefault LongVertexPropertyDefault
        | VertexPropertyDefault_DoubleVertexPropertyDefault DoubleVertexPropertyDefault
        | VertexPropertyDefault_FloatVertexPropertyDefault FloatVertexPropertyDefault
        | VertexPropertyDefault_BooleanVertexPropertyDefault BooleanVertexPropertyDefault
        | VertexPropertyDefault_IntVertexPropertyDefault IntVertexPropertyDefault
        | VertexPropertyDefault_StringVertexPropertyDefault StringVertexPropertyDefault
        
        deriving (Eq,Show)
instance SchemaType VertexPropertyDefault where
    parseSchemaType s = do
        (fmap VertexPropertyDefault_IntMapVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_ArrayVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_LongVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_DoubleVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_FloatVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_BooleanVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_IntVertexPropertyDefault $ parseSchemaType s)
        `onFail`
        (fmap VertexPropertyDefault_StringVertexPropertyDefault $ parseSchemaType s)
        `onFail` fail "Parse failed when expecting an extension type of VertexPropertyDefault,\n\
\  namely one of:\n\
\IntMapVertexPropertyDefault,ArrayVertexPropertyDefault,LongVertexPropertyDefault,DoubleVertexPropertyDefault,FloatVertexPropertyDefault,BooleanVertexPropertyDefault,IntVertexPropertyDefault,StringVertexPropertyDefault"
    schemaTypeToXML _s (VertexPropertyDefault_IntMapVertexPropertyDefault x) = schemaTypeToXML "intMapVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_ArrayVertexPropertyDefault x) = schemaTypeToXML "arrayVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_LongVertexPropertyDefault x) = schemaTypeToXML "longVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_DoubleVertexPropertyDefault x) = schemaTypeToXML "doubleVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_FloatVertexPropertyDefault x) = schemaTypeToXML "floatVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_BooleanVertexPropertyDefault x) = schemaTypeToXML "booleanVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_IntVertexPropertyDefault x) = schemaTypeToXML "intVertexPropertyDefault" x
    schemaTypeToXML _s (VertexPropertyDefault_StringVertexPropertyDefault x) = schemaTypeToXML "stringVertexPropertyDefault" x
 
data StringVertexPropertyDefault = StringVertexPropertyDefault
        { stringVertexPropertyDefault_string :: Maybe Xsd.XsdString
        }
        deriving (Eq,Show)
instance SchemaType StringVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "string" e pos
        commit $ interior e $ return (StringVertexPropertyDefault a0)
    schemaTypeToXML s x@StringVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "string") $ stringVertexPropertyDefault_string x
                       ]
            []
instance Extension StringVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_StringVertexPropertyDefault v
 
elementStringVertexPropertyDefault :: XMLParser StringVertexPropertyDefault
elementStringVertexPropertyDefault = parseSchemaType "StringVertexPropertyDefault"
elementToXMLStringVertexPropertyDefault :: StringVertexPropertyDefault -> [Content ()]
elementToXMLStringVertexPropertyDefault = schemaTypeToXML "StringVertexPropertyDefault"
 
data IntVertexPropertyDefault = IntVertexPropertyDefault
        { intVertexPropertyDefault_intValue :: Maybe Xsd.Int
        }
        deriving (Eq,Show)
instance SchemaType IntVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "intValue" e pos
        commit $ interior e $ return (IntVertexPropertyDefault a0)
    schemaTypeToXML s x@IntVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "intValue") $ intVertexPropertyDefault_intValue x
                       ]
            []
instance Extension IntVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_IntVertexPropertyDefault v
 
elementIntVertexPropertyDefault :: XMLParser IntVertexPropertyDefault
elementIntVertexPropertyDefault = parseSchemaType "IntVertexPropertyDefault"
elementToXMLIntVertexPropertyDefault :: IntVertexPropertyDefault -> [Content ()]
elementToXMLIntVertexPropertyDefault = schemaTypeToXML "IntVertexPropertyDefault"
 
data BooleanVertexPropertyDefault = BooleanVertexPropertyDefault
        { booleanVertexPropertyDefault_booleanValue :: Maybe Xsd.Boolean
        }
        deriving (Eq,Show)
instance SchemaType BooleanVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "booleanValue" e pos
        commit $ interior e $ return (BooleanVertexPropertyDefault a0)
    schemaTypeToXML s x@BooleanVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "booleanValue") $ booleanVertexPropertyDefault_booleanValue x
                       ]
            []
instance Extension BooleanVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_BooleanVertexPropertyDefault v
 
elementBooleanVertexPropertyDefault :: XMLParser BooleanVertexPropertyDefault
elementBooleanVertexPropertyDefault = parseSchemaType "BooleanVertexPropertyDefault"
elementToXMLBooleanVertexPropertyDefault :: BooleanVertexPropertyDefault -> [Content ()]
elementToXMLBooleanVertexPropertyDefault = schemaTypeToXML "BooleanVertexPropertyDefault"
 
data FloatVertexPropertyDefault = FloatVertexPropertyDefault
        { floatVertexPropertyDefault_floatValue :: Maybe Xsd.Float
        }
        deriving (Eq,Show)
instance SchemaType FloatVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "floatValue" e pos
        commit $ interior e $ return (FloatVertexPropertyDefault a0)
    schemaTypeToXML s x@FloatVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "floatValue") $ floatVertexPropertyDefault_floatValue x
                       ]
            []
instance Extension FloatVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_FloatVertexPropertyDefault v
 
elementFloatVertexPropertyDefault :: XMLParser FloatVertexPropertyDefault
elementFloatVertexPropertyDefault = parseSchemaType "FloatVertexPropertyDefault"
elementToXMLFloatVertexPropertyDefault :: FloatVertexPropertyDefault -> [Content ()]
elementToXMLFloatVertexPropertyDefault = schemaTypeToXML "FloatVertexPropertyDefault"
 
data DoubleVertexPropertyDefault = DoubleVertexPropertyDefault
        { doubleVertexPropertyDefault_doubleValue :: Maybe Xsd.Double
        }
        deriving (Eq,Show)
instance SchemaType DoubleVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "doubleValue" e pos
        commit $ interior e $ return (DoubleVertexPropertyDefault a0)
    schemaTypeToXML s x@DoubleVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "doubleValue") $ doubleVertexPropertyDefault_doubleValue x
                       ]
            []
instance Extension DoubleVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_DoubleVertexPropertyDefault v
 
elementDoubleVertexPropertyDefault :: XMLParser DoubleVertexPropertyDefault
elementDoubleVertexPropertyDefault = parseSchemaType "DoubleVertexPropertyDefault"
elementToXMLDoubleVertexPropertyDefault :: DoubleVertexPropertyDefault -> [Content ()]
elementToXMLDoubleVertexPropertyDefault = schemaTypeToXML "DoubleVertexPropertyDefault"
 
data LongVertexPropertyDefault = LongVertexPropertyDefault
        { longVertexPropertyDefault_longValue :: Maybe Xsd.Long
        }
        deriving (Eq,Show)
instance SchemaType LongVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "longValue" e pos
        commit $ interior e $ return (LongVertexPropertyDefault a0)
    schemaTypeToXML s x@LongVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "longValue") $ longVertexPropertyDefault_longValue x
                       ]
            []
instance Extension LongVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_LongVertexPropertyDefault v
 
elementLongVertexPropertyDefault :: XMLParser LongVertexPropertyDefault
elementLongVertexPropertyDefault = parseSchemaType "LongVertexPropertyDefault"
elementToXMLLongVertexPropertyDefault :: LongVertexPropertyDefault -> [Content ()]
elementToXMLLongVertexPropertyDefault = schemaTypeToXML "LongVertexPropertyDefault"
 
data ArrayVertexPropertyDefault = ArrayVertexPropertyDefault
        { arrayVertexPropertyDefault_values :: [VertexPropertyDefault]
        }
        deriving (Eq,Show)
instance SchemaType ArrayVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return ArrayVertexPropertyDefault
            `apply` many (parseSchemaType "values")
    schemaTypeToXML s x@ArrayVertexPropertyDefault{} =
        toXMLElement s []
            [ concatMap (schemaTypeToXML "values") $ arrayVertexPropertyDefault_values x
            ]
instance Extension ArrayVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_ArrayVertexPropertyDefault v
 
elementArrayVertexPropertyDefault :: XMLParser ArrayVertexPropertyDefault
elementArrayVertexPropertyDefault = parseSchemaType "ArrayVertexPropertyDefault"
elementToXMLArrayVertexPropertyDefault :: ArrayVertexPropertyDefault -> [Content ()]
elementToXMLArrayVertexPropertyDefault = schemaTypeToXML "ArrayVertexPropertyDefault"
 
data IntMapVertexPropertyDefault = IntMapVertexPropertyDefault
        { intMapVertexPropertyDefault_indexes :: [Xsd.Int]
        , intMapVertexPropertyDefault_values :: [VertexPropertyDefault]
        }
        deriving (Eq,Show)
instance SchemaType IntMapVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        commit $ interior e $ return IntMapVertexPropertyDefault
            `apply` many (parseSchemaType "indexes")
            `apply` many (parseSchemaType "values")
    schemaTypeToXML s x@IntMapVertexPropertyDefault{} =
        toXMLElement s []
            [ concatMap (schemaTypeToXML "indexes") $ intMapVertexPropertyDefault_indexes x
            , concatMap (schemaTypeToXML "values") $ intMapVertexPropertyDefault_values x
            ]
instance Extension IntMapVertexPropertyDefault VertexPropertyDefault where
    supertype v = VertexPropertyDefault_IntMapVertexPropertyDefault v
 
elementIntMapVertexPropertyDefault :: XMLParser IntMapVertexPropertyDefault
elementIntMapVertexPropertyDefault = parseSchemaType "IntMapVertexPropertyDefault"
elementToXMLIntMapVertexPropertyDefault :: IntMapVertexPropertyDefault -> [Content ()]
elementToXMLIntMapVertexPropertyDefault = schemaTypeToXML "IntMapVertexPropertyDefault"
 
data StringMapVertexPropertyDefault = StringMapVertexPropertyDefault
        { stringMapVertexPropertyDefault_intValue :: Maybe Xsd.Int
        , stringMapVertexPropertyDefault_indexes :: [Xsd.XsdString]
        , stringMapVertexPropertyDefault_values :: [IntVertexPropertyDefault]
        }
        deriving (Eq,Show)
instance SchemaType StringMapVertexPropertyDefault where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "intValue" e pos
        commit $ interior e $ return (StringMapVertexPropertyDefault a0)
            `apply` many (parseSchemaType "indexes")
            `apply` many (parseSchemaType "values")
    schemaTypeToXML s x@StringMapVertexPropertyDefault{} =
        toXMLElement s [ maybe [] (toXMLAttribute "intValue") $ stringMapVertexPropertyDefault_intValue x
                       ]
            [ concatMap (schemaTypeToXML "indexes") $ stringMapVertexPropertyDefault_indexes x
            , concatMap (schemaTypeToXML "values") $ stringMapVertexPropertyDefault_values x
            ]
instance Extension StringMapVertexPropertyDefault IntVertexPropertyDefault where
    supertype (StringMapVertexPropertyDefault a0 e0 e1) =
               IntVertexPropertyDefault a0
instance Extension StringMapVertexPropertyDefault VertexPropertyDefault where
    supertype = (supertype :: IntVertexPropertyDefault -> VertexPropertyDefault)
              . (supertype :: StringMapVertexPropertyDefault -> IntVertexPropertyDefault)
              
 
elementStringMapVertexPropertyDefault :: XMLParser StringMapVertexPropertyDefault
elementStringMapVertexPropertyDefault = parseSchemaType "StringMapVertexPropertyDefault"
elementToXMLStringMapVertexPropertyDefault :: StringMapVertexPropertyDefault -> [Content ()]
elementToXMLStringMapVertexPropertyDefault = schemaTypeToXML "StringMapVertexPropertyDefault"
 
data VertexPropertySpec = VertexPropertySpec
        { vertexPropertySpec_name :: Maybe Xsd.XsdString
        , vertexPropertySpec_propertyType :: Maybe VertexPropertyType
        , vertexPropertySpec_propertyDefault :: Maybe VertexPropertyDefault
        }
        deriving (Eq,Show)
instance SchemaType VertexPropertySpec where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "name" e pos
        commit $ interior e $ return (VertexPropertySpec a0)
            `apply` optional (parseSchemaType "propertyType")
            `apply` optional (parseSchemaType "propertyDefault")
    schemaTypeToXML s x@VertexPropertySpec{} =
        toXMLElement s [ maybe [] (toXMLAttribute "name") $ vertexPropertySpec_name x
                       ]
            [ maybe [] (schemaTypeToXML "propertyType") $ vertexPropertySpec_propertyType x
            , maybe [] (schemaTypeToXML "propertyDefault") $ vertexPropertySpec_propertyDefault x
            ]
 
elementVertexPropertySpec :: XMLParser VertexPropertySpec
elementVertexPropertySpec = parseSchemaType "VertexPropertySpec"
elementToXMLVertexPropertySpec :: VertexPropertySpec -> [Content ()]
elementToXMLVertexPropertySpec = schemaTypeToXML "VertexPropertySpec"
 
data VertexTraitSpec = VertexTraitSpec
        { vertexTraitSpec_name :: Maybe Xsd.XsdString
        , vertexTraitSpec_refinedTraits :: Maybe Xsd.XsdString
        , vertexTraitSpec_requiredPorts :: [PortSpec]
        , vertexTraitSpec_requiredProperties :: [VertexPropertySpec]
        }
        deriving (Eq,Show)
instance SchemaType VertexTraitSpec where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "name" e pos
        a1 <- optional $ getAttribute "refinedTraits" e pos
        commit $ interior e $ return (VertexTraitSpec a0 a1)
            `apply` many (parseSchemaType "requiredPorts")
            `apply` many (parseSchemaType "requiredProperties")
    schemaTypeToXML s x@VertexTraitSpec{} =
        toXMLElement s [ maybe [] (toXMLAttribute "name") $ vertexTraitSpec_name x
                       , maybe [] (toXMLAttribute "refinedTraits") $ vertexTraitSpec_refinedTraits x
                       ]
            [ concatMap (schemaTypeToXML "requiredPorts") $ vertexTraitSpec_requiredPorts x
            , concatMap (schemaTypeToXML "requiredProperties") $ vertexTraitSpec_requiredProperties x
            ]
 
elementVertexTraitSpec :: XMLParser VertexTraitSpec
elementVertexTraitSpec = parseSchemaType "VertexTraitSpec"
elementToXMLVertexTraitSpec :: VertexTraitSpec -> [Content ()]
elementToXMLVertexTraitSpec = schemaTypeToXML "VertexTraitSpec"
 
data EdgeTraitSpec = EdgeTraitSpec
        { edgeTraitSpec_name :: Maybe Xsd.XsdString
        , edgeTraitSpec_refinedTraits :: Maybe Xsd.XsdString
        }
        deriving (Eq,Show)
instance SchemaType EdgeTraitSpec where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "name" e pos
        a1 <- optional $ getAttribute "refinedTraits" e pos
        commit $ interior e $ return (EdgeTraitSpec a0 a1)
    schemaTypeToXML s x@EdgeTraitSpec{} =
        toXMLElement s [ maybe [] (toXMLAttribute "name") $ edgeTraitSpec_name x
                       , maybe [] (toXMLAttribute "refinedTraits") $ edgeTraitSpec_refinedTraits x
                       ]
            []
 
elementEdgeTraitSpec :: XMLParser EdgeTraitSpec
elementEdgeTraitSpec = parseSchemaType "EdgeTraitSpec"
elementToXMLEdgeTraitSpec :: EdgeTraitSpec -> [Content ()]
elementToXMLEdgeTraitSpec = schemaTypeToXML "EdgeTraitSpec"
 
data TraitHierarchy = TraitHierarchy
        { traitHierarchy_namespace :: Maybe Xsd.XsdString
        , traitHierarchy_scopedHierarchy :: [TraitHierarchy]
        , traitHierarchy_vertexTraits :: [VertexTraitSpec]
        , traitHierarchy_edgeTraits :: [EdgeTraitSpec]
        }
        deriving (Eq,Show)
instance SchemaType TraitHierarchy where
    parseSchemaType s = do
        (pos,e) <- posnElement [s]
        a0 <- optional $ getAttribute "namespace" e pos
        commit $ interior e $ return (TraitHierarchy a0)
            `apply` many (parseSchemaType "scopedHierarchy")
            `apply` many (parseSchemaType "vertexTraits")
            `apply` many (parseSchemaType "edgeTraits")
    schemaTypeToXML s x@TraitHierarchy{} =
        toXMLElement s [ maybe [] (toXMLAttribute "namespace") $ traitHierarchy_namespace x
                       ]
            [ concatMap (schemaTypeToXML "scopedHierarchy") $ traitHierarchy_scopedHierarchy x
            , concatMap (schemaTypeToXML "vertexTraits") $ traitHierarchy_vertexTraits x
            , concatMap (schemaTypeToXML "edgeTraits") $ traitHierarchy_edgeTraits x
            ]
 
elementTraitHierarchy :: XMLParser TraitHierarchy
elementTraitHierarchy = parseSchemaType "TraitHierarchy"
elementToXMLTraitHierarchy :: TraitHierarchy -> [Content ()]
elementToXMLTraitHierarchy = schemaTypeToXML "TraitHierarchy"
