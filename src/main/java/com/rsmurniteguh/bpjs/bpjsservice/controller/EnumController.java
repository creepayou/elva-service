package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.FilterTanggalRencanaKontrol;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Lakalantas;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Pembiayaan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusKlaim;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusPulang;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.TaskIdAntrean;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;

@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/getJenisPelayanan")
    public ResponseSts<List<VClaimMappingDto>> getJenisPelayanan() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (JenisPelayanan item : JenisPelayanan.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getJenis().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getStatusKlaim")
    public ResponseSts<List<VClaimMappingDto>> getStatusKlaim() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (StatusKlaim item : StatusKlaim.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getStatus().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getLakaLantas")
    public ResponseSts<List<VClaimMappingDto>> getLakaLantas() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (Lakalantas item : Lakalantas.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getKll().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getPembiayaan")
    public ResponseSts<List<VClaimMappingDto>> getPembiayaan() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (Pembiayaan item : Pembiayaan.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.name());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getStatusPulang")
    public ResponseSts<List<VClaimMappingDto>> getStatusPulang() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (StatusPulang item : StatusPulang.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.name());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getFilterRencanaKontrol")
    public ResponseSts<List<VClaimMappingDto>> getFilterRencanaKontrol() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (FilterTanggalRencanaKontrol item : FilterTanggalRencanaKontrol.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.name());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getListTaskIdAntrean")
    public ResponseSts<List<VClaimMappingDto>> getListTaskIdAntrean() {
        List<VClaimMappingDto> vClaimMappingDtos = new ArrayList<>();
        for (TaskIdAntrean item : TaskIdAntrean.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.name());
            vClaimMappingDtos.add(dto);
        }
        return ResponseSts.onSuccess(vClaimMappingDtos);
    }
}
