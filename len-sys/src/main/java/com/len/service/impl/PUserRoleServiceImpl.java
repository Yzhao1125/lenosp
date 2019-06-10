package com.len.service.impl;

import com.len.entity.PUserRole;
import com.len.mapper.PUserRoleMapper;
import com.len.service.PUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PUserRoleServiceImpl implements PUserRoleService {

    @Autowired
    PUserRoleMapper pUserRoleMapper;

    @Override
    public void insertUserRole(PUserRole pUserRole) {
        pUserRoleMapper.insertUserRole(pUserRole);

    }
}
