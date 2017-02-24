package com.mirror2.mis.dao;

import com.mirror2.util.MirrorDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Khomeni on 23-Feb-17.
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MisDaoImpl implements MisDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getCustomerData(Long offerId) {
        if (offerId == null) {
            Object[] param = new Object[]{MirrorDataList.CUST_STATUS_CANCELLED, MirrorDataList.CUST_STATUS_REFUNDED};
            return hibernateTemplate.find("select CID, name, bookingDate, floorSize, building.nameAlias, " +
                    "AID, referenceBy.mid,  c.offer.offerName  from Customer c where c.status not in(?, ?) order by c.CID", param);
        }else {
            Object[] param = new Object[]{offerId, MirrorDataList.CUST_STATUS_CANCELLED, MirrorDataList.CUST_STATUS_REFUNDED};
          return   hibernateTemplate.find("select CID, name, bookingDate, floorSize, building.nameAlias, " +
                    "AID, referenceBy.mid,  c.offer.offerName  from Customer c where c.offer.id = ? " +
                    " and  c.status not in(?, ?) " +
                    "order by c.CID", param);
        }
    }

}



