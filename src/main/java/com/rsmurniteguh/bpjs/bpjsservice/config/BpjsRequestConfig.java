package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class BpjsRequestConfig {

    private final List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryList;

    @Autowired
    public BpjsRequestConfig(List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryList) {
        this.bpjsConsumerWithCategoryList = bpjsConsumerWithCategoryList;
    }

    private BpjsConsumerWithCategoryDto filterBpjsConsumerWithCategoryDto(
            BpjsConsumerCategoryType bpjsConsumerCategoryType, String entityCode) throws BusinessException {
        for (BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto : bpjsConsumerWithCategoryList) {
            if (bpjsConsumerWithCategoryDto.getEntityCode().equals(entityCode)) {
                if (bpjsConsumerCategoryType == null)
                    return new BpjsConsumerWithCategoryDto().setConsumerId(bpjsConsumerWithCategoryDto.getConsumerId())
                            .setConsumerSecret(bpjsConsumerWithCategoryDto.getConsumerSecret());
                else if (bpjsConsumerWithCategoryDto.getCategory().equals(bpjsConsumerCategoryType)) {
                    return bpjsConsumerWithCategoryDto;
                }
            }
        }
        throw new BusinessException("BPJS Cons Id not found! Entity: " + entityCode);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (RequestTemplate requestTemplate) -> {
            try {
                String entityCode = requestTemplate.headers().get(Constant.MT_ENTITY_CODE).toArray()[0].toString();

                if (StringUtils.hasText(entityCode)) {
                    BpjsConsumerCategoryType bpjsConsumerCategoryType = BpjsConsumerCategoryType
                            .fromType(requestTemplate.feignTarget().name());
                    BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto = filterBpjsConsumerWithCategoryDto(
                            bpjsConsumerCategoryType, entityCode);
                    final Long unixTime = System.currentTimeMillis() / 1000L;
                    final String salt = bpjsConsumerWithCategoryDto.getConsumerId() + "&" + unixTime;
                    requestTemplate.header("X-cons-id", bpjsConsumerWithCategoryDto.getConsumerId());
                    requestTemplate.header("X-timestamp", unixTime + "");
                    requestTemplate.header("X-signature",
                            generateHmacSHA256Signature(salt, bpjsConsumerWithCategoryDto.getConsumerSecret()));
                    if (bpjsConsumerWithCategoryDto.getUserKey() != null) {
                        requestTemplate.header("user_key", bpjsConsumerWithCategoryDto.getUserKey());
                    }
                    if (bpjsConsumerCategoryType != null
                            && bpjsConsumerCategoryType.equals(BpjsConsumerCategoryType.VCLAIM)) {
                        requestTemplate.header(Constant.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                    } else {
                        requestTemplate.header(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    }
                }
            } catch (IllegalStateException | GeneralSecurityException | BusinessException e) {
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
