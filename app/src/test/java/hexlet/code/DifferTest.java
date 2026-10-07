package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class DifferTest {
    @Test
    public void stylishTestBetweenFile1And2() throws Exception {
        Path resultPath =
                Paths.get("src/test/resources/fixtures/stylish_diff_1_2.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file1.json").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file2.json").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2).trim();
        assertEquals(actual, expected);
    }

    @Test
    public void ymlTestBetweenFile1And2() throws Exception {
        Path resultPath =
                Paths.get("src/test/resources/fixtures/stylish_diff_1_2.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file1.yml").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file2.yml").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2).trim();
        assertEquals(actual, expected);
    }

    @Test
    public void stylishTestBetweenFile3And4() throws Exception {
        Path resultPath =
                Paths.get("src/test/resources/fixtures/stylish_diff_3_4.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file3.json").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file4.json").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2).trim();
        assertEquals(actual, expected);
    }

    @Test
    public void plainTestBetweenFile3And4() throws Exception {
        Path resultPath =
                Paths.get("src/test/resources/fixtures/plain_diff_3_4.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file3.json").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file4.json").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2, "plain").trim();
        assertEquals(actual, expected);
    }

    @Test
    public void jsonTestBetweenFile3And4() throws Exception {
        Path resultPath =
                Paths.get("src/test/resources/fixtures/json_diff_3_4.txt").toAbsolutePath();
        String expected = Files.readString(resultPath).trim();

        String path1 =
                Paths.get("src/test/resources/fixtures/file3.json").toAbsolutePath().toString();
        String path2 =
                Paths.get("src/test/resources/fixtures/file4.json").toAbsolutePath().toString();

        String actual = Differ.generate(path1, path2, "json").trim();
        assertEquals(actual, expected);
    }
}
