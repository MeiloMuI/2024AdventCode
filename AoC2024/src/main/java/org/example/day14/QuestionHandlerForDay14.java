package org.example.day14;

import org.example.Main;
import org.example.day13.Prize;
import org.example.util.FileProcessor;

import java.util.List;

public class QuestionHandlerForDay14 {
    FileProcessorForDay14 fileProcessor;
    int wide;
    int tall;
    public QuestionHandlerForDay14() {
        this.fileProcessor = new FileProcessorForDay14();
        this.wide = 101;
        this.tall = 103;
    }

    public int solvePart1(String filePath){
        List<Robot> robots = fileProcessor.getRobots(filePath);
        int leftTop = 0;
        int rightTop = 0;
        int botLeft = 0;
        int botRight = 0;
        int halfWide = wide / 2;
        int halfHeight = tall / 2;

        for(Robot robot: robots){
            int changeOfX = robot.getVelocityOfX() * 100;
            int changeOfY = robot.getVelocityOfY() * 100;
            int finalX = getFinalPosition(robot.getX(), changeOfX, wide);
            int finalY = getFinalPosition(robot.getY(), changeOfY, tall);
            if(finalX < halfWide && finalY < halfHeight){
                leftTop++;
            } else if(finalX < halfWide && finalY > halfHeight){
                botLeft++;
            } else if(finalX > halfWide && finalY < halfHeight){
                rightTop++;
            } else if(finalX > halfWide && finalY > halfHeight){
                botRight++;
            }
        }
        return leftTop * botLeft * rightTop * botRight;
    }

    public int getFinalPosition(int initialPos, int changeOfPos, int len){
        int actualChangePos = getActualChangePosition(changeOfPos, len);
        return (initialPos + actualChangePos) % len;
    }

    public int getActualChangePosition(int changeOfPos, int len){
        if(changeOfPos >= 0){
            return changeOfPos % len;
        } else {
            int absOfValue = Math.abs(changeOfPos);
            int temp = absOfValue / len + 1;
            return temp * len - absOfValue;
        }
    }

    public int solvePart2(String filePath){
        List<Robot> robots = fileProcessor.getRobots(filePath);
        for(int i = 0; i < 8000; i++){
            // 1. Change the status of robots
            changeStatusOfRobots(robots);
            // 2. visualization of data
            if(numberOfRobotsInMid(robots) > 50){
                printPicturesOfRobots(robots, i);
            }
        }
        return 0;
    }

    private int numberOfRobotsInMid(List<Robot> robots){
        int result = 0;
        int limit = 10;
        int boardTop = tall / 2 - limit;
        int boardBot = tall / 2 + limit;
        int boardLeft = wide / 2 - limit;
        int boardRight = wide / 2 + limit;
        for(Robot robot: robots){
            if(robot.getX() >= boardLeft && robot.getX() <= boardRight &&
                robot.getY() >= boardTop && robot.getY() <= boardBot){
                result++;
            }
        }
        return result;
    }

    private void changeStatusOfRobots(List<Robot> robots){
        for(Robot robot: robots){
            int finalX = getFinalPosition(robot.getX(), robot.getVelocityOfX(), wide);
            int finalY = getFinalPosition(robot.getY(), robot.getVelocityOfY(), tall);
            robot.setX(finalX);
            robot.setY(finalY);
        }
    }

    public void printPicturesOfRobots(List<Robot> robots, int num){
        boolean[][] arr = new boolean[tall][wide];
        for(Robot robot: robots) {
            // set the existing robots into arr
            arr[robot.getY()][robot.getX()] = true;
        }
        System.out.println("------- This is the " + num + " time -----------");
        for(int i = 0; i < arr.length;i++){
            for(int j = 0; j < arr[0].length; j++){
                if(arr[i][j]){
                    System.out.print('@');
                } else {
                    System.out.print('.');
                }
            }
            System.out.print("\n");
        }
    }

}
