import java.nio.file.*;
import java.util.*;

public class CeresSearch {

    public static int countOfXmas1(char xmas[][]) {
        int count = 0;
        for (int i = 0; i < xmas.length; i++) {
            for (int j = 0; j < xmas[0].length; j++) {
                // horizontal
                if (j + 3 < xmas[0].length && xmas[i][j] == 'X' && xmas[i][j + 1] == 'M' && xmas[i][j + 2] == 'A' &&
                        xmas[i][j + 3] == 'S') {
                    count = count + 1;
                }
                // vertical
                if (i + 3 < xmas.length && xmas[i][j] == 'X' && xmas[i + 1][j] == 'M'
                        && xmas[i + 2][j] == 'A'
                        && xmas[i + 3][j] == 'S') {
                    count = count + 1;
                }
                // right diagonal
                if (i + 3 < xmas.length && j + 3 < xmas[0].length && xmas[i][j] == 'X'
                        && xmas[i + 1][j + 1] == 'M' && xmas[i + 2][j + 2] == 'A'
                        && xmas[i + 3][j + 3] == 'S') {
                    count = count + 1;
                }
                // left diagonal
                if (i + 3 < xmas.length && j - 3 >= 0 && xmas[i][j] == 'X' && xmas[i + 1][j - 1] == 'M'
                        && xmas[i + 2][j - 2] == 'A'
                        && xmas[i + 3][j - 3] == 'S') {
                    count = count + 1;
                }
                // horizontal backwards
                if (j + 3 < xmas[0].length && xmas[i][j] == 'S' && xmas[i][j + 1] == 'A'
                        && xmas[i][j + 2] == 'M'
                        && xmas[i][j + 3] == 'X') {
                    count = count + 1;
                }
                // vertical backwards
                if (i + 3 < xmas.length && xmas[i][j] == 'S' && xmas[i + 1][j] == 'A'
                        && xmas[i + 2][j] == 'M'
                        && xmas[i + 3][j] == 'X') {
                    count = count + 1;
                }
                // right diagonal backwards
                if (i + 3 < xmas.length && j + 3 < xmas[0].length && xmas[i][j] == 'S'
                        && xmas[i + 1][j + 1] == 'A' && xmas[i + 2][j + 2] == 'M'
                        && xmas[i + 3][j + 3] == 'X') {
                    count = count + 1;
                }
                // left diagonal backwards
                if (i + 3 < xmas.length && j - 3 >= 0 && xmas[i][j] == 'S' && xmas[i + 1][j - 1] == 'A'
                        && xmas[i + 2][j - 2] == 'M'
                        && xmas[i + 3][j - 3] == 'X') {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public static int countOfXmas2(char xmas[][]) {
        int count = 0;
        for (int i = 0; i < xmas.length; i++) {
            for (int j = 0; j < xmas[0].length; j++) {
                if (i + 2 < xmas.length && j + 2 < xmas[0].length && xmas[i][j] == 'M' && xmas[i + 1][j + 1] == 'A'
                        && xmas[i + 2][j + 2] == 'S' && xmas[i][j + 2] == 'S' && xmas[i + 2][j] == 'M') {
                    count = count + 1;
                }
                if (i + 2 < xmas.length && j + 2 < xmas[0].length && xmas[i][j] == 'M' && xmas[i + 1][j + 1] == 'A'
                        && xmas[i + 2][j + 2] == 'S' && xmas[i][j + 2] == 'M' && xmas[i + 2][j] == 'S') {
                    count = count + 1;
                }
                if (i + 2 < xmas.length && j + 2 < xmas[0].length && xmas[i][j] == 'S' && xmas[i + 1][j + 1] == 'A'
                        && xmas[i + 2][j + 2] == 'M' && xmas[i][j + 2] == 'S' && xmas[i + 2][j] == 'M') {
                    count = count + 1;
                }
                if (i + 2 < xmas.length && j + 2 < xmas[0].length && xmas[i][j] == 'S' && xmas[i + 1][j + 1] == 'A'
                        && xmas[i + 2][j + 2] == 'M' && xmas[i][j + 2] == 'M' && xmas[i + 2][j] == 'S') {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String inputFilePath = "./input.txt";
        String outputFilePath = "./output.txt";
        int part1 = 0;
        int part2 = 0;
        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String[] strings = input.split("\\n");
            int StringLength = strings.length;
            char xmas[][] = new char[StringLength][StringLength];
            // extracting each character of strings
            for (int i = 0; i < StringLength; i++) {
                xmas[i] = strings[i].toCharArray();
            }
            part1 = countOfXmas1(xmas);
            part2 = countOfXmas2(xmas);
            System.out.println("Count of XMAS Part 1: " + part1);
            System.out.println("Count of XMAS Part 2: " + part2);
            // output string
            String[] output = {
                    "Count of XMAS Part 1: " + part1,
                    "Count of XMAS Part 2: " + part2
            };
            Files.write(Paths.get(outputFilePath), Arrays.asList(output), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}