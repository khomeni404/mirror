package com.mirror2.csd.util;

import com.mirror2.security.SessionUtil;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.User;

import java.util.List;

public class CurrentUserCredentials {
    public User user = SessionUtil.getSessionUser();
    public static User staticUser = SessionUtil.getSessionUser();
    public String userPassword = user.getToken().getPassword();
    public String username =   user.getToken().getUsername();
    public String name =   user.getToken().getName();
    public String designation =   user.getToken().getDesignation();
    public String department =   user.getToken().getDepartment();
    public Long authorizeId = user.getAuthorizeId();
    public List<AuthorizedGroups> authorizedGroups  = user.getAuthorizedGroups();
}
