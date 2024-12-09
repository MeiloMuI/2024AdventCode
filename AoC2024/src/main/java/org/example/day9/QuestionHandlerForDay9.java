package org.example.day9;

import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.*;

public class QuestionHandlerForDay9 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay9() {
        this.fileProcessor = new FileProcessor();
    }

    public List<String> parseDataPart1(List<String> data){
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
        List<String> dataList = parseDataPart1(data);
        int left = findMemoIndex(dataList, 0);
        int right = findDataIndex(dataList, dataList.size()-1);
        while(left < right){
            dataList.set(left, dataList.get(right));
            dataList.set(right, ".");
            left = findMemoIndex(dataList, left+1);
            right = findDataIndex(dataList, right-1);
        }

        return getResult(dataList);
    }

    public BigInteger getResult(List<String> list){
        int n = list.size();
        // Get the result
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < n; i++){
            if(list.get(i).equals(".")) continue;
            BigInteger value = new BigInteger(list.get(i));
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

    public List<List<Integer>> parseDataPart2(List<String> data){
        String numbers = data.get(0);
        List<Integer> dataList = new ArrayList<>();
        List<Integer> memoryList = new ArrayList<>();
        int n = numbers.length();
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

    public BigInteger solvePart2(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        List<List<Integer>> lists = parseDataPart2(data);
        List<Integer> dataList = lists.get(0);
        List<Integer> memoList = lists.get(1);
        Map<Integer, List<Integer>> map = handleFragmentation(dataList, memoList);
        // Get the result
        List<String> resultList = getListAfterDefragmentation(dataList, memoList, map);
        return getResult(resultList);
    }
    public Map<Integer, List<Integer>> handleFragmentation(List<Integer> dataList, List<Integer> memoList){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = dataList.size() - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                //Find the leftmost memo, whose space is big enough
                if(memoList.get(j) >= dataList.get(i)){
                    //1. record the result into map
                    List<Integer> listsInMemo = map.getOrDefault(j, new ArrayList<>());
                    listsInMemo.add(i);
                    map.put(j, listsInMemo);
                    //2. change the space of the memo
                    memoList.set(j, memoList.get(j) - dataList.get(i));
                    //3. stop the searching
                    break;
                }
            }
        }
        return map;
    }

    public List<String> getListAfterDefragmentation(List<Integer> dataList, List<Integer> memoList, Map<Integer, List<Integer>> map){
        // Get the result
        List<String> resultList = new ArrayList<>();
        Set<Integer> dataUsed = new HashSet<>();
        for(int i = 0; i < memoList.size(); i++){
            // data
            String targetStr = "";
            if(!dataUsed.contains(i)){
                targetStr += i;
            } else {
                targetStr += ".";
            }
            addElementIntoList(resultList, dataList.get(i), targetStr);
            dataUsed.add(i);
            // data in memo
            List<Integer> dataInMemo = map.get(i);
            if(dataInMemo != null){
                for (int dataIndex : dataInMemo) {
                    int size = dataList.get(dataIndex);
                    addElementIntoList(resultList, size,dataIndex+"");
                    dataUsed.add(dataIndex);
                }
            }
            // memo left
            addElementIntoList(resultList, memoList.get(i), ".");
        }
        // Last data
        String targetStr = "";
        int lastIndex = memoList.size();
        if(!dataUsed.contains(lastIndex)){
            targetStr += lastIndex;
        } else {
            targetStr += ".";
        }
        addElementIntoList(resultList, dataList.get(lastIndex), targetStr);
        return resultList;
    }

    public void addElementIntoList(List<String> list, int size, String target){
        while(size > 0){
            list.add(target);
            size--;
        }
    }
}
