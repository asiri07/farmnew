package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceLeaseAsset;
import com.msd.fixAssetRegister.model.MaintenanceLeaseAssetHistory;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreements;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreementsHistory;
import com.msd.fixAssetRegister.repository.MaintenanceLeaseAssetHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceServiceAgreementHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceLeaseAssetHistoryService {

    @Autowired
    MaintenanceLeaseAssetHistoryRepository maintenanceLeaseAssetHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceLeaseAsset maintenanceLeaseAsset, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceLeaseAssetHistory maintenanceLeaseAssetHistory = new MaintenanceLeaseAssetHistory();
        maintenanceLeaseAssetHistory.setLeaseId(maintenanceLeaseAsset.getLeaseID());
        maintenanceLeaseAssetHistory.setTransactionNo(maintenanceLeaseAsset.getTransactionNo());
        maintenanceLeaseAssetHistory.setAssetId(maintenanceLeaseAsset.getAssetId());
        maintenanceLeaseAssetHistory.setUserId(maintenanceLeaseAsset.getUserId());
        maintenanceLeaseAssetHistory.setActionTime(maintenanceLeaseAsset.getActionTime());
        maintenanceLeaseAssetHistory.setContactPerson(maintenanceLeaseAsset.getContactPerson());
        maintenanceLeaseAssetHistory.setLeaseAddress(maintenanceLeaseAsset.getLeaseAddress());
        maintenanceLeaseAssetHistory.setLeaseAgreNo(maintenanceLeaseAsset.getLeaseAgreNo());
        maintenanceLeaseAssetHistory.setLeaseNoPremium(maintenanceLeaseAsset.getLeaseNoPremium());
        maintenanceLeaseAssetHistory.setLeasePeriodFrom(maintenanceLeaseAsset.getLeasePeriodFrom());
        maintenanceLeaseAssetHistory.setLeasePremiumTo(maintenanceLeaseAsset.getLeasePremiumTo());
        maintenanceLeaseAssetHistory.setLeasePremiumTo(maintenanceLeaseAsset.getLeasePremiumTo());
        maintenanceLeaseAssetHistory.setLeaseTelephoneNo(maintenanceLeaseAsset.getLeaseTelephoneNo());
        maintenanceLeaseAssetHistory.setLeasePremiumDate(maintenanceLeaseAsset.getLeasePremiumDate());
        maintenanceLeaseAssetHistory.setLeaseTot(maintenanceLeaseAsset.getLeaseTot());
        maintenanceLeaseAssetHistory.setLeaseCompany(maintenanceLeaseAsset.getLeaseCompany());

        maintenanceLeaseAssetHistory.setUpdateUser(userId);
        maintenanceLeaseAssetHistory.setUpdateTime(date);
        maintenanceLeaseAssetHistoryRepository.save(maintenanceLeaseAssetHistory);

    }

}
