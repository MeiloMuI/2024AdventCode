package org.example;

import org.example.day3.QuestionHandlerForDay3;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay3 questionHandlerForDay3 = new QuestionHandlerForDay3();
        System.out.println(questionHandlerForDay3.getAnswerOfPart1("data/day3/part1.txt"));
        System.out.println(questionHandlerForDay3.getAnswerOfPart2("data/day3/part2.txt"));
    }
}