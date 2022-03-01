package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomJsonDateDeserializer extends JsonDeserializer<Timestamp> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Map<String, String> entityTimeZone;

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String string = jsonParser.getText();

        if (string.contains("+")) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(string);
            return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
        }

        String entityCode = RequestUtil.getEntityCode(request);

        return DateUtil.formatStringWithTimezone(string, entityTimeZone.get(entityCode));
    }
}
