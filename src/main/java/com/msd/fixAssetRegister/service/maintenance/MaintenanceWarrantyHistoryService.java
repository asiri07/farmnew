package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceInsuranceHistory;
import com.msd.fixAssetRegister.model.MaintenanceWarranty;
import com.msd.fixAssetRegister.model.MaintenanceWarrantyHistory;
import com.msd.fixAssetRegister.repository.MaintenanceInsuranceHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceWarrantyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceWarrantyHistoryService {

    @Autowired
    MaintenanceWarrantyHistoryRepository maintenanceWarrantyHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceWarranty maintenanceWarranty, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceWarrantyHistory maintenanceWarrantyHistory = new MaintenanceWarrantyHistory();

        maintenanceWarrantyHistory.setWarrantyId(maintenanceWarranty.getWarrantyId());
        maintenanceWarrantyHistory.setTransactionNo(maintenanceWarranty.getTransactionNo());
        maintenanceWarrantyHistory.setAssetId(maintenanceWarranty.getAssetId());
        maintenanceWarrantyHistory.setWarrantyPeriod(maintenanceWarranty.getWarrantyPeriod());
        maintenanceWarrantyHistory.setUserID(maintenanceWarranty.getUserID());
        maintenanceWarrantyHistory.setActionTime(maintenanceWarranty.getActionTime());
        maintenanceWarrantyHistory.setWarrantyPeriodFrom(maintenanceWarranty.getWarrantyPeriodFrom());
        maintenanceWarrantyHistory.setWarrantyPeriodTo(maintenanceWarranty.getWarrantyPeriodTo());
        maintenanceWarrantyHistory.setUpdateUser(userId);
        maintenanceWarrantyHistory.setUpdateTime(date);

        maintenanceWarrantyHistoryRepository.save(maintenanceWarrantyHistory);

    }

}
