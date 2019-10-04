package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceLand;
import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import com.msd.fixAssetRegister.model.MaintenanceLandBulidingHistory;
import com.msd.fixAssetRegister.model.MaintenanceLandHistory;
import com.msd.fixAssetRegister.repository.MaintenanceLandBuildingHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceLandHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceLandBuildingHistoryService {

    @Autowired
    MaintenanceLandBuildingHistoryRepository maintenanceLandBuildingHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceLandBuliding maintenanceLandBuliding, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceLandBulidingHistory maintenanceLandBuildingHistory = new MaintenanceLandBulidingHistory();
        maintenanceLandBuildingHistory.setLandBuildingId(maintenanceLandBuliding.getLandBuildingID());
        maintenanceLandBuildingHistory.setAssetId(maintenanceLandBuliding.getAssetId());
        maintenanceLandBuildingHistory.setDeedNo(maintenanceLandBuliding.getDeedNo());
        maintenanceLandBuildingHistory.setDeedSignedDate(maintenanceLandBuliding.getDeedSignedDate());
        maintenanceLandBuildingHistory.setDeedRegisterDate(maintenanceLandBuliding.getDeedRegisterDate());
        maintenanceLandBuildingHistory.setUserId(maintenanceLandBuliding.getUserId());
        maintenanceLandBuildingHistory.setDeedType(maintenanceLandBuliding.getDeedType());
        maintenanceLandBuildingHistory.setBuildingAddres1(maintenanceLandBuliding.getBuildingAddress1());
        maintenanceLandBuildingHistory.setBuildingAddres2(maintenanceLandBuliding.getBuildingAddress2());
        maintenanceLandBuildingHistory.setBuildingAddres3(maintenanceLandBuliding.getBuildingAddress3());
        maintenanceLandBuildingHistory.setBuildingAddres4(maintenanceLandBuliding.getBuildingAddress4());
        maintenanceLandBuildingHistory.setDeedValue(maintenanceLandBuliding.getDeedValue());
        maintenanceLandBuildingHistory.setComments(maintenanceLandBuliding.getComments());
        maintenanceLandBuildingHistory.setAttester(maintenanceLandBuliding.getAttester());
        maintenanceLandBuildingHistory.setAttesterAddress(maintenanceLandBuliding.getAttesterAddress());
        maintenanceLandBuildingHistory.setSeller(maintenanceLandBuliding.getSeller());
        maintenanceLandBuildingHistory.setSeller(maintenanceLandBuliding.getSeller());
        maintenanceLandBuildingHistory.setSellerAddress(maintenanceLandBuliding.getSellerAddress());
        maintenanceLandBuildingHistory.setDivisionNo(maintenanceLandBuliding.getDivisionNo());
        maintenanceLandBuildingHistory.setSurveyPlanNo(maintenanceLandBuliding.getSurveyPlanNo());
        maintenanceLandBuildingHistory.setSurveyName(maintenanceLandBuliding.getSurveyName());
        maintenanceLandBuildingHistory.setSurveyAddress(maintenanceLandBuliding.getSurveyAddress());
        maintenanceLandBuildingHistory.setSurveyTelephone(maintenanceLandBuliding.getSurveyTelephone());
        maintenanceLandBuildingHistory.setSurveyDate(maintenanceLandBuliding.getSurveyDate());
        maintenanceLandBuildingHistory.setLotNo(maintenanceLandBuliding.getLotNo());
        maintenanceLandBuildingHistory.setLandName(maintenanceLandBuliding.getLandName());
        maintenanceLandBuildingHistory.setLandSituated(maintenanceLandBuliding.getLandSituated());
        maintenanceLandBuildingHistory.setLandProvince(maintenanceLandBuliding.getLandProvince());
        maintenanceLandBuildingHistory.setLandDistrict(maintenanceLandBuliding.getLandDistrict());
        maintenanceLandBuildingHistory.setLandProvincalArea(maintenanceLandBuliding.getLandProvincalArea());
        maintenanceLandBuildingHistory.setCocNo(maintenanceLandBuliding.getCocNo());

        maintenanceLandBuildingHistory.setActionTime(maintenanceLandBuliding.getActionTime());
        maintenanceLandBuildingHistory.setUpdateUser(userId);
        maintenanceLandBuildingHistory.setUpdateTime(date);
        maintenanceLandBuildingHistoryRepository.save(maintenanceLandBuildingHistory);
    }

}
