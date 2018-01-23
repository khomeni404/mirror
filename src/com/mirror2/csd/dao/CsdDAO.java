package com.mirror2.csd.dao;

import java.util.List;

/**
 * @author Khomeni
 *         Created on : 23-Jan-18 at 10:11 AM
 */


public interface CsdDAO {
    List<Long> getAssignedBadgeIdList(Long customerId);
}
