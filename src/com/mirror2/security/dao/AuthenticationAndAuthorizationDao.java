package com.mirror2.security.dao;

import java.util.List;

import com.mirror2.security.model.Feature;
import com.mirror2.security.model.Authority;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.User;

public interface AuthenticationAndAuthorizationDao {

	public boolean authorizedGroupsAssignmentToUser(User user, List<AuthorizedGroups> authorizedGroups);
	
	public boolean authorityAssignmentToAuthorizedGroups(AuthorizedGroups authorizedGroups, List<Authority> authorities);
	
	public boolean featureAssignmentToAuthority(Authority authority, List<Feature> features);

}
