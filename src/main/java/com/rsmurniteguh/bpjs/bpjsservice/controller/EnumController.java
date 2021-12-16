package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.ArrayList;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Lakalantas;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Pembiayaan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusKlaim;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusPulang;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/getJenisPelayanan")
    public ResponseSts<List<VClaimMappingDto>> getJenisPelayanan(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (JenisPelayanan item : JenisPelayanan.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getJenis().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getStatusKlaim")
    public ResponseSts<List<VClaimMappingDto>> getStatusKlaim(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (StatusKlaim item : StatusKlaim.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getStatus().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getLakaLantas")
    public ResponseSts<List<VClaimMappingDto>> getLakaLantas(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (Lakalantas item : Lakalantas.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getKll().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getPembiayaan")
    public ResponseSts<List<VClaimMappingDto>> getPembiayaan(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (Pembiayaan item : Pembiayaan.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.name());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }

    @GetMapping("/getStatusPulang")
    public ResponseSts<List<VClaimMappingDto>> getStatusPulang(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (StatusPulang item : StatusPulang.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.name());
            vclaimMappingDtoList.add(dto);
        }
        return ResponseSts.onSuccess(vclaimMappingDtoList);
    }
}
