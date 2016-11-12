package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.EmpCsd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EmpCsdDAO {
    public boolean save(EmpCsd empCsd);

    public boolean update(EmpCsd empCsd);

    public boolean delete(EmpCsd empCsd);

    public List<EmpCsd> findAllEmpCsd();

    public EmpCsd getEmpCsd(Long id);

}
