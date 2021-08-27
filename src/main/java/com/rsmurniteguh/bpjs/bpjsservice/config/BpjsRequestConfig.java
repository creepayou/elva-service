package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class BpjsRequestConfig implements RequestInterceptor {

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @Autowired
    private HttpServletRequest request;    

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            String entityCode = request.getHeader("mt-entitycode");

            if(StringUtils.hasText(entityCode)){
                BpjsConsumerDto bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode);
                String signature = generateHmacSHA256Signature(bpjsConsumerDto.getConsumerId(), bpjsConsumerDto.getConsumerSecret());
                
                requestTemplate.header("X-cons-id", bpjsConsumerDto.getConsumerId());
                requestTemplate.header("X-timestamp", (System.currentTimeMillis() / 1000L) + "");
                requestTemplate.header("X-signature", signature);
            }
        } catch (IllegalStateException e) {
            // Do nothing
        } catch (GeneralSecurityException e) {
            log.error(e.getMessage(), e);
        }
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
