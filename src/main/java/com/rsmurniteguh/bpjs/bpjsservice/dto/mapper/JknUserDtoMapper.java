package com.rsmurniteguh.bpjs.bpjsservice.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.JknUserDto;
import com.rsmurniteguh.bpjs.bpjsservice.model.JknUser;

import org.springframework.stereotype.Component;

@Component
public class JknUserDtoMapper {
    private JknUserDtoMapper() {
    }

    public static JknUser toJknUser(JknUserDto jknUserDto) {
        if (jknUserDto == null)
            return null;
        return new JknUser().setJknuser_id(jknUserDto.getJknUserId())
                .setUsername(jknUserDto.getUsername()).setPassword(jknUserDto.getPassword())
                .setCreated_by(jknUserDto.getCreatedBy()).setCreated_datetime(jknUserDto.getCreatedDateTime())
                .setLast_updated_by(jknUserDto.getLastUpdatedBy())
                .setLast_updated_datetime(jknUserDto.getLastUpdatedDateTime())
                .setDefunct_ind(jknUserDto.getDefunctInd()).setEntity_code(jknUserDto.getEntityCode());
    }

    public static List<JknUser> toJknUserList(List<JknUserDto> jknUserDtoList) {
        List<JknUser> jknUserList = new ArrayList<>();
        jknUserDtoList.stream().forEach(
                jknUserDto -> jknUserList.add(JknUserDtoMapper.toJknUser(jknUserDto)));
        return jknUserList;
    }

    public static JknUserDto toJknUserDto(JknUser jknUser) {
        if (jknUser == null)
            return null;
        return new JknUserDto().setJknUserId(jknUser.getJknuser_id())
                .setUsername(jknUser.getUsername()).setPassword(jknUser.getPassword())
                .setCreatedBy(jknUser.getCreated_by()).setCreatedDateTime(jknUser.getCreated_datetime())
                .setLastUpdatedBy(jknUser.getLast_updated_by())
                .setLastUpdatedDateTime(jknUser.getLast_updated_datetime())
                .setDefunctInd(jknUser.getDefunct_ind());
    }

    public static List<JknUserDto> toJknUserDtoList(List<JknUser> jknUserList) {
        List<JknUserDto> jknUserDtoList = new ArrayList<>();
        jknUserList.stream().forEach(
                jknUser -> jknUserDtoList.add(JknUserDtoMapper.toJknUserDto(jknUser)));
        return jknUserDtoList;
    }
}
