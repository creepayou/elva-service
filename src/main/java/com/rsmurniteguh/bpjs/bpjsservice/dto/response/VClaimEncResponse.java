package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataDto;

import lombok.Data;

@Data
public class VClaimEncResponse {
	private BpjsMetaDataDto metaData;

    private Object response;
}
