package com.rsmurniteguh.bpjs.bpjsservice.repository;

import java.util.List;
import java.util.Map;

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

    BpjsConsumer getByEntityCode(Map<String, Object> param);
    
    BpjsConsumer getBpjsConsumerByEntityCode(Map<String, Object> param);

    void insertCategory(BpjsConsumerCategory bpjsConsumerCategory);

    BpjsConsumerWithCategory selectWithCategoryByEntityCode(@Param("category") BpjsConsumerCategoryType category,
            @Param("entity_code") String entityCode);

    List<BpjsConsumerWithCategory> selectWithCategoryByEntityCodeList(
            @Param("category") BpjsConsumerCategoryType category,
            @Param("entity_code_list") List<String> entityCodeList);
    
    List<BpjsConsumer> getBpjsConsumerList(@Param("entity_code_list") List<String> entityCodeList);
}
