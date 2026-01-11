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

    private static final String JSON_FILE_PATH_1 = "src/test/resources/dataset/json/file.json";
    private static final String JSON_FILE_PATH_2 = "src/test/resources/dataset/json/file2.json";

    private static final String YML_FILE_PATH_1 = "src/test/resources/dataset/yml/file1.yml";
    private static final String YML_FILE_PATH_2 = "src/test/resources/dataset/yml/file2.yml";

    private static final String JSON_EXPECTED_FILE = "src/test/resources/dataset/json_formater_expected.json";
    private static final String STYLISH_EXPECTED_FILE = "src/test/resources/dataset/stylish_formater_expected.txt";
    private static final String PLAIN_EXPECTED_FILE = "src/test/resources/dataset/plain_formater_expected.txt";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void generateJsonFormatForJsonFile() throws IOException {
        String actual = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "json");
        JsonNode actualTree = MAPPER.readTree(actual);

        JsonNode expectedTree = readExpectedTree();

        assertEquals(expectedTree, actualTree);
    }

    @Test
    void generateJsonFormatForYml() throws IOException {
        String actual = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "json");
        JsonNode actualTree = MAPPER.readTree(actual);

        JsonNode expectedTree = readExpectedTree();

        assertEquals(expectedTree, actualTree);
    }


    @Test
    void generateStylishFormatForJsonFile() throws IOException {
        String actual = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "stylish");
        String expected = readExpectedStylish();

        assertEquals(expected, actual);
    }

    @Test
    void generateStylishFormatForYml() throws IOException {
        String actual = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "stylish");
        String expected = readExpectedStylish();

        assertEquals(expected, actual);
    }


    @Test
    void generatePlainFormatForJsonFile() throws IOException {
        String actual = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "plain");
        String expected = readExpectedPlain();

        assertEquals(expected, actual);
    }

    @Test
    void generatePlainFormatForYml() throws IOException {
        String actual = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "plain");
        String expected = readExpectedPlain();

        assertEquals(expected, actual);
    }



    private static JsonNode readExpectedTree() throws IOException {
        String expectedContent = Files.readString(Paths.get(JSON_EXPECTED_FILE), StandardCharsets.UTF_8);
        return MAPPER.readTree(expectedContent);
    }

    private String readExpectedStylish() throws IOException {
        return Files.readString(Paths.get(STYLISH_EXPECTED_FILE), StandardCharsets.UTF_8);
    }

    private String readExpectedPlain() throws IOException {
        return Files.readString(Paths.get(PLAIN_EXPECTED_FILE), StandardCharsets.UTF_8);
    }
}
