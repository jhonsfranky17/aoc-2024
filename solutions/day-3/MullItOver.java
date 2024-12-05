
// import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class MullItOver {

    // function to calculate total sum of the products
    public static int calculateSumOfProducts(ArrayList<String> mulPatterns) {
        int totalSum = 0;
        for (String mul : mulPatterns) {
            // remove "mul(" and ")"
            String numbersOnly = mul.replaceAll("mul\\(", "").replaceAll("\\)", "");
            String[] num = numbersOnly.split(",");
            int num1 = Integer.parseInt(num[0]);
            int num2 = Integer.parseInt(num[1]);
            totalSum += num1 * num2;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        String inputFilePath = "./input.txt";
        String outputFilePath = "./output.txt";
        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // regex pattern
            Pattern mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            Pattern controlPattern = Pattern.compile("mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\)");

            // matcher to find matches in the input string
            Matcher mulMatcher = mulPattern.matcher(input);
            Matcher controlMatcher = controlPattern.matcher(input);

            // array list to store matches
            ArrayList<String> mulPatterns = new ArrayList<>();
            ArrayList<String> doDontMulPatterns = new ArrayList<>();
            ArrayList<String> doMulPatterns = new ArrayList<>();

            // part1
            while (mulMatcher.find()) {
                mulPatterns.add(mulMatcher.group());
            }

            // part2
            while (controlMatcher.find()) {
                doDontMulPatterns.add(controlMatcher.group());
            }

            // including mul(int,int) after do()
            boolean isMulEnabled = true;
            for (String doDontMul : doDontMulPatterns) {
                if (!doDontMul.equals("do()") && !doDontMul.equals("don't()") && isMulEnabled) {
                    doMulPatterns.add(doDontMul);
                } else if (doDontMul.equals("do()")) {
                    isMulEnabled = true;
                } else if (doDontMul.equals("don't()")) {
                    isMulEnabled = false;
                }
            }

            // output string
            String[] output = {
                    "Total sum of all the products of mul(x,y): " + calculateSumOfProducts(mulPatterns),
                    "Total sum of all the products of mul(x,y) after do(): " + calculateSumOfProducts(doMulPatterns)
            };
            Files.write(Paths.get(outputFilePath), Arrays.asList(output), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}