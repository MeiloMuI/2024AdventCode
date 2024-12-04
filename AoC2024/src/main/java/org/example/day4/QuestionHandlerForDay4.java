package org.example.day4;

import java.util.List;

public class QuestionHandlerForDay4 {
    FileProcessor fileProcessor = new FileProcessor();

    public int getAnswerOfPart1(String filePath){
        List<List<Character>> data = fileProcessor.readFile(filePath);
        int result = 0;
        for(int i = 0; i < data.size(); i++){
            List<Character> list = data.get(i);
            for(int j = 0; j < list.size(); j++){
                if(list.get(j) == 'X'){
                    result += isXMAS(data, i, j);
                }
            }
        }
        return result;

    }

    public int isXMAS(List<List<Character>> data, int x, int y){
        int result = 0;
        String target = "XMAS";
        //horizontal
        if(y >= 3){
            //get the string
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                sb.append(data.get(x).get(y-i));
            }
            if(sb.toString().equals(target)){
                result++;
            }
        }
        if(y < data.get(x).size() - 3){
            //get the string
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                sb.append(data.get(x).get(y+i));
            }
            if(sb.toString().equals(target)){
                result++;
            }
        }
        //vertical
        if(x >= 3){
            //get the string
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < 4; i++){
                sb.append(data.get(x-i).get(y));

            }
            if(sb.toString().equals(target)){
                result++;
            }
        }
        if(x < data.size() - 3){
            //get the string
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < 4; i++){
                sb.append(data.get(x+i).get(y));

            }
            if(sb.toString().equals(target)){
                result++;
            }
        }

        //diagonal
        // top-left
        if(x >= 3 && y >= 3){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                sb.append(data.get(x-i).get(y-i));
            }
            if(sb.toString().equals(target)) result++;
        }
        //top-right
        if(x >= 3 && y < data.get(x).size() - 3){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                sb.append(data.get(x-i).get(y+i));
            }
            if(sb.toString().equals(target)) result++;
        }
        //bot-left
        if(x < data.size() - 3 && y >= 3){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                sb.append(data.get(x+i).get(y-i));
            }
            if(sb.toString().equals(target)) result++;
        }
        //bot-right
        if(x < data.size() - 3 && y < data.get(x).size() - 3){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++){
                sb.append(data.get(x+i).get(y+i));
            }
            if(sb.toString().equals(target)) result++;
        }
        return result;
    }

    public int getAnswerOfPart2(String filePath){
        List<List<Character>> data = fileProcessor.readFile(filePath);
        int result = 0;
        for(int i = 0; i < data.size(); i++){
            List<Character> list = data.get(i);
            for(int j = 0; j < list.size(); j++){
                if(list.get(j) == 'A' && isX_MAS(data, i, j)){
                    result++;
                }
            }
        }
        return result;
    }

    public boolean isX_MAS(List<List<Character>> data, int x, int y){
        String target = "MAS";
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if(x >= 1 && y >= 1 && x < data.size() - 1 && y < data.get(x).size() - 1){
            for(int i = -1; i <= 1; i++){
                sb1.append(data.get(x+i).get(y+i));
                sb2.append(data.get(x-i).get(y+i));
            }
        }
        // Match the X-MAS
        boolean isMAS1 = sb1.toString().equals(target) || sb1.reverse().toString().equals(target);
        boolean isMAS2 = sb2.toString().equals(target) || sb2.reverse().toString().equals(target);
        return isMAS1 && isMAS2;
    }
}
