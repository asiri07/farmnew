package com.msd.fixAssetRegister.service;


import com.msd.fixAssetRegister.model.Room;
import com.msd.fixAssetRegister.model.RoomHistory;
import com.msd.fixAssetRegister.repository.RoomHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class RoomHistoryService {

    @Autowired
    RoomHistoryRepository roomHistoryRepository;

    @Transactional
    public void updateHistory(Room roomOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        RoomHistory roomHistory = new RoomHistory();
        roomHistory.setId(roomOld.getId());
        roomHistory.setDescription(roomOld.getDescription());
        roomHistory.setRoomCode(roomOld.getRoomCode());
        roomHistory.setUserId(roomOld.getUserId());
        roomHistory.setFlowId(roomOld.getFloor().getId());
        roomHistory.setActionTime(roomOld.getActionTime());
        roomHistory.setStatus(status);
        roomHistory.setUpdateUser(userId);
        roomHistory.setUpdateTime(date);
        roomHistoryRepository.save(roomHistory);
    }
}
