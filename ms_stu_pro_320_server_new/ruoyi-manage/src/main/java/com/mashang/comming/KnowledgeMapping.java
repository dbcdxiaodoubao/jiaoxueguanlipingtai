package com.mashang.comming;

import com.mashang.domain.entity.Knowledge;
import com.mashang.domain.query.management.KnowledgeCreat;
import com.mashang.domain.query.management.KnowledgeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KnowledgeMapping {

    KnowledgeMapping INSTANCE = Mappers.getMapper(KnowledgeMapping.class);

    Knowledge toCreat(KnowledgeCreat knowledgeCreat);

    Knowledge toUpdate(KnowledgeUpdate knowledgeUpdate);
}
