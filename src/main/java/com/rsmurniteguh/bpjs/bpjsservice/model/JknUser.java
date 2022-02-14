package com.rsmurniteguh.bpjs.bpjsservice.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JknUser {
    private Long jknuser_id;
    private String username;
    private String password;
    private String entity_code;
    private Long created_by;
    private Timestamp created_datetime;
    private Long last_updated_by;
    private Timestamp last_updated_datetime;
    private String defunct_ind;
}
