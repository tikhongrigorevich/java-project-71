package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DifferTest {
    private static String readFixtures(String fileName) throws Exception {
        return Files.readString(Paths.get("src/test/resources/fixtures/", fileName)).trim();
    }

    private static Stream<Arguments> provideDiffTestArgumentsForAllFiles() {
        return Stream.of(
                arguments("file1.json", "file2.json", "stylish", "stylish_diff_1_2.txt"),
                arguments("file1.yml", "file2.yml", "stylish", "stylish_diff_1_2.txt"),
                arguments("file3.json", "file4.json", "stylish", "stylish_diff_3_4.txt"),
                arguments("file3.yml", "file4.yml", "stylish", "stylish_diff_3_4.txt"),
                arguments("file1.json", "file2.json", "plain", "plain_diff_1_2.txt"),
                arguments("file1.yml", "file2.yml", "plain", "plain_diff_1_2.txt"),
                arguments("file3.json", "file4.json", "plain", "plain_diff_3_4.txt"),
                arguments("file3.yml", "file4.yml", "plain", "plain_diff_3_4.txt"),
                arguments("file1.json", "file2.json", "json", "json_diff_1_2.txt"),
                arguments("file1.yml", "file2.yml", "json", "json_diff_1_2.txt"),
                arguments("file3.json", "file4.json", "json", "json_diff_3_4.txt"),
                arguments("file3.yml", "file4.yml", "json", "json_diff_3_4.txt"));
    }

    @ParameterizedTest
    @MethodSource("provideDiffTestArgumentsForAllFiles")
    public void diffBetweenFile1And2Test(
            String file1, String file2, String format, String diffResult) throws Exception {
        Path path1 = Path.of("src/test/resources/fixtures/" + file1);
        Path path2 = Path.of("src/test/resources/fixtures/" + file2);
        String expected = readFixtures(diffResult);
        String actual = Differ.generate(path1, path2, format).trim();
        assertEquals(expected, actual);
    }

    @Test
    public void defaultTest() throws Exception {
        Path path1 = Path.of("src/test/resources/fixtures/file1.json");
        Path path2 = Path.of("src/test/resources/fixtures/file2.json");
        String expected =
                Files.readString(Path.of("src/test/resources/fixtures/stylish_diff_1_2.txt"))
                        .trim();
        String actual = Differ.generate(path1, path2);
        assertEquals(expected, actual);
    }
}
