package com.rsmurniteguh.bpjs.bpjsservice.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategory;

public class BpjsConsumerCategoryDtoMapper {

    private BpjsConsumerCategoryDtoMapper() {
    }

    public static BpjsConsumerCategory toBpjsConsumerCategory(BpjsConsumerCategoryDto bpjsConsumerCategoryDto) {
        if (bpjsConsumerCategoryDto == null)
            return null;
        return new BpjsConsumerCategory().setBpjsconsumer_id(bpjsConsumerCategoryDto.getBpjsConsumerCategoryId())
                .setBpjsconsumer_id(bpjsConsumerCategoryDto.getBpjsConsumerId())
                .setCategory(bpjsConsumerCategoryDto.getCategory())
                .setUser_key(bpjsConsumerCategoryDto.getUserKey())
                .setCreated_by(bpjsConsumerCategoryDto.getCreatedBy())
                .setCreated_datetime(bpjsConsumerCategoryDto.getCreatedDatetime())
                .setLast_updated_by(bpjsConsumerCategoryDto.getLastUpdatedBy())
                .setLast_updated_datetime(bpjsConsumerCategoryDto.getLastUpdatedDatetime())
                .setDefunct_ind(bpjsConsumerCategoryDto.getDefunctInd());
    }

    public static List<BpjsConsumerCategory> toBpjsConsumerCategoryList(
            List<BpjsConsumerCategoryDto> bpjsConsumerCateogryDtoList) {
        List<BpjsConsumerCategory> bpjsConsumerCategoryList = new ArrayList<>();
        bpjsConsumerCateogryDtoList.stream().forEach(
                bpjsConsumerCategoryDto -> bpjsConsumerCategoryList
                        .add(BpjsConsumerCategoryDtoMapper.toBpjsConsumerCategory(bpjsConsumerCategoryDto)));
        return bpjsConsumerCategoryList;
    }

    public static BpjsConsumerCategoryDto toBpjsConsumerCategoryDto(BpjsConsumerCategory bpjsConsumerCategory) {
        if (bpjsConsumerCategory == null)
            return null;
        return new BpjsConsumerCategoryDto().setBpjsConsumerCategoryId(bpjsConsumerCategory.getBpjsconsumer_id())
                .setBpjsConsumerId(bpjsConsumerCategory.getBpjsconsumer_id())
                .setCategory(bpjsConsumerCategory.getCategory())
                .setUserKey(bpjsConsumerCategory.getUser_key())
                .setCreatedBy(bpjsConsumerCategory.getCreated_by())
                .setCreatedDatetime(bpjsConsumerCategory.getCreated_datetime())
                .setLastUpdatedBy(bpjsConsumerCategory.getLast_updated_by())
                .setLastUpdatedDatetime(bpjsConsumerCategory.getLast_updated_datetime())
                .setDefunctInd(bpjsConsumerCategory.getDefunct_ind());
    }

    public static List<BpjsConsumerCategoryDto> toBpjsConsumerCategoryDtoList(
            List<BpjsConsumerCategory> bpjsConsumerCategoryList) {
        List<BpjsConsumerCategoryDto> bpjsConsumerCategoryDtoList = new ArrayList<>();
        bpjsConsumerCategoryList.stream().forEach(
                bpjsConsumerCategory -> bpjsConsumerCategoryDtoList
                        .add(BpjsConsumerCategoryDtoMapper.toBpjsConsumerCategoryDto(bpjsConsumerCategory)));
        return bpjsConsumerCategoryDtoList;
    }
}
