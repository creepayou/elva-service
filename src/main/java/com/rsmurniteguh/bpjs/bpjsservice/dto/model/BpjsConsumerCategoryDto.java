package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.BpjsConsumerCategoryDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategory;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsConsumerCategoryDto {
    private Long bpjsConsumerCategoryId;
    private Long bpjsConsumerId;
    private BpjsConsumerCategoryType category;
    private String userKey;
    private Long createdBy;
    private Timestamp createdDatetime;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDatetime;
    private String defunctInd;

    public BpjsConsumerCategory toBpjsConsumerCategory() {
        return BpjsConsumerCategoryDtoMapper.toBpjsConsumerCategory(this);
    }
}
