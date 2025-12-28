package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String FILE_PATH_1 = "src/test/resources/dataset/json/file.json";
    private static final String FILE_PATH_2 = "src/test/resources/dataset/json/file2.json";

    private static final String YML_FILE_PATH_1 = "src/test/resources/dataset/yml/file1.yml";
    private static final String YML_FILE_PATH_2 = "src/test/resources/dataset/yml/file2.yml";

    @Test
    void generate() throws IOException {
        var expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        var result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "json");
        assertEquals(expected, result);
    }

    @Test
    void generateForYml() throws IOException {
        var expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        var result = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "yml");
        assertEquals(expected, result);
    }
}
