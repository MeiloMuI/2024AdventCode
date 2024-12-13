package org.example.day13;

public class Prize {
    int prizeForX;
    int prizeForY;

    public Prize(int prizeForX, int prizeForY) {
        this.prizeForX = prizeForX;
        this.prizeForY = prizeForY;
    }

    public int getPrizeForX() {
        return prizeForX;
    }

    public void setPrizeForX(int prizeForX) {
        this.prizeForX = prizeForX;
    }

    public int getPrizeForY() {
        return prizeForY;
    }

    public void setPrizeForY(int prizeForY) {
        this.prizeForY = prizeForY;
    }
}
