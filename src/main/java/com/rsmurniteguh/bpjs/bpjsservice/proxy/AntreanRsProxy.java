package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDashboardPerTanggalDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanListTaskDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPoliDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanScheduleDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsBatalAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestJadwalDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestListTaskModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.AplicaresResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = Constant.ANTREAN_FEIGN_NAME, url = "${proxy.antreanrs.host}", configuration = {
        BpjsRequestConfig.class, BpjsRequestClientConfig.class })
public interface AntreanRsProxy {

    @GetMapping(value = "/ref/poli")
    public BpjsResponse2<List<BpjsAntreanPoliDto>> getReferensiPoli(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping(value = "/ref/dokter")
    public BpjsResponse2<List<BpjsAntreanDoctorDto>> getReferensiDokter(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping(value = "/jadwaldokter/kodepoli/{kodePoli}/tanggal/{tanggal}")
    public BpjsResponse2<List<BpjsAntreanScheduleDoctorDto>> getReferensiJadwalDokter(@PathVariable("kodePoli") String kodePoli,
    		@PathVariable("tanggal") String tanggal,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @PostMapping("/jadwaldokter/updatejadwaldokter")
	public BpjsResponse2<Object> updateJadwalDokter(@RequestBody RequestJadwalDokterDto requestJadwalDokterDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @PostMapping("/antrean/getlisttask")
   	public BpjsResponse2<List<BpjsAntreanListTaskDto>> getListTask(@RequestBody RequestListTaskModelDto requestListTaskModelDto,
   			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping(value = "/dashboard/waktutunggu/tanggal/{tanggal}/waktu/{waktu}")
    public BpjsResponse2<List<BpjsAntreanDashboardPerTanggalDto>> getDashboardPerTanggal(@PathVariable("tanggal") String tanggal,
    		@PathVariable("waktu") String waktu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
    
    @GetMapping(value = "/dashboard/waktutunggu/bulan/{bulan}/tahun/{tahun}/waktu/{waktu}")
    public BpjsResponse2<List<BpjsAntreanDashboardPerTanggalDto>> getDashboardPerBulan(@PathVariable("bulan") String bulan,
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
}
