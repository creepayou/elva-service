package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;

public class BpjsResponseUtil {

    private static final String BPJS_MESSAGE = "BPJS: ";

    private BpjsResponseUtil() {
    }

    public static <T> Map<String, T> handleBpjsResponse(BpjsResponse<T> bpjsResponse) throws BusinessException {
        if (bpjsResponse.getMetaData() == null || bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_1)
                || bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200)) {
            return bpjsResponse.getResponse();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }

    public static <T> T handleBpjsResponse(BpjsResponse2<T> bpjsResponse) throws BusinessException {
        if (bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_1)
                || bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200)) {
            return bpjsResponse.getResponse();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }

    public static String handleBpjsResponseMessage(BpjsResponse<Object> bpjsResponse) throws BusinessException {
        if(bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200)){
            return bpjsResponse.getMetaData().getMessage();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }

    public static String handleBpjsResponseMessage(BpjsResponse2<Object> bpjsResponse) throws BusinessException {
        if(bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200)){
            return bpjsResponse.getMetaData().getMessage();
        } else {
            throw new BusinessException(BPJS_MESSAGE + bpjsResponse.getMetaData().getMessage());
        }
    }
}
