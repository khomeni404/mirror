package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.DisbursementLog;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DisbursementLogDAO {
    public boolean save(DisbursementLog log);

    public boolean update(DisbursementLog log);

    public boolean delete(DisbursementLog log);

    public List<DisbursementLog> findAllDisbursementLog();

    public DisbursementLog getDisbursementLog(Long id);

    public List<Object[]> getDisbursementLogReport(Date from, Date to);

}
