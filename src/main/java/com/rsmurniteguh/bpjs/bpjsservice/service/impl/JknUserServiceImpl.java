package com.rsmurniteguh.bpjs.bpjsservice.service.impl;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.JknUserDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.JknUserDto;
import com.rsmurniteguh.bpjs.bpjsservice.model.JknUser;
import com.rsmurniteguh.bpjs.bpjsservice.repository.JknUserRepository;
import com.rsmurniteguh.bpjs.bpjsservice.service.JknUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JknUserServiceImpl implements JknUserService {

    @Autowired
    private JknUserRepository jknUserRepository;

    @Override
    public List<JknUserDto> getList() {
        return JknUserDtoMapper.toJknUserDtoList(jknUserRepository.selectList());
    }

    @Override
    public JknUserDto getByEntityCode(String entityCode) {
        return JknUserDtoMapper.toJknUserDto(jknUserRepository.selectByEntityCode(entityCode));
    }

    @Override
    public JknUserDto insert(JknUserDto jknUserDto) {
        JknUser jknUser = jknUserDto.toJknUser();
        jknUserRepository.insert(jknUser);
        return JknUserDtoMapper.toJknUserDto(jknUser);
    }

    @Override
    public void update(JknUserDto jknUserDto) {
        jknUserRepository.update(jknUserDto.toJknUser());
    }
}
