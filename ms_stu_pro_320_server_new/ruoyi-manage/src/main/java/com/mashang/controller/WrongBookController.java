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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/wrong")
@Api(tags = "错题本")
@Validated
public class WrongBookController extends BaseController {
    @Autowired
    private IQuestionAnswerService questionAnswerService;

    @GetMapping("/page")
    @ApiOperation("根据条件分页查询错题信息")
    @PreAuthorize("@ss.hasPermi('student:wrong:list')")
    @ApiImplicitParam(name = "subjectId", value = "学科id", required = true)
    public TableDataInfo<List<WrongBookListVo>> pageWrongBook(@Validated PageQuery pageQuery, @NotNull(message = "学科id不能为空") Long subjectId) {
        WrongBookQuery wrongBookQuery = new WrongBookQuery();
        Long userId = SecurityUtils.getUserId();
        wrongBookQuery.setUserId(userId);
        wrongBookQuery.setSubjectId(subjectId);
        Page<WrongBookListVo> page = questionAnswerService.pageWrongBook(pageQuery, wrongBookQuery);
        return getDataTable(page);
    }
}
