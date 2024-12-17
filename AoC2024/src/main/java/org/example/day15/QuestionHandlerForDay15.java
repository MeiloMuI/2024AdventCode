package org.example.day15;

import java.util.*;

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

    public void updateLocationOfPlayer(GameEntity[][] board, int nextX, int nextY){
        board[player.getX()][player.getY()] = null;
        player.setX(nextX);
        player.setY(nextY);
        board[nextX][nextY] = player;
    }

    public long solvePart2(String boardFile, String commandFile){
        GameEntity[][] board = fileProcessorForDay15.readLargeBoardFile(boardFile);
//        printBoard(board);
        List<String> commands = fileProcessorForDay15.readMoveFile(commandFile);
        this.player = fileProcessorForDay15.player;
        for(String s: commands){
            int len = s.length();
            for(int i = 0; i < len; i++){
                char currentCommand = s.charAt(i);
//                System.out.println(currentCommand  +" ----------------");

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
                getResultOfMovementPart2(board, posX, posY);
//                printBoard(board);
            }
        }
        // Get the result
//        printBoard(board);
        return getResultPart2(board);
    }

    public void getResultOfMovementPart2(GameEntity[][] board, int posX, int posY){
        int nextX = player.getX() + posX;
        int nextY = player.getY() + posY;
        if(board[nextX][nextY] == null){
            updateLocationOfPlayer(board, nextX, nextY);
        } else if (board[nextX][nextY].getClass() == Wall.class){
            return;
        } else {
            // Push box
            if(posY == 0){
                pushBoxTopDown(board, nextX, nextY, posX, posY);
            } else {
                pushBoxLeftRight(board, nextX, nextY, posX, posY);
            }
//            printBoard(board);
        }
    }

    public void pushBoxTopDown(GameEntity[][] board, int startX, int startY, int toX, int toY){

        Queue<GameEntity> q = new LinkedList<>();
        Deque<GameEntity> deque = new LinkedList<>();
        HashSet<GameEntity> set = new HashSet<>();
        q.add(board[startX][startY]);
        set.add(board[startX][startY]);
        if(((Box)(board[startX][startY])).isLeftPart()){
            q.add(board[startX][startY + 1]);
            set.add(board[startX][startY + 1]);
        } else {
            q.add(board[startX][startY - 1]);
            set.add(board[startX][startY - 1]);
        }
        boolean canBePushed = true;
        while(!q.isEmpty()){
            GameEntity box = q.poll();
            deque.addLast(box);
            int x = box.getX() + toX;
            int y = box.getY() + toY;
            GameEntity next = board[x][y];
            if(next == null){
                continue;
            } else if (next.getClass() == Wall.class){
                canBePushed = false;
                break;
            }
            // Issue here
            if(set.add(next)){
                q.add(next);
                if(((Box)next).isLeftPart()){
                    q.add(board[x][y + 1]);
                    set.add(board[x][y + 1]);
                } else {
                    q.add(board[x][y - 1]);
                    set.add(board[x][y - 1]);
                }
            }
        }

        if(canBePushed){
            // Change board
            while(!deque.isEmpty()){
                Box box = (Box)deque.pollLast();
                updateLocationOfBox(board, box, toX, toY);
            }
            updateLocationOfPlayer(board, startX, startY);
        }
    }

    public void pushBoxLeftRight(GameEntity[][] board, int startX, int startY, int toX, int toY){

        int x = startX + toX;
        int y = startY + toY;
        boolean canBePushed = true;

        while(x >= 0 && x < board.length && y >= 0 && y < board[0].length){
            if(board[x][y] == null){
                break;
            } else if (board[x][y].getClass() == Wall.class){
                canBePushed = false;
                break;
            }
            x += toX;
            y += toY;
        }

        if(canBePushed){
            // Push boxes
            while(y != startY){
                y -= toY;
                Box box = (Box)board[startX][y];
                updateLocationOfBox(board, box, toX, toY);
            }
            // changePlayer
            updateLocationOfPlayer(board, startX, startY);
        }

    }

    private void updateLocationOfBox(GameEntity[][] board, Box box, int toX, int toY){
        int nextX = box.getX()+toX;
        int nextY = box.getY()+toY;
//        System.out.println(nextX + " " + nextY + " number----");
        board[box.getX()][box.getY()] = null;
        board[nextX][nextY] = box;
        box.setX(nextX);
        box.setY(nextY);
    }

    public void printBoard(GameEntity[][] board){
        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                GameEntity e = board[i][j];
                if(e == null){
                    System.out.print('.');
                } else if (e.getClass() == Box.class){
                    if (((Box) e).isLeftPart()){
                        System.out.print('[');
                    } else {
                        System.out.print(']');
                    }
                } else if (e.getClass() == Wall.class){
                    System.out.print('#');
                } else {
                    System.out.print('@');
                }
            }
            System.out.print("\n");
        }
    }

    public long getResultPart2(GameEntity[][] board){
        long result = 0;
        int m = board.length;;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] != null && board[i][j].getClass() == Box.class && ((Box)board[i][j]).isLeftPart()){
                    result += 100l * i + j;
                }
            }
        }
        return result;
    }

}
