package com.rsmurniteguh.elva.elvaservice.exception;

import com.rsmurniteguh.elva.elvaservice.base.model.ErrorDto;

import lombok.Getter;

public class ServiceException extends Exception {
    @Getter
    private final ErrorDto error;

    public ServiceException(ErrorDto error) {
        this.error = error;
    }
}
