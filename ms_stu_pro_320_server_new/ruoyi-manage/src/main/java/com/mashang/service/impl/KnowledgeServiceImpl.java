package com.mashang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Knowledge;
import com.mashang.service.IKnowledgeService;
import com.mashang.mapper.KnowledgeMapper;
import org.springframework.stereotype.Service;

/**
* @author 20413
* @description 针对表【ms_knowledge(知识点表)】的数据库操作Service实现
* @createDate 2025-10-22 18:01:40
*/
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge>
    implements IKnowledgeService {

}




