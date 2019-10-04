package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.Building;
import com.msd.fixAssetRegister.model.BuildingHistory;
import com.msd.fixAssetRegister.repository.BuildingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
@Service
public class BuildingHistoryService  {

    @Autowired
    BuildingHistoryRepository buildingHistoryRepository;

    @Transactional
    public void updateHistory(Building buildingOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        BuildingHistory buildingHistory = new BuildingHistory();
        buildingHistory.setId(buildingOld.getId());
        buildingHistory.setBuildingCode(buildingOld.getBuildingCode());
        buildingHistory.setCityId(buildingOld.getCity().getCityId());
        buildingHistory.setDescription(buildingOld.getDescription());
        buildingHistory.setUserId(buildingOld.getUserId());
        buildingHistory.setActionTime(buildingOld.getActionTime());
        buildingHistory.setStatus(status);
        buildingHistory.setUpdateUser(userId);
        buildingHistory.setUpdateTime(date);
        buildingHistoryRepository.save(buildingHistory);
    }
}
