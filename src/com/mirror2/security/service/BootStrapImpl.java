package com.mirror2.security.service;

import com.mirror2.security.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 6/30/12
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class BootStrapImpl implements BootStrap {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDetailsService userDetailsService;

    private List<Feature> features;
    private List<Authority> authorities;
    private List<AuthorizedGroups> authorizedCumulativeGroups;


    public void createDefaultAdmin(){
        System.out.println("userDetailsService.count() = " + userDetailsService.count());
        if(userDetailsService.count() == 0){

            Token token = new Token();
            token.setUsername("admin");
            token.setPassword("asadmin");

            userDetailsService.saveUser(token);

            User user = userDetailsService.findByUsername("admin");
            List<AuthorizedGroups> groups = initializeDefaultSecurityData();
            user.setAuthorizedGroups(groups);

            userDetailsService.saveUser(user);
        }
    }

    @Override
    public boolean createComponents() {
        return false;
    }

    public boolean createFeatures(){
        return false;
    }


    public List<AuthorizedGroups> initializeDefaultSecurityData(){

        Component securityComponent = new Component();
        securityComponent.setComponentName("security");
        securityComponent.setDescription("Security Related Module");
        securityService.save(securityComponent);

        Component adminComponent = new Component();
        adminComponent.setComponentName("admin");
        adminComponent.setDescription("Admin Related Module");
        securityService.save(adminComponent);

        features = new ArrayList<Feature>();

        Feature saveFeature = new Feature();
        saveFeature.setComponent(securityComponent);
        saveFeature.setOperation("saveUser");
        saveFeature.setDescription("Saving User Feature");
        securityService.save(saveFeature);
        features.add(saveFeature);

        Feature createFeature = new Feature();
        createFeature.setComponent(securityComponent);
        createFeature.setOperation("createUser");
        createFeature.setDescription("Creating User Feature");
        securityService.save(createFeature);
        features.add(createFeature);

        Feature featureList = new Feature();
        featureList.setComponent(securityComponent);
        featureList.setOperation("featureList");
        featureList.setDescription("Creating User Feature");
        securityService.save(featureList);
        features.add(featureList);

        Feature userListFeature = new Feature();
        userListFeature.setComponent(securityComponent);
        userListFeature.setOperation("userList");
        userListFeature.setDescription("Listing all User Feature");
        securityService.save(userListFeature);
        features.add(userListFeature);

        Authority authority = new Authority();
        authority.setAuthorityName("super_admin");
        authority.setAuthorityDescription("Super Admin Authority");
        authority.setFeatures(features);
        securityService.save(authority);
        authorities = new ArrayList<Authority>();
        authorities.add(authority);

        AuthorizedGroups authorizedGroups = new AuthorizedGroups();
        authorizedGroups.setAuthorizationName("System Engineer Group");
        authorizedGroups.setAuthorizationDescription("Engineering the whole system having this privilege");
        authorizedGroups.setAuthorities(authorities);
        securityService.save(authorizedGroups);

        authorizedCumulativeGroups = new ArrayList<AuthorizedGroups>();
        authorizedCumulativeGroups.add(authorizedGroups);

        return authorizedCumulativeGroups;


    }
}
