package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {
    };

    private static final ObjectMapper YML_MAPPER = new YAMLMapper();

    public static Map<String, Object> parse(String content, Format format) {
        return switch (format) {
            case JSON -> JSON_MAPPER.readValue(content, MAP_TYPE);
            case YML, YAML -> YML_MAPPER.readValue(content, MAP_TYPE);
            case OTHER -> throw new IllegalArgumentException("Unsupported file format: " + format.getFormatValue());
        };
    }
}
