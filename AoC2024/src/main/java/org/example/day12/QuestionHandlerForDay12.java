package org.example.day12;

import org.example.util.FileProcessor;

import java.util.List;

public class QuestionHandlerForDay12 {
    FileProcessor fileProcessor;
    int areaOfRegion;

    public QuestionHandlerForDay12() {
        this.fileProcessor = new FileProcessor();
    }

    public int solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        int rows = data.size();
        int cols = data.get(0).length();
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!visited[i][j]){
                    // Use dfs / bfs to search for the whole region
                    areaOfRegion = 0;
                    dfs(data, visited, data.get(i).charAt(j), i, j);
                }
            }
        }
        return 0;
    }

    public void dfs(List<String> data, boolean[][] visited, char target, int x, int y){
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int rows = visited.length;
        int cols = visited[0].length;

        areaOfRegion++;
        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) continue;
            if(!visited[nextX][nextY] && data.get(nextX).charAt(nextY) == target){
                dfs(data, visited, target, nextX, nextY);
            }
        }
    }

    public int solvePart2(String filePath){
        return 0;
    }
}
