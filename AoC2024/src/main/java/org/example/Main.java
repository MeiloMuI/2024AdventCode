package org.example;

import org.example.day3.QuestionHandlerForDay3;
import org.example.day4.QuestionHandlerForDay4;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay4 questionHandlerForDay4 = new QuestionHandlerForDay4();
        System.out.println(questionHandlerForDay4.getAnswerOfPart1("data/day4/part1.txt"));
        System.out.println(questionHandlerForDay4.getAnswerOfPart2("data/day4/part2.txt"));
    }
}