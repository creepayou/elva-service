package com.rsmurniteguh.bpjs.bpjsservice.exception;

import com.rsmurniteguh.bpjs.bpjsservice.base.model.ErrorDto;

import lombok.Getter;

public class BusinessException extends Exception {

    @Getter
    private final ErrorDto error;

    public BusinessException(String ex) {
        super(ex);
        this.error = null;
    }

    public BusinessException(ErrorDto error) {
        this.error = error;
    }
}
