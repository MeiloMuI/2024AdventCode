package org.example.day9;

import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class QuestionHandlerForDay8 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay8() {
        this.fileProcessor = new FileProcessor();
    }

    public List<List<Integer>> parseData(List<String> data){
        String numbers = data.get(0);
        List<Integer> dataList = new ArrayList<>();
        List<Integer> memoryList = new ArrayList<>();
        int n = data.size();
        for(int i = 0; i < n; i++){
            int space = numbers.charAt(i) - '0';
            // odd -> empty, even -> data
            if(i % 2 == 0){
                dataList.add(space);
            } else {
                memoryList.add(space);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(dataList);
        result.add(memoryList);
        return result;
    }

    public BigInteger solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        List<List<Integer>> lists = parseData(data);
        List<Integer> dataList = lists.get(0);
        List<Integer> memoList = lists.get(1);
        StringBuilder sb = new StringBuilder();
        int memoUsing = 0;
        int dataUsing = dataList.size() - 1;
        while(dataUsing > memoUsing){
            int dataSize = dataList.get(dataUsing);
            int memoSize = memoList.get(memoUsing);

            if(dataSize > memoSize){
                // fill the data into memo and find the next memo, change the dataSize

            } else if (dataSize < memoSize){
                // fill all the data into memo, find the next data, change the memoSize
            }
        }

    }
}
