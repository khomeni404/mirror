package com.mirror2.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.mirror2.security.model.Authority;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.Feature;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.util.MirrorConstants;


/**
 * This class contains the functionality to find out user's authorized groups,
 * authorities and authorized features and store them into session attributes. 
 *
 */
public class AuthorizationToken {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public User principal;
	public List<Authority> credentials;
	public List<AuthorizedGroups> authorizedGroups;
	public List<Feature> authorities;
	public List<Feature> grantedAuthorities;
	
		
	public AuthorizationToken(User user) {
		setAuthenticationToken(user);
	}
	
	/**
	 * This method contains the functionality to find out user's authorized groups,
	 * authorities and authorized features and store them into session attributes.
	 * @param principal
	 * 		The logged in user object.
	 */
	public void setAuthenticationToken(User principal){

		if(principal != null){
			
			HttpSession session = SessionUtil.getSession();

			session.setAttribute(MirrorConstants.SESSION_USER, principal);

			authorizedGroups = principal.getAuthorizedGroups();

			grantedAuthorities = new ArrayList<Feature>();
			
			if(authorizedGroups != null){
				for(AuthorizedGroups authorizedGroup : authorizedGroups){
					credentials = authorizedGroup.getAuthorities();
					for(Authority authority : credentials){
						authorities = authority.getFeatures();
						grantedAuthorities.addAll(authorities);
					}
				}
			}
			
			session.setAttribute(MirrorConstants.SESSION_USER_FEATURES, grantedAuthorities);
		}
		
		// get the list of authorized groups, authorities and aspects and put them in session.
	}
	

}
