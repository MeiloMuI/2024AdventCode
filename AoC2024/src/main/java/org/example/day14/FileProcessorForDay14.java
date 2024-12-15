package org.example.day14;

import org.example.util.FileProcessor;

import java.util.ArrayList;
import java.util.List;

public class FileProcessorForDay14 extends FileProcessor {

    public List<Robot> parseData(List<String> data){
        List<Robot> robots = new ArrayList<>();
        for(String s: data){
            // Get p and v
            String[] arr = s.split(" ");
            // Parse p
            String[] pos = arr[0].split(",");
            String[] temp1 = pos[0].split("=");
            // Parse v
            String[] velocity = arr[1].split(",");
            String[] temp2 = velocity[0].split("=");
            // new Robot
            robots.add(new Robot(Integer.parseInt(temp1[1]),
                    Integer.parseInt(pos[1]),
                    Integer.parseInt(temp2[1]),
                    Integer.parseInt(velocity[1])
                    ));
        }
        return robots;
    }

    public List<Robot> getRobots(String filePath){
        List<String> data = readFile(filePath);
        return parseData(data);
    }

}
