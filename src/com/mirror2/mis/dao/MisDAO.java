package com.mirror2.mis.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.mis.bean.SearchBean;
import org.hibernate.criterion.Criterion;

import java.util.List;
import java.util.Map;

/**
 * Created by Khomeni on 23-Feb-17.
 */
public interface MisDAO {
    List<Object[]> getCustomerData(Long offerId);

    List<Map<String, Object>> getCustomerDataListMap(SearchBean searchBean);

    Map<Long, Double> getCustomersPayableInstAmtMap(SearchBean searchBean);

    Map<Long, Double> getCustomersPayableOPAmtMap(SearchBean searchBean);

    Map<Long, Double> getCustomersPaidInstAmtMap(SearchBean searchBean);
}
