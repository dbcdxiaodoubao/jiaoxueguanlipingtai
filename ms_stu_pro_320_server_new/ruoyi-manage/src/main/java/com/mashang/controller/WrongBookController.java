package com.mashang.controller;

import com.github.pagehelper.Page;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.student.WrongBookQuery;
import com.mashang.domain.vo.student.WrongBookListVo;
import com.mashang.service.IQuestionAnswerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wrong")
@Api(tags = "错题本")
public class WrongBookController extends BaseController {
    @Autowired
    private IQuestionAnswerService questionAnswerService;

    @GetMapping("/page")
    @ApiOperation("根据条件分页查询错题信息")
    public TableDataInfo<List<WrongBookListVo>> pageWrongBook(@Validated PageQuery pageQuery, Long subjectId){
        WrongBookQuery wrongBookQuery = new WrongBookQuery();
        Long userId = SecurityUtils.getUserId();
        wrongBookQuery.setUserId(userId);
        wrongBookQuery.setSubjectId(subjectId);
        Page<WrongBookListVo> page = questionAnswerService.pageWrongBook(pageQuery, wrongBookQuery);
        return getDataTable(page);
    }
}
