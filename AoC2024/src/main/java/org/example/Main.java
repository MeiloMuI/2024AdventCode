package org.example;

import org.example.day3.QuestionHandlerForDay3;
import org.example.day4.QuestionHandlerForDay4;
import org.example.day5.QuestionHandlerForDay5;
import org.example.day6.QuestionHandlerForDay6;
import org.example.day7.QuestionHandlerForDay7;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay7 questionHandler = new QuestionHandlerForDay7();
        System.out.println(questionHandler.getAnswerOfPart1("data/day7/part1.txt"));
        System.out.println(questionHandler.getAnswerOfPart1("data/day7/test1.txt"));
//        System.out.println(questionHandler.getAnswerOfPart2("data/day6/part2.txt"));
    }
}