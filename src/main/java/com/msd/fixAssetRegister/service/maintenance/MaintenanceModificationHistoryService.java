package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceInsuranceHistory;
import com.msd.fixAssetRegister.model.MaintenanceModification;
import com.msd.fixAssetRegister.model.MaintenanceModificationHistory;
import com.msd.fixAssetRegister.repository.MaintenanceInsuranceHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceModificationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceModificationHistoryService {

    @Autowired
    MaintenanceModificationHistoryRepository maintenanceModificationHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceModification maintenanceModification, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceModificationHistory maintenanceModificationHistory = new MaintenanceModificationHistory();
        maintenanceModificationHistory.setModificationId(maintenanceModification.getModificationId());
        maintenanceModificationHistory.setTransactionNo(maintenanceModification.getTransactionNo());
        maintenanceModificationHistory.setAssetId(maintenanceModification.getAssetId());
        maintenanceModificationHistory.setModiCost(maintenanceModification.getModiCost());
        maintenanceModificationHistory.setModiDate(maintenanceModification.getModiDate());
        maintenanceModificationHistory.setModiReason(maintenanceModification.getModiReason());
        maintenanceModificationHistory.setUserId(maintenanceModification.getUserId());
        maintenanceModificationHistory.setActionTime(maintenanceModification.getActionTime());
        maintenanceModificationHistory.setModiWork(maintenanceModification.getModiWork());
        maintenanceModificationHistory.setModiType(maintenanceModification.getModiType());
        maintenanceModificationHistory.setCurrencyType(maintenanceModification.getCurrencyType());
        maintenanceModificationHistory.setUpdateUser(userId);
        maintenanceModificationHistory.setUpdateTime(date);
        maintenanceModificationHistoryRepository.save(maintenanceModificationHistory);

    }

}
