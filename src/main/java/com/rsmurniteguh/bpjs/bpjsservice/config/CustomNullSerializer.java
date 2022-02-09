package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomNullSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException {
        jgen.writeString("");
    }
}