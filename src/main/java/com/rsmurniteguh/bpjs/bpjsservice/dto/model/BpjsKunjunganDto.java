package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsKunjunganDto {

   private String diagnosa;
   private JenisPelayanan jnsPelayanan;
   private KelasRawat kelasRawat;
   @JsonAlias({ "nama, namaPeserta" })
   private String nama;
   private String noKartu;
   private String noSep;
   private String noRujukan;
   private String poli;
   private String ppkPelayanan;
   @JsonDeserialize(using = CustomJsonDateDeserializer.class)
   private Timestamp tglSep;
   @JsonDeserialize(using = CustomJsonDateDeserializer.class)
   private Timestamp tglPlgSep;

}
