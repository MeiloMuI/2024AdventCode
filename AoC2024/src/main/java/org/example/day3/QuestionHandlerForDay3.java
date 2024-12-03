package org.example.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionHandlerForDay3 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay3() {
        fileProcessor = new FileProcessor();
    }

    public int getAnswerOfPart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        int result = 0;
        int n = data.size();
        String regex = "mul\\((\\d+),\\s*(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        for(int i = 0; i < n; i++){
            Matcher matcher = pattern.matcher(data.get(i));
            while(matcher.find()){
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));
                result += num1*num2;
            }
        }
        return result;
    }

    public long getAnswerOfPart2(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        long result = 0;
        List<List<String>> commands = getCommands(data);
        result = handleCommand(commands);
        return result;
    }

    public long handleCommand(List<List<String>> commands){
        boolean canAdd = true;
        long result = 0;
        for(List<String> command: commands){
            for(String s: command){
                if(s.equals("do()")){
                    canAdd = true;
                } else if(s.equals("don't()")){
                    canAdd = false;
                } else {
                    if(canAdd){
                        result += getNumber(s);
                    }
                }
            }
        }
        return result;
    }

    public long getNumber(String s){
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        long result = 1;
        while(matcher.find()) {
            result *= Long.parseLong(matcher.group(0));
        }
        return result;
    }

    public List<List<String>> getCommands(List<String> data){
        int n = data.size();
        String regex = "do\\(\\)|don't\\(\\)|mul\\((\\d+),\\s*(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        List<List<String>> commands = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Matcher matcher = pattern.matcher(data.get(i));
            List<String> command = new ArrayList<>();
            while(matcher.find()){
                command.add(matcher.group(0));
            }
            commands.add(command);
        }
        return commands;
    }
}
