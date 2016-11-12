/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirror2.security.service;

import com.mirror2.security.dao.UserDao;


import com.mirror2.security.model.AuthorizedGroups;
import com.mirror2.security.model.Token;
import com.mirror2.security.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ronnie
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(Long userID) {
        return userDao.get(userID);
    }

    @Override
    public User getUserByEmail(String emailAddress) {
        return userDao.getUserByEmail(emailAddress);
    }

    @Override
    public boolean saveUser(Token token) {
        userDao.save(token);
        User user = new User();
        user.setToken(token);
        user.setActive(true);
        userDao.save(user);

        return true;
    }

    @Override
    public boolean deleteUser(Token token) {
        userDao.delete(token);
        User user = new User();
        Token token1 = user.getToken();
        User user1 = token1.getUser();
        userDao.delete(user1);
        return true;
    }

    @Override
    public boolean delete(User user) {
        Token token = user.getToken();
        userDao.delete(token);
        userDao.delete(user);
        return true;
    }

    @Override
    public boolean saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean updateToken(Token token) {
        return userDao.update(token);
    }

    @Override
    public boolean updateToken(User user) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User findByUserCodeAndPassword(String username, String password) {
        return userDao.get(username, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException, DataAccessException {
        return null;
    }

    @Override
    public User findUserByUserCode(String userCode) {
        return userDao.get(userCode);
    }

    @Override
    public AuthorizedGroups findByAuthorizedGroupsName(
            String authorizedGroupsName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean saveAuthorizedGroups(AuthorizedGroups authorizedGroups) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.get(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.get(username);
    }


}
