package org.example.day7;
import org.example.util.FileProcessor;

import java.math.BigInteger;
import java.util.*;
public class QuestionHandlerForDay7 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay7() {
        this.fileProcessor = new FileProcessor();
    }

    public BigInteger getAnswerOfPart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        BigInteger sum = BigInteger.ZERO;
        for(String line: data){
            String[] arr = line.split(":");
            BigInteger result = new BigInteger(arr[0]);
            String[] operandStr = arr[1].trim().split(" ");
            int[] operands = new int[operandStr.length];
            for(int i = 0; i < operandStr.length; i++){
                operands[i] = Integer.parseInt(operandStr[i]);
            }
            BigInteger count = BigInteger.valueOf(operands[0]);
            boolean isValid = checkNumbersHaveValidResult(operands,1,count,result);
            if(isValid){
                sum = sum.add(result);
            }
        }
        return sum;
    }

    public BigInteger getAnswerOfPart2(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        BigInteger sum = BigInteger.ZERO;
        for(String line: data){
            String[] arr = line.split(":");
            BigInteger result = new BigInteger(arr[0]);
            String[] operandStr = arr[1].trim().split(" ");
            int[] operands = new int[operandStr.length];
            for(int i = 0; i < operandStr.length; i++){
                operands[i] = Integer.parseInt(operandStr[i]);
            }
            BigInteger count = BigInteger.valueOf(operands[0]);
            boolean isValid = checkNumbersHaveValidResultPart2(operands,1,count,result);
            if(isValid){
                sum = sum.add(result);
            }
        }
        return sum;
    }

    public boolean checkNumbersHaveValidResultPart2(int[] operands, int index, BigInteger count, BigInteger result){
        if(index == operands.length){
            return count.equals(result);
        }
        BigInteger sum1 = count.add(BigInteger.valueOf(operands[index]));
        BigInteger sum2 = count.multiply(BigInteger.valueOf(operands[index]));
        String temp = count.toString() + operands[index];
        BigInteger sum3 = new BigInteger(temp);
        index++;
        boolean check1 = checkNumbersHaveValidResultPart2(operands, index, sum1, result);
        boolean check2 = checkNumbersHaveValidResultPart2(operands, index, sum2, result);
        boolean check3 = checkNumbersHaveValidResultPart2(operands, index, sum3, result);
        return check1 || check2 || check3;
    }

    public boolean checkNumbersHaveValidResult(int[] operands, int index, BigInteger count, BigInteger result){
        if(index == operands.length){
            return count.equals(result);
        }
        BigInteger sum1 = count.add(BigInteger.valueOf(operands[index]));
        BigInteger sum2 = count.multiply(BigInteger.valueOf(operands[index]));
        index++;
        boolean check1 = checkNumbersHaveValidResult(operands, index, sum1, result);
        boolean check2 = checkNumbersHaveValidResult(operands, index, sum2, result);
        return check1 || check2;
    }

}
