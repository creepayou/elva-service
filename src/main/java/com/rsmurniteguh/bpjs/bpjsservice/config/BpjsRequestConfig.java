package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.controller.BpjsConsumerController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;
import com.rsmurniteguh.bpjs.bpjsservice.util.ResponseStsUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class BpjsRequestConfig {

    private final BpjsConsumerController bpjsConsumerController;

    public BpjsRequestConfig(BpjsConsumerController bpjsConsumerController) {
        this.bpjsConsumerController = bpjsConsumerController;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (RequestTemplate requestTemplate) -> {
            try {
                String entityCode = requestTemplate.headers().get(Constant.MT_ENTITY_CODE).toArray()[0].toString();

                if (StringUtils.hasText(entityCode)) {
                    BpjsConsumerCategoryType category = BpjsConsumerCategoryType
                            .fromType(requestTemplate.feignTarget().name());

                    BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto = ResponseStsUtil.handleResponseSts(
                            bpjsConsumerController.getBpjsConsumerWithCategory(category, entityCode));
                    final Long unixTime = System.currentTimeMillis() / 1000L;
                    final String salt = bpjsConsumerWithCategoryDto.getConsumerId() + "&" + unixTime;
                    requestTemplate.header(Constant.X_CONS_ID, bpjsConsumerWithCategoryDto.getConsumerId());
                    requestTemplate.header(Constant.X_TIMESTAMP, unixTime + "");
                    requestTemplate.header(Constant.X_SIGNATURE,
                            generateHmacSHA256Signature(salt, bpjsConsumerWithCategoryDto.getConsumerSecret()));
                    if (StringUtils.hasText(bpjsConsumerWithCategoryDto.getUserKey())) {
                        requestTemplate.header("user_key", bpjsConsumerWithCategoryDto.getUserKey());
                    }
                    if (category != null
                            && category.equals(BpjsConsumerCategoryType.VCLAIM)) {
                        requestTemplate.header(Constant.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                    } else {
                        requestTemplate.header(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
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
