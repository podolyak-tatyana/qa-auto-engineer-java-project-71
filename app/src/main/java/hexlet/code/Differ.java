package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {};


    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String fileContent1 = Files.readString(path1);
        String fileContent2 = Files.readString(path2);

        var result1 = parseJsonToMap(fileContent1);
        var result2 = parseJsonToMap(fileContent2);
        return "";
    }

    private static Map<String, Object> parseJsonToMap(String json) {
        return MAPPER.readValue(json, MAP_TYPE);
    }
}
