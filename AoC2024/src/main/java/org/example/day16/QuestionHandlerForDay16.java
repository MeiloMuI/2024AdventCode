package org.example.day16;

import org.example.util.FileProcessor;

import java.util.Arrays;
import java.util.List;

public class QuestionHandlerForDay16 {
    FileProcessor fileProcessor;
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[] posOfStart;
    int[] posOfEnd;
    long result;

    public QuestionHandlerForDay16() {
        this.fileProcessor = new FileProcessor();
        this.posOfStart = new int[2];
        this.posOfEnd = new int[2];
        this.result = Long.MAX_VALUE;
    }

    public long solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        // Get position of Starting and Ending
        getPosOfStartAndEnd(data);
        boolean[][] visited = new boolean[data.size()][data.get(0).length()];
        long[][] cost = new long[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            Arrays.fill(cost[i], Long.MAX_VALUE);
        }
        dfs(data, visited, posOfStart[0], posOfStart[1], -1, 0, 1, cost);

        return result;
    }

    public void dfs(List<String> data, boolean[][] visited, int x, int y, int currentDir, int path, int turn, long[][] cost){
        long value = path + turn * 1000L;

        if (value >= cost[x][y]) {
            return;
        }

        cost[x][y] = value;

        if(x == posOfEnd[0] && y == posOfEnd[1]){
            printBoard(data, visited);
            System.out.println(path + turn * 1000L);
            System.out.println("----------------");
            result = Math.min(result, value);
            return;
        }

        visited[x][y] = true;
//        printBoard(data, visited);
//        System.out.println("----------------");

        for(int i = 0; i < dir.length; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextX >= data.size() || nextY < 0 || nextY >= data.get(0).length() ||
            data.get(nextX).charAt(nextY) == '#' || visited[nextX][nextY]) continue;
            if(currentDir == -1){
                dfs(data, visited, nextX, nextY, i, path+1, turn, cost);
            } else {
                if(i != currentDir) {
                    dfs(data, visited, nextX, nextY, i, path + 1, turn + 1, cost);
                } else {
                    dfs(data, visited, nextX, nextY, i, path + 1, turn, cost);
                }
            }

        }
        visited[x][y] = false;
    }

    public void printBoard(List<String> data, boolean[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]){
                    System.out.print("Y");
                } else {
                    System.out.print(data.get(i).charAt(j));
                }
            }
            System.out.print("\n");
        }
    }

    public long solvePart2(String filePath){

        return 0;
    }

    public void getPosOfStartAndEnd(List<String> data){
        int m = data.size();
        int n = data.get(0).length();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char c = data.get(i).charAt(j);
                if(c == 'S'){
                    posOfStart[0] = i;
                    posOfStart[1] = j;
                } else if (c == 'E'){
                    posOfEnd[0] = i;
                    posOfEnd[1] = j;
                }
            }
        }
    }
}
