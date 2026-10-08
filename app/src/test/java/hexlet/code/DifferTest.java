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

    private static Stream<Arguments> provideDiffTestArgumentsForFiles1And2() {
        return Stream.of(
                arguments("json", "stylish", "stylish_diff_1_2.txt"),
                arguments("yml", "stylish", "stylish_diff_1_2.txt"),
                arguments("json", "plain", "plain_diff_1_2.txt"),
                arguments("yml", "plain", "plain_diff_1_2.txt"),
                arguments("json", "json", "json_diff_1_2.txt"),
                arguments("yml", "json", "json_diff_1_2.txt"));
    }

    @ParameterizedTest
    @MethodSource("provideDiffTestArgumentsForFiles1And2")
    public void diffBetweenFile1And2Test(String extension, String format, String diffResult)
            throws Exception {
        Path path1 = Path.of("src/test/resources/fixtures/file1." + extension);
        Path path2 = Path.of("src/test/resources/fixtures/file2." + extension);
        String expected = readFixtures(diffResult);
        String actual = Differ.generate(path1, path2, format).trim();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideDiffTestArgumentsForFiles3And4() {
        return Stream.of(
                arguments("json", "stylish", "stylish_diff_3_4.txt"),
                arguments("yml", "stylish", "stylish_diff_3_4.txt"),
                arguments("json", "plain", "plain_diff_3_4.txt"),
                arguments("yml", "plain", "plain_diff_3_4.txt"),
                arguments("json", "json", "json_diff_3_4.txt"),
                arguments("yml", "json", "json_diff_3_4.txt"));
    }

    @ParameterizedTest
    @MethodSource("provideDiffTestArgumentsForFiles3And4")
    public void diffBetweenFile3And4Test(String extension, String format, String diffResult)
            throws Exception {
        Path path3 = Path.of("src/test/resources/fixtures/file3." + extension);
        Path path4 = Path.of("src/test/resources/fixtures/file4." + extension);
        String expected = readFixtures(diffResult);
        String actual = Differ.generate(path3, path4, format).trim();
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
