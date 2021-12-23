package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.util.List;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import org.springframework.stereotype.Component;

@Component
public class CustomBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, 
           BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {

        for (BeanPropertyWriter beanProperty : beanProperties) {
            if (beanProperty.getAnnotation(EmptyIfNull.class) != null) {
                beanProperty.assignNullSerializer(new CustomNullSerializer());
            }
        }

        return beanProperties;
    }
}
