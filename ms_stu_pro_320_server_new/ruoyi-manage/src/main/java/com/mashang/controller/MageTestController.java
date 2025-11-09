package com.mashang.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mashang.comming.TestMapping;
import com.mashang.domain.entity.Test;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.QuestionTestCreat;
import com.mashang.domain.query.management.TestCreat;
import com.mashang.domain.query.management.TestListQuery;
import com.mashang.domain.query.management.TestUpdate;
import com.mashang.domain.vo.management.ManageTestListVo;
import com.mashang.domain.vo.management.TestDtlVo;
import com.mashang.service.ITestAnswerService;
import com.mashang.service.ITestService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage-test")
@Api(tags = "管理端试卷管理")
public class MageTestController extends BaseController {

    @Autowired
    private ITestService testService;
    @Autowired
    private ITestAnswerService testAnswerService;

    @GetMapping
    @ApiOperation("管理端查询试卷列表")
    public TableDataInfo<List<ManageTestListVo>> list(@Validated PageQuery pageQuery
            , TestListQuery testListQuery){
        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<ManageTestListVo> list = testService.list(testListQuery);

        return getDataTable(page.getResult(),page.getTotal());
    }


    @GetMapping("/dtl/{testId}")
    @ApiOperation("查询试卷详情")
    public R<TestDtlVo> dtl(@PathVariable @Validated Integer testId){
        return R.ok(testService.dtl(testId));
    }

    @PostMapping
    @ApiOperation("新增试卷")
    public R insert(@RequestBody @Validated TestCreat testCreat){
        Test test = TestMapping.INSTANCE.toCreat(testCreat);
        testService.save(test);
        for(QuestionTestCreat questionTestCreat: testCreat.getQuestion()){
            testService.linkTestQuestion(test.getTestId(),questionTestCreat);
        }
        return R.ok();
    }

    @PutMapping
    @ApiOperation("修改试卷")
    public R update(TestUpdate testUpdate){
        testService.updateById(TestMapping.INSTANCE.toUpdate(testUpdate));
        testService.breakTestQuestion(testUpdate.getTestId());
        for(QuestionTestCreat questionTestCreat: testUpdate.getQuestion()){
            testService.linkTestQuestion(testUpdate.getTestId(),questionTestCreat);
        }
        return R.ok();
    }

    @DeleteMapping
    @ApiOperation("删除试卷")
    public R delete(Integer testId){
        if (testService.haveTestAnswer(testId) != 0){
            return R.fail("该试卷下存在答案请删除答案再删除试卷");
        }
        testService.removeById(testId);
        return R.ok();
    }
}
