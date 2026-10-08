package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;

class Formatter {
    public static String render(List<DiffNode> diffTree, String format) throws Exception {
        return switch (format.toLowerCase()) {
            case "stylish" -> Stylish.build(diffTree);
            case "plain" -> Plain.build(diffTree);
            case "json" -> Json.build(diffTree);
            default -> throw new IllegalStateException("Unknown format: " + format.toLowerCase());
        };
    }
}
