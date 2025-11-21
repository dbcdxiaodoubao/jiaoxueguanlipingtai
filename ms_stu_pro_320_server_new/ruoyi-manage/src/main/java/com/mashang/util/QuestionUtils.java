package com.mashang.util;

import com.mashang.constant.QuestionType;

import java.util.Arrays;

public class QuestionUtils {
    private static final Integer[] ALL_OBJECTIVE_QUESTION_TYPE ={QuestionType.SINGLE_CHOICE,QuestionType.MULTIPLE_CHOICE,QuestionType.TRUE_FALSE};
    private static final Integer[] ALL_SUBJECT_QUESTION_TYPE ={QuestionType.FILL_IN_THE_BLANK,QuestionType.SHORT_ANSWER};

    public static boolean isObjective(Integer questionType){
        return Arrays.asList(ALL_OBJECTIVE_QUESTION_TYPE).contains(questionType);
    }

    public static boolean isSubjective(Integer questionType){
        return Arrays.asList(ALL_SUBJECT_QUESTION_TYPE).contains(questionType);
    }
}
