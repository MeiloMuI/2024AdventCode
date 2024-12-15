package org.example;

import org.example.day10.QuestionHandlerForDay10;
import org.example.day11.QuestionHandlerForDay11;
import org.example.day12.QuestionHandlerForDay12;
import org.example.day13.QuestionHandlerForDay13;
import org.example.day14.QuestionHandlerForDay14;
import org.example.day3.QuestionHandlerForDay3;
import org.example.day4.QuestionHandlerForDay4;
import org.example.day5.QuestionHandlerForDay5;
import org.example.day6.QuestionHandlerForDay6;
import org.example.day7.QuestionHandlerForDay7;
import org.example.day8.QuestionHandlerForDay8;
import org.example.day9.QuestionHandlerForDay9;

public class Main {
    public static void main(String[] args) {
        QuestionHandlerForDay14 questionHandler = new QuestionHandlerForDay14();
        System.out.println(questionHandler.solvePart1("data/day14/part1.txt"));
        System.out.println(questionHandler.solvePart2("data/day14/part1.txt"));
    }
}