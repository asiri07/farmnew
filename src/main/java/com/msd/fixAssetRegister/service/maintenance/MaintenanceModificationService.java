package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceModification;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreements;
import com.msd.fixAssetRegister.repository.MaintenanceModificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MaintenanceModificationService {

    @Autowired
    MaintenanceModificationRepository maintenanceModificationRepository;


    public MaintenanceModification saveUpdateMaintenanceModification(MaintenanceModification maintenanceModification) {
        return maintenanceModificationRepository.save(maintenanceModification);
    }


    @Transactional
    public int generateTransactionNo() {
        int rowCount = maintenanceModificationRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = maintenanceModificationRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }

    @Transactional
    public List<MaintenanceModification> getTransactionNoList(int assetId) {
        return maintenanceModificationRepository.getTransactionNoList(assetId);

    }

    @Transactional
    public MaintenanceModification getModificationDetails(int transactionNo) {
        return maintenanceModificationRepository.getModificationDetails(transactionNo);
    }
    @Transactional
    public MaintenanceModification findById(int modiId) {
        return maintenanceModificationRepository.findById(modiId).get();
    }
}
