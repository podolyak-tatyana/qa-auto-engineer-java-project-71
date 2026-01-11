package hexlet.code;

public enum Format {
    JSON("json"),
    YML("yml"),
    YAML("yaml"),
    OTHER("other");

    private final String formatValue;

    Format(String format) {
        this.formatValue = format;
    }

    public String getFormatValue() {
        return formatValue;
    }
}
