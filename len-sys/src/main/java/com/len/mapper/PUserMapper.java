package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.PUser;
import tk.mybatis.mapper.common.Mapper;

public interface PUserMapper extends com.len.base.BaseMapper<PUser, String> {
    int insertpUser(PUser pUser);

    int checkpUser(String username);

    int getuserid(String uname);
}