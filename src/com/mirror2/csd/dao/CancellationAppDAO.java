package com.mirror2.csd.dao;

import com.mirror2.csd.model.CancellationApp;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CancellationAppDAO {
    public boolean save(CancellationApp app);

    public boolean update(CancellationApp app);

    public boolean delete(CancellationApp app);

    public List<CancellationApp> findAllCancellationApp();

    public CancellationApp getCancellationApp(Long id);

}
