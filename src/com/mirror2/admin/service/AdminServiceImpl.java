package com.mirror2.admin.service;


import com.mirror2.admin.dao.BoardMeetingDAO;
import com.mirror2.admin.model.BoardMeeting;
import com.mirror2.common.dao.CommonDAO;
import com.mirror2.common.model.Badge;
import com.mirror2.csd.dao.CsdDAO;
import com.mirror2.csd.dao.DirectorDAO;
import com.mirror2.csd.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl  implements AdminService {
    @Autowired
    BoardMeetingDAO boardMeetingDAO;
    @Autowired
    private DirectorDAO directorDAO;
    @Autowired
    private CommonDAO commonDAO;
    @Autowired
    private CsdDAO csdDAO;

    @Override
    public boolean save(Director director) {
        return directorDAO.save(director);
    }

    @Override
    public boolean update(Director director) {
        return directorDAO.update(director);
    }

    @Override
    public boolean delete(Director director) {
        return directorDAO.delete(director);
    }

    @Override
    public List<Director> findAllDirector() {
        return directorDAO.findAllDirector();
    }

    @Override
    public Director getDirector(Long id) {
        return directorDAO.getDirector(id);
    }


    //Meeting
    @Override
    public boolean save(BoardMeeting meeting) {
        return boardMeetingDAO.save(meeting);
    }

    @Override
    public boolean update(BoardMeeting meeting) {
        return boardMeetingDAO.update(meeting);
    }

    @Override
    public boolean delete(BoardMeeting meeting) {
        return boardMeetingDAO.delete(meeting);
    }

    @Override
    public List<BoardMeeting> findAllBoardMeeting() {
        return boardMeetingDAO.findAllBoardMeeting();
    }

    @Override
    public BoardMeeting getBoardMeeting(Long id) {
        return boardMeetingDAO.getBoardMeeting(id);
    }

    @Override
    public Date getLastBoardMeetingDate() {
        return boardMeetingDAO.getLastBoardMeetingDate();
    }

    @Override
    public List<Map<String, String>> getCustomerBadgeDataMapList(Long customerId) {
        List<Long> assignedGroupIds = csdDAO.getAssignedBadgeIdList(customerId);
        List<Badge> badgeList = commonDAO.findAll(Badge.class);

        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        Map<String, String> dataMap;
        for (Badge badge : badgeList) {
            dataMap = new HashMap<String, String>();
            Long groupId = badge.getId();
            dataMap.put("id", groupId.toString());
            dataMap.put("name", badge.getName());
            boolean isAssigned = assignedGroupIds.contains(groupId);
            dataMap.put("checked", isAssigned ? "checked" : "");
            dataList.add(dataMap);
        }
        return dataList;
    }
}
