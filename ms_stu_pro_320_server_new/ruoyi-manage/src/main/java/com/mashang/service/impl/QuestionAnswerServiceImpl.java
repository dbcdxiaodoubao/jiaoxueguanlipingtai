package com.mashang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.constant.MessageConstant;
import com.mashang.domain.entity.QuestionAnswer;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.WrongBookQuery;
import com.mashang.domain.vo.student.WrongBookListVo;
import com.mashang.service.IQuestionAnswerService;
import com.mashang.mapper.QuestionAnswerMapper;
import com.mashang.util.SubjectUtils;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 20413
 * @description 针对表【ms_question_answer(答题表)】的数据库操作Service实现
 * @createDate 2025-10-22 18:01:40
 */
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer>
        implements IQuestionAnswerService {

    @Autowired
    private SubjectUtils subjectUtils;


    /**
     * 查询用户错题列表
     *
     * @param pageQuery      分页条件
     * @param wrongBookQuery 查询条件
     * @return 错题列表
     */
    @Override
    public Page<WrongBookListVo> pageWrongBook(PageQuery pageQuery, WrongBookQuery wrongBookQuery) {
        if (wrongBookQuery.getUserId() == null) {
            throw new ServiceException(MessageConstant.UNKONWN_ERROR);
        }

        if (!subjectUtils.checkSubject(wrongBookQuery.getSubjectId())) {
            throw new ServiceException(MessageConstant.SUBJECT_NOT_CORRESPOND_TO_GRADE);
        }
        Page<WrongBookListVo> page = PageHelper.startPage(pageQuery);
        baseMapper.pageWrongBook(wrongBookQuery);
        return page;
    }
}




