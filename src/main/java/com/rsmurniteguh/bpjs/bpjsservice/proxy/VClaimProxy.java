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
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsFeignClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestErrorConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsFingerPrintDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsFingerPrintStatusDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsJaminanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKlaimDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKunjunganDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsListUpdateTglPulangListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaResponseDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukBalikDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukBalikListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepInternalListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepKllDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepSearchDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepSuplesiDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.DataDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolCrudDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RujukanKhususDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SaranaDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpesialistikDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpesialistikRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestPengajuanSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukBalikDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanKhususDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepInternal;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestUpdateTglPulangDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;

@FeignClient(name = Constant.VCLAIM_FEIGN_NAME, url = "${proxy.vclaimrest.host}", configuration = {
		BpjsRequestConfig.class, BpjsFeignClientConfig.class, BpjsRequestErrorConfig.class })
public interface VClaimProxy {

	// #region Referensi

	@GetMapping("/referensi/diagnosa/{param}")
	public BpjsResponse<List<VClaimMappingDto>> getDiagnosa(@PathVariable("param") String parameter,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/poli/{param}")
	public BpjsResponse<List<VClaimMappingDto>> getPoli(@PathVariable("param") String parameter,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/faskes/{param}/{jenisFaskes}")
	public BpjsResponse<List<VClaimMappingDto>> getFaskes(@PathVariable("param") String parameter,
			@PathVariable("jenisFaskes") String jenisFaskes,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/dokter/pelayanan/{jenisPelayanan}/tglPelayanan/{tglPelayanan}/Spesialis/{spesialis}")
	public BpjsResponse<List<VClaimMappingDto>> getDokterDPJP(
			@PathVariable("jenisPelayanan") String jenisPelayanan,
			@PathVariable("tglPelayanan") String tglPelayanan, @PathVariable("spesialis") String spesialis,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/propinsi")
	public BpjsResponse<List<VClaimMappingDto>> getPropinsi(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/kabupaten/propinsi/{kdPropinsi}")
	public BpjsResponse<List<VClaimMappingDto>> getKabupaten(@PathVariable("kdPropinsi") String kdPropinsi,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/kecamatan/kabupaten/{kdKabupaten}")
	public BpjsResponse<List<VClaimMappingDto>> getKecamatan(@PathVariable("kdKabupaten") String kdKabupaten,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/procedure/{param}")
	public BpjsResponse<List<VClaimMappingDto>> getProcedure(@PathVariable("param") String parameter,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/kelasrawat")
	public BpjsResponse<List<VClaimMappingDto>> getKelasRawat(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/dokter/{param}")
	public BpjsResponse<List<VClaimMappingDto>> getDokter(@PathVariable("param") String namaDokter,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/carakeluar")
	public BpjsResponse<List<VClaimMappingDto>> getCaraKeluar(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/diagnosaprb")
	public BpjsResponse<List<VClaimMappingDto>> getDiagnosaPRB(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/obatprb/{param}")
	public BpjsResponse<List<VClaimMappingDto>> getObatPRB(@PathVariable("param") String namaObat,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/pascapulang")
	public BpjsResponse<List<VClaimMappingDto>> getPascaPulang(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/ruangrawat")
	public BpjsResponse<List<VClaimMappingDto>> getRuangRawat(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/referensi/spesialistik")
	public BpjsResponse<List<VClaimMappingDto>> getSpesialistik(
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	// #endregion

	// #region Peserta

	@GetMapping("/Peserta/nokartu/{noKartu}/tglSEP/{tglSEP}")
	public BpjsResponse<BpjsPesertaResponseDto> getPesertaByNoKartu(@PathVariable("noKartu") String noKartu,
			@PathVariable("tglSEP") String tglSEP,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Peserta/nik/{nik}/tglSEP/{tglSEP}")
	public BpjsResponse<BpjsPesertaResponseDto> getPesertaByNik(@PathVariable("nik") String nik,
			@PathVariable("tglSEP") String tglSEP,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	// #endregion

	// #region SEP

	@GetMapping("/SEP/{noSep}")
	public BpjsResponse2<BpjsSepSearchDto> searchSEP(@PathVariable("noSep") String noSep,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/SEP/2.0/insert")
	public BpjsResponse<BpjsSepDto> insertSEPV2(@RequestBody BpjsRequestDto<RequestSepDtoV2> requestSepDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PutMapping("/SEP/2.0/update")
	public BpjsResponse<Object> updateSEPV2(@RequestBody BpjsRequestDto<RequestSepDtoV2> requestSepDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@DeleteMapping("/SEP/2.0/delete")
	public BpjsResponse2<Object> deleteSEPV2(@RequestBody BpjsRequestDto<RequestSepDtoV2> requestSepDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/Sep/pengajuanSEP")
	public BpjsResponse2<String> pengajuanSEP(
			@RequestBody BpjsRequestDto<RequestPengajuanSepDto> requestPengajuanSepDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/Sep/aprovalSEP")
	public BpjsResponse2<String> approvalSEP(
			@RequestBody BpjsRequestDto<RequestPengajuanSepDto> requestPengajuanSepDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PutMapping("/SEP/2.0/updtglplg")
	public BpjsResponse2<String> updateTglPulangSEPV2(
			@RequestBody BpjsRequestDto<RequestUpdateTglPulangDto> requestSepDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Sep/updtglplg/list/bulan/{bulan}/tahun/{tahun}/")
	public BpjsResponse2<BpjsListUpdateTglPulangListDto> getListUpdateTglPulangSEP(
			@PathVariable("bulan") int bulan,
			@PathVariable("tahun") int tahun,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/SEP/Internal/{noSep}")
	public BpjsResponse2<BpjsSepInternalListDto> getDataSepInternal(@PathVariable("noSep") String noSep,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@DeleteMapping("/SEP/Internal/delete")
	public BpjsResponse2<String> deleteSepInternal(
			@RequestBody BpjsRequestDto<RequestSepInternal> requestSepInternal,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/SEP/FingerPrint/Peserta/{noKartu}/TglPelayanan/{tglPelayanan}")
	public BpjsResponse2<BpjsFingerPrintStatusDto> getFingerPrintStatus(@PathVariable("noKartu") String noKartu,
			@PathVariable("tglPelayanan") String tglPelayanan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/SEP/FingerPrint/List/Peserta/TglPelayanan/{tglPelayanan}")
	public BpjsResponse<List<BpjsFingerPrintDto>> getFingerPrintList(
			@PathVariable("tglPelayanan") String tglPelayanan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/sep/KllInduk/List/{noKartu}")
	public BpjsResponse<List<BpjsSepKllDto>> getDataKecelakaanInduk(@PathVariable("noKartu") String noKartu,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/sep/JasaRaharja/Suplesi/{noKartu}/tglPelayanan/{tglPelayanan}")
	public BpjsResponse<List<BpjsSepSuplesiDto>> getPotensiSuplesiJasaRaharja(
			@PathVariable("noKartu") String noKartu,
			@PathVariable("tglPelayanan") String tglPelayanan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	// #endregion

	// #region Rujukan
	@GetMapping("/Rujukan/{noRujukan}")
	public BpjsResponse2<BpjsRujukanDto> getRujukanPCareByNoRujukan(@PathVariable("noRujukan") String noRujukan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/RS/{noRujukan}")
	public BpjsResponse2<BpjsRujukanDto> getRujukanRsByNoRujukan(@PathVariable("noRujukan") String noRujukan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/Peserta/{noKartu}")
	public BpjsResponse2<BpjsRujukanDto> getRujukanPCareByNoKartu(@PathVariable("noKartu") String noKartu,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/RS/Peserta/{noKartu}")
	public BpjsResponse2<BpjsRujukanDto> getRujukanRsByNoKartu(@PathVariable("noKartu") String noKartu,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/List/Peserta/{noKartu}")
	public BpjsResponse2<BpjsRujukanListDto> getRujukanPCareListByNoKartu(@PathVariable("noKartu") String noKartu,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/RS/List/Peserta/{noKartu}")
	public BpjsResponse2<BpjsRujukanListDto> getRujukanRsListByNoKartu(@PathVariable("noKartu") String noKartu,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/Rujukan/2.0/insert")
	public BpjsResponse2<BpjsRujukanDto> insertRujukanV2(
			@RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PutMapping("/Rujukan/2.0/Update")
	public BpjsResponse<Object> updateRujukanV2(@RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@DeleteMapping("/Rujukan/delete")
	public BpjsResponse2<String> deleteRujukan(@RequestBody BpjsRequestDto<RequestRujukanDto> requestRujukanDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/ListSarana/PPKRujukan/{ppkRujukan}")
	public BpjsResponse<List<SaranaDto>> getSarana(@PathVariable("ppkRujukan") String ppkRujukan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/ListSpesialistik/PPKRujukan/{ppkRujukan}/TglRujukan/{tglRujukan}")
	public BpjsResponse<List<SpesialistikRujukanDto>> getSpesialistikRujukan(
			@PathVariable("ppkRujukan") String ppkRujukan, @PathVariable("tglRujukan") String tglRujukan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/Khusus/List/Bulan/{bulan}/Tahun/{tahun}")
	public BpjsResponse<List<RujukanKhususDto>> getRujukanKhusus(@PathVariable("bulan") String bulan,
			@PathVariable("tahun") String tahun, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/Rujukan/Khusus/insert")
	public BpjsResponse<RujukanKhususDto> insertRujukanKhusus(
			@RequestBody RequestRujukanKhususDto rqRujukanKhususDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@DeleteMapping("/Rujukan/Khusus/delete")
	public BpjsResponse<String> deleteRujukanKhusus(
			@RequestBody BpjsRequestDto<RequestRujukanKhususDto> rqRujukanKhususDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Rujukan/Keluar/{noRujukan}")
	public BpjsResponse<Object> getRujukanKeluarByNoRujukan(
			@PathVariable String noRujukan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	// #endregion

	// #region Monitoring

	@GetMapping("/Monitoring/Kunjungan/Tanggal/{tglSEP}/JnsPelayanan/{jnsPelayanan}")
	public BpjsResponse<List<BpjsKunjunganDto>> getDataKunjungan(@PathVariable("tglSEP") String tglSEP,
			@PathVariable("jnsPelayanan") String jnsPelayanan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/Monitoring/Klaim/Tanggal/{tglPulang}/JnsPelayanan/{jnsPelayanan}/Status/{status}")
	public BpjsResponse<List<BpjsKlaimDto>> getDataKlaim(@PathVariable("tglPulang") String tglPulang,
			@PathVariable("jnsPelayanan") String jnsPelayanan, @PathVariable("status") String status,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/monitoring/HistoriPelayanan/NoKartu/{noKartu}/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}")
	public BpjsResponse<List<BpjsKunjunganDto>> getHistoriPelayanan(@PathVariable("noKartu") String noKartu,
			@PathVariable("tglAwal") String tglAwal, @PathVariable("tglAkhir") String tglAkhir,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/monitoring/JasaRaharja/tglMulai/{tglMulai}/tglAkhir/{tglAkhir}")
	public BpjsResponse<List<BpjsJaminanDto>> getDataKlaimJasaRaharja(@PathVariable("tglMulai") String tglMulai,
			@PathVariable("tglAkhir") String tglAkhir,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	// #endregion

	// #region RencanaKontrol

	@GetMapping("/RencanaKontrol/ListRencanaKontrol/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}/filter/{filter}")
	public BpjsResponse<List<RencanaKontrolDto>> getRencanaKontrol(@PathVariable("tglAwal") String tglAwal,
			@PathVariable("tglAkhir") String tglAkhir, @PathVariable("filter") String filter,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/RencanaKontrol/JadwalPraktekDokter/JnsKontrol/{jnsKontrol}/KdPoli/{kdPoli}/TglRencanaKontrol/{tglRencanaKontrol}")
	public BpjsResponse<List<DataDokterDto>> getDataDokter(@PathVariable("jnsKontrol") String jnsKontrol,
			@PathVariable("kdPoli") String kdPoli,
			@PathVariable("tglRencanaKontrol") String tglRencanaKontrol,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/RencanaKontrol/ListSpesialistik/JnsKontrol/{jnsKontrol}/nomor/{nomor}/TglRencanaKontrol/{tglRencanaKontrol}")
	public BpjsResponse<List<SpesialistikDto>> getSpesialistik(@PathVariable("jnsKontrol") String jnsKontrol,
			@PathVariable("nomor") String nomor,
			@PathVariable("tglRencanaKontrol") String tglRencanaKontrol,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/RencanaKontrol/noSuratKontrol/{noSuratKontrol}")
	public BpjsResponse2<RencanaKontrolDto> getRencanaKontrolByNoSurat(
			@PathVariable("noSuratKontrol") String noSuratKontrol,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/RencanaKontrol/nosep/{noSep}")
	public BpjsResponse2<BpjsSepDto> getRencanaKontrolByNoSep(@PathVariable("noSep") String noSep,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/RencanaKontrol/insert")
	public BpjsResponse2<RencanaKontrolCrudDto> insertRencanaKontrol(
			@RequestBody BpjsRequestDto2<RequestRencanaKontrolDto> rqRencanaKontrolDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PutMapping("/RencanaKontrol/Update")
	public BpjsResponse2<RencanaKontrolCrudDto> updateRencanaKontrol(
			@RequestBody BpjsRequestDto2<RequestRencanaKontrolDto> rqRencanaKontrolDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@DeleteMapping("/RencanaKontrol/Delete")
	public BpjsResponse<String> deleteRencanaKontrol(
			@RequestBody BpjsRequestDto<RequestRencanaKontrolDto> rqRencanaKontrolDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PostMapping("/RencanaKontrol/insertSPRI")
	public BpjsResponse2<SpriDto> insertSpri(@RequestBody BpjsRequestDto2<RequestSpriDto> rqSpriDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PutMapping("/RencanaKontrol/UpdateSPRI")
	public BpjsResponse2<SpriDto> updateSpri(@RequestBody BpjsRequestDto2<RequestSpriDto> rqSpriDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	// #endregion

	// #region PRB
	@PostMapping("/PRB/insert")
	public BpjsResponse2<BpjsRujukBalikDto> insertRujukBalik(
			@RequestBody BpjsRequestDto<RequestRujukBalikDto> requestRujukBalikDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@PutMapping("/PRB/Update")
	public BpjsResponse2<String> updateRujukBalik(
			@RequestBody BpjsRequestDto<RequestRujukBalikDto> requestRujukBalikDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@DeleteMapping("/PRB/Delete")
	public BpjsResponse2<String> deleteRujukBalik(
			@RequestBody BpjsRequestDto<RequestRujukBalikDto> requestRujukBalikDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/prb/{noSrb}/nosep/{noSep}")
	public BpjsResponse<BpjsRujukBalikDto> getRujukBalik(@PathVariable("noSrb") String noSrb,
			@PathVariable("noSep") String noSep, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/prb/tglMulai/{tglMulai}/tglAkhir/{tglAkhir}")
	public BpjsResponse<BpjsRujukBalikListDto> getRujukBalikList(@PathVariable("tglMulai") String tglMulai,
			@PathVariable("tglAkhir") String tglAkhir,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	// #endregion
}
