package com.rsmurniteguh.bpjs.bpjsservice.base.controller;

import javax.servlet.http.HttpServletRequest;

import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.apachecommons.CommonsLog;

@ControllerAdvice
@CommonsLog
public class ErrorControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseSts<Object>> handleBusinessException(
            Exception e) {
        return ResponseEntity.ok().body(ResponseSts.onFail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseSts<Object>> handleAllUncaughtException(
            Exception e,
            HttpServletRequest request) {
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            return ResponseEntity.ok()
                    .body(ResponseSts.onError(se.getError().getMessage(), se.getError().getPath(),
                            se.getError().getStackTrace()));
        }
        log.error(e.getMessage(), e);
        return ResponseEntity.ok()
                .body(ResponseSts.onError(e.getMessage(), request.getRequestURI(), ExceptionUtils.getStackTrace(e)));
    }
}
