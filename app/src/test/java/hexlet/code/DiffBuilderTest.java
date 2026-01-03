package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DiffBuilderTest {

    @Test
    void buildShouldDetectAllStatuses() {
        Map<String, Object> map1 = Map.of(
                "same", "value",
                "removed", 1,
                "updated", false
        );

        Map<String, Object> map2 = Map.of(
                "same", "value",
                "added", 2,
                "updated", true
        );

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
    void buildShouldReturnNodesSortedByKey() {
        Map<String, Object> map1 = Map.of(
                "b", 1,
                "a", 1
        );
        Map<String, Object> map2 = Map.of(
                "c", 2
        );

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
}
