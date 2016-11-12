package com.mirror2.security.dao;

import java.util.List;

import com.mirror2.security.model.Feature;
import com.mirror2.security.model.Authority;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.Component;

public interface SecurityDao {
	
	public boolean save(Feature feature);

    public boolean update(Feature feature);

    public Feature findFeatureByOperation(String operation);

    public Feature getFeature(int featureId);
	
	public List<Feature> findAllFeatures();
	
	public boolean delete(Feature feature);
	
	public boolean save(Authority authority);

    public boolean update(Authority authority);

    public Authority getAuthority(int authorityId);
	
	public List<Authority> findAllAuthorities();
	
	public Authority findByAuthorityName(String authorityName);
	
	public boolean delete(Authority authority);
	
	public boolean save(Component component);

    public boolean update(Component component);

    public Component getComponent(int componentId);
	
	public List<Component> findAllComponents();

    public Component getComponentById(Long componentId);

    public Component getComponentByName(String componentName);
	
	public boolean delete(Component component);

    public boolean save(AuthorizedGroups authorizedGroups);

    public boolean update(AuthorizedGroups authorizedGroups);

    public AuthorizedGroups getAuthorizedGroups(int authorizedGroupId);
    
    public List<AuthorizedGroups> findAllAuthorizedGroups();
	
	public List<Authority> findAllAuthorities(List<AuthorizedGroups> authorizedGroups);
	
	public List<Feature> findAllFeatures(List<Authority> authorities);
    
    public List<AuthorizedGroups> findAllAuthorizedGroup();
	
}
