package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class RequestRujukanDto {
    private String noRujukan;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Timestamp tglRujukan;
    private String ppkDirujuk;
    private String jnsPelayanan;
    private String catatan;
    private String diagRujukan;
    private String tipeRujukan;
    private String poliRujukan;
    private String user;
}
