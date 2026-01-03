package hexlet.code;

public enum Format {
    JSON("json"),
    YML("yml"),
    YAML("yaml");

    private final String formatValue;

    Format(String format) {
        this.formatValue = format;
    }

    public String getFormatValue() {
        return formatValue;
    }

    static Format getFormatByString(String value) {
        for (Format format: Format.values()) {
            if (value.equals(format.formatValue)) {
                return format;
            }
        }
        throw new RuntimeException("Unknown format: " + value);
    }

    public static Format fromFileName(String filePath) {
        int dot = filePath.lastIndexOf('.');
        if (dot == -1 || dot == filePath.length() - 1) {
            throw new IllegalArgumentException("Cannot determine file format (no extension): " + filePath);
        }

        String extension = filePath.substring(dot + 1).toLowerCase();
        return switch (extension) {
            case "json" -> JSON;
            case "yml" -> YML;
            case "yaml" -> YAML;
            default -> throw new IllegalArgumentException("Unsupported input file format: " + extension);
        };
    }
}
