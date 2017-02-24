package com.mirror2.mis.dao;

import java.util.List;

/**
 * Created by Khomeni on 23-Feb-17.
 */
public interface MisDAO {
    List<Object[]> getCustomerData(Long offerId);
}
