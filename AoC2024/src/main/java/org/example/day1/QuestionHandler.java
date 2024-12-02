package org.example.day1;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionHandler {

    FileProcessor fileProcessor;

    public QuestionHandler() {
        this.fileProcessor = new FileProcessor();
    }

    public int getAnswerOfPart1(String filePath){
        List<List<Integer>> list = fileProcessor.readFile(filePath);
        List<Integer> group1 = list.get(0);
        List<Integer> group2 = list.get(1);
        Collections.sort(group1);
        Collections.sort(group2);
        int n = group1.size();
        int result = 0;
        for(int i = 0; i < n; i++){
            result += Math.abs(group1.get(i) - group2.get(i));
        }
        return result;
    }

    public int getAnswerOfPart2(String filePath){
        List<List<Integer>> list = fileProcessor.readFile(filePath);
        List<Integer> group1 = list.get(0);
        List<Integer> group2 = list.get(1);
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: group2){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for(int num: group1){
            result += num * map.getOrDefault(num, 0);
        }
        return result;
    }

}
