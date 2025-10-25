package com.mashang.constant;

/**
 * 信息提示常量类
 */
public class MessageConstant {
    public static final String UNKONWN_ERROR="发生神秘错误";

    public static final String STUDENT_NOT_EXIST = "学生不存在";
    /** 该用户不是学生 */
    public static final String USER_NOT_STUDENT = "该用户不是学生";

    /********************************  考试相关  ********************************/
    /** 考试不存在 */
    public static final String EXAM_NOT_EXIST = "考试不存在";
    /** 考试已结束 */
    public static final String EXAM_ALREADY_END = "考试已结束";
    /** 考试未开始 */
    public static final String EXAM_NOT_START = "考试未开始";
    /** 考试已禁用 */
    public static final String EXAM_DISABLED = "考试已禁用";
    /** 考试名称重复 */
    public static final String EXAM_NAME_DUPLICATE = "考试名称重复";

    /********************************  试题相关  ********************************/
    /** 试题不存在 */
    public static final String QUESTION_NOT_EXIST = "试题不存在";
    /** 题库太少，请联系管理员 */
    public static final String QUESTION_TOO_LESS = "题目太少，请联系管理员或老师 ";
    /** 试题已被引用，无法删除 */
    public static final String QUESTION_ALREADY_QUOTED = "试题已被引用，无法删除";
    /** 题目分值非法 */
    public static final String QUESTION_SCORE_INVALID = "题目分值非法";

    /********************************  答卷/成绩相关  ********************************/
    /** 答卷不存在 */
    public static final String ANSWER_SHEET_NOT_EXIST = "答卷不存在";
    /** 试卷不存在 */
    public static final String SHEET_NOT_EXIST = "试卷不存在";
    /** 成绩不存在 */
    public static final String SCORE_NOT_EXIST = "成绩不存在";
    /** 已交卷，无法重复提交 */
    public static final String ANSWER_SHEET_ALREADY_SUBMIT = "已交卷，无法重复提交";
    /** 答卷超时 */
    public static final String ANSWER_SHEET_TIMEOUT = "答卷超时";
}
