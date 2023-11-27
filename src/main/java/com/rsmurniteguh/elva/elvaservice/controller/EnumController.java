package com.rsmurniteguh.elva.elvaservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.elva.elvaservice.base.model.ResponseSts;

@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/home")
    public ResponseSts<String> getJenisPelayanan() {
        return ResponseSts.onSuccess("Ok dude.");
    }

}
