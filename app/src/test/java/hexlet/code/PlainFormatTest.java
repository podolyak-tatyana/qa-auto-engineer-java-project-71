package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainFormatTest {
    private static final String FILE_PATH_1 = "src/test/resources/dataset/json/file.json";
    private static final String FILE_PATH_2 = "src/test/resources/dataset/json/file2.json";

    private static final String YML_FILE_PATH_1 = "src/test/resources/dataset/yml/file1.yml";
    private static final String YML_FILE_PATH_2 = "src/test/resources/dataset/yml/file2.yml";

    private static final String EXPECTED = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";


    @Test
    void generate() throws IOException {
        var result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "plain");
        assertEquals(EXPECTED, result);
    }

    @Test
    void generateForYml() throws IOException {
        var result = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "plain");
        assertEquals(EXPECTED, result);
    }
}
