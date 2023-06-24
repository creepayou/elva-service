package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDashboardPerTanggalDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanListTaskDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPatientFpDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPerTanggalDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPoliDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanScheduleDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.FilterWaktuAntrean;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsAntreanModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsBatalAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsBatalAntreanModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAntreanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAntreanModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestJadwalDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestJadwalDokterModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestListTaskModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.AntreanRsProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;

@RestController
@RequestMapping("/antreanrs")
public class AntreanRsController extends BaseController {

    @Autowired
    private AntreanRsProxy antreanRsProxy;

    @GetMapping("/getReferensiPoli")
    public ResponseSts<List<BpjsAntreanPoliDto>> getReferensiPoli(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getReferensiPoli(entityCode)));
    }

    @GetMapping("/getReferensiDokter")
    public ResponseSts<List<BpjsAntreanDoctorDto>> getReferensiDokter(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.getReferensiDokter(entityCode)));
    }

    @GetMapping("/getReferensiJadwalDokter")
    public ResponseSts<List<BpjsAntreanScheduleDoctorDto>> getReferensiJadwalDokter(
            @RequestParam(value = "kodePoli", required = true) String kodePoli,
            @RequestParam(value = "tanggal", required = true) String tanggal,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.getReferensiJadwalDokter(kodePoli,
                                tanggal, entityCode)));
    }

    @GetMapping("/getReferensiPoliFp")
    public ResponseSts<List<BpjsAntreanPoliDto>> getReferensiPoliFp(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getReferensiPoliFp(entityCode)));
    }

    @GetMapping("/getReferensiPasienFpByBpjsNo/{bpjsNo}")
    public ResponseSts<BpjsAntreanPatientFpDto> getReferensiPasienFpByBpjsNo(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode,
            @PathVariable("bpjsNo") String bpjsNo)
            throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(
                        antreanRsProxy.getReferensiPasienFpByBpjsNo(entityCode, bpjsNo)));
    }

    @GetMapping("/getReferensiPasienFpByNIK/{nik}")
    public ResponseSts<BpjsAntreanPatientFpDto> getReferensiPasienFpByNIK(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode,
            @PathVariable("nik") String nik)
            throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(
                        antreanRsProxy.getReferensiPasienFpByNIK(entityCode, nik)));
    }

    @PostMapping("/updateJadwalDokter")
    public ResponseSts<Object> updateJadwalDokter(
            @RequestBody RequestJadwalDokterModelDto requestJadwalDokterModelDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        RequestJadwalDokterDto requestJadwalDokterDto = new RequestJadwalDokterDto();
        requestJadwalDokterDto.setKodepoli(requestJadwalDokterModelDto.getKodePoli());
        requestJadwalDokterDto.setKodedokter(requestJadwalDokterModelDto.getKodeDokter());
        requestJadwalDokterDto.setKodesubspesialis(requestJadwalDokterModelDto.getKodeSubSpesialis());
        requestJadwalDokterDto.setJadwal(requestJadwalDokterModelDto.getJadwal());
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponseMessage(antreanRsProxy.updateJadwalDokter(
                                requestJadwalDokterDto, entityCode)));

    }

    @GetMapping("/getListTask/{kodeBooking}")
    public ResponseSts<List<BpjsAntreanListTaskDto>> getListTask(@PathVariable("kodeBooking") String kodeBooking,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        RequestListTaskModelDto requestListTaskModelDto = new RequestListTaskModelDto()
                .setKodebooking(kodeBooking);
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.getListTask(requestListTaskModelDto,
                                entityCode)));
    }

    @GetMapping("/getDashboardPerTanggal")
    public ResponseSts<List<BpjsAntreanDashboardPerTanggalDto>> getDashboardPerTanggal(
            @RequestParam(value = "tanggal", required = true) String tanggal,
            @RequestParam(value = "waktu", required = true) FilterWaktuAntrean waktu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.getDashboardPerTanggal(tanggal,
                                waktu.getWaktu(), entityCode)).get("list"));
    }

    @GetMapping("/getDashboardPerBulan")
    public ResponseSts<List<BpjsAntreanDashboardPerTanggalDto>> getDashboardPerBulan(
            @RequestParam(value = "bulan", required = true) String bulan,
            @RequestParam(value = "tahun", required = true) String tahun,
            @RequestParam(value = "waktu", required = true) FilterWaktuAntrean waktu,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.getDashboardPerBulan(bulan, tahun,
                                waktu.getWaktu(), entityCode)).get("list"));
    }

    @PostMapping("/updateWaktuAntrean")
    public ResponseSts<Object> updateWaktuAntrean(@RequestBody RequestAntreanModelDto requestAntreanModelDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        RequestAntreanDto requestAntreanDto = new RequestAntreanDto();
        requestAntreanDto.setKodebooking(requestAntreanModelDto.getKodeBooking());
        requestAntreanDto.setTaskid(requestAntreanModelDto.getTaskId());
        requestAntreanDto.setWaktu(requestAntreanModelDto.getWaktu());
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.updateWaktuAntrean(requestAntreanDto,
                                entityCode)));

    }

    @PostMapping("/tambahAntrean")
    public ResponseSts<Object> tambahAntrean(@RequestBody BpjsAntreanModelDto model,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        BpjsAntreanDto bpjsDto = new BpjsAntreanDto().setKodebooking(model.getKodeBooking())
                .setJenispasien(model.getJenisPasien()).setNomorkartu(model.getNomorKartu())
                .setNik(model.getNik()).setNohp(model.getNoHp()).setKodepoli(model.getKodePoli())
                .setNamapoli(model.getNamaPoli()).setPasienbaru(model.isPasienBaru() ? 1 : 0)
                .setNorm(model.getNorm()).setTanggalperiksa(model.getTanggalPeriksa())
                .setKodedokter(model.getKodeDokter()).setNamadokter(model.getNamaDokter())
                .setJampraktek(model.getJamPraktek())
                .setJeniskunjungan(model.getJenisKunjungan().getJenis())
                .setNomorreferensi(model.getNomorReferensi()).setNomorantrean(model.getNomorAntrean())
                .setAngkaantrean(model.getAngkaAntrean())
                .setEstimasidilayani(model.getEstimasiDilayani())
                .setSisakuotajkn(model.getSisaKuotaJkn()).setKuotajkn(model.getKuotaJkn())
                .setSisakuotanonjkn(model.getSisaKuotaNonJkn()).setKuotanonjkn(model.getKuotaNonJkn())
                .setKeterangan(model.getKeterangan());
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.tambahAntrean(bpjsDto, entityCode)));

    }

    @PostMapping("/batalAntrean")
    public ResponseSts<Object> batalAntrean(@RequestBody BpjsBatalAntreanModelDto bpjsBatalAntreanModelDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        BpjsBatalAntreanDto bpjsBatalAntreanDto = new BpjsBatalAntreanDto()
                .setKodebooking(bpjsBatalAntreanModelDto.getKodeBooking())
                .setKeterangan(bpjsBatalAntreanModelDto.getKeterangan());
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(antreanRsProxy.batalAntrean(bpjsBatalAntreanDto,
                                entityCode)));
    }

    @GetMapping("/antreanPerTanggal")
    public ResponseSts<List<BpjsAntreanPerTanggalDto>> getAntreanPerTanggal(
        @RequestParam (value = "tanggal", required=true) String tanggal,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getAntreanPerTanggal(tanggal, entityCode)));
    }
    
    @GetMapping("/antreanBelumDilayani")
    public ResponseSts<List<BpjsAntreanPerTanggalDto>> antreanBelumDilayani(
        @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getAntreanBelumDilayani(entityCode)));
    }

    @GetMapping("/antreanPerKodeBooking")
    public ResponseSts<List<BpjsAntreanPerTanggalDto>> getAntreanPerKodeBooking(
        @RequestParam (value = "kodeBooking", required=true) String kodeBooking,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getAntreanPerKodeBooking(kodeBooking, entityCode)));
    }

}
