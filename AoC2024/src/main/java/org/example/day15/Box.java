package org.example.day15;

public class Box extends GameEntity{

    boolean isLeftPart;

    public Box(int x, int y) {
        super(x, y);
    }

    public Box(int x, int y, boolean isLeftPart) {
        super(x, y);
        this.isLeftPart = isLeftPart;
    }

    public boolean isLeftPart() {
        return isLeftPart;
    }

    public void setLeftPart(boolean leftPart) {
        isLeftPart = leftPart;
    }
}
