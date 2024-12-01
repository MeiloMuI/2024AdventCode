package org.hxy;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuestionHandler questionHandler = new QuestionHandler();

        System.out.println(questionHandler.getAnswerOfPart1("data.txt"));
        System.out.println(questionHandler.getAnswerOfPart2("dataForPart2.txt"));
    }
}