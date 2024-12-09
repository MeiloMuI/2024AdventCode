package org.example.day9;

import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.List;

public class QuestionHandlerForDay9 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay9() {
        this.fileProcessor = new FileProcessor();
    }

    public StringBuilder parseData(List<String> data){
        String numbers = data.get(0);
        int n = numbers.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int space = numbers.charAt(i) - '0';
            int index = -1;
            // odd -> empty, even -> data
            if(i % 2 == 0){
                index = i / 2;
            }
            while(space > 0){
                if(index == -1){
                    sb.append('.');
                } else {
                    sb.append(index);
                }
                space--;
            }
        }
        return sb;
    }

    public BigInteger solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        StringBuilder sb = parseData(data);
        int left = findMemoIndex(sb, 0);
        int right = findDataIndex(sb, sb.length()-1);
        while(left < right){
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, '.');
            left = findMemoIndex(sb, left+1);
            right = findDataIndex(sb, right-1);
        }

        int n = sb.length();
        // Get the result
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < n; i++){
            if(sb.charAt(i) == '.') break;
            BigInteger value = BigInteger.valueOf(sb.charAt(i) - '0');
            value = value.multiply(BigInteger.valueOf(i));
            result = result.add(value);
        }
        return result;
    }

    public int findMemoIndex(StringBuilder sb, int startIndex){
        while(sb.charAt(startIndex) != '.'){
            startIndex++;
        }
        return startIndex;
    }

    public int findDataIndex(StringBuilder sb, int startIndex){
        while(sb.charAt(startIndex) == '.'){
            startIndex--;
        }
        return startIndex;
    }
}
