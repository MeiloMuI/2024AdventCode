package org.example.day5;

import java.util.*;

public class QuestionHandlerForDay5 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay5() {
        fileProcessor = new FileProcessor();
    }

    public int getAnswerOfPart1(String ruleFilePath, String dataFilePath){
        Map<Integer, HashSet<Integer>> map = fileProcessor.readRuleFile(ruleFilePath);
        List<List<Integer>> data = fileProcessor.readDataFile(dataFilePath);

        int result = 0;

        for(List<Integer> list: data){
            if(checkValidityOfData(map, list)){
                // Get the mid
                result += getMid(list);
            }
        }
        return result;
    }

    public int getAnswerOfPart2(String ruleFilePath, String dataFilePath){
        Map<Integer, HashSet<Integer>> map = fileProcessor.readRuleFile(ruleFilePath);
        List<List<Integer>> data = fileProcessor.readDataFile(dataFilePath);

        int result = 0;

        for(List<Integer> list: data){
            if(!checkValidityOfData(map, list)){
                // Get the mid
                result += getMid(list);
            }
        }
        return result;
    }

    public boolean checkValidityOfData(Map<Integer, HashSet<Integer>> map, List<Integer> oneRowData){
        for(int i = 0; i < oneRowData.size(); i++){
            HashSet<Integer> duplicated = map.get(oneRowData.get(i));
            if(duplicated == null) continue;
            for(int j = 0; j < i; j++){
                if(duplicated.contains(oneRowData.get(j))){
                    return false;
                }
            }
        }
        return true;
    }

    public int getMid(List<Integer> data){
        int n = data.size();
        int left = (n - 1) / 2;
        int right = n / 2;
        return (data.get(left) + data.get(right)) / 2;
    }
}
