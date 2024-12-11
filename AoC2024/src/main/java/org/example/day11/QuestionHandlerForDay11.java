package org.example.day11;

import org.example.QuestionHandler;
import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.*;

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

    public long solvePart2(String filePath, int timesOfBlinks) {
        List<String> data = fileProcessor.readFile(filePath);
        String[] stones = data.get(0).split(" ");
        Map<String, Long> map = new HashMap<>();
        // Initialize the map
        for(String s: stones){
            map.put(s, map.getOrDefault(s, 0l) + 1);
        }
        for(int i = 0; i < timesOfBlinks; i++){
            Map<String, Long> tempMap = new HashMap<>();
            for(Map.Entry<String, Long> entry: map.entrySet()){
                String stone = entry.getKey();
                long number = entry.getValue();
                int lenOfStone = stone.length();
                if(stone.equals("0")){
                    stone = "1";
                    long prev = tempMap.getOrDefault(stone, 0l);
                    tempMap.put(stone, prev+number);
                } else if (lenOfStone % 2 == 0){
                    int middle = lenOfStone / 2;
                    String subStone1 = stone.substring(0, middle);
                    String subStone2 = stone.substring(middle);
                    // Handle subStone2
                    int valueOfStone2 = Integer.parseInt(subStone2);
                    subStone2 = valueOfStone2 + "";
                    long prev1 = tempMap.getOrDefault(subStone1, 0l);
                    tempMap.put(subStone1, prev1+number);
                    long prev2 = tempMap.getOrDefault(subStone2, 0l);
                    tempMap.put(subStone2, prev2+number);
                } else {
                    BigInteger num = new BigInteger(stone);
                    num = num.multiply(BigInteger.valueOf(2024));
                    long prev = tempMap.getOrDefault(num.toString(), 0l);
                    tempMap.put(num.toString(), prev+number);
                }
            }
            map = tempMap;
            int n = i + 1;
            System.out.println("The " + n + " Round");
        }
        long result = 0;
        for(long n: map.values()){
            result += n;
        }
        return result;
    }
}
