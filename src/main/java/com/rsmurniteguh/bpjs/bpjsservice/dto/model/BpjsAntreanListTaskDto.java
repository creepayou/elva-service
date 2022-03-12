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
public class BpjsAntreanListTaskDto {
    @JsonAlias("wakturs")
    private String waktuRs;
    @JsonAlias("waktu")
    private String waktu;
    @JsonAlias("taskname")
    private String taskName;
    @JsonAlias("taskid")
    private Long taskId;
    @JsonAlias("kodebooking")
    private String kodeBooking;
}


