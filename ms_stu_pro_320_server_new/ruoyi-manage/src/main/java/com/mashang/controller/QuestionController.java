package com.mashang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.comming.QuestionMapping;
import com.mashang.domain.entity.Question;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.QuestionCteat;
import com.mashang.domain.query.management.QuestionExcelCteat;
import com.mashang.domain.query.management.QuestionListQuery;
import com.mashang.domain.query.management.QuestionUpdate;
import com.mashang.domain.vo.management.QuestionDtlVo;
import com.mashang.domain.vo.management.QuestionListVo;
import com.mashang.service.IQuestionService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/question")
@Api(tags = "题目管理")
public class QuestionController extends BaseController {

    @Autowired
    IQuestionService iQuestionService;

    @GetMapping("/list")
    @ApiOperation("查询题目信息列表")
    public TableDataInfo<List<QuestionListVo>> list(@Validated PageQuery pageQuery
            , QuestionListQuery questionListQuery){
        Page<QuestionListVo> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<QuestionListVo> list = iQuestionService.list(questionListQuery);

        return getDataTable(page.getResult(),page.getTotal());
    }


    @GetMapping("/dtl/{questionId}")
    @ApiOperation("查询问题详情")
    public R<QuestionDtlVo> dtl(@PathVariable @Validated Integer questionId){
        return R.ok(iQuestionService.dtl(questionId));
    }

    @PostMapping()
    @ApiOperation("新增题目")
    public R insert(@RequestBody @Validated QuestionCteat questionCteat){
        if(iQuestionService.saveQuestion(questionCteat)==1){
            return R.ok();
        }
        return R.fail();
    }

    @PostMapping("/importTemplate")
    @ApiOperation("下载导入模版")
    public void importTemplate(HttpServletResponse response){
        ExcelUtil<QuestionExcelCteat> util = new ExcelUtil<QuestionExcelCteat>(QuestionExcelCteat.class);
        util.importTemplateExcel(response, "题目数据");
    }

    @PostMapping("/importData")
    @ApiOperation("通过excel导入题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "Excel文件（支持.xlsx/.xls）", required = true,
                    dataType = "MultipartFile", dataTypeClass = MultipartFile.class, paramType = "form")
    })
    public R importData(@RequestPart("file")MultipartFile file) throws Exception
    {
        ExcelUtil<QuestionExcelCteat> util = new ExcelUtil<QuestionExcelCteat>(QuestionExcelCteat.class);
        List<QuestionExcelCteat> list = util.importExcel(file.getInputStream());

        List<Question> questions = QuestionMapping.INSTANCE.toList(list);
        if (iQuestionService.saveBatch(questions)){
            for (Question question : questions){
                Long questionId = question.getQuestionId();
                for (Integer knowledgeId : question.getKnowledgeId()){
                    iQuestionService.linkQuestionKnowledge(questionId, knowledgeId);
                }
            }
            return R.ok();
        }

        return R.fail("导入失败");
    }

    @PutMapping
    @ApiOperation("修改题目")
    public R update(@RequestBody @Validated QuestionUpdate questionUpdate){
        if (iQuestionService.updateById(QuestionMapping.INSTANCE.toUpdate(questionUpdate))){
            return R.ok();
        }
        return R.fail("修改失败");
    }

    @ApiOperation("删除题目")
    @DeleteMapping("/{questionId}")
    public R delete(@PathVariable @Validated Integer questionId){

        if (iQuestionService.haveOnTest(questionId)!=0){
            return R.fail("该题目与试卷关联，请先删除相关试卷再删除题目");
        }

        if(iQuestionService.removeById(questionId)){
            iQuestionService.deleteLink(questionId);
            return R.ok();
        }
        return R.fail();
    }

}
