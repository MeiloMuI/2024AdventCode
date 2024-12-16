package org.example.day15;

import org.example.util.FileProcessor;

import java.util.List;

public class FileProcessorForDay15 extends FileProcessor {

    Player player = null;

    public GameEntity[][] readBoardFile(String boardFilePath){
        List<String> boardData = readFile(boardFilePath);
        int m = boardData.size();
        int n = boardData.get(0).length();
        GameEntity[][] board = new GameEntity[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char c = boardData.get(i).charAt(j);
                if(c == '#'){
                    board[i][j] = new Wall(i, j);
                } else if (c == 'O'){
                    board[i][j] = new Box(i, j);
                } else if (c == '@'){
                    this.player = new Player(i, j);
                    board[i][j] = this.player;
                }
            }
        }
        return board;
    }

    public List<String> readMoveFile(String moveFilePath){
        return readFile(moveFilePath);
    }

}
