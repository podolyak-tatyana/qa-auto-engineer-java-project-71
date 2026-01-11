package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {

    public static List<Node> build(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        List<Node> diff = new ArrayList<>();

        for (String key : keys) {
            boolean in1 = map1.containsKey(key);
            boolean in2 = map2.containsKey(key);

            if (in1 && in2) {
                Object v1 = map1.get(key);
                Object v2 = map2.get(key);

                if (valuesEqual(v1, v2)) {
                    diff.add(Node.unchanged(key, v1));
                } else {
                    diff.add(Node.updated(key, v1, v2));
                }
            } else if (in1) {
                diff.add(Node.removed(key, map1.get(key)));
            } else { // in2
                diff.add(Node.added(key, map2.get(key)));
            }
        }

        return diff;
    }

    private static boolean valuesEqual(Object a, Object b) {
        if (Objects.equals(a, b)) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }
}
