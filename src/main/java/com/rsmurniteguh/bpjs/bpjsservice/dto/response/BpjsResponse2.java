package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsMetaDataDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BpjsResponse2<T> {
    @JsonAlias({ "metadata" })
    private BpjsMetaDataDto metaData;

    private T response;
}
