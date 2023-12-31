package com.rsmurniteguh.elva.elvaservice.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsmurniteguh.elva.elvaservice.base.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BpjsConsumerCategoryType {
    VCLAIM(Constant.VCLAIM_FEIGN_NAME), ANTREANRS(Constant.ANTREAN_FEIGN_NAME), IHS(Constant.IHS_FEIGN_NAME);

    @Getter
    private String type;

    public static BpjsConsumerCategoryType fromType(String type) {
        return getType(type);
    }

    private static final Map<String, BpjsConsumerCategoryType> BY_TYPE = new HashMap<>();

    static {
        for (BpjsConsumerCategoryType v : values()) {
            BY_TYPE.put(v.getType(), v);
            BY_TYPE.put(v.name(), v);
        }
    }

    @JsonIgnore
    public static BpjsConsumerCategoryType getType(String type) {
        if (BY_TYPE.containsKey(type))
            return BY_TYPE.get(type);
        else
            return null;
    }
}
