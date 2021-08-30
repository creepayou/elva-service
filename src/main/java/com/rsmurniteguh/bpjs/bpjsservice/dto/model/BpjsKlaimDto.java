package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsKlaimDto {

    private VClaimMappingDto Inacbg;
    private Biaya biaya;
    private String kelasRawat;
    private String noFPK;
    private String noSEP;
    private BpjsResponsePesertaDto peserta;
    private String poli;
    private String status;
    @JsonAlias({ "tglPlgSEP" })
    private Timestamp tglPulang;
    @JsonAlias({ "tglSEP", "tglSep" })
    private Timestamp tglSep;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class Biaya {

    private String byPengajuan;
    private String bySetujui;
    private String byTarifGruper;
    private String byTarifRS;
    private String byTopup;
}
