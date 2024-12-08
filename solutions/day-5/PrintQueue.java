import java.nio.file.*;
import java.util.*;

public class PrintQueue {

    // function to check whether the rule is present or not
    public static boolean isRulePresent(ArrayList<Integer> rulesList1, ArrayList<Integer> rulesList2, int page1,
            int page2) {
        boolean isRulePresent = false;
        for (int i = 0; i < rulesList1.size(); i++) {
            int rule1 = rulesList1.get(i);
            int rule2 = rulesList2.get(i);
            if (rule1 == page1 && rule2 == page2) {
                isRulePresent = true;
                return isRulePresent;
            }
        }
        return isRulePresent;
    }

    // function to add up the middle page numbers of the correct updates
    public static int sumOfMiddlePageNumbers(ArrayList<List<Integer>> correctUpdates) {
        int sum = 0;
        for (int i = 0; i < correctUpdates.size(); i++) {
            List<Integer> correctUpdate = correctUpdates.get(i);
            sum += correctUpdate.get((correctUpdate.size() / 2));
        }
        return sum;
    }

    public static void main(String[] args) {
        String inputFilePath = "./input.txt";
        String outputFilePath = "./output.txt";
        int part1 = 0;
        int part2 = 0;
        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String[] strings = input.split("\\n\\n");
            String rulesString = strings[0];
            String updatesString = strings[1];

            ArrayList<Integer> rules = new ArrayList<>();
            ArrayList<Integer> rulesList1 = new ArrayList<>();
            ArrayList<Integer> rulesList2 = new ArrayList<>();
            ArrayList<List<Integer>> correctUpdates = new ArrayList<>();
            ArrayList<List<Integer>> incorrectUpdates = new ArrayList<>();

            // separating rules
            String[] rulesArray = rulesString.split("\\n");
            for (String rule : rulesArray) {
                String[] parts = rule.split("\\|");
                for (String part : parts) {
                    rules.add(Integer.parseInt(part.trim()));
                }
            }

            // separating updates
            ArrayList<List<Integer>> updates = new ArrayList<>();
            String[] updatesArray = updatesString.split("\\n");
            for (String update : updatesArray) {
                String[] parts = update.split(",");
                List<Integer> numbers = new ArrayList<>();
                for (String part : parts) {
                    numbers.add(Integer.parseInt(part.trim())); // Convert to Integer and add to list
                }
                updates.add(numbers);
            }

            // separating the rule into two array lists
            for (int i = 0; i < rules.size(); i++) {
                if (i % 2 == 0) {
                    rulesList1.add(rules.get(i));
                } else {
                    rulesList2.add(rules.get(i));
                }
            }

            // part 1
            // traversing through each update
            for (int i = 0; i < updates.size(); i++) {
                List<Integer> innerList = updates.get(i);
                boolean correctUpdate = true;
                for (int j = 0; j < innerList.size(); j++) {
                    for (int k = j + 1; k < innerList.size(); k++) {
                        boolean pagesInCorrectOrder;
                        int page1 = innerList.get(j);
                        int page2 = innerList.get(k);
                        if (page1 != page2) {
                            pagesInCorrectOrder = isRulePresent(rulesList1, rulesList2, page1, page2);
                            if (!pagesInCorrectOrder) {
                                correctUpdate = false;
                                break;
                            }
                        }
                    }
                    if (!correctUpdate) {
                        break;
                    }
                }
                if (correctUpdate) {
                    correctUpdates.add(innerList);
                } else {
                    incorrectUpdates.add(innerList);
                }
            }

            // part 2
            // traversing through each incorrect update
            for (int i = 0; i < incorrectUpdates.size(); i++) {
                List<Integer> innerList = incorrectUpdates.get(i);
                for (int j = 0; j < innerList.size(); j++) {
                    for (int k = j + 1; k < innerList.size(); k++) {
                        boolean pagesInCorrectOrder;
                        int temp;
                        int page1 = innerList.get(j);
                        int page2 = innerList.get(k);
                        if (page1 != page2) {
                            pagesInCorrectOrder = isRulePresent(rulesList1, rulesList2, page1, page2);
                            if (!pagesInCorrectOrder) {
                                temp = page1;
                                page1 = page2;
                                page2 = temp;
                                pagesInCorrectOrder = isRulePresent(rulesList1, rulesList2, page1, page2);
                                if (pagesInCorrectOrder) {
                                    innerList.set(j, page1);
                                    innerList.set(k, page2);
                                }
                            }
                        }
                    }
                }
            }

            part1 = sumOfMiddlePageNumbers(correctUpdates);
            part2 = sumOfMiddlePageNumbers(incorrectUpdates);
            System.out.println("The total sum of the middle page numbers of the correct updates is: " + part1);
            System.out.println(
                    "The total sum of the middle page numbers after correctly re-ordering the wrong updates is: "
                            + part2);

            // output
            String[] output = {
                    "The total sum of the middle page numbers of the correct updates is: " + part1,
                    "The total sum of the middle page numbers after correctly re-ordering the wrong updates is: "
                            + part2
            };
            Files.write(Paths.get(outputFilePath), Arrays.asList(output), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}
