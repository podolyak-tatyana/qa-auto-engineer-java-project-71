package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String FILE_PATH_1 = "src/test/resources/file.json";
    private static final String FILE_PATH_2 = "src/test/resources/file2.json";

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

        var result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "");
        assertEquals(expected, result);
    }
}
