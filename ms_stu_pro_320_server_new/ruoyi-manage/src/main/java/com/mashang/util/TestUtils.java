package com.mashang.util;

import com.mashang.constant.TestType;
import com.mashang.domain.entity.Test;

import java.util.Arrays;

public class TestUtils {
    private final static Integer[] TEST_TYPE_NEED = new Integer[]{TestType.FIXED_EXAM, TestType.TIME_PERIOD_EXAM, TestType.CLASS_EXAM};

    public static boolean isNeed(Integer testType){
        return Arrays.asList(TEST_TYPE_NEED).contains(testType);
    }
}
