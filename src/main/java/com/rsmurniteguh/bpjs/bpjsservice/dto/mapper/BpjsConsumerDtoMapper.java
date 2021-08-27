package com.rsmurniteguh.bpjs.bpjsservice.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumer;

import org.springframework.stereotype.Component;

@Component
public class BpjsConsumerDtoMapper {

    private BpjsConsumerDtoMapper() {
    }

    public static BpjsConsumer toBpjsConsumer(BpjsConsumerDto bpjsConsumerDto) {
        if (bpjsConsumerDto == null)
            return null;
        return new BpjsConsumer().setBpjsconsumer_id(bpjsConsumerDto.getBpjsConsumerId())
                .setConsumer_id(bpjsConsumerDto.getConsumerId()).setConsumer_secret(bpjsConsumerDto.getConsumerSecret())
                .setCreated_by(bpjsConsumerDto.getCreatedBy()).setCreated_datetime(bpjsConsumerDto.getCreatedDateTime())
                .setUpdated_by(bpjsConsumerDto.getUpdatedBy()).setUpdated_datetime(bpjsConsumerDto.getUpdatedDateTime())
                .setDefunct_ind(bpjsConsumerDto.getDefunctInd()).setEntity_code(bpjsConsumerDto.getEntityCode());
    }

    public static List<BpjsConsumer> toBpjsConsumerList(List<BpjsConsumerDto> bpjsCosnumerDtoList) {
        List<BpjsConsumer> bpjsConsumerList = new ArrayList<>();
        bpjsCosnumerDtoList.stream().forEach(
                bpjsConsumerDto -> bpjsConsumerList.add(BpjsConsumerDtoMapper.toBpjsConsumer(bpjsConsumerDto)));
        return bpjsConsumerList;
    }

    public static BpjsConsumerDto toBpjsConsumerDto(BpjsConsumer bpjsConsumer) {
        if (bpjsConsumer == null)
            return null;
        return new BpjsConsumerDto().setBpjsConsumerId(bpjsConsumer.getBpjsconsumer_id())
                .setConsumerId(bpjsConsumer.getConsumer_id()).setConsumerSecret(bpjsConsumer.getConsumer_secret())
                .setCreatedBy(bpjsConsumer.getCreated_by()).setCreatedDateTime(bpjsConsumer.getCreated_datetime())
                .setUpdatedBy(bpjsConsumer.getUpdated_by()).setUpdatedDateTime(bpjsConsumer.getUpdated_datetime())
                .setDefunctInd(bpjsConsumer.getDefunct_ind());
    }

    public static List<BpjsConsumerDto> toBpjsConsumerDtoList(List<BpjsConsumer> bpjsConsumerList) {
        List<BpjsConsumerDto> bpjsConsumerDtoList = new ArrayList<>();
        bpjsConsumerList.stream().forEach(
                bpjsConsumer -> bpjsConsumerDtoList.add(BpjsConsumerDtoMapper.toBpjsConsumerDto(bpjsConsumer)));
        return bpjsConsumerDtoList;
    }
}
