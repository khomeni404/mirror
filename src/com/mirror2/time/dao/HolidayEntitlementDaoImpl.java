package com.mirror2.time.dao;

import com.mirror2.time.model.HolidayEntitlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class HolidayEntitlementDaoImpl implements HolidayEntitlementDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(HolidayEntitlement entitlement) {
        try {
            hibernateTemplate.persist(entitlement);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(HolidayEntitlement entitlement) {
        hibernateTemplate.merge(entitlement);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(HolidayEntitlement entitlement) {
        hibernateTemplate.delete(entitlement);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement() {
        return hibernateTemplate.find("from HolidayEntitlement");
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end) {
        Object[] paramArr = new Object[2];
        paramArr[0] = start;
        paramArr[1] = end;
        return hibernateTemplate.find("from HolidayEntitlement where date between ? and ?", paramArr);
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end, String holidayType) {
        Object[] paramArr = new Object[3];
        paramArr[0] = holidayType;
        paramArr[1] = start;
        paramArr[2] = end;
        return hibernateTemplate.find("from HolidayEntitlement where holidayType=? and date between ? and ?", paramArr);
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement(String year, Integer month, String holidayType) {
        Object[] paramArr = new Object[3];
        paramArr[0] = year;
        paramArr[1] = month;
        paramArr[2] = holidayType;
        return hibernateTemplate.find("from HolidayEntitlement where year=? and month=? and holidayType=?", paramArr);
    }

    @Override
    public HolidayEntitlement getHolidayEntitlement(Long id) {
        return hibernateTemplate.get(HolidayEntitlement.class, id);
    }

    @Override
    public HolidayEntitlement getHolidayEntitlement(Date date) {
        Object[] paramArr = new Object[1];
        paramArr[0] = date;
        try {
            return (HolidayEntitlement) hibernateTemplate.find("from HolidayEntitlement where date=?", paramArr).get(0);
        }catch (IndexOutOfBoundsException ex){
            return null;
        }
    }


}
