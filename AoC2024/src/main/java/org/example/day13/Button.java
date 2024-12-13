package org.example.day13;

public class Button {
    int x;
    int y;
    int tokenSpend;

    public Button(int x, int y, int tokenSpend) {
        this.x = x;
        this.y = y;
        this.tokenSpend = tokenSpend;
    }

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

    public int getTokenSpend() {
        return tokenSpend;
    }

    public void setTokenSpend(int tokenSpend) {
        this.tokenSpend = tokenSpend;
    }
}
