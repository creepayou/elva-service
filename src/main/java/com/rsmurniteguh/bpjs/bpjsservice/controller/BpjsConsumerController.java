package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.InsertBpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bpjsconsumer")
public class BpjsConsumerController extends BaseController {

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @GetMapping("/getProviderCode")
    public ResponseSts<String> getProviderCode(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(bpjsConsumerService.getProviderCodeByEntityCode(entityCode));
    }

    @GetMapping("/isBpjsConsumerAvailable")
    public ResponseSts<Boolean> isBpjsConsumerAvailable(@RequestParam("entityCode") String entityCode) {
        return ResponseSts.onSuccess(bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode) != null);
    }

    @PostMapping("/category")
    public ResponseSts<BpjsConsumerCategoryDto> insertCategory(
            @RequestBody InsertBpjsConsumerCategoryDto insertBpjsConsumerCategoryDto) {
        return ResponseSts.onSuccess(bpjsConsumerService.insertBpjsConsumerCategory(
                new BpjsConsumerCategoryDto().setBpjsConsumerId(insertBpjsConsumerCategoryDto.getBpjsConsumerId())
                        .setCategory(insertBpjsConsumerCategoryDto.getCategoryType())
                        .setUserKey(insertBpjsConsumerCategoryDto.getUserKey())
                        .setCreatedBy(insertBpjsConsumerCategoryDto.getCreatedBy())));
    }

    @GetMapping("/category/{categoryType}")
    public ResponseSts<BpjsConsumerWithCategoryDto> getBpjsConsumerWithCategory(
            @PathVariable("categoryType") BpjsConsumerCategoryType category,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        return ResponseSts.onSuccess(bpjsConsumerService.getBpjsConsumerWithCategory(category, entityCode));
    }
}
