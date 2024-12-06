package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        try {
            Path path = Path.of("src/day02/input.txt");
            var lines = Files.readAllLines(path);

            int safeCountPartOne = 0;
            int safeCountPartTwo = 0;

            for (String line : lines) {
                if (isLineSafe(line)) {
                    safeCountPartOne++;
                }
                if (isLineSafe(line) || removeOneAndBeSafe(line)) {
                    safeCountPartTwo++;
                }
            }

            System.out.println(safeCountPartOne);
            System.out.println(safeCountPartTwo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isLineSafe(String strNumbers) {
        String[] numbersArray = strNumbers.split("\\s");
        boolean lineAscending = false;
        boolean lineDescending = false;

        for (int i = 1; i < numbersArray.length; i++) {
            int current = Integer.parseInt(numbersArray[i]);
            int previous = Integer.parseInt(numbersArray[i - 1]);

            int diff = current - previous;

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }
            if (diff > 0) {
                lineAscending = true;
            } else if (diff < 0) {
                lineDescending = true;
            }
            if (lineAscending && lineDescending) {
                return false;
            }
        }
        return true;
    }

    private static boolean removeOneAndBeSafe(String strNumbers) {
        String[] numbers = strNumbers.split("\\s");

        for (int i = 0; i < numbers.length; i++) {
            StringBuilder modifiedLine = new StringBuilder();
            for (int j = 0; j < numbers.length; j++) {
                if (j != i) {
                    modifiedLine.append(numbers[j]).append(" ");
                }
            }
            if (isLineSafe(modifiedLine.toString().trim())) {
                return true;
            }
        }
        return false;
    }
}
