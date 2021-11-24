package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimEncResponse;
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
	
    public FeignClientConfig(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier, BpjsConsumerService bpjsConsumerService) {
        super(sslContextFactory, hostnameVerifier);
        this.bpjsConsumerService = bpjsConsumerService;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
    	String entityCode = request.requestTemplate().headers().get(Constant.MT_ENTITY_CODE).toArray()[0].toString();
    	request.requestTemplate().removeHeader(Constant.MT_ENTITY_CODE);
    	String reqTimestamp = request.requestTemplate().headers().get("X-timestamp").toArray()[0].toString();
        Response response = super.execute(request, options);
        InputStream bodyStream = response.body().asInputStream();
        String responseBody = StreamUtils.copyToString(bodyStream, StandardCharsets.UTF_8);
        
        VClaimEncResponse bpjsResponse = JsonUtil.fromJson(responseBody, VClaimEncResponse.class);
        
        BpjsConsumerDto bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode);
        
        String key = bpjsConsumerDto.getConsumerId() + bpjsConsumerDto.getConsumerSecret() + reqTimestamp;
        
        if(bpjsResponse.getMetaData().getCode().equals("200")) {
        	try {
        		String decrypted = DecryptUtil.decrypt(bpjsResponse.getResponse().toString(), key);
        		log.info(decrypted);
        		LinkedHashMap<String, Object> testMap = JsonUtil.fromJson(decrypted, new TypeReference<LinkedHashMap<String, Object>>() {});
        		bpjsResponse.setResponse(testMap);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
        }
        String jsonResult = JsonUtil.toJsonString(bpjsResponse);
        log.info(jsonResult);
        return response.toBuilder().body(jsonResult, StandardCharsets.UTF_8).build();
    }
}