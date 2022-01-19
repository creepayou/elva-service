package com.rsmurniteguh.bpjs.bpjsservice.base.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonInclude(value = Include.NON_EMPTY)
public class ErrorDto implements Serializable {
   private Timestamp timestamp;
   private String message;
   private String path;
   private String stackTrace;
}
