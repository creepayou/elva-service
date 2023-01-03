package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.controller.BpjsConsumerController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.util.ResponseStsUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class BpjsApotekRequestConfig {

    private final BpjsConsumerController bpjsConsumerController;

    public BpjsApotekRequestConfig(BpjsConsumerController bpjsConsumerController) {
        this.bpjsConsumerController = bpjsConsumerController;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (RequestTemplate requestTemplate) -> {
            try {
                String entityCode = requestTemplate.headers().get(Constant.MT_ENTITY_CODE).toArray()[0].toString();

                if (StringUtils.hasText(entityCode)) {
                    BpjsConsumerDto bpjsConsumerDto = ResponseStsUtil.handleResponseSts(
                            bpjsConsumerController.getBpjsConsumer(entityCode));
                    final Long unixTime = System.currentTimeMillis() / 1000L;
                    final String salt = bpjsConsumerDto.getConsumerId() + "&" + unixTime;
                    requestTemplate.header(Constant.X_CONS_ID, bpjsConsumerDto.getConsumerId());
                    requestTemplate.header(Constant.X_TIMESTAMP, unixTime + "");
                    requestTemplate.header(Constant.X_SIGNATURE,
                            generateHmacSHA256Signature(salt, bpjsConsumerDto.getConsumerSecret()));
                    if (StringUtils.hasText(bpjsConsumerDto.getUserKey())) {
                        requestTemplate.header("user_key", bpjsConsumerDto.getUserKey());
                    }
                }
            } catch (BusinessException e) {
                log.error(e.getMessage());
            } catch (IllegalStateException | GeneralSecurityException | ServiceException e) {
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
