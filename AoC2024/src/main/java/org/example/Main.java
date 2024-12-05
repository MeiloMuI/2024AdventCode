package org.example;

import org.example.day3.QuestionHandlerForDay3;
import org.example.day4.QuestionHandlerForDay4;
import org.example.day5.QuestionHandlerForDay5;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay5 questionHandlerForDay5 = new QuestionHandlerForDay5();
        System.out.println(questionHandlerForDay5.getAnswerOfPart1("data/day5/ruleOfPart1.txt", "data/day5/dataOfPart1.txt"));
//        System.out.println(questionHandlerForDay5.getAnswerOfPart2("data/day4/part2.txt"));
    }
}