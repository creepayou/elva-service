package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VClaimResponse<T> {

    private BpjsMetaDataDto metaData;

    private Map<String, T> response;
}
