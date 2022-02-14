package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.JknUserDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.model.JknUser;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonInclude(value = Include.NON_NULL)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JknUserDto {
    private Long jknUserId;
    private String username;
    private String password;
    private String entityCode;
    private Long createdBy;
    private Timestamp createdDateTime;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDateTime;
    private String defunctInd;

    public JknUser toJknUser() {
        return JknUserDtoMapper.toJknUser(this);
    }
}
