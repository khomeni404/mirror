package com.mirror2.csd.dao;

import com.mirror2.csd.model.Director;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DirectorDAO {
    public boolean save(Director director);

    public boolean update(Director director);

    public boolean delete(Director director);

    public List<Director> findAllDirector();

    public Director getDirector(Long id);
}
