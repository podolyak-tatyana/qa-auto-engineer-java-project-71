package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String outputFormat) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String fileContent1 = Files.readString(path1);
        String fileContent2 = Files.readString(path2);

        // входной формат определяется по расширению файла
        Format inputFormat1 = Format.fromFileName(filePath1);
        Format inputFormat2 = Format.fromFileName(filePath2);

        Map<String, Object> map1 = Parser.parse(fileContent1, inputFormat1);
        Map<String, Object> map2 = Parser.parse(fileContent2, inputFormat2);

        List<Node> diff = DiffBuilder.build(map1, map2);

        String fmt = (outputFormat == null || outputFormat.isBlank()) ? "stylish" : outputFormat;

        return switch (fmt) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unsupported output format: " + fmt);
        };
    }
}
