package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        try {
            int totalSumPart1 = 0;
            int totalSumPart2 = 0;
            boolean isDo = true;

            Path path = Path.of("src/day03/input.txt");
            String file = Files.readString(path);

            String regex = "(mul\\((\\d+),\\s*(\\d+)\\))|(do\\(\\))|(don't\\(\\))";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file);


            while (matcher.find()) {
                if (matcher.group(2) != null && matcher.group(3) != null) {
                    int a = Integer.parseInt(matcher.group(2));
                    int b = Integer.parseInt(matcher.group(3));
                    totalSumPart1 += a * b;
                    if (isDo) {
                        totalSumPart2 += a * b;
                    }

                } else if (matcher.group().equals("do()")) {
                    isDo = true;
                } else if (matcher.group().equals("don't()")) {
                    isDo = false;
                }
            }

            System.out.println(totalSumPart1);
            System.out.println(totalSumPart2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
