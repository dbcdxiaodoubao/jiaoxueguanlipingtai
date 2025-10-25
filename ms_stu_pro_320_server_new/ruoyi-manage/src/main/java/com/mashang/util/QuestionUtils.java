package com.mashang.util;

import com.mashang.constant.QuestionType;

import java.util.Arrays;

public class QuestionUtils {
    private static final Integer[] ALL_OBJECTIVE_QUESTION_TYPE ={QuestionType.SHORT_ANSWER,QuestionType.MULTIPLE_CHOICE,QuestionType.TRUE_FALSE};

    public static boolean isObjective(Integer questionType){
        return Arrays.asList(ALL_OBJECTIVE_QUESTION_TYPE).contains(questionType);
    }
}
