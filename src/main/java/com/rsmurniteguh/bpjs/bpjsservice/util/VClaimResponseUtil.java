package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;

public class VClaimResponseUtil {
    
    private VClaimResponseUtil(){}

    public static <T> Map<String, T> handleVClaimResponse(VClaimResponse<T> vClaimResponse) throws BusinessException {
        if(vClaimResponse.getMetaData().getCode().equals(Constant.HTTP_OK)){
            return vClaimResponse.getResponse();
        } else {
            throw new BusinessException(vClaimResponse.getMetaData().getMessage());
        }
    }

    public static <T> T handleVClaimResponse(VClaimResponse2<T> vClaimResponse) throws BusinessException {
        if(vClaimResponse.getMetaData().getCode().equals(Constant.HTTP_OK)){
            return vClaimResponse.getResponse();
        } else {
            throw new BusinessException(vClaimResponse.getMetaData().getMessage());
        }
    }
}
