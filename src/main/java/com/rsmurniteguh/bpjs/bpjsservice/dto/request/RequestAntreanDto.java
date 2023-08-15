package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomNullSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.TaskIdAntrean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestAntreanDto {
    private String kodebooking;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private TaskIdAntrean taskid;
    private Long waktu;
    private String jenisresep;
}
