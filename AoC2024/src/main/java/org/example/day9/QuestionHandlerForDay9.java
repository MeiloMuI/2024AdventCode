package org.example.day9;

import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class QuestionHandlerForDay9 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay9() {
        this.fileProcessor = new FileProcessor();
    }

    public List<String> parseData(List<String> data){
        String numbers = data.get(0);
        int n = numbers.length();
        List<String> result = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int space = numbers.charAt(i) - '0';
            int index = -1;
            // odd -> empty, even -> data
            if(i % 2 == 0){
                index = i / 2;
            }
            while(space > 0){
                if(index == -1){
                    result.add(".");
                } else {
                    result.add(index+"");
                }
                space--;
            }
        }
        return result;
    }

    public BigInteger solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        List<String> dataList = parseData(data);
        int left = findMemoIndex(dataList, 0);
        int right = findDataIndex(dataList, dataList.size()-1);
        while(left < right){
            dataList.set(left, dataList.get(right));
            dataList.set(right, ".");
            left = findMemoIndex(dataList, left+1);
            right = findDataIndex(dataList, right-1);
        }

        int n = dataList.size();
        // Get the result
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < n; i++){
            if(dataList.get(i).equals(".")) break;
            BigInteger value = new BigInteger(dataList.get(i));
            value = value.multiply(BigInteger.valueOf(i));
            result = result.add(value);
        }
        return result;
    }

    public int findMemoIndex(List<String> list, int startIndex){
        while(!list.get(startIndex).equals(".")){
            startIndex++;
        }
        return startIndex;
    }

    public int findDataIndex(List<String> list, int startIndex){
        while(list.get(startIndex).equals(".")){
            startIndex--;
        }
        return startIndex;
    }
}
