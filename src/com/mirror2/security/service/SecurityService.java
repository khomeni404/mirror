package com.mirror2.security.service;

import java.util.List;

import com.mirror2.security.model.Feature;
import com.mirror2.security.model.Authority;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.Component;

public interface SecurityService {
	
	public boolean save(Feature feature);
	
	public List<Feature> findAllFeatures();
    
	public Feature findFeatureByOperation(String operation);
    
	public boolean delete(Feature feature);
  
	
	public boolean save(Authority authority);
	
	public List<Authority> findAllAuthorities();
	
	public Authority findByAuthorityName(String authorityName);
	
	public boolean delete(Authority authority);
	
	public boolean save(Component component);
	
	public List<Component> findAllComponents();
    
    public Component getComponentById(Long componentId);
    
    public Component getComponentByName(String componentName);
	
	public boolean delete(Component component);
	
	public List<Authority> findAllAuthorities(List<AuthorizedGroups> authorizedGroups);
	
	public List<Feature> findAllFeatures(List<Authority> authorities);

    public boolean save(AuthorizedGroups authorizedGroups);


    public List<AuthorizedGroups> findAllAuthorizedGroup();

}
