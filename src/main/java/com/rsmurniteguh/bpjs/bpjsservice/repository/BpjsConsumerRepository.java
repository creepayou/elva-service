package com.rsmurniteguh.bpjs.bpjsservice.repository;

import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BpjsConsumerRepository {

    void insert(BpjsConsumer bpjsConsumer);

    void update(BpjsConsumer bpjsConsumer);

    BpjsConsumer getByEntityCode(@Param("entity_code") String entityCode);
}
