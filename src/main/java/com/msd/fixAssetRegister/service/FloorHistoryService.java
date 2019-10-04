package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.Floor;
import com.msd.fixAssetRegister.model.FloorHistory;
import com.msd.fixAssetRegister.repository.FloorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
@Service
public class FloorHistoryService{

    @Autowired
    FloorHistoryRepository floorHistoryRepository;

    @Transactional
    public void updateHistory(Floor flowOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        FloorHistory flowHistory = new FloorHistory();
        flowHistory.setId(flowOld.getId());
        flowHistory.setBuildingId(flowOld.getBuilding().getId());
        flowHistory.setDescription(flowOld.getDescription());
        flowHistory.setFloorCode(flowOld.getFloorCode());
        flowHistory.setUserId(flowOld.getUserId());
        flowHistory.setActionTime(flowOld.getActionTime());
        flowHistory.setStatus(status);
        flowHistory.setUpdateUser(userId);
        flowHistory.setUpdateTime(date);
        floorHistoryRepository.save(flowHistory);
    }
}
