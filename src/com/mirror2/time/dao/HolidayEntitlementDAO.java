package com.mirror2.time.dao;

import com.mirror2.time.model.HolidayEntitlement;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HolidayEntitlementDAO {
    public boolean save(HolidayEntitlement entitlement);

    public boolean update(HolidayEntitlement entitlement);

    public boolean delete(HolidayEntitlement entitlement);

    public List<HolidayEntitlement> findAllHolidayEntitlement();

    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end);

    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end, String holidayType);

    public List<HolidayEntitlement> findAllHolidayEntitlement(String year, Integer month, String holidayType);

    public HolidayEntitlement getHolidayEntitlement(Long id);

    public HolidayEntitlement getHolidayEntitlement(Date date);


}
