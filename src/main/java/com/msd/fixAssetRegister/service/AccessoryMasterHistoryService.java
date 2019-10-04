package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AccessoryMaster;
import com.msd.fixAssetRegister.model.AccessoryMasterHistory;
import com.msd.fixAssetRegister.model.Building;
import com.msd.fixAssetRegister.model.BuildingHistory;
import com.msd.fixAssetRegister.repository.AccessoryMasterHistoryRepository;
import com.msd.fixAssetRegister.repository.AccessoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class AccessoryMasterHistoryService {

    @Autowired
    AccessoryMasterHistoryRepository accessoryMasterHistoryRepository;

    @Transactional
    public void updateHistory(AccessoryMaster accessoryOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        AccessoryMasterHistory accessoryMasterHistory = new AccessoryMasterHistory();
        accessoryMasterHistory.setAccessoryId(accessoryOld.getAccerId());
        accessoryMasterHistory.setAccessoryCode(accessoryOld.getAccerCode());
        accessoryMasterHistory.setAccessoryName(accessoryOld.getAccerName());
        accessoryMasterHistory.setActionTime(accessoryOld.getActionTime());
        accessoryMasterHistory.setUserId(accessoryOld.getUserId());
        accessoryMasterHistory.setStatus(status);
        accessoryMasterHistory.setUpdateUser(userId);
        accessoryMasterHistory.setUpdateTime(date);
        accessoryMasterHistoryRepository.save(accessoryMasterHistory);
    }
}
