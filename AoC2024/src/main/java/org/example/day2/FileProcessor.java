package org.example.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public List<List<Integer>> readFile(String filePath){
        List<List<Integer>> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] temp = line.split("\\s+");
                int n = temp.length;
                List<Integer> level = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    level.add(Integer.parseInt(temp[i]));
                }
                result.add(level);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
