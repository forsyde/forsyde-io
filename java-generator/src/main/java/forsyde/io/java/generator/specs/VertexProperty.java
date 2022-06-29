package forsyde.io.java.generator.specs;

import org.derive4j.Data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public abstract class VertexProperty {

    interface Cases<R> {
        R StringVertexProperty (String string);
        R IntVertexProperty (int intValue);
        R BooleanVertexProperty (boolean booleanValue);
        R FloatVertexProperty (float floatValue);
        R DoubleVertexProperty (double doubleValue);
        R LongVertexProperty (long longValue);
        R ArrayVertexProperty (List<VertexProperty> values);
        R IntMapVertexProperty (Map<Integer, VertexProperty> intMap);
        R StringMapVertexProperty (Map<String, VertexProperty> strMap);
    }

    public abstract <R> R match(Cases<R> cases);

    public static VertexProperty create(float f) {
        return VertexProperties.FloatVertexProperty(f);
    }

    public static VertexProperty create(double d) {
        return VertexProperties.DoubleVertexProperty(d);
    }

    public static VertexProperty create(long l) {
        return VertexProperties.LongVertexProperty(l);
    }

    public static VertexProperty create(int l) {
        return VertexProperties.IntVertexProperty(l);
    }

    public static VertexProperty create(boolean b) {
        return VertexProperties.BooleanVertexProperty(b);
    }

    public static VertexProperty create(Object obj) {
        if (obj instanceof VertexProperty) {
            return (VertexProperty) obj;
        } else {
            if (obj instanceof Boolean) {
                return VertexProperties.BooleanVertexProperty((Boolean) obj);
            } else if (obj instanceof Integer) {
                return VertexProperties.IntVertexProperty((Integer) obj);
            } else if (obj instanceof Long) {
                return VertexProperties.LongVertexProperty((Long) obj);
            } else if (obj instanceof Float) {
                return VertexProperties.FloatVertexProperty((Float) obj);
            } else if (obj instanceof Double) {
                return VertexProperties.DoubleVertexProperty((Double) obj);
            } else if (obj instanceof List) {
                return VertexProperties.ArrayVertexProperty(
                        ((List<Object>) obj).stream().map(VertexProperty::create).collect(Collectors.toList())
                );
            } else if (obj.getClass().isArray()) {
                                return VertexProperties.ArrayVertexProperty(
                    Arrays.stream((Object[]) obj).map(VertexProperty::create).collect(Collectors.toList())
                );
            } else if (obj instanceof Map) {
                Map<Object, Object> srcMap = (Map<Object, Object>) obj;
                // just probe one key element ot check if it is Int or not
                if (!srcMap.isEmpty() && srcMap.keySet().stream().findAny().get() instanceof Integer) {
                    return VertexProperties.IntMapVertexProperty(
                            ((Map<Integer, Object>) obj).entrySet().stream()
                                    .collect(Collectors.toMap(Map.Entry::getKey, v -> VertexProperty.create(v.getValue())))
                    );
                } else {
                    return VertexProperties.StringMapVertexProperty(
                            ((Map<String, Object>) obj).entrySet().stream()
                                    .collect(Collectors.toMap(e -> e.getKey().toString(), v -> VertexProperty.create(v.getValue())))
                    );
                }
            } else {
                return VertexProperties.StringVertexProperty(obj.toString());
            }
        }
    }

    static public Object decreate(VertexProperty prop) {
        return VertexProperties.cases()
                .StringVertexProperty(p -> (Object) p)
                .IntVertexProperty(p -> (Object) Integer.valueOf(p))
                .BooleanVertexProperty(p -> (Object) Boolean.valueOf(p))
                .FloatVertexProperty(p -> (Object) Float.valueOf(p))
                .DoubleVertexProperty(d -> (Object) Double.valueOf(d))
                .LongVertexProperty(p -> (Object) Long.valueOf(p))
                .ArrayVertexProperty(l -> (Object) l.stream().map(VertexProperty::unwrap).collect(Collectors.toList()))
                .IntMapVertexProperty(m -> (Object) m.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().unwrap())))
                .StringMapVertexProperty(m -> (Object) m.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().unwrap())))
                .apply(prop);
    }

    public Object unwrap() {
        return VertexProperty.decreate(this);
    }

    public static boolean mergeInPlace(VertexProperty main, VertexProperty other) {
        return VertexProperties.cases()
                .StringVertexProperty(s -> VertexProperties.cases().StringVertexProperty(s::equals).otherwise_(false).apply(other))
                .IntVertexProperty(i -> VertexProperties.cases().IntVertexProperty(i::equals).otherwise_(false).apply(other))
                .BooleanVertexProperty(b -> VertexProperties.cases().BooleanVertexProperty(b::equals).otherwise_(false).apply(other))
                .FloatVertexProperty(f -> VertexProperties.cases().FloatVertexProperty(f::equals).otherwise_(false).apply(other))
                .DoubleVertexProperty(d -> VertexProperties.cases().DoubleVertexProperty(d::equals).otherwise_(false).apply(other))
                .LongVertexProperty(l -> VertexProperties.cases().LongVertexProperty(l::equals).otherwise_(false).apply(other))
                .ArrayVertexProperty(a -> VertexProperties.cases().ArrayVertexProperty(aOther -> {
                    final Iterator<VertexProperty> otherIt = aOther.iterator();
                    final Iterator<VertexProperty> thisIt = a.iterator();
                    while (otherIt.hasNext() && thisIt.hasNext()) {
                        // until the sizes overlap, the elements should be mergeable
                        if (!thisIt.next().mergeInPlace(otherIt.next()))
                            return false;
                    }
                    // if other is bigger than this
                    otherIt.forEachRemaining(a::add);
                    return true;
                }).otherwise_(false).apply(other))
                .IntMapVertexProperty(m -> VertexProperties.cases().IntMapVertexProperty(mOther -> {
                    // merge keys that overlap and just insert keys that don't
                    for (Integer key : mOther.keySet()) {
                        if (m.containsKey(key)) {
                            if(!m.get(key).mergeInPlace(mOther.get(key)))
                                return false;
                        } else {
                            m.put(key, mOther.get(key));
                        }
                    }
                    return true;
                }).otherwise_(false).apply(other))
                .StringMapVertexProperty(m -> VertexProperties.cases().StringMapVertexProperty(mOther -> {
                    for (String key : mOther.keySet()) {
                        if (m.containsKey(key)) {
                            if(!m.get(key).mergeInPlace(mOther.get(key)))
                                return false;
                        } else {
                            m.put(key, mOther.get(key));
                        }
                    }
                    return true;
                }).otherwise_(false).apply(other))
                .apply(main);
    }

    public boolean mergeInPlace(VertexProperty other) {
        // try to merge two arrays. For the merge to be well defined, they
        // must have mergeable elements in the same order, save for additionals
        // tail elements in the other array
        return VertexProperty.mergeInPlace(this, other);
    }

}
