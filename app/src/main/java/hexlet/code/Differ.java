package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format)
            throws Exception {
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

        List<Map<String, Object>> diffTree = new ArrayList<>();

        for (var key : sortedKeys) {
            boolean isMap1 = contentMap1.containsKey(key);
            boolean isMap2 = contentMap2.containsKey(key);
            Object value1 = contentMap1.get(key);
            Object value2 = contentMap2.get(key);

            Map<String, Object> node = new LinkedHashMap<>();
            node.put("key", key);

            if (isMap1 && !isMap2) {
                node.put("type", "deleted");
                node.put("oldValue", value1);
            } else if (!isMap1 && isMap2) {
                node.put("type", "added");
                node.put("newValue", value2);
            } else if (Objects.equals(value1, value2)) {
                node.put("type", "unchanged");
                node.put("oldValue", value1);
            } else {
                node.put("type", "changed");
                node.put("oldValue", value1);
                node.put("newValue", value2);
            }

            diffTree.add(node);
        }

        return Stylish.stylish(diffTree, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return Differ.generate(filePath1, filePath2, "stylish");
    }

    private static String getFormat(String filePath) {
        int index = filePath.lastIndexOf(".");

        return index == -1 ? "" : filePath.substring(index + 1).toLowerCase();
    }
}
