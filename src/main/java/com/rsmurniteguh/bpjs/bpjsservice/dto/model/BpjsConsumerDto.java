package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.BpjsConsumerDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsConsumerDto {

    private Long bpjsConsumerId;

    private String consumerId;

    private String consumerSecret;

    private Long createdBy;
    
    private Timestamp createdDateTime;
    
    private Long updatedBy;
    
    private Timestamp updatedDateTime;
    
    private String defunctInd;

    private String entityCode;

    private String providerCode;

    public BpjsConsumer toBpjsConsumer(){
        return BpjsConsumerDtoMapper.toBpjsConsumer(this);
    }
}
