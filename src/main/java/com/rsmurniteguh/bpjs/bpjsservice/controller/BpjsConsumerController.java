package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.InsertBpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.ResponseStsUtil;

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

    private final BpjsConsumerService bpjsConsumerService;

    private final List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryList;

    public BpjsConsumerController(BpjsConsumerService bpjsConsumerService,
            List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryList) {
        this.bpjsConsumerService = bpjsConsumerService;
        this.bpjsConsumerWithCategoryList = bpjsConsumerWithCategoryList;
    }

    @GetMapping("/getProviderCode")
    public ResponseSts<String> getProviderCode(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException, ServiceException {
        BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto = ResponseStsUtil
                .handleResponseSts(getBpjsConsumerWithCategoryResponse(null, entityCode));
        return ResponseSts.onSuccess(bpjsConsumerWithCategoryDto.getProviderCode());
    }

    @GetMapping("/isBpjsConsumerAvailable")
    public ResponseSts<Boolean> isBpjsConsumerAvailable(@RequestParam("entityCode") String entityCode) {
        ResponseSts<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryDto = getBpjsConsumerWithCategoryResponse(null,
                entityCode);
        return ResponseSts.onSuccess(bpjsConsumerWithCategoryDto.getData() != null);
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
    public ResponseSts<BpjsConsumerWithCategoryDto> getBpjsConsumerWithCategoryResponse(
            @PathVariable("categoryType") BpjsConsumerCategoryType category,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto = getBpjsConsumerWithCategory(category, entityCode);
        if (bpjsConsumerWithCategoryDto == null)
            return ResponseSts
                    .onFail(String.format("BPJS Cons Id not found! Entity: %s, Category: %s", entityCode, category));
        return ResponseSts.onSuccess(bpjsConsumerWithCategoryDto);
    }

    public BpjsConsumerWithCategoryDto getBpjsConsumerWithCategory(BpjsConsumerCategoryType category,
            String entityCode) {
        for (BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto : bpjsConsumerWithCategoryList) {
            if (bpjsConsumerWithCategoryDto.getEntityCode().equals(entityCode)) {
                if (category == null)
                    return new BpjsConsumerWithCategoryDto().setConsumerId(bpjsConsumerWithCategoryDto.getConsumerId())
                            .setConsumerSecret(bpjsConsumerWithCategoryDto.getConsumerSecret())
                            .setProviderCode(bpjsConsumerWithCategoryDto.getProviderCode());
                else if (bpjsConsumerWithCategoryDto.getCategory().equals(category)) {
                    return bpjsConsumerWithCategoryDto;
                }
            }
        }
        return null;
    }

}
