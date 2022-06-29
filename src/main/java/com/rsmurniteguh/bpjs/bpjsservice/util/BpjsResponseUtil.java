package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;

import org.springframework.util.StringUtils;

public class BpjsResponseUtil {

    private static final String BPJS_MESSAGE = "BPJS: ";

    private BpjsResponseUtil() {
    }

    private static <T> boolean validateBpjsResponse(BpjsResponse<T> bpjsResponse) {
        return (bpjsResponse.getMetaData() == null || (StringUtils.hasText(bpjsResponse.getMetaData().getCode())
                && (bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_1)
                        || bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200))));
    }

    private static <T> boolean validateBpjsResponse2(BpjsResponse2<T> bpjsResponse) {
        return (bpjsResponse.getMetaData() == null || (bpjsResponse.getMetaData().getCode() != null
                && (bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_1)
                        || bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200))));
    }

    public static <T> Map<String, T> handleBpjsResponse(BpjsResponse<T> bpjsResponse) throws BusinessException {
        if (validateBpjsResponse(bpjsResponse)) {
            return bpjsResponse.getResponse();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }

    public static <T> T handleBpjsResponse(BpjsResponse2<T> bpjsResponse) throws BusinessException {
        if (validateBpjsResponse2(bpjsResponse)) {
            return bpjsResponse.getResponse();
        } else {
            if(bpjsResponse.getResponse() != null && bpjsResponse.getResponse() instanceof String) {
                throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage() + ", " + bpjsResponse.getResponse());
            }
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }

    public static String handleBpjsResponseMessage(BpjsResponse<Object> bpjsResponse) throws BusinessException {
        if (validateBpjsResponse(bpjsResponse)) {
            return bpjsResponse.getMetaData().getMessage();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }

    public static String handleBpjsResponseMessage(BpjsResponse2<Object> bpjsResponse) throws BusinessException {
        if (validateBpjsResponse2(bpjsResponse)) {
            return bpjsResponse.getMetaData().getMessage();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }
}
