package com.mashang.constant;

public class TestType {

        /**
         * 固定试卷 - 只有一次作答机会，由管理员发布
         */
        public static final Integer FIXED_EXAM = 0;

        /**
         * 时段试卷 - 只有一次作答机会，有开始时间和截止时间，由管理员发布
         */
        public static final Integer TIME_PERIOD_EXAM = 1;

        /**
         * 班级试卷 - 只有一次作答机会，用于后期发布任务，由管理员发布
         */
        public static final Integer CLASS_EXAM = 2;

        /**
         * 视频试卷 - 只有一次作答机会，有视频的试卷，由管理员发布
         */
        public static final Integer VIDEO_EXAM = 3;

        /**
         * 任务试卷 - 老师发布的试卷，只有被选中的班级才可看到，由老师发布
         */
        public static final Integer TASK_EXAM = 4;

        /**
         * 随机试卷
         */
        public static final Integer RANDOM_EXAM = 5;

    }
