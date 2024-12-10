package org.example.day10;

import org.example.QuestionHandler;
import org.example.util.FileProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class QuestionHandlerForDay10 implements QuestionHandler {

    FileProcessor fileProcessor;
    int[][] dir = new int[][]{{1,0}, {0, 1}, {-1, 0}, {0,-1}};
    int scoreOfTrailhead;
    int ratingOfTrailhead;

    public QuestionHandlerForDay10() {
        this.fileProcessor = new FileProcessor();
    }

    @Override
    public int solvePart1(String filePath) {
        List<String> data = fileProcessor.readFile(filePath);
        int m = data.size();
        int n = data.get(0).length();
        int result = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char c = data.get(i).charAt(j);
                if(c == '0'){
                    scoreOfTrailhead = 0;
                    boolean[][] visited = new boolean[m][n];
                    dfs(data, '1', visited, i, j);
                    result += scoreOfTrailhead;
                }
            }
        }

        return result;
    }

    private void dfs(List<String> data, char target, boolean[][] visited, int x, int y){
        for(int i = 0; i < 4; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextX >= data.size() || nextY < 0 || nextY >= data.get(0).length()) continue;
            if(!visited[nextX][nextY] && data.get(nextX).charAt(nextY) == target){
                if(target == '9'){
                    visited[nextX][nextY] = true;
                    scoreOfTrailhead++;
                } else {
                    dfs(data, (char)(target+1),visited, nextX, nextY);
                }
            }
        }
    }

    @Override
    public int solvePart2(String filePath) {
        List<String> data = fileProcessor.readFile(filePath);
        int m = data.size();
        int n = data.get(0).length();
        int result = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char c = data.get(i).charAt(j);
                if(c == '0'){
                    ratingOfTrailhead = 0;
                    boolean[][] visited = new boolean[m][n];
                    dfsForPart2(data, '1', i, j);
                    result += ratingOfTrailhead;
                }
            }
        }

        return result;
    }

    private void dfsForPart2(List<String> data, char target, int x, int y){
        for(int i = 0; i < 4; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextX >= data.size() || nextY < 0 || nextY >= data.get(0).length()) continue;
            if(data.get(nextX).charAt(nextY) == target){
                if(target == '9'){
                    ratingOfTrailhead++;
                } else {
                    dfsForPart2(data, (char)(target+1), nextX, nextY);
                }
            }
        }
    }

}
