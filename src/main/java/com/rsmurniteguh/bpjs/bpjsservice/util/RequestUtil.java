package com.rsmurniteguh.bpjs.bpjsservice.util;

import jakarta.servlet.http.HttpServletRequest;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;

public class RequestUtil {

    private RequestUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getEntityCode(HttpServletRequest request) {
        return request.getHeader(Constant.MT_ENTITY_CODE);
    }
}
