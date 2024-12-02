package org.example;

public class Main {
    public static void main(String[] args) {
        QuestionHandler questionHandler = new QuestionHandler();

        System.out.println(questionHandler.getAnswerOfPart1("part1.txt"));
        System.out.println(questionHandler.getAnswerOfPart2("part2.txt"));
    }
}