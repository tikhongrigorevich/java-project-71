package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String build(List<DiffNode> diffTree) {
        StringBuilder result = new StringBuilder();

        for (var node : diffTree) {
            String key = node.getKey();
            String status = node.getStatus();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();
            String propertyKey = "Property " + "'" + key + "'";

            switch (status) {
                case "deleted" -> result.append(propertyKey).append(" was removed").append("\n");
                case "added" ->
                        result.append(propertyKey)
                                .append(" was added with value: ")
                                .append(getType(newValue))
                                .append("\n");
                case "update" ->
                        result.append(propertyKey)
                                .append(" was updated. From ")
                                .append(getType(oldValue))
                                .append(" to ")
                                .append(getType(newValue))
                                .append("\n");
            }
        }

        return result.toString().trim();
    }

    private static String getType(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map<?, ?> || value instanceof Collection<?>) {
            return "[complex value]";
        }
        return value.toString();
    }
}
