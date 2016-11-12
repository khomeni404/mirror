package com.mirror2.admin.dao;



import com.mirror2.admin.model.BoardMeeting;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BoardMeetingDAO {
    public boolean save(BoardMeeting meeting);

    public boolean update(BoardMeeting meeting);

    public boolean delete(BoardMeeting meeting);

    public List<BoardMeeting> findAllBoardMeeting();

    public BoardMeeting getBoardMeeting(Long id);
    public Date getLastBoardMeetingDate();

}
