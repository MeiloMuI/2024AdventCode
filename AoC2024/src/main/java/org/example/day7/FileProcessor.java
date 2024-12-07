package org.example.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileProcessor {

    public Map<Long, List<Long>> readFile(String filePath){
        Map<Long, List<Long>> result = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                int n = line.length();
                String[] numberGroup = line.split(":");
                String[] numbers = numberGroup[1].trim().split("\\s+");
                List<Long> list = new ArrayList<>();
                for (String number : numbers) {
                    long i = Long.parseLong(number);
                    list.add(i);
                }
                result.put(Long.parseLong(numberGroup[0]), list);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
