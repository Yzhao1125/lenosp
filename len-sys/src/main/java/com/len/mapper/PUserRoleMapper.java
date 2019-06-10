package com.len.mapper;

import com.len.entity.PUserRole;
import tk.mybatis.mapper.common.Mapper;

public interface PUserRoleMapper extends Mapper<PUserRole> {
    void insertUserRole(PUserRole pUserRole);
}