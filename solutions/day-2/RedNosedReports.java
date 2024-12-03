import java.io.*;
import java.util.*;

public class RedNosedReports {

    // checking if the report is completely adhering to the decreasing pattern rules
    public static int isDecreasingSafeReport(ArrayList<Integer> list) {
        // flag to confirm if the report is safe or not
        boolean isSafe = true;
        for (int i = 0; i < list.size() - 1; i++) {
            int num1 = list.get(i);
            int num2 = list.get(i + 1);
            int difference = num1 - num2;
            if ((difference > 0) && (difference <= 3)) {
                continue;
            } else {
                isSafe = false;
                break;
            }
        }
        // checking if the isSafe is true after all the iterations
        if (isSafe) {
            return 1;
        } else {
            return 0;
        }
    }

    // checking if the report is completely adhering to the increasing pattern rules
    public static int isIncreasingSafeReport(ArrayList<Integer> list) {

        // flag to confirm if the report is safe or not
        boolean isSafe = true;
        for (int i = 0; i < list.size() - 1; i++) {
            int num1 = list.get(i);
            int num2 = list.get(i + 1);
            int difference = num1 - num2;
            if ((difference < 0) && (Math.abs(difference) <= 3)) {
                continue;
            } else {
                isSafe = false;
                break;
            }
        }
        // checking if the isSafe is true after all the iterations
        if (isSafe) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        // counter for safe reports
        int safeCount = 0;
        int prevSafeCount = 0;
        // input and output file paths
        String inputFilePath = "./input.txt";
        String outputFilePath = "./output.txt";
        // reading input and writing output
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            // reading one line at a time from the input file
            while ((line = br.readLine()) != null) {
                // creating an arraylist to store numbers from the current line
                ArrayList<Integer> numbers = new ArrayList<>();
                // splitting the line based on whitespace
                String[] parts = line.split("\\s+");
                // looping through each string
                for (String part : parts) {
                    try {
                        // parsing and adding each number to the arraylist
                        numbers.add(Integer.parseInt(part));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number skipped: " + part);
                    }
                }
                prevSafeCount = safeCount;
                safeCount += isDecreasingSafeReport(numbers);
                safeCount += isIncreasingSafeReport(numbers);
                // checking if previous safeCount is unchanged
                if (safeCount == prevSafeCount) {
                    for (int i = 0; i < numbers.size(); i++) {
                        // creating a copy of the arraylist
                        ArrayList<Integer> copyNumbers = new ArrayList<>(numbers);
                        // removing the ith index iteratively until the unsafe report becomes safe
                        copyNumbers.remove(i);
                        safeCount += isDecreasingSafeReport(copyNumbers);
                        safeCount += isIncreasingSafeReport(copyNumbers);
                        // checking if the safeCount is increased or not
                        if (safeCount != prevSafeCount)
                            break;
                    }
                }
            }
            // printing count of safe reports
            System.out.println("Total Safe Reports: " + safeCount);
            // writing the output to a file
            bw.write("Total Safe Reports: " + safeCount);
            // adding a new line
            bw.newLine();
            // closing the file
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}