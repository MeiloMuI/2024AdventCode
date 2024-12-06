package org.example;

import org.example.day3.QuestionHandlerForDay3;
import org.example.day4.QuestionHandlerForDay4;
import org.example.day5.QuestionHandlerForDay5;
import org.example.day6.QuestionHandlerForDay6;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay6 questionHandlerForDay6 = new QuestionHandlerForDay6();
        System.out.println(questionHandlerForDay6.getAnswerOfPart1("data/day6/part1.txt"));
        System.out.println(questionHandlerForDay6.getAnswerOfPart2("data/day6/part2.txt"));
    }
}