package com.rsmurniteguh.elva.elvaservice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rsmurniteguh.elva.elvaservice.model.BpjsConsumer;
import com.rsmurniteguh.elva.elvaservice.model.BpjsConsumerCategory;
import com.rsmurniteguh.elva.elvaservice.model.BpjsConsumerCategoryType;
import com.rsmurniteguh.elva.elvaservice.model.BpjsConsumerWithCategory;

@Mapper
public interface BpjsConsumerRepository {

    void insert(BpjsConsumer bpjsConsumer);

    void update(BpjsConsumer bpjsConsumer);

    BpjsConsumer getByEntityCode(@Param("entity_code") String entityCode);

    void insertCategory(BpjsConsumerCategory bpjsConsumerCategory);

    BpjsConsumerWithCategory selectWithCategoryByEntityCode(@Param("category") BpjsConsumerCategoryType category,
            @Param("entity_code") String entityCode);

    List<BpjsConsumerWithCategory> selectWithCategoryByEntityCodeList(
            @Param("category") BpjsConsumerCategoryType category,
            @Param("entity_code_list") List<String> entityCodeList);
}
