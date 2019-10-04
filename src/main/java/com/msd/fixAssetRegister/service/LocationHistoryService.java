package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.LocationMaster;
import com.msd.fixAssetRegister.model.LocationMasterHistory;
import com.msd.fixAssetRegister.repository.LocationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
@Service
public class LocationHistoryService{

    @Autowired
    LocationHistoryRepository locationHistoryRepository;

    @Transactional
    public void updateHistory(LocationMaster locationMasterOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        LocationMasterHistory locationMasterHistory = new LocationMasterHistory();
        locationMasterHistory.setLocId(locationMasterOld.getLocId());
        locationMasterHistory.setLocCode(locationMasterOld.getLocCode());
        locationMasterHistory.setLocDes(locationMasterOld.getLocDes());
        locationMasterHistory.setSecId(locationMasterOld.getSectionMaster().getSecId());
        locationMasterHistory.setRoomId(locationMasterOld.getRoom().getId());
        locationMasterHistory.setUserId(locationMasterOld.getUserId());
        locationMasterHistory.setActionTime(locationMasterOld.getActionTime());
        locationMasterHistory.setStatus(status);
        locationMasterHistory.setUpdateUser(userId);
        locationMasterHistory.setUpdateTime(date);
        locationHistoryRepository.save(locationMasterHistory);
    }
}
