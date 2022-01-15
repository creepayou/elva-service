package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataAplicaresDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AplicaresResponse<T> {

    private BpjsMetaDataAplicaresDto metadata;

    private Map<String, T> response;
}
