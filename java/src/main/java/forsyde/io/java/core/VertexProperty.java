package forsyde.io.java.core;

import java.util.*;
import java.util.stream.Collectors;

import forsyde.io.java.core.VertexPropertyType;

public class VertexProperty {

    public VertexPropertyType type;

    public boolean b;
    public int i;
    public long l;
    public float f;
    public double d;
    public List<VertexProperty> array = null;
    public Map<String, VertexProperty> stringMap = null;
    public Map<Integer, VertexProperty> intMap = null;
    public String s;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        switch (type) {
            case DOUBLE: sb.append(d); break;
            case INTEGER: sb.append(i); break;
            case LONG: sb.append(l); break;
            case BOOLEAN: sb.append(b); break;
            case FLOAT: sb.append(f); break;
            case ARRAY:
                sb.append("[");
                sb.append(array.stream().map(VertexProperty::toString).collect(Collectors.joining(", ")));
                sb.append("]");
                break;
            case INTMAP:
                sb.append("{");
                sb.append(
                        intMap.entrySet().stream().map(e -> e.getKey().toString() + ": " + e.getValue().toString())
                        .collect(Collectors.joining(", "))
                );
                sb.append("}");
                break;
            case STRINGMAP:
                sb.append("{");
                sb.append(
                        stringMap.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().toString())
                                .collect(Collectors.joining(", "))
                );
                sb.append("}");
                break;
            default:
                sb.append(s);
                break;
        }
        return sb.toString();
    }

    public static VertexProperty create(Object obj) {
        if (obj instanceof VertexProperty) {
            return (VertexProperty) obj;
        } else {
            VertexProperty p = new VertexProperty();
            if (obj instanceof Boolean) {
                p.type = VertexPropertyType.BOOLEAN;
                p.b = (Boolean) obj;
                return p;
            } else if (obj instanceof Integer) {
                p.type = VertexPropertyType.INTEGER;
                p.i = (Integer) obj;
                return p;
            } else if (obj instanceof Long) {
                p.type = VertexPropertyType.LONG;
                p.l = (Long) obj;
                return p;
            } else if (obj instanceof Float) {
                p.type = VertexPropertyType.FLOAT;
                p.f = (Float) obj;
                return p;
            } else if (obj instanceof Double) {
                p.type = VertexPropertyType.DOUBLE;
                p.d = (Double) obj;
                return p;
            } else if (obj instanceof List) {
                p.type = VertexPropertyType.ARRAY;
                List<Object> src = (List<Object>) obj;
                p.array = src.stream().map(VertexProperty::create).collect(Collectors.toList());
                return p;
            } else if (obj.getClass().isArray()) {
                p.type = VertexPropertyType.ARRAY;
                Object[] src = (Object[]) obj;
                p.array = new ArrayList<>(src.length);
                for (Object o : src) {
                    p.array.add(VertexProperty.create(o));
                }
                return p;
            } else if (obj instanceof Map) {
                Map<Object, Object> srcMap = (Map<Object, Object>) obj;
                // just probe one key element ot check if it is Int or not
                if (!srcMap.isEmpty() && srcMap.keySet().stream().findAny().get() instanceof Integer) {
                    p.type = VertexPropertyType.INTMAP;
                    Map<Integer, Object> src = (Map<Integer, Object>) obj;
                    p.intMap = src.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, v -> VertexProperty.create(v.getValue())));
                    return p;
                } else {
                    p.type = VertexPropertyType.STRINGMAP;
                    p.stringMap = srcMap.entrySet().stream()
                            .collect(Collectors.toMap(k -> k.getKey().toString(), v -> VertexProperty.create(v.getValue())));
                    return p;
                }
            } else {
                p.type = VertexPropertyType.STRING;
                p.s = obj.toString();
                return p;
            }
        }
    }

    public Object unwrap() {
        switch (type) {
            case DOUBLE: return d;
            case INTEGER: return i;
            case LONG: return l;
            case BOOLEAN: return b;
            case FLOAT: return f;
            case ARRAY:
                return array.stream().map(VertexProperty::unwrap).collect(Collectors.toList());
            case INTMAP:
                return intMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().unwrap()));
            case STRINGMAP:
                return stringMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().unwrap()));
            default:
                return s;
        }
    }

    public boolean mergeInPlace(VertexProperty other) {
        // try to merge two arrays. For the merge to be well defined, they
        // must have mergeable elements in the same order, save for additionals
        // tail elements in the other array
        if (type == VertexPropertyType.ARRAY && other.type == VertexPropertyType.ARRAY) {
            boolean mergeDefined = true;
            Iterator<VertexProperty> otherIt = other.array.iterator();
            Iterator<VertexProperty> thisIt = array.iterator();
            while (mergeDefined && otherIt.hasNext() && thisIt.hasNext()) {
                mergeDefined = mergeDefined && thisIt.next().mergeInPlace(otherIt.next());
            }
            // if other is bigger than this
            otherIt.forEachRemaining(e -> array.add(e));
            return mergeDefined;
        }
        // try to merge two int maps (dicts).
        else if (type == VertexPropertyType.INTMAP && other.type == VertexPropertyType.INTMAP) {
            boolean mergeDefined = true;
            for (Integer key : other.intMap.keySet()) {
                if (intMap.containsKey(key)) {
                    mergeDefined = mergeDefined && intMap.get(key).mergeInPlace(other.intMap.get(key));
                } else {
                    intMap.put(key, other.intMap.get(key));
                }
            }
            return mergeDefined;
        }
        // try to merge two string maps (dicts).
        else if (type == VertexPropertyType.STRINGMAP && other.type == VertexPropertyType.STRINGMAP) {
            boolean mergeDefined = true;
            for (String key : other.stringMap.keySet()) {
                if (stringMap.containsKey(key)) {
                    mergeDefined = mergeDefined && stringMap.get(key).mergeInPlace(other.stringMap.get(key));
                } else {
                    stringMap.put(key, other.stringMap.get(key));
                }
            }
            return mergeDefined;
        }
        // try to merge all the primitive types, i.e. just check if they are equal.
        else if (type == VertexPropertyType.BOOLEAN && other.type == VertexPropertyType.BOOLEAN) return Objects.equals(b, other.b);
        else if (type == VertexPropertyType.INTEGER && other.type == VertexPropertyType.INTEGER) return Objects.equals(i, other.i);
        else if (type == VertexPropertyType.FLOAT && other.type == VertexPropertyType.FLOAT) return Objects.equals(f, other.f);
        else if (type == VertexPropertyType.DOUBLE && other.type == VertexPropertyType.DOUBLE) return Objects.equals(d, other.d);
        else if (type == VertexPropertyType.STRING && other.type == VertexPropertyType.STRING) return Objects.equals(s, other.s);
        else if (type == VertexPropertyType.LONG && other.type == VertexPropertyType.LONG) return Objects.equals(l, other.l);
        else
            return false;
    }

//    public static VertexProperty create(boolean b) {
//        VertexProperty p = BOOLEAN;
//        p.b = b;
//        return p;
//    }
//
//    public static VertexProperty create(int i) {
//        VertexProperty p = INTEGER;
//        p.i = i;
//        return p;
//    }
//
//    public static VertexProperty create(long l) {
//        VertexProperty p = LONG;
//        p.l = l;
//        return p;
//    }
//
//    public static VertexProperty create(float f) {
//        VertexProperty p = FLOAT;
//        p.f = f;
//        return p;
//    }
//
//    public static VertexProperty create(double d) {
//        VertexProperty p = DOUBLE;
//        p.d = d;
//        return p;
//    }
//
//    public static VertexProperty create(List<? extends Object> array) {
//        VertexProperty p = ARRAY;
//        List<VertexProperty> propArray = array.stream().map(o -> VertexProperty.create(o)).collect(Collectors.toList());
//        p.d = d;
//        return p;
//    }

}
