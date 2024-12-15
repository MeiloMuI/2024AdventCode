package org.example.day13;

public class Prize {
    long prizeForX;
    long prizeForY;

    public Prize(long prizeForX, long prizeForY) {
        this.prizeForX = prizeForX;
        this.prizeForY = prizeForY;
    }

    public long getPrizeForX() {
        return prizeForX;
    }

    public void setPrizeForX(long prizeForX) {
        this.prizeForX = prizeForX;
    }

    public long getPrizeForY() {
        return prizeForY;
    }

    public void setPrizeForY(long prizeForY) {
        this.prizeForY = prizeForY;
    }
}
