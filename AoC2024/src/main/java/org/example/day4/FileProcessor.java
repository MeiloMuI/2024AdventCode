package org.example.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileProcessor {

    public List<List<Character>> readFile(String filePath){
        List<List<Character>> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                int n = line.length();
                List<Character> list = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    list.add(line.charAt(i));
                }
                result.add(list);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
