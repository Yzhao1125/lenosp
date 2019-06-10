package com.len.entity;

import javax.persistence.*;

@Table(name = "p_user_role")
public class PUserRole {
    @Id
    @Column(name = "p_user")
    private String pUser;

    @Id
    @Column(name = "p_role")
    private String pRole;

    /**
     * @return p_user
     */
    public String getpUser() {
        return pUser;
    }

    /**
     * @param pUser
     */
    public void setpUser(String pUser) {
        this.pUser = pUser == null ? null : pUser.trim();
    }

    /**
     * @return p_role
     */
    public String getpRole() {
        return pRole;
    }

    /**
     * @param pRole
     */
    public void setpRole(String pRole) {
        this.pRole = pRole == null ? null : pRole.trim();
    }
}