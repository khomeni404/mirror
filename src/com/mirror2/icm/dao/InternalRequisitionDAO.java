package com.mirror2.icm.dao;

import com.mirror2.icm.model.InternalRequisition;
import com.mirror2.icm.model.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface InternalRequisitionDAO {
    public boolean save(InternalRequisition internalRequisition);

    public boolean update(InternalRequisition internalRequisition);

    public boolean delete(InternalRequisition internalRequisition);

    public List<InternalRequisition> findAllInternalRequisition();

    public InternalRequisition getInternalRequisition(Long id);

}
