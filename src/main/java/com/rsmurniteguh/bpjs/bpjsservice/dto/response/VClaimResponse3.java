package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.ProvPerujukDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.ProvUmumDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VClaimResponse3 {
    
    private BpjsMetaDataDto metaData;

    private BpjsSepDto response;
}
