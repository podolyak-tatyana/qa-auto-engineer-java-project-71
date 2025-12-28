package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String strFormat) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String fileContent1 = Files.readString(path1);
        String fileContent2 = Files.readString(path2);

        Format format = parseFormat(strFormat);

        Map<String, Object> map1 = Parser.parse(fileContent1, format);
        Map<String, Object> map2 = Parser.parse(fileContent2, format);

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
        return result.toString();
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

    private static Format parseFormat(String value) {
        return Format.getFormatByString(value);
    }
}
