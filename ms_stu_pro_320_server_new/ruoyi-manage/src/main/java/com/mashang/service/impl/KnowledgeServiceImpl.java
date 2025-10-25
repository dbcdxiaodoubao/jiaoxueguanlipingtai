package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Knowledge;
import com.mashang.domain.vo.management.KnowledgeDtlVo;
import com.mashang.domain.vo.management.KnowledgeListVo;
import com.mashang.domain.vo.management.KnowledgeTreeVo;
import com.mashang.service.IKnowledgeService;
import com.mashang.mapper.KnowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author 20413
* @description 针对表【ms_knowledge(知识点表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge>
    implements IKnowledgeService {

    @Autowired
    KnowledgeMapper knowledgeMapper;

    @Override
    public List<KnowledgeListVo> list(Integer grade) {
        return knowledgeMapper.list(grade);
    }

    @Override
    public List<KnowledgeTreeVo> selectBySubjectId(Long subjectId) {
        List<KnowledgeTreeVo> list = knowledgeMapper.selectBySubjectId(subjectId);

        // 2. 构建 Map：key=knowledgeId，value=KnowledgeTreeVo（便于快速查找父节点）
        Map<Integer, KnowledgeTreeVo> nodeMap = new HashMap<>();
        for (KnowledgeTreeVo node : list) {
            // 初始化子节点列表（避免空指针）
            node.setChildren(new ArrayList<>());
            nodeMap.put(node.getKnowledgeId(), node);
        }

        // 3. 遍历所有节点，关联父子关系
        List<KnowledgeTreeVo> rootNodes = new ArrayList<>(); // 存储根节点（最终树形结构的入口）
        for (KnowledgeTreeVo node : list) {
            Integer parentId = node.getParentId();
            if (parentId == 0) {
                // 根节点（parentId=0）直接加入根列表
                rootNodes.add(node);
            }
            else {
                // 非根节点：从Map中找到父节点，加入父节点的children
                KnowledgeTreeVo parentNode = nodeMap.get(parentId);
                if (parentNode != null) {
                    parentNode.getChildren().add(node);
                }
            }
        }

        return rootNodes;
    }

    @Override
    public Integer selectGradeByKnowledgeId(Integer knowledgeId) {
        return knowledgeMapper.selectGradeByKnowledgeId(knowledgeId);
    }

    @Override
    public Integer selectSubjectIdByKnowledgeId(Integer knowledgeId) {
        return knowledgeMapper.selectSubjectIdByKnowledgeId(knowledgeId);
    }

    @Override
    public KnowledgeDtlVo selectDtl(Integer knowledgeId) {
        return knowledgeMapper.selectDtl(knowledgeId);
    }

    @Override
    public Integer selectOneByKnowledgeName(String knowledgeName, Integer parentId) {
        return knowledgeMapper.selectOneByKnowledgeName(knowledgeName, parentId);
    }

    @Override
    public Integer selectCountById(Integer knowledgeId) {
        return knowledgeMapper.selectCountById(knowledgeId);
    }
}




