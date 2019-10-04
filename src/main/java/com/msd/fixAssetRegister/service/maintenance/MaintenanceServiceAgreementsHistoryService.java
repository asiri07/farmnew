package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceInsuranceHistory;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreements;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreementsHistory;
import com.msd.fixAssetRegister.repository.MaintenanceInsuranceHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceServiceAgreementHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceServiceAgreementsHistoryService {

    @Autowired
    MaintenanceServiceAgreementHistoryRepository maintenanceServiceAgreementHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceServiceAgreements maintenanceServiceAgreements, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceServiceAgreementsHistory maintenanceServiceAgreementsHistory = new MaintenanceServiceAgreementsHistory();
        maintenanceServiceAgreementsHistory.setAGREE_ID(maintenanceServiceAgreements.getAgreeId());
        maintenanceServiceAgreementsHistory.setTransactionNo(maintenanceServiceAgreements.getTransactionNo());
        maintenanceServiceAgreementsHistory.setAssetId(maintenanceServiceAgreements.getAssetId());
        maintenanceServiceAgreementsHistory.setUserId(maintenanceServiceAgreements.getUserId());
        maintenanceServiceAgreementsHistory.setActionTime(maintenanceServiceAgreements.getActionTime());
        maintenanceServiceAgreementsHistory.setAgreeFrom(maintenanceServiceAgreements.getAgreeFrom());
        maintenanceServiceAgreementsHistory.setAgreeTo(maintenanceServiceAgreements.getAgreeTo());
        maintenanceServiceAgreementsHistory.setAgreePeriod(maintenanceServiceAgreements.getAgreePeriod());
        maintenanceServiceAgreementsHistory.setCurrencyType(maintenanceServiceAgreements.getCurrencyType());
        maintenanceServiceAgreementsHistory.setAgreeCost(maintenanceServiceAgreements.getAgreeCost());
        maintenanceServiceAgreementsHistory.setAgreeCompany(maintenanceServiceAgreements.getAgreeCompany());
        maintenanceServiceAgreementsHistory.setAgreeNumber(maintenanceServiceAgreements.getAgreeNumber());
        maintenanceServiceAgreementsHistory.setAgreeServiceInterval(maintenanceServiceAgreements.getAgreeServiceInterval());
        maintenanceServiceAgreementsHistory.setUpdateUser(userId);
        maintenanceServiceAgreementsHistory.setUpdateTime(date);
        maintenanceServiceAgreementHistoryRepository.save(maintenanceServiceAgreementsHistory);

    }

}
