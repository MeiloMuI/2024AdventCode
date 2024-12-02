package org.example;

import java.util.ArrayList;
import java.util.List;

public class QuestionHandler {
    FileProcessor fileProcessor;

    public QuestionHandler() {
        fileProcessor = new FileProcessor();
    }

    public int getAnswerOfPart1(String filePath){
        List<List<Integer>> data = fileProcessor.readFile(filePath);
        int result = 0;

        for(List<Integer> list: data){
            if(isLevelSafe(list)){
                result++;
            }
        }

        return result;
    }

    public int getAnswerOfPart2(String filePath){
        List<List<Integer>> data = fileProcessor.readFile(filePath);
        int result = 0;

        for(List<Integer> list: data){
            if(isLevelSafeAfterDeleteOneElement(list)){
                result++;
            }
        }

        return result;
    }

    public boolean isLevelSafe(List<Integer> level){
        int n = level.size();
        if(n <= 1) return true;
        int gap = 0;
        for(int i = 1; i < n; i++){
            int tempGap = level.get(i) - level.get(i - 1);
            if(Math.abs(tempGap) > 3 || Math.abs(tempGap) < 1) return false;
            if(tempGap * gap < 0) return false;
            gap = tempGap;
        }
        return true;
    }

    public boolean isLevelSafeAfterDeleteOneElement(List<Integer> level){
        int n = level.size();
        if(n <= 2) return true;
        List<Integer> gaps = new ArrayList<>();
        for(int i = 1; i < n; i++){
            gaps.add(level.get(i) - level.get(i - 1));
        }
        int prev = gaps.get(0);
        for(int i = 1; i < n - 1; i++){
            int curr = gaps.get(i);
            if(Math.abs(curr) > 3 || Math.abs(curr) < 1 || prev * curr <= 0) {
                if(consider(level, i) || consider(level, i - 1) || consider(level, i + 1)){
                    return true;
                } else {
                    return false;
                }
            }
            prev = curr;
        }
        return true;
    }

    public boolean consider(List<Integer> level, int indexShouldBeDeleted){
        int temp = level.remove(indexShouldBeDeleted);
        boolean result = isLevelSafe(level);
        level.add(indexShouldBeDeleted, temp);
        return result;
    }
}
