package hexlet.code.formatters;


import hexlet.code.Node;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonFormatterTest {


    @Test
    void jsonFormatterProducesValidJson() throws Exception {
        List<Node> diff = List.of(
                Node.updated("checked", false, true),
                Node.removed("key1", "value1"),
                Node.added("key2", "value2")
        );

        String json = JsonFormatter.format(diff);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        assertTrue(root.isArray());
        assertEquals("checked", root.get(0).get("key").asText());
        assertEquals("UPDATED", root.get(0).get("status").asText());
    }
}
