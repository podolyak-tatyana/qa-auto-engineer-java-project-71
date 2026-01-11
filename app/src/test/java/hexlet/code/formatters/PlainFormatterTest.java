package hexlet.code.formatters;

import hexlet.code.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainFormatterTest {

    @Test
    void plainFormatterRendersExpected() {
        List<Node> diff = List.of(
                Node.updated("checked", false, true),
                Node.removed("key1", "value1"),
                Node.added("key2", "value2")
        );

        String expected = """
            Property 'checked' was updated. From false to true
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'""";

        assertEquals(expected, PlainFormatter.format(diff));
    }

}
