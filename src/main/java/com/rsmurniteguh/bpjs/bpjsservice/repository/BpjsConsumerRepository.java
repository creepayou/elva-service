package com.rsmurniteguh.bpjs.bpjsservice.repository;

import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumer;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategory;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerWithCategory;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BpjsConsumerRepository {

    void insert(BpjsConsumer bpjsConsumer);

    void update(BpjsConsumer bpjsConsumer);

    BpjsConsumer getByEntityCode(@Param("entity_code") String entityCode);

    void insertCategory(BpjsConsumerCategory bpjsConsumerCategory);

    BpjsConsumerWithCategory getWithCategoryByEntityCode(@Param("category") BpjsConsumerCategoryType category, @Param("entity_code") String entityCode);
}
