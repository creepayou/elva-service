package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.AssesmentPel;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.FlagProcedure;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Indikator;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPengajuan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KodePenunjang;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Pembiayaan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Penjamin;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusKlaim;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusPulang;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.TipeRujukan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.TujuanKunjungan;

public class CustomBpjsEnumSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object bpjsEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (bpjsEnum instanceof JenisPelayanan) {
            jsonGenerator.writeString(((JenisPelayanan) bpjsEnum).getJenis().getKode());
        } else if (bpjsEnum instanceof KelasRawat) {
            jsonGenerator.writeString(((KelasRawat) bpjsEnum).getKelas().getKode());
        } else if (bpjsEnum instanceof TipeRujukan) {
            jsonGenerator.writeString(((TipeRujukan) bpjsEnum).getTipe());
        } else if (bpjsEnum instanceof Faskes) {
            jsonGenerator.writeString(((Faskes) bpjsEnum).getJenis());
        } else if (bpjsEnum instanceof Indikator) {
            jsonGenerator.writeString(((Indikator) bpjsEnum).getInd().getKode());
        } else if (bpjsEnum instanceof StatusKlaim) {
            jsonGenerator.writeString(((StatusKlaim) bpjsEnum).getStatus().getKode());
        } else if (bpjsEnum instanceof Penjamin) {
            jsonGenerator.writeString(((Penjamin) bpjsEnum).getJenis());
        } else if (bpjsEnum instanceof JenisPengajuan) {
            jsonGenerator.writeString(((JenisPengajuan) bpjsEnum).getJenis());
        } else if (bpjsEnum instanceof TujuanKunjungan) {
            jsonGenerator.writeString(((TujuanKunjungan) bpjsEnum).getTujuan());
        } else if (bpjsEnum instanceof Pembiayaan) {
            jsonGenerator.writeString(((Pembiayaan) bpjsEnum).getJenis());
        } else if (bpjsEnum instanceof FlagProcedure) {
            jsonGenerator.writeString(((FlagProcedure) bpjsEnum).getFlag());
        } else if (bpjsEnum instanceof AssesmentPel) {
            jsonGenerator.writeString(((AssesmentPel) bpjsEnum).getAssesmen());
        } else if (bpjsEnum instanceof KodePenunjang) {
            jsonGenerator.writeString(((KodePenunjang) bpjsEnum).getKode());
        } else if (bpjsEnum instanceof StatusPulang) {
            jsonGenerator.writeString(((StatusPulang) bpjsEnum).getStatus());
        }
    }

}
