package com.rsmurniteguh.bpjs.bpjsservice.service;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.JknUserDto;

public interface JknUserService {
    
    public List<JknUserDto> getList();
    
    public JknUserDto getByEntityCode(String entityCode);
    
    public JknUserDto insert(JknUserDto jknUserDto);
    
    public void update(JknUserDto jknUserDto);
}
