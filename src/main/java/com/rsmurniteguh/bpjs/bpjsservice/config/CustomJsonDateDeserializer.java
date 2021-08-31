package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;

public class CustomJsonDateDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String string = jsonParser.getText();

        if(string.contains("+")) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(string);
            return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
        }

        return DateUtil.formatStringWithTimezone(string, Constant.TIMEZONE_JKT);
    }
  }
