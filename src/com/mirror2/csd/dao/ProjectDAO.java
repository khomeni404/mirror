package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Project;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectDAO {
    public boolean save(Project project);

    public boolean update(Project project);

    public boolean delete(Project project);

    public List<Project> findAllProject();

    public Project getProject(Long id);
}
