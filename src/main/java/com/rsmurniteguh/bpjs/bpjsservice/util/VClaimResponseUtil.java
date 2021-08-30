package com.rsmurniteguh.bpjs.bpjsservice.util;

import java.io.IOException;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse;

public class VClaimResponseUtil {
    
    private VClaimResponseUtil(){}

    public static <T> Map<String, T> handleVClaimResponse(VClaimResponse<T> vClaimResponse) throws IOException{
        if(vClaimResponse.getMetaData().getCode().equals("200")){
            return vClaimResponse.getResponse();
        } else {
            throw new IOException(vClaimResponse.getMetaData().getMessage());
        }
    } 
}
