package com.rsmurniteguh.bpjs.bpjsservice.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BpjsConsumer {

    private Long bpjsconsumer_id;

    private String consumer_id;

    private String consumer_secret;

    private Long created_by;
    
    private Timestamp created_datetime;
    
    private Long updated_by;
    
    private Timestamp updated_datetime;
    
    private String defunct_ind;

    private String entity_code;
}
