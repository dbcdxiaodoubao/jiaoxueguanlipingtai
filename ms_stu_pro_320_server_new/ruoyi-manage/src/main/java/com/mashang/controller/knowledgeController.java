package com.mashang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashang.comming.KnowledgeMapping;
import com.mashang.domain.query.common.PageQuery;
import com.mashang.domain.query.management.KnowledgeCreat;
import com.mashang.domain.query.management.KnowledgeUpdate;
import com.mashang.domain.vo.management.KnowledgeDtlVo;
import com.mashang.domain.vo.management.KnowledgeListVo;
import com.mashang.domain.vo.management.KnowledgeTreeVo;
import com.mashang.service.IKnowledgeService;
import com.mashang.service.ISubjectsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
@Api(tags =  "知识点管理")
public class knowledgeController extends BaseController {

    @Autowired
    IKnowledgeService knowledgeService;

    @GetMapping("/list")
    @ApiOperation("查询知识点信息列表")
    @PreAuthorize("@ss.hasPermi('manage:knowledge:list')")
    public TableDataInfo<List<KnowledgeListVo>> list(@Validated PageQuery pageQuery , Integer grade){
        Page<Object> page = PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());

        List<KnowledgeListVo> list = knowledgeService.list(grade);

        return getDataTable(page.getResult(),page.getTotal());
    }

    @GetMapping("/tree/{subjectId}")
    @ApiOperation("查询知识点树")
    @PreAuthorize("@ss.hasPermi('manage:knowledge:tree')")
    public R<List<KnowledgeTreeVo>> tree(@Validated @PathVariable Long subjectId){
        return R.ok(knowledgeService.selectBySubjectId(subjectId));
    }

    @GetMapping("/dtl")
    @ApiOperation("查询知识点详情")
    @PreAuthorize("@ss.hasPermi('manage:knowledge:dtl')")
    public R<KnowledgeDtlVo> dtl(Integer knowledgeId){
        return R.ok(knowledgeService.selectDtl(knowledgeId));
    }

    @PostMapping
    @ApiOperation("新增知识点")
    @PreAuthorize("@ss.hasPermi('manage:knowledge:insert')")
    public R insert(@RequestBody @Validated KnowledgeCreat knowledgeCreat){

        //子节点校验
        if(knowledgeCreat.getParentId()!=0) {
            if (knowledgeService.selectOneByKnowledgeName(knowledgeCreat.getKnowledgeName(),
                    knowledgeCreat.getParentId()) != 0) {
                return R.fail("该父节点下已经存在该知识点");
            }

            if (knowledgeService.selectGradeByKnowledgeId(knowledgeCreat.getParentId()) != knowledgeCreat.getGrade()) {
                return R.fail("子节点的年级应和父节点的年级相同");
            }

            if (knowledgeService.selectSubjectIdByKnowledgeId(knowledgeCreat.getParentId())
                    != knowledgeCreat.getSubjectId()) {
                return R.fail("子节点的学科id应和父节点的学科id相同");
            }
        }

        if(knowledgeService.save(KnowledgeMapping.INSTANCE.toCreat(knowledgeCreat))){
            return R.ok();
        }
        return R.fail("保存失败");
    }

    @PutMapping
    @ApiOperation("修改知识点")
    @PreAuthorize("@ss.hasPermi('manage:knowledge:update')")
    public R update(@RequestBody @Validated KnowledgeUpdate knowledgeUpdate){

        //子节点校验
        if(knowledgeUpdate.getParentId()!=0) {
            if (knowledgeService.selectOneByKnowledgeName(knowledgeUpdate.getKnowledgeName(),
                    knowledgeUpdate.getParentId()) != 0) {
                return R.fail("该父节点下已经存在该知识点");
            }

            if (knowledgeService.selectGradeByKnowledgeId(knowledgeUpdate.getParentId()) != knowledgeUpdate.getGrade()) {
                return R.fail("子节点的年级应和父节点的年级相同");
            }

            if (knowledgeService.selectSubjectIdByKnowledgeId(knowledgeUpdate.getParentId())
                    != knowledgeUpdate.getSubjectId()) {
                return R.fail("子节点的学科id应和父节点的学科id相同");
            }
        }

        if(knowledgeService.updateById(KnowledgeMapping.INSTANCE.toUpdate(knowledgeUpdate))){
            return R.ok();
        }
        return R.fail("修改失败");
    }

    @DeleteMapping("/{knowledgeId}")
    @ApiOperation("删除知识点")
    @PreAuthorize("@ss.hasPermi('manage:knowledge:delete')")
    public R delete(@Validated @PathVariable Integer knowledgeId){

        if(knowledgeService.selectCountById(knowledgeId)!=0){
            return R.fail("该节点下存在子节点，请先删除子节点再删除该节点");
        }

        knowledgeService.removeById(knowledgeId);
        return R.ok();
    }
}
