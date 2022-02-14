package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.io.IOException;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.JknUserDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.InsertJknUserDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.UpdateJknUserDto;
import com.rsmurniteguh.bpjs.bpjsservice.service.JknUserService;
import com.rsmurniteguh.bpjs.bpjsservice.util.ObjectUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jknuser")
public class JknUserController extends BaseController {

    @Autowired
    private JknUserService jknUserService;

    @GetMapping("/getUserList")
    public ResponseSts<List<JknUserDto>> getJknUserList() {
        return ResponseSts.onSuccess(jknUserService.getList());
    }

    @GetMapping("/getUser")
    public ResponseSts<JknUserDto> getJknUserByEntityCode(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        return ResponseSts.onSuccess(jknUserService.getByEntityCode(entityCode));
    }

    @PostMapping("/insert")
    public ResponseSts<JknUserDto> insertJknUser(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode,
            @RequestBody InsertJknUserDto insertJknUserDto) throws IOException {
        return ResponseSts.onSuccess(jknUserService
                .insert(ObjectUtil.convertObjectToClass(insertJknUserDto, JknUserDto.class).setEntityCode(entityCode)));
    }

    @PutMapping("/update/{id}")
    public ResponseSts<Object> updateJknUser(@PathVariable("id") Long jknUserId, @RequestBody UpdateJknUserDto updateJknUserDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws IOException {
        jknUserService.update(ObjectUtil.convertObjectToClass(updateJknUserDto, JknUserDto.class)
                .setJknUserId(jknUserId)
                .setEntityCode(entityCode));
        return ResponseSts.onSuccess(null);
    }
}
