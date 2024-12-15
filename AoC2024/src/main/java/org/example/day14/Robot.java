package org.example.day14;

public class Robot {
    int x;
    int y;
    int velocityOfX;
    int velocityOfY;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocityOfX() {
        return velocityOfX;
    }

    public void setVelocityOfX(int velocityOfX) {
        this.velocityOfX = velocityOfX;
    }

    public int getVelocityOfY() {
        return velocityOfY;
    }

    public void setVelocityOfY(int velocityOfY) {
        this.velocityOfY = velocityOfY;
    }

    public Robot(int x, int y, int velocityOfX, int velocityOfY) {
        this.x = x;
        this.y = y;
        this.velocityOfX = velocityOfX;
        this.velocityOfY = velocityOfY;
    }
}
