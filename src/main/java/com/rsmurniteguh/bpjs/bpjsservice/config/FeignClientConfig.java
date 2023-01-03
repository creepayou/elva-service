package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsEncResponse;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.DecryptUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.JsonUtil;

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
        if (request.requestTemplate().headers().get(Constant.X_TIMESTAMP) == null) {
            throw new IOException("Bpjs Cons Id Not Found");
        }
        String reqTimestamp = request.requestTemplate().headers().get(Constant.X_TIMESTAMP).toArray()[0].toString();
        String consId = request.requestTemplate().headers().get(Constant.X_CONS_ID).toArray()[0].toString();
        try {
            log.info(new String(request.body()));
        } catch (Exception e) {
            // ignore
        }
        Response response = super.execute(request, options);
        InputStream bodyStream = response.body().asInputStream();
        String responseBody = StreamUtils.copyToString(bodyStream, StandardCharsets.UTF_8);
        BpjsEncResponse bpjsResponse = null;
        try {
            bpjsResponse = JsonUtil.fromJson(responseBody, BpjsEncResponse.class);
        } catch (Exception e) {
            return response.toBuilder().body(responseBody, StandardCharsets.UTF_8).build();
        }

		Map<String, Object> param = new HashMap<>();
		param.put("entity_code", entityCode);
		param.put("consumer_id", consId);
        
		BpjsConsumerDto bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode(param);
        if (bpjsConsumerDto == null) {
        	bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode2(param);
        }

        String key = bpjsConsumerDto.getConsumerId() + bpjsConsumerDto.getConsumerSecret() + reqTimestamp;

        if (!ObjectUtils.isEmpty(bpjsResponse.getResponse())) {
            Object decrypted = decryptedResponse(JsonUtil.toJsonString(bpjsResponse.getResponse()), key);
            bpjsResponse.setResponse(decrypted);
        } else {
            bpjsResponse.setResponse(null);
        }
        String jsonResult = JsonUtil.toJsonString(bpjsResponse);
        return response.toBuilder().body(jsonResult, StandardCharsets.UTF_8).build();
    }

    private Object decryptedResponse(String response, String key) {
        try {
            response = DecryptUtil.decrypt(response.replaceAll("\"", ""), key);
        } catch (Exception e) {
            // ignore
        }
        try {
            return JsonUtil.fromJson(response, new TypeReference<Object>() {
            });
        } catch (Exception e) {
            return response;
        }
    }
}