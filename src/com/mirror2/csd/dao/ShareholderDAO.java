package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Shareholder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ShareholderDAO {
    public boolean save(Shareholder shareholder);

    public boolean update(Shareholder shareholder);

    public boolean delete(Shareholder shareholder);

    public List<Shareholder> findAllShareholder();

    public Shareholder getShareholder(Long id);
}
