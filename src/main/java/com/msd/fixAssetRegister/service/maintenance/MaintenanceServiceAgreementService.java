package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreements;
import com.msd.fixAssetRegister.model.MaintenanceWarranty;
import com.msd.fixAssetRegister.repository.MaintenanceServiceAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class MaintenanceServiceAgreementService {

    @Autowired
    MaintenanceServiceAgreementRepository maintenanceServiceAgreementRepository;

    public MaintenanceServiceAgreements saveUpdateMaintenanceServiceAgreement(MaintenanceServiceAgreements maintenanceServiceAgreements) {
        return maintenanceServiceAgreementRepository.save(maintenanceServiceAgreements);
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount = maintenanceServiceAgreementRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = maintenanceServiceAgreementRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }

    @Transactional
    public List<MaintenanceServiceAgreements> getTransactionNoList(int assetId) {

        return maintenanceServiceAgreementRepository.getTransactionNoList(assetId);

    }

    @Transactional
    public MaintenanceServiceAgreements getServiceAgreeDetails(int transactionNo) {
        return maintenanceServiceAgreementRepository.getServiceAgreeDetails(transactionNo);
    }
    @Transactional
    public MaintenanceServiceAgreements findById(int agreeId) {
        return maintenanceServiceAgreementRepository.findById(agreeId).get();
    }

    @Transactional
    public List<MaintenanceServiceAgreements> loadData(Date parse, int noDays, int branch) {

        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        c.add(Calendar.DATE,noDays);
        Date date =c.getTime();
        if(branch == 0) {
            return maintenanceServiceAgreementRepository.loadData(parse,date);
        }else{
            return maintenanceServiceAgreementRepository.loadData(parse,date,branch);
        }

    }

}
