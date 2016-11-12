package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.IncentiveDist;
import com.mirror2.csd.model.MID;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IncentiveDistDAO {
    public boolean save(IncentiveDist dist);

    public boolean update(IncentiveDist dist);

    public boolean delete(IncentiveDist dist);

    public List<IncentiveDist> findAllIncentiveDist();

    public List<IncentiveDist> findAllIncentiveDist(Integer status);

    public IncentiveDist getIncentiveDist(Long id);


}
