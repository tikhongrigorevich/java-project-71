package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper mapper;

        if (format.equals("yaml") || format.equals("yml")) {
            mapper = new YAMLMapper();
        } else if (format.equals("json")) {
            mapper = new ObjectMapper();
        } else {
            throw new Exception("Unknown format: " + format);
        }

        return mapper.readValue(content, new TypeReference<>() {});
    }
}
