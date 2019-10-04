package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceInsuranceHistory;
import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import com.msd.fixAssetRegister.model.MaintenanceSoftwareHistory;
import com.msd.fixAssetRegister.repository.MaintenanceInsuranceHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceSoftwareHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceInsuranceHistoryService {

    @Autowired
    MaintenanceInsuranceHistoryRepository maintenanceInsuranceHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceInsurance maintenanceInsurance, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceInsuranceHistory maintenanceInsuranceHistory = new MaintenanceInsuranceHistory();
        maintenanceInsuranceHistory.setInsuranceId(maintenanceInsurance.getInsuranceID());
        maintenanceInsuranceHistory.setTransactionNo(maintenanceInsurance.getTransactionNo());
        maintenanceInsuranceHistory.setAssetId(maintenanceInsurance.getAssetId());
        maintenanceInsuranceHistory.setInsuranceType(maintenanceInsurance.getInsuranceType());
        maintenanceInsuranceHistory.setInsurancePolicyNo(maintenanceInsurance.getInsurancePolicyNo());
        maintenanceInsuranceHistory.setInsurancePolicy(maintenanceInsurance.getInsurancePolicy());
        maintenanceInsuranceHistory.setInsurancePeriod(maintenanceInsurance.getInsurancePeriod());
        maintenanceInsuranceHistory.setInsurancePeriodFrom(maintenanceInsurance.getInsurancePeriodFrom());
        maintenanceInsuranceHistory.setInsurancePeriodTo(maintenanceInsurance.getInsurancePeriodTo());
        maintenanceInsuranceHistory.setPremium(maintenanceInsurance.getPremium());
        maintenanceInsuranceHistory.setCurrencyType(maintenanceInsurance.getCurrencyType());
        maintenanceInsuranceHistory.setInsuranceValue(maintenanceInsurance.getInsuranceValue());
        maintenanceInsuranceHistory.setInsuranceCompany(maintenanceInsurance.getInsuranceCompany());
        maintenanceInsuranceHistory.setInsuranceAgent(maintenanceInsurance.getInsuranceAgent());
        maintenanceInsuranceHistory.setInsuranceAddress(maintenanceInsurance.getInsuranceAddress());
        maintenanceInsuranceHistory.setCategory(maintenanceInsurance.getCategory());
        maintenanceInsuranceHistory.setInsuranceTelephoneNo(maintenanceInsurance.getInsuranceTelephoneNo());
        maintenanceInsuranceHistory.setUserId(maintenanceInsurance.getUserId());
        maintenanceInsuranceHistory.setActionTime(maintenanceInsurance.getActionTime());
        maintenanceInsuranceHistory.setUpdateUser(userId);
        maintenanceInsuranceHistory.setUpdateTime(date);
        maintenanceInsuranceHistoryRepository.save(maintenanceInsuranceHistory);

    }

}
