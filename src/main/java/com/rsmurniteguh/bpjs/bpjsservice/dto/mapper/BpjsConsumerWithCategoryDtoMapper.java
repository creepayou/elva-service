package com.rsmurniteguh.bpjs.bpjsservice.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerWithCategory;

public class BpjsConsumerWithCategoryDtoMapper {
    private BpjsConsumerWithCategoryDtoMapper() {
    }

    public static BpjsConsumerWithCategory toBpjsConsumerWithCategory(
            BpjsConsumerWithCategoryDto bpjsConsumerWithCategoryDto) {
        if (bpjsConsumerWithCategoryDto == null)
            return null;
        return new BpjsConsumerWithCategory().setConsumer_id(bpjsConsumerWithCategoryDto.getConsumerId())
                .setConsumer_secret(bpjsConsumerWithCategoryDto.getConsumerSecret())
                .setUser_key(bpjsConsumerWithCategoryDto.getUserKey())
                .setEntity_code(bpjsConsumerWithCategoryDto.getEntityCode())
                .setCategory(bpjsConsumerWithCategoryDto.getCategory());
    }

    public static List<BpjsConsumerWithCategory> toBpjsConsumerWithCategoryList(
            List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryDtoList) {
        List<BpjsConsumerWithCategory> bpjsConsumerWithCategoryList = new ArrayList<>();
        bpjsConsumerWithCategoryDtoList.stream().forEach(
                bpjsConsumerWithCategoryDto -> bpjsConsumerWithCategoryList
                        .add(BpjsConsumerWithCategoryDtoMapper
                                .toBpjsConsumerWithCategory(bpjsConsumerWithCategoryDto)));
        return bpjsConsumerWithCategoryList;
    }

    public static BpjsConsumerWithCategoryDto toBpjsConsumerWithCategoryDto(
            BpjsConsumerWithCategory bpjsConsumerWithCategory) {
        if (bpjsConsumerWithCategory == null)
            return null;
        return new BpjsConsumerWithCategoryDto().setConsumerId(bpjsConsumerWithCategory.getConsumer_id())
                .setConsumerSecret(bpjsConsumerWithCategory.getConsumer_secret())
                .setUserKey(bpjsConsumerWithCategory.getUser_key())
                .setEntityCode(bpjsConsumerWithCategory.getEntity_code())
                .setCategory(bpjsConsumerWithCategory.getCategory());
    }

    public static List<BpjsConsumerWithCategoryDto> toBpjsConsumerWithCategoryDtoList(
            List<BpjsConsumerWithCategory> bpjsConsumerWithCategoryList) {
        List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryDtoList = new ArrayList<>();
        bpjsConsumerWithCategoryList.stream().forEach(
                bpjsConsumerWithCategory -> bpjsConsumerWithCategoryDtoList
                        .add(BpjsConsumerWithCategoryDtoMapper
                                .toBpjsConsumerWithCategoryDto(bpjsConsumerWithCategory)));
        return bpjsConsumerWithCategoryDtoList;
    }
}
