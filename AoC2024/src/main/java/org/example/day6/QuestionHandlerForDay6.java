package org.example.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionHandlerForDay6 {
    FileProcessor fileProcessor;
    int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    char[] dirChars = new char[]{'u', 'r', 'd', 'l'};
    public QuestionHandlerForDay6() {
        fileProcessor = new FileProcessor();
    }

    public int getAnswerOfPart1(String filePath){
        List<List<Character>> data = fileProcessor.readFile(filePath);
        // Find the start position of the guard
        int[] positionOfGuardian = getPositionOfGuardian(data);
        return predictThePath(data, positionOfGuardian);
    }

    public int predictThePath(List<List<Character>> data, int[] position){
        int rows = data.size();
        int cols = data.get(0).size();
        int numOfDir = 0;
        int result = 1;
        // Initial position -> X
        data.get(position[0]).set(position[1], 'X');
        while(position[0] != 0 && position[0] != rows - 1 && position[1] != 0 && position[1] != cols - 1){
            boolean isObstruction = false;
            // Next Position
            int next0 = position[0] + dir[numOfDir][0];
            int next1 = position[1] + dir[numOfDir][1];
            // Deal with the Char: '.', '#'
            char c = data.get(next0).get(next1);
            if(c == '.'){
                data.get(next0).set(next1, 'X');
                result++;
            } else if (c == '#'){
                numOfDir = (numOfDir + 1) % 4;
                isObstruction = true;
            }
            // change the position
            if(!isObstruction){
                position[0] = next0;
                position[1] = next1;
            }
        }
        return result;
    }

    public int[] getPositionOfGuardian(List<List<Character>> data){
        int rows = data.size();
        int cols = data.get(0).size();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(data.get(i).get(j) == '^'){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int getAnswerOfPart2(String filePath){
        List<List<Character>> data = fileProcessor.readFile(filePath);
        // Find the start position of the guard
        int[] positionOfGuardian = getPositionOfGuardian(data);
        int m = data.size();
        int n = data.get(0).size();
        int result = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(data.get(i).get(j) == '.'){
                    //Test if it can be a valid position to set an obstruction
                    data.get(i).set(j, '#');
                    if(findTheValidObstruction(data, positionOfGuardian[0], positionOfGuardian[1])){
                        result++;
                    }
                    data.get(i).set(j, '.');
                }
            }
        }
        return result;
    }

    public boolean findTheValidObstruction(List<List<Character>> data, int posX, int posY){
        int rows = data.size();
        int cols = data.get(0).size();
        int numOfDir = 0;
        String[][] map = new String[rows][cols];
        for(int i = 0; i < rows; i++){
            Arrays.fill(map[i], "");
        }
        // Initial position -> X
        while(!map[posX][posY].contains(String.valueOf(dirChars[numOfDir]))){
            if(posX == 0 || posX == rows - 1 || posY == 0 || posY == cols - 1) return false;
            // save the direction of current position
            map[posX][posY] += dirChars[numOfDir];
            boolean isObstruction = false;
            // Next Position
            int next0 = posX + dir[numOfDir][0];
            int next1 = posY + dir[numOfDir][1];
            // Deal with the Char: '.', '#'
            char c = data.get(next0).get(next1);
            if (c == '#'){
                numOfDir = (numOfDir + 1) % 4;
                isObstruction = true;
            }
            // change the position
            if(!isObstruction){
                posX = next0;
                posY = next1;
            }
        }
        return true;
    }
}
