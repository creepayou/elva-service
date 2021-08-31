package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;

public class CustomJsonDateDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String string = jsonParser.getText();

        if(string.contains("+")) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(string);
            return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
        }
        LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of(Constant.TIMEZONE_JKT));

        return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(ZoneId.of(Constant.TIMEZONE_UTC)).toLocalDateTime());
    }
  }
