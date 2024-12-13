package org.example.day12;

import org.example.util.FileProcessor;

import java.util.List;

public class QuestionHandlerForDay12 {
    FileProcessor fileProcessor;
    int areaOfRegion;
    int perimeter;

    public QuestionHandlerForDay12() {
        this.fileProcessor = new FileProcessor();
    }

    public int solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        int rows = data.size();
        int cols = data.get(0).length();
        boolean[][] visited = new boolean[rows][cols];
        int result = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!visited[i][j]){
                    // Use dfs / bfs to search for the whole region
                    areaOfRegion = 0;
                    perimeter = 0;
                    dfs(data, visited, data.get(i).charAt(j), i, j);
                    result += areaOfRegion * perimeter;
                }
            }
        }
        return result;
    }

    public void dfs(List<String> data, boolean[][] visited, char target, int x, int y){
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int rows = visited.length;
        int cols = visited[0].length;

        // Deal with the block (1. visited 2. area 3. perimeter)
        areaOfRegion++;
        visited[x][y] = true;
        perimeter += addPerimeterToDetectedRegion(data, target, x, y);

        for(int i = 0; i < 4; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) continue;
            if(!visited[nextX][nextY] && data.get(nextX).charAt(nextY) == target){
                dfs(data, visited, target, nextX, nextY);
            }
        }
    }

    public int addPerimeterToDetectedRegion(List<String> data, char target, int x, int y){
        // Need to detect if there existing the same block on top or left
        int[][] dir = new int[][]{{-1, 0}, {0, -1}};
        int count = 0;
        for(int i = 0; i < dir.length; i++){
            int blockX = x + dir[i][0];
            int blockY = y + dir[i][1];
            if(blockX < 0 || blockY < 0) continue;
            if(data.get(blockX).charAt(blockY) == target){
                count++;
            }
        }
        return 4 - 2 * count;
    }

    public int solvePart2(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        int rows = data.size();
        int cols = data.get(0).length();
        boolean[][] visited = new boolean[rows][cols];
        int result = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!visited[i][j]){
                    // Use dfs / bfs to search for the whole region
                    areaOfRegion = 0;
                    boolean[][] region = new boolean[rows][cols];
                    dfsForPart2(data, visited, region, data.get(i).charAt(j), i, j);
                    int peri = getPerimeter(region);
                    result += areaOfRegion * peri;
                }
            }
        }
        return result;
    }

    public int getPerimeter(boolean[][] region){
        int rows = region.length;
        int cols = region[0].length;
        int result = 0;
        // top-down
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                //top
                if(region[i][j]){
                    if (i == 0 || !region[i - 1][j]) {
                        result++;
                        while(j < cols && region[i][j]){
                            j++;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                //top
                if(region[i][j]){
                    //down
                    if (i == rows - 1 || !region[i + 1][j]) {
                        result++;
                        while(j < cols && region[i][j]){
                            j++;
                        }
                    }
                }
            }
        }
        //left-right
        for(int j = 0; j < cols; j++){
            for(int i = 0; i < rows; i++){
                //right
                if(region[i][j]){
                    if(j == cols - 1 || !region[i][j + 1]){
                        result++;
                        while(i < rows && region[i][j]){
                            i++;
                        }
                    }
                }

            }
        }
        for(int j = 0; j < cols; j++){
            for(int i = 0; i < rows; i++){
                //left
                if(region[i][j]){
                    if(j == 0 || !region[i][j - 1]){
                        result++;
                        while(i < rows && region[i][j]){
                            i++;
                        }
                    }
                }

            }
        }
        return result;
    }

    public void dfsForPart2(List<String> data, boolean[][] visited, boolean[][] region, char target, int x, int y){
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int rows = visited.length;
        int cols = visited[0].length;

        // Deal with the block (1. visited 2. area 3. perimeter)
        areaOfRegion++;
        visited[x][y] = true;
        region[x][y] = true;
        // How to deal with perimeter?

        for(int i = 0; i < 4; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) continue;
            if(!visited[nextX][nextY] && data.get(nextX).charAt(nextY) == target){
                dfsForPart2(data, visited, region, target, nextX, nextY);
            }
        }

    }
}
