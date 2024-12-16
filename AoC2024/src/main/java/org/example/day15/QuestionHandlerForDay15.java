package org.example.day15;

import java.util.List;

public class QuestionHandlerForDay15 {
    FileProcessorForDay15 fileProcessorForDay15;

    Player player;

    public QuestionHandlerForDay15() {
        this.fileProcessorForDay15 = new FileProcessorForDay15();
    }

    public int solvePart1(String boardFile, String commandFile){
        GameEntity[][] board = fileProcessorForDay15.readBoardFile(boardFile);
        List<String> commands = fileProcessorForDay15.readMoveFile(commandFile);
        this.player = fileProcessorForDay15.player;
        for(String s: commands){
            int len = s.length();
            for(int i = 0; i < len; i++){
                char currentCommand = s.charAt(i);
                int posX = 0;
                int posY = 0;
                switch (currentCommand){
                    case '<':
                        posY = -1;
                        break;
                    case '^':
                        posX = -1;
                        break;
                    case '>':
                        posY = 1;
                        break;
                    case 'v':
                        posX = 1;
                        break;
                    default:
                }
                // get result of movement
                getResultOfMovement(board, posX, posY);
            }
        }
        // Get the result
        return getResult(board);
    }

    public int getResult(GameEntity[][] board){
        int result = 0;
        int m = board.length;;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] != null && board[i][j].getClass() == Box.class){
                    result += 100 * i + j;
                }
            }
        }
        return result;
    }

    /*
        1. user move to a null location (ok)
        2. user move to a box location (push the box / boxes)
        3. user move to a wall location (cannot be executed)
    */
    public void getResultOfMovement(GameEntity[][] board, int posX, int posY){
        int nextX = player.getX() + posX;
        int nextY = player.getY() + posY;
        if(board[nextX][nextY] == null){
            updateLocationOfPlayer(board, nextX, nextY);
        } else if (board[nextX][nextY].getClass() == Wall.class){
            return;
        } else {
            // Push box
            pushBox(board, nextX, nextY, posX, posY);
        }
    }

    public void pushBox(GameEntity[][] board, int startX, int startY, int toX, int toY){
        int x = startX + toX;
        int y = startY + toY;

        while(x >= 0 && x < board.length && y >= 0 && y < board[0].length){
            if(board[x][y] == null){
                // It can be pushed, exchange the box
                board[x][y] = new Box(x, y);
                // Change the position of player
                updateLocationOfPlayer(board, startX, startY);
                break;
            } else if (board[x][y].getClass() == Wall.class){
                break;
            }
            x += toX;
            y += toY;
        }
    }

    public void updateLocationOfPlayer(GameEntity[][] board, int x, int y){
        board[player.getX()][player.getY()] = null;
        player.setX(x);
        player.setY(y);
        board[x][y] = player;
    }

    public int solvePart2(String filePath){

        return 0;
    }
}
