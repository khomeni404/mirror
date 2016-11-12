package com.mirror2.csd.dao;

import com.mirror2.csd.model.Application;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationDAO {
    public boolean save(Application application);

    public boolean update(Application application);

    public boolean delete(Application application);

    public List<Application> findAllApplication();

    public Application getApplication(Long id);

}
