package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' doesn't exist.");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' doesn't exist.");
        }

        String readFile1 = Files.readString(path1);
        String readFile2 = Files.readString(path2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(readFile1, new TypeReference<>() {});
        Map<String, Object> map2 = mapper.readValue(readFile2, new TypeReference<>() {});

        Set<String> allKeys = new HashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        StringBuilder result = new StringBuilder("{\n");

        for (var key : sortedKeys) {
            boolean isMap1 = map1.containsKey(key);
            boolean isMap2 = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

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
}
