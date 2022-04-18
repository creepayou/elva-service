package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsEncResponse;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.DecryptUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.JsonUtil;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class FeignClientConfig extends Client.Default {

    private BpjsConsumerService bpjsConsumerService;

    public FeignClientConfig(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier,
            BpjsConsumerService bpjsConsumerService) {
        super(sslContextFactory, hostnameVerifier);
        this.bpjsConsumerService = bpjsConsumerService;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        String entityCode = request.requestTemplate().headers().get(Constant.MT_ENTITY_CODE).toArray()[0].toString();
        String reqTimestamp = request.requestTemplate().headers().get("X-timestamp").toArray()[0].toString();
        try {
            log.info(new String(request.body()));
        } catch (Exception e) {
            // ignore
        }
        Response response = super.execute(request, options);
        if (entityCode != null) {
            return response.toBuilder().status(500).build();
        }
        InputStream bodyStream = response.body().asInputStream();
        String responseBody = StreamUtils.copyToString(bodyStream, StandardCharsets.UTF_8);
        BpjsEncResponse bpjsResponse = null;
        try {
            bpjsResponse = JsonUtil.fromJson(responseBody, BpjsEncResponse.class);
        } catch (Exception e) {
            return response.toBuilder().body(responseBody, StandardCharsets.UTF_8).build();
        }

        BpjsConsumerDto bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode);

        String key = bpjsConsumerDto.getConsumerId() + bpjsConsumerDto.getConsumerSecret() + reqTimestamp;

        if ((bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_200)
                || bpjsResponse.getMetaData().getCode().equals(Constant.METADATA_OK_1))
                && !ObjectUtils.isEmpty(bpjsResponse.getResponse())) {
            String decrypted = decryptResponse(JsonUtil.toJsonString(bpjsResponse.getResponse()), key);

            try {
                Object decryptedResult = JsonUtil.fromJson(decrypted, new TypeReference<Object>() {
                });
                bpjsResponse.setResponse(decryptedResult);
            } catch (Exception e) {
                bpjsResponse.setResponse(decrypted);
            }
        } else {
            if (!ObjectUtils.isEmpty(bpjsResponse.getResponse())) {
                log.info(decryptResponse(bpjsResponse.getResponse().toString(), key));
            }
            bpjsResponse.setResponse(null);
        }
        String jsonResult = JsonUtil.toJsonString(bpjsResponse);
        return response.toBuilder().body(jsonResult, StandardCharsets.UTF_8).build();
    }

    private String decryptResponse(String response, String key) {
        try {
            return DecryptUtil.decrypt(response.replaceAll("\"", ""), key);
        } catch (Exception e) {
            return response;
        }
    }
}