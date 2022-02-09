package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestRujukanKhususDto {
	private String noRujukan;
	private String idRujukan;
    private String user;
    private List<VClaimMappingDto> diagnosa;
    private List<VClaimMappingDto> procedure;
}
