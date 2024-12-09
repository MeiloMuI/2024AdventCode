package org.example.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor implements org.example.FileProcessor {
    public List<String> readFile(String filePath){
        List<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                result.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
