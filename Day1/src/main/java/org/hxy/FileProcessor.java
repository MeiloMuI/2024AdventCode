package org.hxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public List<List<Integer>> readFile(String filePath){
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] temp = line.split("\\s+");
                group1.add(Integer.parseInt(temp[0]));
                group2.add(Integer.parseInt(temp[1]));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(group1);
        result.add(group2);
        return result;
    }

}
