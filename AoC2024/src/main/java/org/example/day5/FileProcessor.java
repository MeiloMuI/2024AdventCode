package org.example.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileProcessor {

    public List<List<Integer>> readDataFile(String filePath){
        List<List<Integer>> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] arr = line.split(",");
                int n = arr.length;
                List<Integer> list = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    list.add(Integer.parseInt(arr[i]));
                }
                result.add(list);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public Map<Integer, HashSet<Integer>> readRuleFile(String filePath){
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] arr = line.split("\\|");
                int before = Integer.parseInt(arr[0]);
                int after = Integer.parseInt(arr[1]);
                HashSet<Integer> set = map.getOrDefault(before, new HashSet<>());
                set.add(after);
                map.put(before, set);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return map;
    }

}
