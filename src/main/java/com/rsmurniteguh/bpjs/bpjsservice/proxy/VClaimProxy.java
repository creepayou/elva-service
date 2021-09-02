package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKlaimDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKunjunganDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsResponsePesertaDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "vclaim", url = "${proxy.vclaimrest.host}", configuration = BpjsRequestConfig.class)
public interface VClaimProxy {

        @GetMapping("/referensi/diagnosa/{param}")
        public VClaimResponse<List<VClaimMappingDto>> getDiagnosa(@PathVariable("param") String parameter,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/referensi/poli/{param}")
        public VClaimResponse<List<VClaimMappingDto>> getPoli(@PathVariable("param") String parameter,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/referensi/faskes/{param}/{jenisFaskes}")
        public VClaimResponse<List<VClaimMappingDto>> getFaskes(@PathVariable("param") String parameter,
                        @PathVariable("jenisFaskes") String jenisFaskes,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/referensi/dokter/pelayanan/{jenisPelayanan}/tglPelayanan/{tglPelayanan}/Spesialis/{spesialis}")
        public VClaimResponse<List<VClaimMappingDto>> getDokterDPJP(
                        @PathVariable("jenisPelayanan") String jenisPelayanan,
                        @PathVariable("tglPelayanan") String tglPelayanan,
                        @PathVariable("spesialis") String spesialis, @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/referensi/propinsi")
        public VClaimResponse<List<VClaimMappingDto>> getPropinsi(@RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/referensi/kabupaten/propinsi/{kdPropinsi}")
        public VClaimResponse<List<VClaimMappingDto>> getKabupaten(@PathVariable("kdPropinsi") String kdPropinsi,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/referensi/kecamatan/kabupaten/{kdKabupaten}")
        public VClaimResponse<List<VClaimMappingDto>> getKecamatan(@PathVariable("kdKabupaten") String kdKabupaten,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Peserta/nokartu/{noKartu}/tglSEP/{tglSEP}")
        public VClaimResponse<BpjsResponsePesertaDto> getPesertaByNoKartu(@PathVariable("noKartu") String noKartu,
                        @PathVariable("tglSEP") String tglSEP,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Peserta/nik/{nik}/tglSEP/{tglSEP}")
        public VClaimResponse<BpjsResponsePesertaDto> getPesertaByNik(@PathVariable("nik") String nik,
                        @PathVariable("tglSEP") String tglSEP,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/SEP/{noSep}")
        public VClaimResponse2<BpjsSepDto> searchSEP(@PathVariable("noSep") String noSep,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PostMapping("/SEP/1.1/Insert")
        public VClaimResponse<BpjsSepDto> insertSEP(@RequestBody RequestSepDto requestSepDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PutMapping("/SEP/1.1/Update")
        public VClaimResponse<String> updateSEP(@RequestBody RequestSepDto requestSepDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @DeleteMapping("/SEP/Delete")
        public VClaimResponse<String> deleteSEP(@RequestBody RequestSepDto requestSepDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PostMapping("/Sep/pengajuanSep")
        public VClaimResponse<String> pengajuanSEP(@RequestBody RequestSepDto requestSepDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PostMapping("/Sep/aprovalSEP")
        public VClaimResponse<String> approvalSEP(@RequestBody RequestSepDto requestSepDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PutMapping("/Sep/updtglplg")
        public VClaimResponse<String> updateTglPulangSEP(@RequestBody RequestSepDto requestSepDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Rujukan/{noRujukan}")
        public VClaimResponse2<BpjsRujukanDto> getRujukanPCareByNoRujukan(@PathVariable("noRujukan") String noRujukan,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Rujukan/RS/{noRujukan}")
        public VClaimResponse2<BpjsRujukanDto> getRujukanRsByNoRujukan(@PathVariable("noRujukan") String noRujukan,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Rujukan/Peserta/{noKartu}")
        public VClaimResponse2<BpjsRujukanDto> getRujukanPCareByNoKartu(@PathVariable("noKartu") String noKartu,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Rujukan/RS/Peserta/{noKartu}")
        public VClaimResponse2<BpjsRujukanDto> getRujukanRsByNoKartu(@PathVariable("noKartu") String noKartu,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Rujukan/List/Peserta/{noKartu}")
        public VClaimResponse2<BpjsRujukanListDto> getRujukanPCareListByNoKartu(
                        @PathVariable("noKartu") String noKartu, @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Rujukan/RS/List/Peserta/{noKartu}")
        public VClaimResponse2<BpjsRujukanListDto> getRujukanRsListByNoKartu(@PathVariable("noKartu") String noKartu,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PostMapping("/Rujukan/insert")
        public VClaimResponse2<BpjsRujukanDto> insertRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PutMapping("/Rujukan/update")
        public VClaimResponse<String> updateRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @PutMapping("/Rujukan/delete")
        public VClaimResponse<String> deleteRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Monitoring/Kunjungan/Tanggal/{tglSEP}/JnsPelayanan/{jnsPelayanan}")
        public VClaimResponse<List<BpjsKunjunganDto>> getDataKunjungan(
                        @PathVariable("tglSEP") String tglSEP,
                        @PathVariable("jnsPelayanan") String jnsPelayanan,
                        @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/Monitoring/Klaim/Tanggal/{tglPulang}/JnsPelayanan/{jnsPelayanan}/Status/{status}")
        public VClaimResponse<List<BpjsKlaimDto>> getDataKlaim(
                        @PathVariable("tglPulang") String tglPulang,
                        @PathVariable("jnsPelayanan") String jnsPelayanan,
                        @PathVariable("status") String status, @RequestHeader(Constant.ENTITY) String entityCode);

        @GetMapping("/monitoring/HistoriPelayanan/NoKartu/{noKartu}/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}")
        public VClaimResponse<List<BpjsKunjunganDto>> getHistoriPelayanan(@PathVariable("noKartu") String noKartu,
                        @PathVariable("tglAwal") String tglAwal,
                        @PathVariable("tglAkhir") String tglAkhir,
                        @RequestHeader(Constant.ENTITY) String entityCode);

}
