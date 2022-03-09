package com.rsmurniteguh.bpjs.bpjsservice.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BpjsConsumerCategoryType {
    VCLAIM("vclaim"), ANTREANRS("antreanrs");

    @Getter
    private String type;

    public static BpjsConsumerCategoryType fromType(String version) {
        return getType(version);
    }

    private static final Map<String, BpjsConsumerCategoryType> BY_TYPE = new HashMap<>();

    static {
        for (BpjsConsumerCategoryType v : values()) {
            BY_TYPE.put(v.getType(), v);
            BY_TYPE.put(v.name(), v);
        }
    }

    @JsonIgnore
    public static BpjsConsumerCategoryType getType(String version) {
        if (BY_TYPE.containsKey(version))
            return BY_TYPE.get(version);
        else
            return null;
    }
}
