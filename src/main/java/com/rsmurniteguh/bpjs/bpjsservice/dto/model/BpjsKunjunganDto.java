package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsKunjunganDto {

   private String diagnosa;
   private String jnsPelayanan;
   private KelasRawat kelasRawat;
   @JsonAlias({ "nama, namaPeserta" })
   private String nama;
   private String noKartu;
   private String noSep;
   private String noRujukan;
   private String poli;
   private String ppkPelayanan;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
   private Timestamp tglPlgSep;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
   private Timestamp tglSep;

}
