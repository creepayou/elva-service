package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;

public class CustomJsonDateDeserializer2 extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String string = jsonParser.getText();

        try {
            SimpleDateFormat formatter6=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");  
            Date date6=formatter6.parse(string); 
            Timestamp str =  new java.sql.Timestamp(date6.getTime());
            return str;
          } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
          }
    }
  }
