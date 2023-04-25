package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.sql.Date;
import java.sql.Timestamp;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class BpjsRequestErrorConfig implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String arg0, Response arg1) {
        Exception exception = defaultErrorDecoder.decode(arg0, arg1);

        if (exception instanceof RetryableException) {
            return exception;
        }
        if (arg1.status() == 500 || arg1.status() == 503) {
            return new RetryableException(arg1.status(), arg1.reason(), arg1.request().httpMethod(),
                    new Date(new Timestamp(2000L).getTime()), arg1.request());
        }

        return exception;
    }

}
