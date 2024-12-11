package org.example.day11;

import org.example.QuestionHandler;
import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionHandlerForDay11 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay11() {
        this.fileProcessor = new FileProcessor();
    }

    public int solvePart1(String filePath, int timesOfBlinks) {
        List<String> data = fileProcessor.readFile(filePath);
        String[] stones = data.get(0).split(" ");
        List<String> results = new ArrayList<>(Arrays.asList(stones));
        for(int i = 0; i < timesOfBlinks; i++){
            for(int j = results.size() - 1; j >= 0; j--){
                String stone = results.get(j);
                int lenOfStone = stone.length();
                if(stone.equals("0")){
                    results.set(j, "1");
                } else if (lenOfStone % 2 == 0){
                    int middle = lenOfStone / 2;
                    String subStone1 = stone.substring(0, middle);
                    String subStone2 = stone.substring(middle);
                    // Handle subStone2
                    int valueOfStone2 = Integer.parseInt(subStone2);
                    subStone2 = valueOfStone2 + "";
                    results.set(j, subStone1);
                    results.add(j+1, subStone2);
                } else {
                    BigInteger temp = new BigInteger(stone);
                    temp = temp.multiply(BigInteger.valueOf(2024));
                    results.set(j, temp.toString());
                }
            }
            int n = i + 1;
            System.out.println("The " + n + " Round");
        }
        return results.size();
    }

    public int solvePart2(String filePath) {
        return 0;
    }
}
