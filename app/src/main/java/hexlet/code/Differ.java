package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        Map<String, Object> contentMap1 =
                Parser.parse(Files.readString(path1), getFormat(filePath1));
        Map<String, Object> contentMap2 =
                Parser.parse(Files.readString(path2), getFormat(filePath2));

        Set<String> allKeys = new HashSet<>(contentMap1.keySet());
        allKeys.addAll(contentMap2.keySet());
        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        StringBuilder result = new StringBuilder("{\n");

        for (var key : sortedKeys) {
            boolean isMap1 = contentMap1.containsKey(key);
            boolean isMap2 = contentMap2.containsKey(key);
            Object value1 = contentMap1.get(key);
            Object value2 = contentMap2.get(key);

            if (isMap1 && !isMap2) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (!isMap1 && isMap2) {
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (Objects.equals(value1, value2)) {
                result.append("    ").append(key).append(": ").append(value1).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }

    private static String getFormat(String filePath) {
        int index = filePath.lastIndexOf(".");

        return index == -1 ? "" : filePath.substring(index + 1).toLowerCase();
    }
}
