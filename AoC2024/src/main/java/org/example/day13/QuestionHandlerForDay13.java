package org.example.day13;

import org.example.util.FileProcessor;

import java.util.ArrayList;
import java.util.List;

public class QuestionHandlerForDay13 {
    FileProcessor fileProcessor;

    public QuestionHandlerForDay13() {
        this.fileProcessor = new FileProcessor();
    }

    private List<Machine> parseData(List<String> data, String addition){
        List<Machine> lists = new ArrayList<>();
        data.add(""); // Make the data and be groups of four
        int n = data.size();
        int i = 0;
        while(i < n){
            // Button A
            Button buttonA = generateButtonFromString(data.get(i));
            i++;
            // Button B
            Button buttonB = generateButtonFromString(data.get(i));
            i++;
            // Prize
            Prize prize = generatePrizeFromString(data.get(i), addition);
            i++;
            // Skip one line
            Machine machine = new Machine(prize, buttonA, buttonB);
            lists.add(machine);
            i++;
        }
        return lists;
    }

    private Prize generatePrizeFromString(String s, String addition){
        String[] arr = s.split(":");
        String[] prizes = arr[1].split(",");
        long[] valueOfPrizes = new long[2];
        for(int i = 0; i < prizes.length; i++){
            String[] temp = prizes[i].split("=");
            valueOfPrizes[i] = Long.parseLong(temp[1]) + Long.parseLong(addition);
        }
        return new Prize(valueOfPrizes[0], valueOfPrizes[1]);
    }

    private Button generateButtonFromString(String s){
        String[] arr = s.split(":");
        String[] params = arr[1].split(",");
        int[] values = new int[2];
        for(int i = 0; i < params.length; i++){
            String[] temp = params[i].split("\\+");
            values[i] = Integer.parseInt(temp[1]);
        }
        int numOfTokens = 0;
        if(s.contains("A")){
            numOfTokens = 3;
        } else {
            numOfTokens = 1;
        }
        return new Button(values[0], values[1], numOfTokens);
    }

    public long solvePart1(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        List<Machine> machines = parseData(data, "0");
        long result = 0;
        for(Machine machine: machines){
            Button buttonA = machine.getButtonA();
            Button buttonB = machine.getButtonB();
            Prize prize = machine.getPrize();
            int det = buttonA.getX() * buttonB.getY() - buttonB.getX() * buttonA.getY();
            long denoOfX = prize.getPrizeForX() * buttonB.getY() - prize.getPrizeForY() * buttonB.getX();
            long denoOfY = buttonA.getX() * prize.getPrizeForY() - buttonA.getY() * prize.getPrizeForX();
            if(det != 0){
                // One solution
                if(denoOfX % det == 0 && denoOfY % det == 0){
                    result += buttonA.getTokenSpend() * denoOfX / det + buttonB.getTokenSpend() * denoOfY / det;
                }
            } else {
                // Multiply solutions
                if(buttonA.getX() / buttonB.getX() >= buttonA.getTokenSpend()){
                    result += buttonA.getTokenSpend() * prize.getPrizeForX() / buttonA.getX();
                } else {
                    result += buttonB.getTokenSpend() * prize.getPrizeForX() / buttonB.getX();
                }
            }
        }
        return result;
    }

    public long solvePart2(String filePath){
        List<String> data = fileProcessor.readFile(filePath);
        List<Machine> machines = parseData(data, "10000000000000");
        long result = 0;
        for(Machine machine: machines){
            Button buttonA = machine.getButtonA();
            Button buttonB = machine.getButtonB();
            Prize prize = machine.getPrize();
            int det = buttonA.getX() * buttonB.getY() - buttonB.getX() * buttonA.getY();
            long denoOfX = prize.getPrizeForX() * buttonB.getY() - prize.getPrizeForY() * buttonB.getX();
            long denoOfY = buttonA.getX() * prize.getPrizeForY() - buttonA.getY() * prize.getPrizeForX();
            if(det != 0){
                // One solution
                if(denoOfX % det == 0 && denoOfY % det == 0){
                    result += buttonA.getTokenSpend() * denoOfX / det + buttonB.getTokenSpend() * denoOfY / det;
                }
            } else {
                // Multiply solutions
                if(buttonA.getX() / buttonB.getX() >= buttonA.getTokenSpend()){
                    result += buttonA.getTokenSpend() * prize.getPrizeForX() / buttonA.getX();
                } else {
                    result += buttonB.getTokenSpend() * prize.getPrizeForX() / buttonB.getX();
                }
            }
        }
        return result;
    }
}
