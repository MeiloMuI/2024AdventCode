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
                    // Record the position into the map
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
        boolean[][] visited = new boolean[data.size()][data.get(0).length()];
        Map<Character, List<int[]>> dataMap = getPositionsFromData(data);
        int result = 0;
        for(List<int[]> positionsOfSameCharacter: dataMap.values()){
            int n = positionsOfSameCharacter.size();
            for(int i = 0; i < n; i++){
                int[] pos1 = positionsOfSameCharacter.get(i);
                for(int j = i + 1; j < n; j++){
                    // Get the distance of gap
                    int[] pos2 = positionsOfSameCharacter.get(j);
                    int gap1 = pos2[0] - pos1[0];
                    int gap2 = pos2[1] - pos1[1];
                    // pos1 - gap1, gap2 && pos2 + gap1, gap2 (check the boundary before occupied or not)
                    int[] antinode1 = new int[]{pos1[0] - gap1, pos1[1] - gap2};
                    int[] antinode2 = new int[]{pos2[0] + gap1, pos2[1] + gap2};
                    if(checkIsValidPosition(visited, antinode1)){
                        result++;
                        visited[antinode1[0]][antinode1[1]] = true;
                    }
                    if(checkIsValidPosition(visited, antinode2)){
                        result++;
                        visited[antinode2[0]][antinode2[1]] = true;
                    }
                }
            }
        }
        return result;
    }

    public int solvePart2(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        boolean[][] visited = new boolean[data.size()][data.get(0).length()];
        Map<Character, List<int[]>> dataMap = getPositionsFromData(data);

        for(List<int[]> positionsOfSameCharacter: dataMap.values()){
            int n = positionsOfSameCharacter.size();
            for(int i = 0; i < n; i++){
                int[] pos1 = positionsOfSameCharacter.get(i);
                for(int j = i + 1; j < n; j++){
                    // Get the distance of gap
                    int[] pos2 = positionsOfSameCharacter.get(j);

                    // pos1 - gap1, gap2 && pos2 + gap1, gap2 (check the boundary before occupied or not)
                    checkValidPositions(visited, pos1[0], pos1[1], pos2[0], pos2[1]);

                }
            }
        }

        int result = 0;
        for(int i = 0; i < visited.length; i++){
            for(int j = 0; j < visited[0].length; j++){
                if(visited[i][j]) result++;
            }
        }
        return result;
    }

    public void checkValidPositions(boolean[][] occupied, int pos1X, int pos1Y, int pos2X, int pos2Y){
        int m = occupied.length;
        int n = occupied[0].length;

        int gap1 = pos2X - pos1X;
        int gap2 = pos2Y - pos1Y;

        while(pos1X >=0 && pos1X < m && pos1Y >= 0 && pos1Y < n){
            occupied[pos1X][pos1Y] = true;
            pos1X -= gap1;
            pos1Y -= gap2;
        }

        while(pos2X >=0 && pos2X < m && pos2Y >= 0 && pos2Y < n){
            occupied[pos2X][pos2Y] = true;
            pos2X += gap1;
            pos2Y += gap2;
        }
    }

    public boolean checkIsValidPosition(boolean[][] occupied, int[] posForAntinode){
        int m = occupied.length;
        int n = occupied[0].length;

        if(posForAntinode[0] < 0 || posForAntinode[0] >= m || posForAntinode[1] < 0 || posForAntinode[1] >= n){
            return false;
        }

        return !occupied[posForAntinode[0]][posForAntinode[1]];
    }

}
