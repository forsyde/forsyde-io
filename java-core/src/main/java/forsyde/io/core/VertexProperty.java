package forsyde.io.core;

import forsyde.io.core.properties.*;
import forsyde.io.core.properties.*;

import java.util.*;
import java.util.stream.Collectors;

@Deprecated
public interface VertexProperty {

    static VertexProperty create(float f) {
        return new FloatVertexProperty(f);
    }

    static VertexProperty create(double d) {
        return new DoubleVertexProperty(d);
    }

    static VertexProperty create(long l) {
        return new LongVertexProperty(l);
    }

    static VertexProperty create(int l) {
        return new IntVertexProperty(l);
    }

    static VertexProperty create(boolean b) {
        return new BooleanVertexProperty(b);
    }

    static VertexProperty create(Object obj) {
        if (obj instanceof VertexProperty) {
            return (VertexProperty) obj;
        } else {
            if (obj instanceof Boolean) {
                return new BooleanVertexProperty((Boolean) obj);
            } else if (obj instanceof Long) {
                return new LongVertexProperty((Long) obj);
            } else if (obj instanceof Integer) {
                return new IntVertexProperty((Integer) obj);
            } else if (obj instanceof Float) {
                return new FloatVertexProperty((Float) obj);
            } else if (obj instanceof Double) {
                return new DoubleVertexProperty((Double) obj);
            } else if (obj instanceof List) {
                return new ArrayVertexProperty(
                        ((List<Object>) obj).stream().map(VertexProperty::create).collect(Collectors.toList())
                );
            } else if (obj.getClass().isArray()) {
                                return new ArrayVertexProperty(
                    Arrays.stream((Object[]) obj).map(VertexProperty::create).collect(Collectors.toList())
                );
            } else if (obj instanceof Map) {
                Map<Object, Object> srcMap = (Map<Object, Object>) obj;
                // just probe one key element ot check if it is Int or not
                if (!srcMap.isEmpty() && srcMap.keySet().stream().findAny().get() instanceof Integer) {
                    return new IntMapVertexProperty(
                            ((Map<Integer, Object>) obj).entrySet().stream()
                                    .collect(Collectors.toMap(Map.Entry::getKey, v -> VertexProperty.create(v.getValue())))
                    );
                } else {
                    return new StringMapVertexProperty(
                            ((Map<String, Object>) obj).entrySet().stream()
                                    .collect(Collectors.toMap(e -> e.getKey().toString(), v -> VertexProperty.create(v.getValue())))
                    );
                }
            } else {
                return new StringVertexProperty(obj.toString());
            }
        }
    }

    static Object decreate(VertexProperty prop) {
        if (prop instanceof StringVertexProperty) return ((StringVertexProperty) prop).string;
        if (prop instanceof IntVertexProperty) return ((IntVertexProperty) prop).intValue;
        if (prop instanceof BooleanVertexProperty) return ((BooleanVertexProperty) prop).boolValue;
        if (prop instanceof FloatVertexProperty) return ((FloatVertexProperty) prop).floatValue;
        if (prop instanceof DoubleVertexProperty) return ((DoubleVertexProperty) prop).doubleValue;
        if (prop instanceof LongVertexProperty) return ((LongVertexProperty) prop).longValue;
        if (prop instanceof ArrayVertexProperty) return ((ArrayVertexProperty) prop).values.stream().map(VertexProperty::unwrap).collect(Collectors.toList());
        if (prop instanceof IntMapVertexProperty) return ((IntMapVertexProperty) prop).intMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().unwrap()));
        if (prop instanceof StringMapVertexProperty) return ((StringMapVertexProperty) prop).strMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().unwrap()));
        return prop.unwrap();
    }

    default Object unwrap() {
        return VertexProperty.decreate(this);
    }

    static boolean mergeInPlace(VertexProperty main, VertexProperty other) {
        if (main instanceof StringVertexProperty && other instanceof StringVertexProperty) {
            return ((StringVertexProperty) main).string.equals(((StringVertexProperty) other).string);
        }
        if (main instanceof IntVertexProperty && other instanceof IntVertexProperty) {
            return ((IntVertexProperty) main).intValue == ((IntVertexProperty) other).intValue;
        }
        if (main instanceof BooleanVertexProperty && other instanceof BooleanVertexProperty) {
            return ((BooleanVertexProperty) main).boolValue == ((BooleanVertexProperty) other).boolValue;
        }
        if (main instanceof FloatVertexProperty && other instanceof FloatVertexProperty) {
            return ((FloatVertexProperty) main).floatValue == ((FloatVertexProperty) other).floatValue;
        }
        if (main instanceof DoubleVertexProperty && other instanceof DoubleVertexProperty) {
            return ((DoubleVertexProperty) main).doubleValue == ((DoubleVertexProperty) other).doubleValue;
        }
        if (main instanceof LongVertexProperty && other instanceof LongVertexProperty) {
            return ((LongVertexProperty) main).longValue == ((LongVertexProperty) other).longValue;
        }
        if (main instanceof ArrayVertexProperty && other instanceof ArrayVertexProperty) {
            final Iterator<VertexProperty> otherIt = ((ArrayVertexProperty) other).values.iterator();
            final Iterator<VertexProperty> thisIt = ((ArrayVertexProperty) main).values.iterator();
            while (otherIt.hasNext() && thisIt.hasNext()) {
                // until the sizes overlap, the elements should be mergeable
                if (!thisIt.next().mergeInPlace(otherIt.next()))
                    return false;
            }
            // if other is bigger than this
            otherIt.forEachRemaining(((ArrayVertexProperty) main).values::add);
            return true;
        }
        if (main instanceof IntMapVertexProperty && other instanceof IntMapVertexProperty) {
            // merge keys that overlap and just insert keys that don't
            final Map<Integer, VertexProperty> m = ((IntMapVertexProperty) main).intMap;
            final Map<Integer, VertexProperty> mOther = ((IntMapVertexProperty) other).intMap;
            for (Integer key : mOther.keySet()) {
                if (m.containsKey(key)) {
                    if(!m.get(key).mergeInPlace(mOther.get(key)))
                        return false;
                } else {
                    m.put(key, mOther.get(key));
                }
            }
            return true;
        }
        if (main instanceof StringMapVertexProperty && other instanceof StringMapVertexProperty) {
            final Map<String, VertexProperty> m = ((StringMapVertexProperty) main).strMap;
            final Map<String, VertexProperty> mOther = ((StringMapVertexProperty) other).strMap;
            for (String key : mOther.keySet()) {
                if (m.containsKey(key)) {
                    if(!m.get(key).mergeInPlace(mOther.get(key)))
                        return false;
                } else {
                    m.put(key, mOther.get(key));
                }
            }
            return true;
        }
        return false;
    }

    default boolean mergeInPlace(VertexProperty other) {
        // try to merge two arrays. For the merge to be well defined, they
        // must have mergeable elements in the same order, save for additionals
        // tail elements in the other array
        return VertexProperty.mergeInPlace(this, other);
    }

//    @Override
//    default String toString() {
//        return VertexProperties.cases()
//                .StringVertexProperty(p -> p)
//                .IntVertexProperty(Object::toString)
//                .BooleanVertexProperty(Object::toString)
//                .FloatVertexProperty(Object::toString)
//                .DoubleVertexProperty(Object::toString)
//                .LongVertexProperty(Object::toString)
//                .ArrayVertexProperty(l -> "[" + l.stream().map(VertexProperty::toString).collect(Collectors.joining(", ")) + "]")
//                .IntMapVertexProperty(m -> "{" + m.entrySet().stream().map(e -> e.getKey().toString() + ": " + e.getValue().toString()).collect(Collectors.joining(", ")) + "}")
//                .StringMapVertexProperty(m -> "{" + m.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().toString()).collect(Collectors.joining(", ")) + "}")
//                .apply(this);
//    }
}
