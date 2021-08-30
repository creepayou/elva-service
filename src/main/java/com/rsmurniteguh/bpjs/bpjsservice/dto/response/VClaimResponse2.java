package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VClaimResponse2<T> {
    
    private BpjsMetaDataDto metaData;

    private T response;
}
