package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(Path path1, Path path2) throws Exception {
        return Differ.generate(path1, path2, "stylish");
    }

    public static String generate(Path filePath1, Path filePath2, String format) throws Exception {
        Path absolutePath1 = filePath1.toAbsolutePath().normalize();
        Path absolutePath2 = filePath2.toAbsolutePath().normalize();

        Map<String, Object> contentMap1 =
                Parser.parse(
                        Files.readString(absolutePath1),
                        getFormat(absolutePath1.getFileName().toString()));
        Map<String, Object> contentMap2 =
                Parser.parse(
                        Files.readString(absolutePath2),
                        getFormat(absolutePath2.getFileName().toString()));

        Set<String> allKeys = new HashSet<>(contentMap1.keySet());
        allKeys.addAll(contentMap2.keySet());
        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        List<DiffNode> diffTree = new ArrayList<>();

        for (var key : sortedKeys) {
            boolean isMap1 = contentMap1.containsKey(key);
            boolean isMap2 = contentMap2.containsKey(key);
            Object value1 = contentMap1.get(key);
            Object value2 = contentMap2.get(key);

            if (isMap1 && !isMap2) {
                diffTree.add(new DiffNode(key, "deleted", value1, null));
            } else if (!isMap1 && isMap2) {
                diffTree.add(new DiffNode(key, "added", null, value2));
            } else if (Objects.equals(value1, value2)) {
                diffTree.add(new DiffNode(key, "notUpdate", value1, null));
            } else {
                diffTree.add(new DiffNode(key, "update", value1, value2));
            }
        }

        return Formatter.render(diffTree, format);
    }

    private static String getFormat(String filePath) {
        int index = filePath.lastIndexOf(".");

        return index == -1 ? "" : filePath.substring(index + 1).toLowerCase();
    }
}
