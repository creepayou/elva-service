package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBooleanDeserializer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsSepSearchDto extends BpjsSepDto {
    private VClaimMappingDto tujuanKunj;
    private VClaimMappingDto flagProcedure;
    private VClaimMappingDto kdPenunjang;
    private VClaimMappingDto assestmenPel;
    @JsonAlias("eSEP")
    @JsonProperty("eSep")
    @JsonDeserialize(using = CustomBooleanDeserializer.class)
    private boolean eSep;
}
