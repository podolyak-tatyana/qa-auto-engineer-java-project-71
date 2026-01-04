package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Node> diff) {
        StringBuilder out = new StringBuilder();

        for (Node node : diff) {
            String key = node.getKey();

            switch (node.getStatus()) {
                case ADDED -> appendLine(out,
                        "Property '%s' was added with value: %s"
                                .formatted(key, renderValue(node.getNewValue()))
                );

                case REMOVED -> appendLine(out,
                        "Property '%s' was removed".formatted(key)
                );

                case UPDATED -> appendLine(out,
                        "Property '%s' was updated. From %s to %s"
                                .formatted(key, renderValue(node.getOldValue()), renderValue(node.getNewValue()))
                );

                case UNCHANGED -> {
                }

                default -> throw new IllegalStateException("Unknown node status: " + node.getStatus());
            }
        }

        // убрать последний перенос строки
        if (!out.isEmpty() && out.charAt(out.length() - 1) == '\n') {
            out.deleteCharAt(out.length() - 1);
        }
        return out.toString();
    }

    private static void appendLine(StringBuilder out, String line) {
        out.append(line).append('\n');
    }

    private static String renderValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (isComplex(value)) {
            return "[complex value]";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        return value.toString();
    }

    private static boolean isComplex(Object value) {
        if (value instanceof Map<?, ?>) {
            return true;
        }
        return value instanceof Iterable<?>;
    }
}
