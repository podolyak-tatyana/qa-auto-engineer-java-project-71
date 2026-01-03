package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String FILE_PATH_1 = "src/test/resources/dataset/json/file.json";
    private static final String FILE_PATH_2 = "src/test/resources/dataset/json/file2.json";

    private static final String YML_FILE_PATH_1 = "src/test/resources/dataset/yml/file1.yml";
    private static final String YML_FILE_PATH_2 = "src/test/resources/dataset/yml/file2.yml";

    private static final String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

    @Test
    void generate() throws IOException {
        var result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "json");
        assertEquals(expected, result);
    }

    @Test
    void generateForYml() throws IOException {
        var result = Differ.generate(YML_FILE_PATH_1, YML_FILE_PATH_2, "yml");
        assertEquals(expected, result);
    }
}
