package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.AplicaresResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.AplicaresResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;

public class AplicaresResponseUtil {
    
    private AplicaresResponseUtil(){}

    public static <T> Map<String, T> handleAplicaresResponse(AplicaresResponse<T> aplicaresResponse) throws BusinessException {
        if(aplicaresResponse.getMetadata().getCode().equals(Constant.HTTP_OK_APLICARE)){
            return aplicaresResponse.getResponse();
        } else {
            throw new BusinessException(aplicaresResponse.getMetadata().getMessage());
        }
    }

    public static <T> T handleAplicaresResponse(AplicaresResponse2<T> aplicaresResponse) throws BusinessException {
        if(aplicaresResponse.getMetaData().getCode().equals(Constant.HTTP_OK_APLICARE)){
            return aplicaresResponse.getResponse();
        } else {
            throw new BusinessException(aplicaresResponse.getMetaData().getMessage());
        }
    }
    
    public static String handleAplicaresResponseMessage(AplicaresResponse<Object> aplicaresResponse) throws BusinessException {
        if(aplicaresResponse.getMetadata().getCode().equals(Constant.HTTP_OK_APLICARE)){
            return aplicaresResponse.getMetadata().getMessage();
        } else {
            throw new BusinessException(aplicaresResponse.getMetadata().getMessage());
        }
    }

    public static String handleAplicaresResponseMessage(AplicaresResponse2<Object> aplicaresResponse) throws BusinessException {
        if(aplicaresResponse.getMetaData().getCode().equals(Constant.HTTP_OK_APLICARE)){
            return aplicaresResponse.getMetaData().getMessage();
        } else {
            throw new BusinessException(aplicaresResponse.getMetaData().getMessage());
        }
    }
}
