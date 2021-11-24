package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKlaimDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKunjunganDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaResponseDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.DataDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolCrudDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpesialistikDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse3;

@FeignClient(name = "vclaim", url = "${proxy.vclaimrest.host}", configuration = BpjsRequestConfig.class)
public interface VClaimProxy {

    @GetMapping("/referensi/diagnosa/{param}")
    public VClaimResponse<List<VClaimMappingDto>> getDiagnosa(@PathVariable("param") String parameter,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/referensi/poli/{param}")
    public VClaimResponse<List<VClaimMappingDto>> getPoli(@PathVariable("param") String parameter,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/referensi/faskes/{param}/{jenisFaskes}")
    public VClaimResponse<List<VClaimMappingDto>> getFaskes(@PathVariable("param") String parameter,
            @PathVariable("jenisFaskes") String jenisFaskes, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/referensi/dokter/pelayanan/{jenisPelayanan}/tglPelayanan/{tglPelayanan}/Spesialis/{spesialis}")
    public VClaimResponse<List<VClaimMappingDto>> getDokterDPJP(@PathVariable("jenisPelayanan") String jenisPelayanan,
            @PathVariable("tglPelayanan") String tglPelayanan, @PathVariable("spesialis") String spesialis,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/referensi/propinsi")
    public VClaimResponse<List<VClaimMappingDto>> getPropinsi(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/referensi/kabupaten/propinsi/{kdPropinsi}")
    public VClaimResponse<List<VClaimMappingDto>> getKabupaten(@PathVariable("kdPropinsi") String kdPropinsi,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/referensi/kecamatan/kabupaten/{kdKabupaten}")
    public VClaimResponse<List<VClaimMappingDto>> getKecamatan(@PathVariable("kdKabupaten") String kdKabupaten,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Peserta/nokartu/{noKartu}/tglSEP/{tglSEP}")
    public VClaimResponse<BpjsPesertaResponseDto> getPesertaByNoKartu(@PathVariable("noKartu") String noKartu,
            @PathVariable("tglSEP") String tglSEP, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Peserta/nik/{nik}/tglSEP/{tglSEP}")
    public VClaimResponse<BpjsPesertaResponseDto> getPesertaByNik(@PathVariable("nik") String nik,
            @PathVariable("tglSEP") String tglSEP, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/SEP/{noSep}")
    public VClaimResponse2<BpjsSepDto> searchSEP(@PathVariable("noSep") String noSep,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    /**
     * @deprecated migrate to V2
     * @param requestSepDto
     * @param entityCode
     * @return
     */
    @Deprecated(forRemoval = true)
    @PostMapping("/SEP/1.1/insert")
    public VClaimResponse<BpjsSepDto> insertSEP(@RequestBody BpjsRequestDto<RequestSepDto> requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PostMapping("/SEP/2.0/insert") //FIXME: Sesuaikan model ke v2
    public VClaimResponse<BpjsSepDto> insertSEPV2(@RequestBody BpjsRequestDto<RequestSepDtoV2> requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    /**
     * @deprecated migrate to V2
     * @param requestSepDto
     * @param entityCode
     * @return
     */
    @Deprecated(forRemoval = true)
    @PutMapping("/SEP/1.1/Update")
    public VClaimResponse<String> updateSEP(@RequestBody BpjsRequestDto<RequestSepDto> requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PutMapping("/SEP/2.0/Update") //FIXME: Sesuaikan model ke v2
    public VClaimResponse<String> updateSEPV2(@RequestBody BpjsRequestDto<RequestSepDtoV2> requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    /**
     * @deprecated migrate to V2
     * @param requestSepDto
     * @param entityCode
     * @return
     */
    @Deprecated(forRemoval = true)
    @DeleteMapping("/SEP/Delete")
    public VClaimResponse<String> deleteSEP(@RequestBody BpjsRequestDto<RequestSepDto> requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @DeleteMapping("/SEP/2.0/Delete") //FIXME: Sesuaikan model ke v2
    public VClaimResponse<String> deleteSEPV2(@RequestBody BpjsRequestDto<RequestSepDtoV2> requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PostMapping("/Sep/pengajuanSep")
    public VClaimResponse<String> pengajuanSEP(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PostMapping("/Sep/aprovalSEP")
    public VClaimResponse<String> approvalSEP(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    /**
     * @deprecated migrate to V2
     * @param requestSepDto
     * @param entityCode
     * @return
     */
    @Deprecated(forRemoval = true)
    @PutMapping("/Sep/updtglplg")
    public VClaimResponse<String> updateTglPulangSEP(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PutMapping("/Sep/2.0/updtglplg") //FIXME: Sesuaikan model ke v2
    public VClaimResponse<String> updateTglPulangSEPV2(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Rujukan/{noRujukan}")
    public VClaimResponse2<BpjsRujukanDto> getRujukanPCareByNoRujukan(@PathVariable("noRujukan") String noRujukan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Rujukan/RS/{noRujukan}")
    public VClaimResponse2<BpjsRujukanDto> getRujukanRsByNoRujukan(@PathVariable("noRujukan") String noRujukan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Rujukan/Peserta/{noKartu}")
    public VClaimResponse2<BpjsRujukanDto> getRujukanPCareByNoKartu(@PathVariable("noKartu") String noKartu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Rujukan/RS/Peserta/{noKartu}")
    public VClaimResponse2<BpjsRujukanDto> getRujukanRsByNoKartu(@PathVariable("noKartu") String noKartu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Rujukan/List/Peserta/{noKartu}")
    public VClaimResponse2<BpjsRujukanListDto> getRujukanPCareListByNoKartu(@PathVariable("noKartu") String noKartu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Rujukan/RS/List/Peserta/{noKartu}")
    public VClaimResponse2<BpjsRujukanListDto> getRujukanRsListByNoKartu(@PathVariable("noKartu") String noKartu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    /**
     * @deprecated migrate to V2
     * @param requestRujukanDto
     * @param entityCode
     * @return
     */
    @Deprecated(forRemoval = true)
    @PostMapping("/Rujukan/insert")
    public VClaimResponse2<BpjsRujukanDto> insertRujukan(
            @RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PostMapping("/Rujukan/2.0/insert") //FIXME: Sesuaikan model ke v2
    public VClaimResponse2<BpjsRujukanDto> insertRujukanV2(
            @RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    /**
     * @deprecated migrate to V2
     * @param requestRujukanDto
     * @param entityCode
     * @return
     */
    @Deprecated(forRemoval = true)
    @PutMapping("/Rujukan/update")
    public VClaimResponse<String> updateRujukan(@RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PutMapping("/Rujukan/2.0/update") //FIXME: Sesuaikan model ke v2
    public VClaimResponse<String> updateRujukanV2(@RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @DeleteMapping("/Rujukan/delete")
    public VClaimResponse<String> deleteRujukan(@RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Monitoring/Kunjungan/Tanggal/{tglSEP}/JnsPelayanan/{jnsPelayanan}")
    public VClaimResponse<List<BpjsKunjunganDto>> getDataKunjungan(@PathVariable("tglSEP") String tglSEP,
            @PathVariable("jnsPelayanan") String jnsPelayanan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/Monitoring/Klaim/Tanggal/{tglPulang}/JnsPelayanan/{jnsPelayanan}/Status/{status}")
    public VClaimResponse<List<BpjsKlaimDto>> getDataKlaim(@PathVariable("tglPulang") String tglPulang,
            @PathVariable("jnsPelayanan") String jnsPelayanan, @PathVariable("status") String status,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/monitoring/HistoriPelayanan/NoKartu/{noKartu}/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}")
    public VClaimResponse<List<BpjsKunjunganDto>> getHistoriPelayanan(@PathVariable("noKartu") String noKartu,
            @PathVariable("tglAwal") String tglAwal, @PathVariable("tglAkhir") String tglAkhir,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping("/RencanaKontrol/ListRencanaKontrol/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}/filter/{filter}")
    public VClaimResponse<List<RencanaKontrolDto>> getRencanaKontrol(@PathVariable("tglAwal") String tglAwal,
            @PathVariable("tglAkhir") String tglAkhir, @PathVariable("filter") String filter,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping("/RencanaKontrol/JadwalPraktekDokter/JnsKontrol/{jnsKontrol}/KdPoli/{kdPoli}/TglRencanaKontrol/{tglRencanaKontrol}")
    public VClaimResponse<List<DataDokterDto>> getDataDokter(@PathVariable("jnsKontrol") String jnsKontrol,
            @PathVariable("kdPoli") String kdPoli, @PathVariable("tglRencanaKontrol") String tglRencanaKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping("/RencanaKontrol/ListSpesialistik/JnsKontrol/{jnsKontrol}/nomor/{nomor}/TglRencanaKontrol/{tglRencanaKontrol}")
    public VClaimResponse<List<SpesialistikDto>> getSpesialistik(@PathVariable("jnsKontrol") String jnsKontrol,
            @PathVariable("nomor") String nomor, @PathVariable("tglRencanaKontrol") String tglRencanaKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping("/RencanaKontrol/noSuratKontrol/{noSuratKontrol}")
    public VClaimResponse2<RencanaKontrolDto> getRencanaKontrolByNoSurat(@PathVariable("noSuratKontrol") String noSuratKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping("/RencanaKontrol/nosep/{noSep}")
    public VClaimResponse3 getRencanaKontrolByNoSep(@PathVariable("noSep") String noSep,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @PostMapping("/RencanaKontrol/insert") 
    public VClaimResponse2<RencanaKontrolCrudDto> insertRencanaKontrol(
            @RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @PutMapping("/RencanaKontrol/Update") 
    public VClaimResponse2<RencanaKontrolCrudDto> updateRencanaKontrol(
            @RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @DeleteMapping("/RencanaKontrol/Delete") 
    public VClaimResponse<String> deleteRencanaKontrol(
            @RequestBody BpjsRequestDto<RequestRencanaKontrolDto> rqRencanaKontrolDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
}
