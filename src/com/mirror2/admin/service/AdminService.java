package com.mirror2.admin.service;

import com.mirror2.admin.model.BoardMeeting;
import com.mirror2.csd.model.Director;

import java.util.Date;
import java.util.List;

public interface AdminService {
    public boolean save(BoardMeeting meeting);

    public boolean update(BoardMeeting meeting);

    public boolean delete(BoardMeeting meeting);

    public List<BoardMeeting> findAllBoardMeeting();

    public BoardMeeting getBoardMeeting(Long id);

    public Date getLastBoardMeetingDate();

    //Director
    public boolean save(Director director);

    public boolean update(Director director);

    public boolean delete(Director director);

    public List<Director> findAllDirector();

    public Director getDirector(Long id);

}
