package com.rsmurniteguh.elva.elvaservice.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.rsmurniteguh.elva.elvaservice.base.model.ResponseSts;
import com.rsmurniteguh.elva.elvaservice.exception.BusinessException;
import com.rsmurniteguh.elva.elvaservice.exception.ServiceException;

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
