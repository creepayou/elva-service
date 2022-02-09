package com.rsmurniteguh.bpjs.bpjsservice.util;

import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ResponseStsUtil {
    private ResponseStsUtil() {
    }

    public static <T> T handleResponseSts(ResponseSts<T> response) throws BusinessException, ServiceException {
        if (!ObjectUtils.isEmpty(response.getError())) {
            throw new ServiceException(response.getError());
        }

        if (response.isSuccess()) {
            return response.getData();
        } else {
            throw new BusinessException(response.getMessage());
        }
    }
}
