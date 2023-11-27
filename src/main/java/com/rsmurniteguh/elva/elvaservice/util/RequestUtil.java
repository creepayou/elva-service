package com.rsmurniteguh.elva.elvaservice.util;

import com.rsmurniteguh.elva.elvaservice.base.constant.Constant;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {

    private RequestUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getEntityCode(HttpServletRequest request) {
        return request.getHeader(Constant.MT_ENTITY_CODE);
    }
}
