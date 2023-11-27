package com.rsmurniteguh.elva.elvaservice.base.model;

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
@JsonInclude(value = Include.NON_EMPTY)
@ToString
public class ErrorDto implements Serializable {
   private Timestamp timestamp;
   private String entityCode;
   private String message;
   private String path;
   private String param;
   private String stackTrace;
}
