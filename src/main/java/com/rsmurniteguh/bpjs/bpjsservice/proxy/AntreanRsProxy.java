package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsFeignClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestErrorConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDashboardPerTanggalDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPatientFpDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPerTanggalDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanListTaskDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPoliDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanScheduleDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsBatalAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestJadwalDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestListTaskModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;

@FeignClient(name = Constant.ANTREAN_FEIGN_NAME, url = "${proxy.antreanrs.host}", configuration = {
        BpjsRequestConfig.class, BpjsFeignClientConfig.class, BpjsRequestErrorConfig.class })
public interface AntreanRsProxy {

    @GetMapping(value = "/ref/poli")
    public BpjsResponse2<List<BpjsAntreanPoliDto>> getReferensiPoli(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping(value = "/ref/dokter")
    public BpjsResponse2<List<BpjsAntreanDoctorDto>> getReferensiDokter(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping(value = "/ref/poli/fp")
    public BpjsResponse2<List<BpjsAntreanPoliDto>> getReferensiPoliFp(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping(value = "/ref/pasien/fp/identitas/nik/noidentitas/{nik}")
    public BpjsResponse2<BpjsAntreanPatientFpDto> getReferensiPasienFpByNIK(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode,
            @PathVariable("nik") String nik);

   @GetMapping(value = "/ref/pasien/fp/identitas/noka/noidentitas/{bpjsNo}")
   public BpjsResponse2<BpjsAntreanPatientFpDto> getReferensiPasienFpByBpjsNo(
           @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode,
           @PathVariable("bpjsNo") String bpjsNo);

    @GetMapping(value = "/jadwaldokter/kodepoli/{kodePoli}/tanggal/{tanggal}")
    public BpjsResponse2<List<BpjsAntreanScheduleDoctorDto>> getReferensiJadwalDokter(
            @PathVariable("kodePoli") String kodePoli,
            @PathVariable("tanggal") String tanggal,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PostMapping("/jadwaldokter/updatejadwaldokter")
    public BpjsResponse2<Object> updateJadwalDokter(@RequestBody RequestJadwalDokterDto requestJadwalDokterDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @PostMapping("/antrean/getlisttask")
    public BpjsResponse2<List<BpjsAntreanListTaskDto>> getListTask(
            @RequestBody RequestListTaskModelDto requestListTaskModelDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping(value = "/dashboard/waktutunggu/tanggal/{tanggal}/waktu/{waktu}")
    public BpjsResponse<List<BpjsAntreanDashboardPerTanggalDto>> getDashboardPerTanggal(
            @PathVariable("tanggal") String tanggal,
            @PathVariable("waktu") String waktu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
            
            @GetMapping(value = "/dashboard/waktutunggu/bulan/{bulan}/tahun/{tahun}/waktu/{waktu}")
    public BpjsResponse<List<BpjsAntreanDashboardPerTanggalDto>> getDashboardPerBulan(
            @PathVariable("bulan") String bulan,
            @PathVariable("tahun") String tahun,
            @PathVariable("waktu") String waktu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
            
            @PostMapping("/antrean/updatewaktu")
            public BpjsResponse2<Object> updateWaktuAntrean(@RequestBody RequestAntreanDto requestAntreanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
            
            @PostMapping("/antrean/add")
            public BpjsResponse2<Object> tambahAntrean(@RequestBody BpjsAntreanDto bpjsAntreanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
            
            @PostMapping("/antrean/batal")
            public BpjsResponse2<Object> batalAntrean(@RequestBody BpjsBatalAntreanDto bpjsBatalAntreanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

        @GetMapping(value = "/antrean/pendaftaran/tanggal/{tanggal}")
        public BpjsResponse2<List<BpjsAntreanPerTanggalDto>> getAntreanPerTanggal(
                @PathVariable("tanggal") String tanggal,
                @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
}
