package com.rsmurniteguh.bpjs.bpjsservice.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.apachecommons.CommonsLog;

@Component
@CommonsLog
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        String logString = String.format(
                "Method: %s, URI: %s, Entity: %s, Request Time: %d ms, Status: %d, Parameter: %s, Body: %s",
                request.getMethod(), request.getRequestURI(), request.getHeader(Constant.MT_ENTITY_CODE), executeTime,
                response.getStatus(), parameterString(request.getParameterMap()), bodyString(request));
        if (!HttpStatus.valueOf(response.getStatus()).isError()) {
            log.info(logString);
        } else {
            log.error(logString);
        }
    }

    private String parameterString(Map<String, String[]> parameterMap) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!parameterMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                stringBuilder.append(entry.getKey() + ": " + entry.getValue()[0] + ",");
            }
        }

        String parameterString = stringBuilder.toString();
        if (parameterString.lastIndexOf(',') != -1) {
            return "(" + parameterString.substring(0, parameterString.lastIndexOf(',')) + ")";
        }
        return stringBuilder.toString();
    }

    private String bodyString(HttpServletRequest request) {
        try {
            if (request.getMethod().equals("POST")) {
                return IOUtils.toString(request.getReader());
            }
        } catch (Exception e) {
            // ignore
        }
        return "";
    }
}
