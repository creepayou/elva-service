package com.rsmurniteguh.bpjs.bpjsservice.base.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;
import lombok.extern.apachecommons.CommonsLog;

@ControllerAdvice
@CommonsLog
public class ErrorControllerAdvice {

    private static final List<String> BPJS_PROXY_FEIGN_NAME = Arrays.asList(Constant.VCLAIM_FEIGN_NAME,
            Constant.ANTREAN_FEIGN_NAME, Constant.APLICARES_FEIGN_NAME);

    private static final List<Integer> BPJS_UNAVAILABLE_STATUS = Arrays.asList(403, 503);

    private static final String ERROR_FORMAT = "Message: %s, Path: %s";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseSts<Object>> handleBusinessException(
            Exception e,
            HttpServletRequest request) {
        log.warn(String.format(ERROR_FORMAT, e.getMessage(), request.getRequestURI()));
        return ResponseEntity.ok().body(ResponseSts.onFail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseSts<Object>> handleAllUncaughtException(
            Exception e,
            HttpServletRequest request) {
        if (e instanceof FeignException) {
            FeignException fe = (FeignException) e;
            if (BPJS_PROXY_FEIGN_NAME.contains(fe.request().requestTemplate().feignTarget().name())) {
                if (BPJS_UNAVAILABLE_STATUS.contains(fe.status())) {
                    log.warn(String.format(ERROR_FORMAT, e.getMessage(), request.getRequestURI()));
                    return ResponseEntity.ok().body(ResponseSts.onFail("BPJS API Temporarily Unavailable"));
                } else if (fe.getMessage().contains("Read timed out")) {
                    log.warn(String.format(ERROR_FORMAT, e.getMessage(), request.getRequestURI()));
                    return ResponseEntity.ok().body(ResponseSts.onFail(String
                            .format("Request Timeout when connecting to BPJS API! (reason: %s)", fe.getMessage())));
                }
            }
        } else if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            return ResponseEntity.ok()
                    .body(ResponseSts.onError(se.getError()));
        }
        return ResponseEntity.ok()
                .body(ResponseSts.onError(e.getMessage(), request.getRequestURI(), ExceptionUtils.getStackTrace(e)));
    }
}
