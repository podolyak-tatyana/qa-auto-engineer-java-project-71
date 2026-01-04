package hexlet.code;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonDifferTest {

    private static final String FILE_PATH_1 = "src/test/resources/dataset/json/file.json";
    private static final String FILE_PATH_2 = "src/test/resources/dataset/json/file2.json";

    private static final String YML_FILE_PATH_1 = "src/test/resources/dataset/yml/file1.yml";
    private static final String YML_FILE_PATH_2 = "src/test/resources/dataset/yml/file2.yml";

    private static final String EXPECTED_FILE = "src/test/resources/dataset/json_formater_expected.json";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void generate() throws IOException {
        String actual = Differ.generate(FILE_PATH_1, FILE_PATH_2, "json");
        JsonNode actualTree = MAPPER.readTree(actual);

        JsonNode expectedTree = readExpectedTree();

        assertEquals(expectedTree, actualTree);
    }

    @Test
    void generateForYml() throws IOException {
        String actual = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "json");
        JsonNode actualTree = MAPPER.readTree(actual);

        JsonNode expectedTree = readExpectedTree();

        assertEquals(expectedTree, actualTree);
    }

    private static JsonNode readExpectedTree() throws IOException {
        String expectedContent = Files.readString(Paths.get(EXPECTED_FILE), StandardCharsets.UTF_8);
        return MAPPER.readTree(expectedContent);
    }
}
