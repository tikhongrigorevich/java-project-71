package hexlet.code;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> diffTree) {
        String minus = "  - ";
        String plus = "  + ";
        String space = "    ";
        String colon = ": ";
        String oldValue = "oldValue";
        String newValue = "newValue";

        StringBuilder result = new StringBuilder("{\n");

        for (var node : diffTree) {
            String type = (String) node.get("type");
            String key = (String) node.get("key");

            switch (type) {
                case "deleted" ->
                    result.append(minus).append(key).append(colon).append(node.get(oldValue)).append("\n");
                case "added" ->
                    result.append(plus).append(key).append(colon).append(node.get(newValue)).append("\n");
                case "unchanged" ->
                    result.append(space).append(key).append(colon).append(node.get(oldValue)).append("\n");
                case "changed" -> {
                    result.append(minus).append(key).append(colon).append(node.get(oldValue)).append("\n");
                    result.append(plus).append(key).append(colon).append(node.get(newValue)).append("\n");
                }
            }
        }

        result.append("}");

        return result.toString();
    }

    public static String stylish(List<Map<String, Object>> diffTree, String format) {
        return Stylish.stylish(diffTree);
    }
}
