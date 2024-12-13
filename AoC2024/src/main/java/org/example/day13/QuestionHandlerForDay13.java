package org.example.day13;

import org.example.util.FileProcessor;

import java.util.ArrayList;
import java.util.List;

public class QuestionHandlerForDay13 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay13() {
        this.fileProcessor = new FileProcessor();
    }

    private List<Machine> parseData(List<String> data){
        List<Machine> lists = new ArrayList<>();
        data.add(""); // Make the data and be groups of four
        int n = data.size();
        int i = 0;
        while(i < n){
            // Button A

            i++;
            // Button B

            i++;
            // Prize

            i++;
            // Skip one line
            i++;
        }

        return lists;
    }

    private Button generateButtonFromString(String s){
        
    }

    public int solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);


        return 0;
    }

    public int solvePart2(String filePath){

        return 0;
    }
}
