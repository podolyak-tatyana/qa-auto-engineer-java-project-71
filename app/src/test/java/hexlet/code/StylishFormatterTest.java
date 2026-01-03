package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {

    @Test
    void formatShouldRenderStylishOutput() {
        LinkedHashMap<String, Object> nested = new LinkedHashMap<>();
        nested.put("nestedKey", "value");
        nested.put("isNested", true);

        List<Node> diff = List.of(
                Node.unchanged("host", "hexlet.io"),
                Node.removed("timeout", 50),
                Node.added("timeout", 20),
                Node.added("obj1", nested),
                Node.removed("default", null),
                Node.added("default", List.of("value1", "value2"))
        );

        String actual = StylishFormatter.format(diff);

        String expected = """
                {
                    host: hexlet.io
                  - timeout: 50
                  + timeout: 20
                  + obj1: {nestedKey=value, isNested=true}
                  - default: null
                  + default: [value1, value2]
                }""";

        assertEquals(expected, actual);
    }
}
