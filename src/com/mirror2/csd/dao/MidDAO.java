package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.MID;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MidDAO {
    public boolean save(MID mid);

    public boolean update(MID mid);

    public boolean delete(MID mid);

    public List<MID> findAllMID();

    public List<MID> findAllMID(String type);

    public MID getMID(Long id);

    public MID getMID(String mid);
}
