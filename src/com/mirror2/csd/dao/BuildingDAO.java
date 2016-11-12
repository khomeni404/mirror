package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BuildingDAO {
    public boolean save(Building building);

    public boolean update(Building building);

    public boolean delete(Building building);

    public List<Building> findAllBuilding();

    public Building getBuilding(Long id);

}
