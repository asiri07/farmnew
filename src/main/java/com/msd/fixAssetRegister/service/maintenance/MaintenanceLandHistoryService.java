package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceLand;
import com.msd.fixAssetRegister.model.MaintenanceLandHistory;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinaryHistory;
import com.msd.fixAssetRegister.repository.MaintenanceLandHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenancePlantMachinaryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceLandHistoryService {

    @Autowired
    MaintenanceLandHistoryRepository maintenanceLandHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceLand maintenanceLand, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceLandHistory maintenanceLandHistory = new MaintenanceLandHistory();
        maintenanceLandHistory.setLandId(maintenanceLand.getLandId());
        maintenanceLandHistory.setAssetId(maintenanceLand.getAssetId());
        maintenanceLandHistory.setDeedNo(maintenanceLand.getDeedNo());
        maintenanceLandHistory.setDeedRegDate(maintenanceLand.getDeedRegDate());
        maintenanceLandHistory.setAssesmentNo(maintenanceLand.getAssesmentNo());
        maintenanceLandHistory.setUserId(maintenanceLand.getUserId());
        maintenanceLandHistory.setAssesmentValue(maintenanceLand.getAssesmentValue());
        maintenanceLandHistory.setLanAdd1(maintenanceLand.getLanAdd1());
        maintenanceLandHistory.setLanAdd2(maintenanceLand.getLanAdd2());
        maintenanceLandHistory.setLanAdd3(maintenanceLand.getLanAdd3());
        maintenanceLandHistory.setLanAdd4(maintenanceLand.getLanAdd4());
        maintenanceLandHistory.setExtent(maintenanceLand.getExtent());
        maintenanceLandHistory.setComments(maintenanceLand.getComments());
        maintenanceLandHistory.setAttester(maintenanceLand.getAttester());
        maintenanceLandHistory.setAttesterAdd(maintenanceLand.getAttesterAdd());
        maintenanceLandHistory.setSeller(maintenanceLand.getSeller());
        maintenanceLandHistory.setSelAdd(maintenanceLand.getSelAdd());
        maintenanceLandHistory.setGraNilDiviNo(maintenanceLand.getGraNilDiviNo());
        maintenanceLandHistory.setDivisionNo(maintenanceLand.getDivisionNo());
        maintenanceLandHistory.setSurveyPlanNo(maintenanceLand.getSurveyPlanNo());
        maintenanceLandHistory.setSurveyName(maintenanceLand.getSurveyName());
        maintenanceLandHistory.setSurveyAdd(maintenanceLand.getSurveyAdd());
        maintenanceLandHistory.setSurveyTel(maintenanceLand.getSurveyTel());
        maintenanceLandHistory.setSurveyDate(maintenanceLand.getSurveyDate());
        maintenanceLandHistory.setLotNo(maintenanceLand.getLotNo());
        maintenanceLandHistory.setLandName(maintenanceLand.getLandName());
        maintenanceLandHistory.setIsInsurance(maintenanceLand.getIsInsurance());
        maintenanceLandHistory.setLandSituated(maintenanceLand.getLandSituated());
        maintenanceLandHistory.setLandPArea(maintenanceLand.getLandPArea());
        maintenanceLandHistory.setLandDistrict(maintenanceLand.getLandDistrict());
        maintenanceLandHistory.setLandProvince(maintenanceLand.getLandProvince());
        maintenanceLandHistory.setDeedSignDate(maintenanceLand.getDeedSignDate());

        maintenanceLandHistory.setActionTime(maintenanceLand.getActionTime());
        maintenanceLandHistory.setUpdateUser(userId);
        maintenanceLandHistory.setUpdateTime(date);
        maintenanceLandHistoryRepository.save(maintenanceLandHistory);
    }

}
