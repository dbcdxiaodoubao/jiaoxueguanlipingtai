package com.mashang.service;

import com.mashang.domain.entity.Knowledge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.domain.vo.management.KnowledgeDtlVo;
import com.mashang.domain.vo.management.KnowledgeListVo;
import com.mashang.domain.vo.management.KnowledgeTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 20413
* @description 针对表【ms_knowledge(知识点表)】的数据库操作Service
* @createDate 2025-10-22 18:01:40
*/
public interface IKnowledgeService extends IService<Knowledge> {

    /**
     * 查询知识点信息列表
     * @param grade
     * @return
     */
    List<KnowledgeListVo> list(Integer grade);

    /**
     * 根据学科id查询知识点树
     * @param subjectId
     * @return
     */
    List<KnowledgeTreeVo> selectBySubjectId(Long subjectId);

    /**
     * 根据id查知识点的年级
     * @param knowledgeId
     * @return
     */
    Integer selectGradeByKnowledgeId(Integer knowledgeId);

    /**
     * 根据id查学科id
     * @param knowledgeId
     * @return
     */
    Integer selectSubjectIdByKnowledgeId(Integer knowledgeId);

    /**
     * 根据id查询详情
     * @param knowledgeId
     * @return
     */
    KnowledgeDtlVo selectDtl(Integer knowledgeId);

    /**
     * 根据知识点名称判断是否存在相同知识点
     * @param knowledgeName
     * @return
     */
    Integer selectOneByKnowledgeName(@Param("knowledgeName")String knowledgeName
            ,@Param("parentId") Integer parentId);

    /**
     * 根据id查询该节点下有无子节点
     * @param knowledgeId
     * @return
     */
    Integer selectCountById(Integer knowledgeId);

    /**
     * 根据学科id查询是有知识点
     * @param subjectId
     * @return
     */
    Integer haveKnowledege(Integer subjectId);
}
