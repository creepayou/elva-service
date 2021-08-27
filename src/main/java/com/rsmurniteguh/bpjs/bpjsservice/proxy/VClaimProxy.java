package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.google.cloud.Timestamp;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${proxy.vclaimrest.host}", configuration = BpjsRequestConfig.class)
public interface VClaimProxy {

    @GetMapping("/referensi/diagnosa/{param}")
    public VClaimResponse<List<VClaimMappingDto>> getDiagnosa(@PathVariable("param") String parameter);

    @GetMapping("/referensi/poli/{param}")
    public VClaimResponse<List<VClaimMappingDto>> getPoli(@PathVariable("param") String parameter);

    @GetMapping("/referensi/faskes/{param}/{jenisFaskes}")
    public VClaimResponse<List<VClaimMappingDto>> getFaskes(@PathVariable("param") String parameter,
            @PathVariable("jenisFaskes") Faskes jenisFaskes);

    @GetMapping("/referensi/dokter/pelayanan/{jenisPelayanan}/tglPelayanan/{tglPelayanan}/Spesialis/{spesialis}")
    public VClaimResponse<List<VClaimMappingDto>> getDokterDPJP(
            @PathVariable("jenisPelayanan") JenisPelayanan jenisPelayanan,
            @PathVariable("tglPelayanan") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = Constant.TIMEZONE_JKT) Timestamp tglPelayanan,
            @PathVariable("spesialis") String spesialis);

    @GetMapping("/referensi/propinsi")
    public VClaimResponse<List<VClaimMappingDto>> getPropinsi();

    @GetMapping("/referensi/kabupaten/{kdPropinsi}")
    public VClaimResponse<List<VClaimMappingDto>> getKabupaten(@PathVariable("kdPropinsi") String kdPropinsi);

    @GetMapping("/referensi/kecamatan/{kdKabupaten}")
    public VClaimResponse<List<VClaimMappingDto>> getKecamatan(@PathVariable("kdKabupaten") String kdKabupaten);

    @GetMapping("/Peserta/nokartu/{noKartu}/tglSEP/{tglSEP}")
    public VClaimResponse<BpjsPesertaDto> getPesertaByNoKartu(@PathVariable("noKartu") String noKartu,
            @PathVariable("tglSEP") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglSEP);

    @GetMapping("/Peserta/nik/{nik}/tglSEP/{tglSEP}")
    public VClaimResponse<BpjsPesertaDto> getPesertaByNik(@PathVariable("nik") String nik,
            @PathVariable("tglSEP") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglSEP);

    @GetMapping("/SEP/{noSep}")
    public VClaimResponse<List<VClaimMappingDto>> searchSEP(@PathVariable("noSep") String noSep);

    @PostMapping("/SEP/1.1/Insert")
    public VClaimResponse<List<VClaimMappingDto>> insertSEP(@RequestBody RequestSepDto requestSepDto);

    @PutMapping("/SEP/1.1/Update")
    public VClaimResponse<List<VClaimMappingDto>> updateSEP(@RequestBody RequestSepDto requestSepDto);

    @DeleteMapping("/SEP/Delete")
    public VClaimResponse<List<VClaimMappingDto>> deleteSEP(@RequestBody RequestSepDto requestSepDto);
}
