package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsAntreanDashboardPerTanggalDto {
    @JsonAlias("kdppk")
    private String kodePPK;
    @JsonAlias("waktu_task1")
    private Long waktuTask1;
    @JsonAlias("avg_waktu_task4")
    private Long avgWaktuTask4;
    @JsonAlias("jumlah_antrean")
    private Long jumlahAntrean;
    @JsonAlias("avg_waktu_task3")
    private Long avgWaktuTask3;
    @JsonAlias("namapoli")
    private String namaPoli;
    @JsonAlias("avg_waktu_task6")
    private Long avgWaktuTask6;
    @JsonAlias("avg_waktu_task5")
    private Long avgWaktuTask5;
    @JsonAlias("nmppk")
    private String namaPPK;
    @JsonAlias("avg_waktu_task2")
    private Long avgWaktuTask2;
    @JsonAlias("avg_waktu_task1")
    private Long avgWaktuTask1;
    @JsonAlias("kodepoli")
    private String kodePoli;
    @JsonAlias("waktu_task5")
    private Long waktuTask5;
    @JsonAlias("waktu_task4")
    private Long waktuTask4;
    @JsonAlias("waktu_task3")
    private Long waktuTask3;
    @JsonAlias("insertdate")
    private String insertDate;
    @JsonAlias("tanggal")
    private String tanggal;
    @JsonAlias("waktu_task2")
    private Long waktuTask2;
    @JsonAlias("waktu_task6")
    private Long waktuTask6;
}
