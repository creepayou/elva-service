package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsObatDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRujukBalikDto {
    private String noSrb;
    private String noSep;
    private String noKartu;
    private String alamat;
    private String email;
    private String programPRB;
    private String kodeDPJP;
    private String keterangan;
    private String saran;
    private String user;
    private List<BpjsObatDto> obat;
}
