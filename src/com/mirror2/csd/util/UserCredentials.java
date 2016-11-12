package com.mirror2.csd.util;

import com.mirror2.security.SessionUtil;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.User;

import java.util.List;

public class UserCredentials {
    User user = SessionUtil.getSessionUser();
    public String userPassword = user.getToken().getPassword();
    public String username =   user.getToken().getUsername();
    public Long authorizeId = user.getAuthorizeId();
    public List<AuthorizedGroups> authorizedGroups  = user.getAuthorizedGroups();
}
