package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.model.VClaimVersion;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class VClaimVersionTypeHandler implements TypeHandler<VClaimVersion> {

    @Override
    public void setParameter(PreparedStatement ps, int paramInt, VClaimVersion paramType, JdbcType jdbctype)
            throws SQLException {
        ps.setString(paramInt, paramType.getVersion());
    }

    @Override
    public VClaimVersion getResult(ResultSet rs, String param) throws SQLException {
        try {
            return VClaimVersion.fromVersion(rs.getString(param));
        } catch (BusinessException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public VClaimVersion getResult(CallableStatement cs, int col) throws SQLException {
        try {
            return VClaimVersion.fromVersion(cs.getString(col));
        } catch (BusinessException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public VClaimVersion getResult(ResultSet rs, int col) throws SQLException {
        try {
            return VClaimVersion.fromVersion(rs.getString(col));
        } catch (BusinessException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
