package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;

public class CustomJsonDateTimeSerializer extends JsonSerializer<Timestamp>{

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(Constant.TIMEZONE_JKT)).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    
}
