package com.len.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class Principal {

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static CurrentUser getPrincipal(){
        return (CurrentUser)getSubject().getPrincipal();
    }

    public static Session getSession(){
        return getSubject().getSession();
    }

    public static CurrentUser getCurrentUser(){
        return (CurrentUser) getSession().getAttribute("currentPrincipal");
    }
}
