package org.example.day8;

import org.example.util.FileProcessor;
import java.util.*;

public class QuestionHandlerForDay8 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay8() {
        this.fileProcessor = new FileProcessor();
    }

    public Map<Character, List<int[]>> getPositionsFromData(List<String> data){
        Map<Character, List<int[]>> map = new HashMap<>();
        int n = data.size();
        for(int i = 0; i < n; i++){
            String s = data.get(i);
            char[] ch = s.toCharArray();
            for(int j = 0; j < ch.length; j++){
                if(ch[j] != '.'){
                    int[] pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                    List<int[]> list = map.getOrDefault(ch[j], new ArrayList<>());
                    list.add(pos);
                    map.put(ch[j], list);
                }
            }
        }
        return map;
    }

    public int solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        
    }
}
