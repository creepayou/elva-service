package com.rsmurniteguh.elva.elvaservice.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsConsumerCategory {
    private Long bpjsconsumer_category_id;
    private Long bpjsconsumer_id;
    private BpjsConsumerCategoryType category;
    private String user_key;
    private Long created_by;
    private Timestamp created_datetime;
    private Long last_updated_by;
    private Timestamp last_updated_datetime;
    private String defunct_ind;
}
