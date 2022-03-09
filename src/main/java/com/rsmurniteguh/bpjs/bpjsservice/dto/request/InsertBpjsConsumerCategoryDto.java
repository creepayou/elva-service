package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsertBpjsConsumerCategoryDto {
    private @NonNull Long bpjsConsumerId;
    private @NonNull BpjsConsumerCategoryType categoryType;
    private @NonNull String userKey;
    private @NonNull Long createdBy;
}
