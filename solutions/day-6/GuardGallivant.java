import java.nio.file.*;
import java.util.*;

public class GuardGallivant {

    public static int distanceCovered(char[][] path) {
        int distanceCovered = 1;
        boolean isExit = false;
        int row = 0;
        int x = 0;
        int y = 0;
        ArrayList<List<Integer>> positions = new ArrayList<>();
        while (row < path.length) {
            int col = 0;
            while (col < path.length) {
                // up
                if (path[row][col] == '^') {
                    List<Integer> pos = new ArrayList<>();
                    x = row;
                    y = col;
                    boolean posNotInTheList = true;
                    pos.add(x);
                    pos.add(y);
                    positions.add(pos);
                    if (row - 1 >= 0) {
                        if (path[row - 1][col] != '#') {
                            // swapping . and ^
                            char temp = path[row - 1][col];
                            path[row - 1][col] = path[row][col];
                            path[row][col] = temp;
                            row = x - 1;
                            col = y;
                            // checking for distinct position
                            for (int i = 0; i < positions.size(); i++) {
                                List<Integer> innerList = positions.get(i);
                                int prevRow = innerList.get(0);
                                int prevCol = innerList.get(1);
                                if (row == prevRow && col == prevCol) {
                                    posNotInTheList = false;
                                }
                            }
                            if (posNotInTheList) {
                                distanceCovered++;
                            }
                            continue;
                        } else {
                            // changing the direction
                            path[row][col] = '>';
                            row = 0;
                            col = 0;
                        }
                    } else {
                        isExit = true;
                        break;
                    }
                }
                // right
                else if (path[row][col] == '>') {
                    List<Integer> pos = new ArrayList<>();
                    x = row;
                    y = col;
                    boolean posNotInTheList = true;
                    pos.add(x);
                    pos.add(y);
                    positions.add(pos);
                    if (col + 1 < path.length) {
                        if (path[row][col + 1] != '#') {
                            // swapping . and >
                            char temp = path[row][col + 1];
                            path[row][col + 1] = path[row][col];
                            path[row][col] = temp;
                            row = x;
                            col = y + 1;
                            // checking for distinct position
                            for (int i = 0; i < positions.size(); i++) {
                                List<Integer> innerList = positions.get(i);
                                int prevRow = innerList.get(0);
                                int prevCol = innerList.get(1);
                                if (row == prevRow && col == prevCol) {
                                    posNotInTheList = false;
                                }
                            }
                            if (posNotInTheList) {
                                distanceCovered++;
                            }
                            continue;
                        } else {
                            // changing the direction
                            path[row][col] = 'v';
                            row = 0;
                            col = 0;
                        }
                    } else {
                        isExit = true;
                        break;
                    }
                }
                // down
                else if (path[row][col] == 'v') {
                    List<Integer> pos = new ArrayList<>();
                    x = row;
                    y = col;
                    boolean posNotInTheList = true;
                    pos.add(x);
                    pos.add(y);
                    positions.add(pos);
                    if (row + 1 < path.length) {
                        // swapping . and v
                        if (path[row + 1][col] != '#') {
                            char temp = path[row + 1][col];
                            path[row + 1][col] = path[row][col];
                            path[row][col] = temp;
                            row = x + 1;
                            col = y;
                            // checking for distinct position
                            for (int i = 0; i < positions.size(); i++) {
                                List<Integer> innerList = positions.get(i);
                                int prevRow = innerList.get(0);
                                int prevCol = innerList.get(1);
                                if (row == prevRow && col == prevCol) {
                                    posNotInTheList = false;
                                }
                            }
                            if (posNotInTheList) {
                                distanceCovered++;
                            }
                            continue;
                        } else {
                            // changing the direction
                            path[row][col] = '<';
                            row = 0;
                            col = 0;
                        }
                    } else {
                        isExit = true;
                        break;
                    }
                }
                // left
                else if (path[row][col] == '<') {
                    List<Integer> pos = new ArrayList<>();
                    x = row;
                    y = col;
                    boolean posNotInTheList = true;
                    pos.add(x);
                    pos.add(y);
                    positions.add(pos);
                    if (col - 1 >= 0) {
                        if (path[row][col - 1] != '#') {
                            // swapping . and <
                            char temp = path[row][col - 1];
                            path[row][col - 1] = path[row][col];
                            path[row][col] = temp;
                            row = x;
                            col = y - 1;
                            // checking for distinct position
                            for (int i = 0; i < positions.size(); i++) {
                                List<Integer> innerList = positions.get(i);
                                int prevRow = innerList.get(0);
                                int prevCol = innerList.get(1);
                                if (row == prevRow && col == prevCol) {
                                    posNotInTheList = false;
                                }
                            }
                            if (posNotInTheList) {
                                distanceCovered++;
                            }
                            continue;
                        } else {
                            // changing the direction
                            path[row][col] = '^';
                            row = 0;
                            col = 0;
                        }
                    } else {
                        isExit = true;
                        break;
                    }
                }
                col++;
            }
            // exiting when the guard reaches the end
            if (isExit) {
                break;
            }
            row++;
        }
        return distanceCovered;
    }

    public static void main(String[] args) {
        String inputFilePath = "./input.txt";
        // String outputFilePath = "./output.txt";
        int part1;
        int part2;
        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            // System.out.println(input);
            String[] strings = input.split("\\n");

            int StringLength = strings.length;
            char path[][] = new char[StringLength][StringLength];
            // extracting each character of strings
            for (int i = 0; i < StringLength; i++) {
                path[i] = strings[i].toCharArray();
            }
            part1 = distanceCovered(path);
            System.out.println("Total distinct positions covered: " + part1);
        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}
