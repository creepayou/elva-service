package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataDto;

import lombok.Data;

@Data
public class VClaimEncResponse {
    @JsonAlias({"metadata"})
	private BpjsMetaDataDto metaData;

    private Object response;
}
