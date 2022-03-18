package com.rsmurniteguh.bpjs.bpjsservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BpjsConsumerWithCategory {
    private String consumer_id;
    private String consumer_secret;
    private String user_key;
    private String entity_code;
    private BpjsConsumerCategoryType category;
}
