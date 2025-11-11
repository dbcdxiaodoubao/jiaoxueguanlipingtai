package com.mashang.util;

import java.util.Arrays;

public class TestUtils {
    private final static Integer[] TEST_TYPE_NEED = new Integer[]{1, 2, 5};

    public static boolean isNeed(Integer testType){
        return Arrays.asList(TEST_TYPE_NEED).contains(testType);
    }
}
