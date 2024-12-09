package org.example;

import org.example.day3.QuestionHandlerForDay3;
import org.example.day4.QuestionHandlerForDay4;
import org.example.day5.QuestionHandlerForDay5;
import org.example.day6.QuestionHandlerForDay6;
import org.example.day7.QuestionHandlerForDay7;
import org.example.day8.QuestionHandlerForDay8;
import org.example.day9.QuestionHandlerForDay9;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay9 questionHandler = new QuestionHandlerForDay9();
        System.out.println(questionHandler.solvePart1("data/day9/part1.txt"));
//        System.out.println(questionHandler.solvePart2("data/day8/part1.txt"));

    }
}