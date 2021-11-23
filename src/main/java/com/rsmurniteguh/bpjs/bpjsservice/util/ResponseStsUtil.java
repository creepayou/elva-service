package com.rsmurniteguh.bpjs.bpjsservice.util;

import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BpjsServiceException;

import org.springframework.stereotype.Component;

@Component
public class ResponseStsUtil {
    private ResponseStsUtil() {}

    public static <T> T handleResponseSts(ResponseSts<T> response) throws BpjsServiceException {
        if(response.isSuccess()){
            return response.getData();
        } else {
            throw new BpjsServiceException(response.getMessage());
        }
    }
}
