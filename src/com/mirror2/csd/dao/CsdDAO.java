package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.DelayCharge;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * @author Khomeni
 *         Created on : 23-Jan-18 at 10:11 AM
 */


public interface CsdDAO {
    List<DelayCharge> calculateDelayCharge(Customer customer, LocalDate from, LocalDate to);

    List<Long> getAssignedBadgeIdList(Long customerId);
}
