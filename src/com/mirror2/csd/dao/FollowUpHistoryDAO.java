
package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.FollowUpHistory;
import java.util.Date;
import java.util.List;

public interface FollowUpHistoryDAO {
    boolean save(FollowUpHistory var1);

    boolean update(FollowUpHistory var1);

    boolean delete(FollowUpHistory var1);

    List<FollowUpHistory> findAllFollowUpHistory();

    List<FollowUpHistory> findAllFollowUpHistory(Customer var1);

    List<FollowUpHistory> findAllFollowUpHistory(Date var1, String var2);

    FollowUpHistory getFollowUpHistory(Long var1);

    boolean setInactiveHistory(Customer var1);
}
