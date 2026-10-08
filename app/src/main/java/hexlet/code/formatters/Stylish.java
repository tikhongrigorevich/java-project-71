package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

public class Stylish {
    public static String build(List<DiffNode> diffTree) {
        String minus = "  - ";
        String plus = "  + ";
        String space = "    ";
        String colon = ": ";

        StringBuilder result = new StringBuilder("{\n");

        for (var node : diffTree) {
            String key = node.getKey();
            String status = node.getStatus();

            switch (status) {
                case "deleted" ->
                        result.append(minus)
                                .append(key)
                                .append(colon)
                                .append(node.getOldValue())
                                .append("\n");
                case "added" ->
                        result.append(plus)
                                .append(key)
                                .append(colon)
                                .append(node.getNewValue())
                                .append("\n");
                case "notUpdate" ->
                        result.append(space)
                                .append(key)
                                .append(colon)
                                .append(node.getOldValue())
                                .append("\n");
                case "update" -> {
                    result.append(minus)
                            .append(key)
                            .append(colon)
                            .append(node.getOldValue())
                            .append("\n");
                    result.append(plus)
                            .append(key)
                            .append(colon)
                            .append(node.getNewValue())
                            .append("\n");
                }
            }
        }

        result.append("}");

        return result.toString();
    }
}
