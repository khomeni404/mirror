package com.mirror2.admin.dao;


import com.mirror2.admin.model.BoardMeeting;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BoardMeetingDaoImpl implements BoardMeetingDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(BoardMeeting meeting) {
        try {
            hibernateTemplate.persist(meeting);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(BoardMeeting meeting) {
        hibernateTemplate.merge(meeting);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(BoardMeeting meeting) {
        hibernateTemplate.delete(meeting);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<BoardMeeting> findAllBoardMeeting() {
        return hibernateTemplate.find("from BoardMeeting order by heldOn desc ");
    }

    @Override
    public BoardMeeting getBoardMeeting(Long id) {
        return hibernateTemplate.get(BoardMeeting.class, id);
    }

    @Override
    public Date getLastBoardMeetingDate() {
        DetachedCriteria maxQuery = DetachedCriteria.forClass( BoardMeeting.class );
        maxQuery.setProjection( Projections.max("heldOn") );

        try {
            return (Date) hibernateTemplate.findByCriteria(maxQuery).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
