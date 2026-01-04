package hexlet.code.formatters;


import hexlet.code.Node;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String format(List<Node> diff) {
        List<Map<String, Object>> out = new ArrayList<>();

        for (Node node : diff) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("key", node.getKey());
            item.put("status", node.getStatus().name());
            item.put("oldValue", node.getOldValue());
            item.put("newValue", node.getNewValue());

            out.add(item);
        }
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(out);
    }
}
