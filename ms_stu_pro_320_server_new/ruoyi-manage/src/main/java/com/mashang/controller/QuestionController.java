package com.mashang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.QuestionListQuery;
import com.mashang.domain.vo.management.QuestionDtlVo;
import com.mashang.domain.vo.management.QuestionListVo;
import com.mashang.service.IQuestionService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
@Api(tags = "题目管理")
public class QuestionController {

    @Autowired
    IQuestionService iQuestionService;

    @GetMapping("/list")
    @ApiOperation("查询题目信息列表")
    public R<PageInfo<QuestionListVo>> list(@Validated PageQuery pageQuery
            ,QuestionListQuery questionListQuery){
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<QuestionListVo> list = iQuestionService.list(questionListQuery);

        return R.ok(new PageInfo<>(list));
    }


    @GetMapping("/dtl/{questionId}")
    @ApiOperation("查询问题详情")
    public R<QuestionDtlVo> dtl(@PathVariable Integer questionId){
        return R.ok(iQuestionService.dtl(questionId));
    }
}
