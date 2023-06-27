package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomJsonDateSerializer extends JsonSerializer<Timestamp>{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Map<String, String> entityTimeZone;

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String entityCode = RequestUtil.getEntityCode(request);
        jsonGenerator.writeString(DateUtil.formatTimestampWithTimezone(timestamp, entityTimeZone.get(entityCode)));
    }
    
}
