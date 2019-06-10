package com.len.service;

import com.len.entity.PUser;

public interface PUserService {

    int checkpUser(String username);

    int registerUser(PUser pUser);

    String getuserid(String uname);
}
