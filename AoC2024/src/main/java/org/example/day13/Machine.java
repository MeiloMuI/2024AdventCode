package org.example.day13;

public class Machine {
    Prize prize;
    Button buttonA;
    Button buttonB;

    public Machine(Prize prize, Button buttonA, Button buttonB) {
        this.prize = prize;
        this.buttonA = buttonA;
        this.buttonB = buttonB;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Button getButtonA() {
        return buttonA;
    }

    public void setButtonA(Button buttonA) {
        this.buttonA = buttonA;
    }

    public Button getButtonB() {
        return buttonB;
    }

    public void setButtonB(Button buttonB) {
        this.buttonB = buttonB;
    }
}
