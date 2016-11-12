package com.mirror2.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirror2.security.dao.SecurityDao;
import com.mirror2.security.model.Feature;
import com.mirror2.security.model.Authority;
import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.Component;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private SecurityDao securityDao;
	
	@Override
	public List<Authority> findAllAuthorities(List<AuthorizedGroups> authorizedGroups) {
		return securityDao.findAllAuthorities(authorizedGroups);
	}

	@Override
	public List<Feature> findAllFeatures(List<Authority> authorities) {
		return securityDao.findAllFeatures(authorities);
	}

    @Override
    public Component getComponentById(Long componentId){
        return securityDao.getComponentById(componentId);
    }
    @Override
    public Component getComponentByName(String componentName){
       return securityDao.getComponentByName(componentName);
    }

    @Override
    public boolean save(AuthorizedGroups authorizedGroups) {
        return securityDao.save(authorizedGroups);
    }

    @Override
	public boolean save(Feature feature) {
		return securityDao.save(feature);
	}
    @Override
    public Feature findFeatureByOperation(String operation){
      return securityDao.findFeatureByOperation(operation);
    }

	@Override
	public List<Feature> findAllFeatures() {
		return securityDao.findAllFeatures();
	}

	@Override
	public boolean delete(Feature feature) {
		return securityDao.delete(feature);
	}

	@Override
	public boolean save(Authority authority) {
		return securityDao.save(authority);
	}

	@Override
	public List<Authority> findAllAuthorities() {
		return securityDao.findAllAuthorities();
	}

	@Override
	public Authority findByAuthorityName(String authorityName) {
		return securityDao.findByAuthorityName(authorityName);
	}

	@Override
	public boolean delete(Authority authority) {
		return securityDao.delete(authority);
	}

	@Override
	public boolean save(Component component) {
		return securityDao.save(component);
	}

	@Override
	public List<Component> findAllComponents() {
		return securityDao.findAllComponents();
	}

	@Override
	public boolean delete(Component component) {
		return securityDao.delete(component);
	}
    @Override
    public List<AuthorizedGroups> findAllAuthorizedGroup(){
        return securityDao.findAllAuthorizedGroup();
    }

}
