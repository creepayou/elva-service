package com.rsmurniteguh.bpjs.bpjsservice.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VClaimVersion {
    V1_1("1.1"), V2("2.0");

    @Getter
    private String version;

    @JsonCreator
    public static VClaimVersion fromVersion(String version) throws BusinessException {
        return getVersion(version);
    }

    private static final Map<String, VClaimVersion> BY_VERSION = new HashMap<>();

    static {
        for (VClaimVersion v : values()) {
            BY_VERSION.put(v.getVersion(), v);
            BY_VERSION.put(v.name(), v);
        }
    }

    @JsonIgnore
    public static VClaimVersion getVersion(String version) throws BusinessException {
        if (BY_VERSION.containsKey(version))
            return BY_VERSION.get(version);
        else
            throw new BusinessException("Version tidak sesuai!");
    }
}
