package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {
    };

    public static void generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String fileContent1 = Files.readString(path1);
        String fileContent2 = Files.readString(path2);

        Map<String, Object> map1 = parseJsonToMap(fileContent1);
        Map<String, Object> map2 = parseJsonToMap(fileContent2);

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            boolean in1 = map1.containsKey(key);
            boolean in2 = map2.containsKey(key);

            if (in1 && in2) {
                Object v1 = map1.get(key);
                Object v2 = map2.get(key);

                if (valuesEqual(v1, v2)) {
                    result.append("    ").append(key).append(": ").append(renderValue(v1)).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(renderValue(v1)).append("\n");
                    result.append("  + ").append(key).append(": ").append(renderValue(v2)).append("\n");
                }
            } else if (in1) {
                Object v1 = map1.get(key);
                result.append("  - ").append(key).append(": ").append(renderValue(v1)).append("\n");
            } else {
                Object v2 = map2.get(key);
                result.append("  + ").append(key).append(": ").append(renderValue(v2)).append("\n");
            }
        }
        result.append("}");
        System.out.println(result);
    }

    private static Map<String, Object> parseJsonToMap(String json) throws IOException {
        return MAPPER.readValue(json, MAP_TYPE);
    }

    private static boolean valuesEqual(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    private static String renderValue(Object value) {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
