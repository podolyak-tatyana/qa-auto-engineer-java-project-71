package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;

public class StylishFormatter {

    public static String format(List<Node> diff) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (Node node : diff) {
            String key = node.getKey();

            switch (node.getStatus()) {
                case UNCHANGED -> result
                        .append("    ").append(key).append(": ")
                        .append(renderValue(node.getOldValue()))
                        .append("\n");

                case REMOVED -> result
                        .append("  - ").append(key).append(": ")
                        .append(renderValue(node.getOldValue()))
                        .append("\n");

                case ADDED -> result
                        .append("  + ").append(key).append(": ")
                        .append(renderValue(node.getNewValue()))
                        .append("\n");

                case UPDATED -> {
                    result.append("  - ").append(key).append(": ")
                            .append(renderValue(node.getOldValue()))
                            .append("\n");
                    result.append("  + ").append(key).append(": ")
                            .append(renderValue(node.getNewValue()))
                            .append("\n");
                }
                default -> throw new IllegalStateException("Unknown node status: " + node.getStatus());
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String renderValue(Object value) {
        if (value == null) {
            return "null";
        }
        // по требованию: если объект/массив — выводим как есть через toString()
        return value.toString();
    }
}
