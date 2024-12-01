package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static int totalDistance = 0;

    private static int similarityScore = 0;

    public static void main(String[] args) {

        try {

            Path path = Path.of("src/day01/input.txt");
            List<String> lines = Files.readAllLines(path);


            List<Integer> leftSideList = new ArrayList<>();
            List<Integer> rightSideList = new ArrayList<>();


            for (String eachLine : lines) {
                String[] numbers = eachLine.split("\\s+");

                if (numbers.length == 2) {
                    leftSideList.add(Integer.parseInt(numbers[0]));
                    rightSideList.add(Integer.parseInt(numbers[1]));

                } else {
                    System.out.println("Fel format" + eachLine);
                }

            }

            Collections.sort(leftSideList);
            Collections.sort(rightSideList);


            for (int i = 0; i < leftSideList.size(); i++) {
                int difference = Math.abs(leftSideList.get(i) - rightSideList.get(i));
                totalDistance += difference;
                int currentLeft = leftSideList.get(i);


                int matchingOccurences = 0;
                for (int j = 0; j < rightSideList.size(); j++) {
                    if (currentLeft == rightSideList.get(j)) {
                        matchingOccurences++;
                    }
                }
                similarityScore += currentLeft * matchingOccurences;
            }


        } catch (IOException e) {
            System.out.println("fel vid inläsning av fil." + e.getMessage());
        }
        System.out.println(totalDistance);
        System.out.println(similarityScore);
    }
}

