package org.example.day7;
import java.math.BigInteger;
import java.util.*;
public class QuestionHandlerForDay7 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay7() {
        this.fileProcessor = new FileProcessor();
    }

    public long getAnswerOfPart1(String filePath){
        Map<Long, List<Long>> data = fileProcessor.readFile(filePath);
        long result = 0;
        for(Map.Entry<Long, List<Long>> entry: data.entrySet()){
            List<Long> list = entry.getValue();
            if(checkNumbersHaveValidResult(entry.getKey(), entry.getValue(), list.size() - 1)){
                System.out.println(entry.getKey() + " : true");
                result += entry.getKey();
            }
        }
        return result;
    }

    public boolean checkNumbersHaveValidResult(long target, List<Long> list, int index){
        if(index == -1){
            return target == 0;
        }
        if(target <= 0) return false;
        return checkNumbersHaveValidResult(target - list.get(index), list, index-1)
                || (target % list.get(index) == 0 && checkNumbersHaveValidResult(target/list.get(index), list, index-1));
    }

}
