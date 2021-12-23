package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.EmptyIfNull;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Pembiayaan;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsKelasRawatDto {
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private KelasRawat klsRawatHak;
    private @EmptyIfNull String klsRawatNaik;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private @EmptyIfNull Pembiayaan pembiayaan;
    private @EmptyIfNull String penanggungJawab;
}
