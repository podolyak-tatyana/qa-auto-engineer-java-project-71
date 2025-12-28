package hexlet.code;

public enum Format {
    JSON("json"),
    YML("yml");

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
}
