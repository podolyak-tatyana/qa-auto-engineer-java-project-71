package hexlet.code.formatters;

import hexlet.code.Node;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {

    private static final int FIFTY = 50;
    private static final int TWENTY = 20;

    @Test
    void formatShouldRenderStylishOutput() {
        LinkedHashMap<String, Object> nested = new LinkedHashMap<>();
        nested.put("nestedKey", "value");
        nested.put("isNested", true);

        List<Node> diff = List.of(
                Node.unchanged("host", "hexlet.io"),
                Node.removed("timeout", FIFTY),
                Node.added("timeout", TWENTY),
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
