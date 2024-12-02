import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class HistorianHysteria {

    public static void main(String[] args) {

        int difference, count, similarity;
        difference = count = similarity = 0;
        String inputFilePath = "solutions/day-1/input.txt";
        String outputFilePath = "solutions/day-1/output.txt";
        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String[] numbers = input.split("\\s+");

            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();

            for (int i = 0; i < numbers.length; i++) {
                int num = Integer.parseInt(numbers[i]);
                if (i % 2 == 0) {
                    list1.add(num);
                } else {
                    list2.add(num);
                }
            }

            // sorting lists
            Collections.sort(list1);
            Collections.sort(list2);

            // finding the total distance
            for (int i = 0; i < list1.size(); i++) {
                difference += Math.abs(list1.get(i) - list2.get(i));
            }
            System.out.println("Total Distance: " + difference);

            // finding the similarity
            for (int i = 0; i < list1.size(); i++) {
                int num1 = list1.get(i);
                for (int j = 0; j < list2.size(); j++) {
                    int num2 = list2.get(j);
                    if (num1 == num2) {
                        count++;
                    }
                }
                similarity += num1 * count;
                count = 0;
            }
            System.out.println("Similarity: " + similarity);
            // output string
            String[] output = {
                    "Total Distance: " + difference,
                    "Similarity: " + similarity
            };
            Files.write(Paths.get(outputFilePath), Arrays.asList(output), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.out.println("An error occurred:" + e.getMessage());
            e.printStackTrace();
        }
    }
}