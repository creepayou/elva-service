package com.rsmurniteguh.bpjs.bpjsservice.exception;

import com.rsmurniteguh.bpjs.bpjsservice.base.model.ErrorDto;

import lombok.Getter;

public class ServiceException extends Exception {
    @Getter
    private final ErrorDto error;

    public ServiceException(ErrorDto error) {
        this.error = error;
    }
}
