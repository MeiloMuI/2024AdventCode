package org.example.day14;

import org.example.Main;
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

        return 0;
    }

}