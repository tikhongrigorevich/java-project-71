package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import java.util.*;

public class Json {
    public static String build(List<DiffNode> diffTree) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> result = new ArrayList<>();

        for (var node : diffTree) {
            Map<String, Object> mapTree = new LinkedHashMap<>();

            mapTree.put("key", node.getKey());
            mapTree.put("status", node.getStatus());

            if (node.getStatus().equals("deleted") || node.getStatus().equals("notUpdate")) {
                mapTree.put("oldValue", node.getOldValue());
            } else if (node.getStatus().equals("added")) {
                mapTree.put("newValue", node.getNewValue());
            } else {
                mapTree.put("oldValue", node.getOldValue());
                mapTree.put("newValue", node.getNewValue());
            }

            result.add(mapTree);
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
