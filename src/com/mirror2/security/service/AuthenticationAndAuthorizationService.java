package com.mirror2.security.service;

import java.util.List;

import com.mirror2.security.model.Feature;
import com.mirror2.security.model.Authority;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.User;

public interface AuthenticationAndAuthorizationService {
	
	public boolean authenticate(String username, String password);
	
	public boolean checkAuthorization(List<Feature> features, String controller, String operation);
	
	public boolean authorizedGroupsAssignmentToUser(User user, List<AuthorizedGroups> authorizedGroups);
	
	public boolean authorityAssignmentToAuthorizedGroups(AuthorizedGroups authorizedGroups, List<Authority> authorities);
	
	public boolean aspectAssignmentToAuthority(Authority authority, List<Feature> features);
	
	

}
