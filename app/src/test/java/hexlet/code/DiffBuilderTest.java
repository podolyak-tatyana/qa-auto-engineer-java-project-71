package hexlet.code;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DiffBuilderTest {

    private static final String TEST1_MAP1_PATH = "src/test/resources/dataset/diffbuilder/test1/file1.json";
    private static final String TEST1_MAP2_PATH = "src/test/resources/dataset/diffbuilder/test1/file2.json";
    private static final String TEST2_MAP1_PATH = "src/test/resources/dataset/diffbuilder/test2/file1.json";
    private static final String TEST2_MAP2_PATH = "src/test/resources/dataset/diffbuilder/test2/file2.json";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void buildShouldDetectAllStatuses() throws IOException {
        Map<String, Object> map1 = readJsonAsMap(TEST1_MAP1_PATH);
        Map<String, Object> map2 = readJsonAsMap(TEST1_MAP2_PATH);

        List<Node> diff = DiffBuilder.build(map1, map2);

        Node added = find(diff, "added");
        assertEquals(Node.Status.ADDED, added.getStatus());
        assertNull(added.getOldValue());
        assertEquals(2, added.getNewValue());

        Node removed = find(diff, "removed");
        assertEquals(Node.Status.REMOVED, removed.getStatus());
        assertEquals(1, removed.getOldValue());
        assertNull(removed.getNewValue());

        Node updated = find(diff, "updated");
        assertEquals(Node.Status.UPDATED, updated.getStatus());
        assertEquals(false, updated.getOldValue());
        assertEquals(true, updated.getNewValue());

        Node same = find(diff, "same");
        assertEquals(Node.Status.UNCHANGED, same.getStatus());
        assertEquals("value", same.getOldValue());
        assertEquals("value", same.getNewValue());
    }

    @Test
    void buildShouldReturnNodesSortedByKey() throws IOException {
        Map<String, Object> map1 = readJsonAsMap(TEST2_MAP1_PATH);
        Map<String, Object> map2 = readJsonAsMap(TEST2_MAP2_PATH);

        List<Node> diff = DiffBuilder.build(map1, map2);

        assertEquals("a", diff.get(0).getKey());
        assertEquals("b", diff.get(1).getKey());
        assertEquals("c", diff.get(2).getKey());
    }

    private static Node find(List<Node> diff, String key) {
        return diff.stream()
                .filter(n -> n.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Node not found: " + key));
    }

    private static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        return MAPPER.readValue(content, Map.class);
    }
}
