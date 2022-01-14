package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class BpjsRequestConfig {

    @Autowired
    private BpjsConsumerService bpjsConsumerService;
    
    @Bean
    public RequestInterceptor requestInterceptor(){
        return (RequestTemplate requestTemplate) -> {
            try {
                String entityCode = requestTemplate.headers().get(Constant.MT_ENTITY_CODE).toArray()[0].toString();

                if(StringUtils.hasText(entityCode)){
                    BpjsConsumerDto bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode);
                    final Long unixTime = System.currentTimeMillis() / 1000L;
                    final String salt = bpjsConsumerDto.getConsumerId() + "&" + unixTime;
                    requestTemplate.header("X-cons-id", bpjsConsumerDto.getConsumerId());
                    requestTemplate.header("X-timestamp", unixTime + "");
                    requestTemplate.header("X-signature", generateHmacSHA256Signature(salt, bpjsConsumerDto.getConsumerSecret()));
                    requestTemplate.header("user_key", bpjsConsumerDto.getUserKey());
                    requestTemplate.header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                }
            } catch (IllegalStateException | GeneralSecurityException e) {
                log.error(e.getMessage(), e);
            }
        };
    }

    private String generateHmacSHA256Signature(String data, String key) throws GeneralSecurityException {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            byte[] hmacData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmacData);
        } catch (GeneralSecurityException e) {
            throw new GeneralSecurityException(e);
        }
    }

}
