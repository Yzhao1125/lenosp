package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.PUser;
import com.len.mapper.PUserMapper;
import com.len.service.PUserService;
import com.len.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PUserServiceImpl extends BaseServiceImpl<PUser,String> implements PUserService {

    @Autowired
    PUserMapper pUserMapper;

    @Override
    public int registerUser(PUser pUser) {
        System.out.println("serviceImpl registerUser"+pUser.getUname());
        String pwd = Md5Util.getMD5(pUser.getPwd().trim(),pUser.getUname().trim());
        pUser.setPwd(pwd);
        return pUserMapper.insertpUser(pUser);
    }

    @Override
    public String getuserid(String uname) {
        return String.valueOf(pUserMapper.getuserid(uname));
    }

    @Override
    public int checkpUser(String username) {
        System.out.println("serviceImpl checkpUser"+username);
        return pUserMapper.checkpUser(username);
    }

    @Override
    public BaseMapper<PUser, String> getMappser() {
        return pUserMapper;
    }
}
