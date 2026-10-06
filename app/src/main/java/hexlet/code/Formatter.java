package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

class Formatter {
    public static String formatter(List<Map<String, Object>> diffTree, String format) {
        return switch (format.toLowerCase()) {
            case "stylish" -> Stylish.stylish(diffTree);
            case "plain" -> Plain.plain(diffTree);
            default -> throw new IllegalStateException("Unknown format: " + format.toLowerCase());
        };
    }
}
