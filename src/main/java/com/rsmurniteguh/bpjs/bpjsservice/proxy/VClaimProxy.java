package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.google.cloud.Timestamp;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusKlaim;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKlaimDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKunjunganDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsResponsePesertaDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDto;
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
        public VClaimResponse<BpjsResponsePesertaDto> getPesertaByNoKartu(@PathVariable("noKartu") String noKartu,
                        @PathVariable("tglSEP") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglSEP);

        @GetMapping("/Peserta/nik/{nik}/tglSEP/{tglSEP}")
        public VClaimResponse<BpjsResponsePesertaDto> getPesertaByNik(@PathVariable("nik") String nik,
                        @PathVariable("tglSEP") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglSEP);

        @GetMapping("/SEP/{noSep}")
        public VClaimResponse<RequestSepDto> searchSEP(@PathVariable("noSep") String noSep);

        @PostMapping("/SEP/1.1/Insert")
        public VClaimResponse<BpjsSepDto> insertSEP(@RequestBody RequestSepDto requestSepDto);

        @PutMapping("/SEP/1.1/Update")
        public VClaimResponse<String> updateSEP(@RequestBody RequestSepDto requestSepDto);

        @DeleteMapping("/SEP/Delete")
        public VClaimResponse<String> deleteSEP(@RequestBody RequestSepDto requestSepDto);

        @PostMapping("/Sep/pengajuanSep")
        public VClaimResponse<String> pengajuanSEP(@RequestBody RequestSepDto requestSepDto);

        @PostMapping("/Sep/aprovalSEP")
        public VClaimResponse<String> approvalSEP(@RequestBody RequestSepDto requestSepDto);

        @PutMapping("/Sep/updtglplg")
        public VClaimResponse<String> updateTglPulangSEP(@RequestBody RequestSepDto requestSepDto);

        @GetMapping("/Rujukan/{noRujukan}")
        public VClaimResponse<BpjsRujukanDto> getRujukanPCareByNoRujukan(@PathVariable("noRujukan") String noRujukan);

        @GetMapping("/Rujukan/RS/{noRujukan}")
        public VClaimResponse<BpjsRujukanDto> getRujukanRsByNoRujukan(@PathVariable("noRujukan") String noRujukan);

        @GetMapping("/Rujukan/Peserta/{noKartu}")
        public VClaimResponse<BpjsRujukanDto> getRujukanPCareByNoKartu(@PathVariable("noKartu") String noKartu);

        @GetMapping("/Rujukan/RS/Peserta/{noKartu}")
        public VClaimResponse<BpjsRujukanDto> getRujukanRsByNoKartu(@PathVariable("noKartu") String noKartu);

        @GetMapping("/Rujukan/List/Peserta/{noKartu}")
        public VClaimResponse<List<BpjsRujukanDto>> getRujukanPCareListByNoKartu(@PathVariable("noKartu") String noKartu);

        @GetMapping("/Rujukan/RS/List/Peserta/{noKartu}")
        public VClaimResponse<List<BpjsRujukanDto>> getRujukanRsListByNoKartu(@PathVariable("noKartu") String noKartu);

        @PostMapping("/Rujukan/insert")
        public VClaimResponse<BpjsRujukanDto> insertRujukan(@RequestBody RequestRujukanDto requestRujukanDto);

        @PutMapping("/Rujukan/update")
        public VClaimResponse<String> updateRujukan(@RequestBody RequestRujukanDto requestRujukanDto);

        @PutMapping("/Rujukan/delete")
        public VClaimResponse<String> deleteRujukan(@RequestBody RequestRujukanDto requestRujukanDto);

        @GetMapping("/Monitoring/Kunjungan/Tanggal/{tglSEP}/JnsPelayanan/{jnsPelayanan}")
        public VClaimResponse<BpjsKunjunganDto> getDataKunjungan(
                        @PathVariable("tglSEP") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglSEP,
                        @PathVariable("jnsPelayanan") JenisPelayanan jnsPelayanan);

        @GetMapping("/Monitoring/Klaim/Tanggal/{tglSEP}/JnsPelayanan/{jnsPelayanan}/Status/{status}")
        public VClaimResponse<BpjsKlaimDto> getDataKlaim(
                        @PathVariable("tglSEP") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglSEP,
                        @PathVariable("jnsPelayanan") JenisPelayanan jnsPelayanan,
                        @PathVariable("status") StatusKlaim status);

        @GetMapping("/monitoring/Klaim/HistoriPelayanan/NoKartu/{noKartu}/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}")
        public VClaimResponse<BpjsKunjunganDto> getHistoriPelayanan(@PathVariable("noKartu") String noKartu,
                        @PathVariable("tglAwal") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglAwal,
                        @PathVariable("tglAkhir") @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta") Timestamp tglAkhir);

}
