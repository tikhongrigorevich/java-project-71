package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class DifferTest {
    @Test
    public void differTest() throws Exception {
        Path resultPath = Paths.get("src/test/resources/fixtures/result.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file1.json").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file2.json").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2).trim();
        assertEquals(actual, expected);
    }

    @Test
    public void differYamlTest() throws Exception {
        Path resultPath = Paths.get("src/test/resources/fixtures/result.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file1.yaml").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file2.yaml").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2).trim();
        assertEquals(actual, expected);
    }
}
