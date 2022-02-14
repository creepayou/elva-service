package com.rsmurniteguh.bpjs.bpjsservice.repository;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.model.JknUser;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JknUserRepository {

    @Select("select * from jknuser where defunct_ind = 'N'")
    public List<JknUser> selectList();

    @Select("select * from jknuser where entity_code =#{entity_code} and defunct_ind = 'N'")
    public JknUser selectByEntityCode(@Param("entity_code") String entityCode);

    @Options(useGeneratedKeys = true, keyProperty = "jknuser_id", keyColumn = "jknuser_id")
    @Insert("insert into jknuser "
            + "    		("
            + "    			username"
            + "    			,password"
            + "    			,entity_code"
            + "    			,created_by"
            + "    			,created_datetime"
            + "    			,defunct_ind"
            + "    		)"
            + "    	values"
            + "    		("
            + "    			#{username}"
            + "    			,#{password}"
            + "    			,#{entity_code}"
            + "    			,#{created_by}"
            + "    			,now()"
            + "    			,'N'"
            + "    		)")
    public void insert(JknUser jknUser);

    public void update(JknUser jknUser);
}
