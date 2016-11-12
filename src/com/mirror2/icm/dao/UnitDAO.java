package com.mirror2.icm.dao;

import com.mirror2.icm.model.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UnitDAO {
    public boolean save(Unit unit);

    public boolean update(Unit unit);

    public boolean delete(Unit unit);

    public List<Unit> findAllUnit();

    public Unit getUnit(Long id);

}
