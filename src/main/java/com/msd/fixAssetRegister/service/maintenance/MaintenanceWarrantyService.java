package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceWarranty;
import com.msd.fixAssetRegister.repository.MaintenanceWarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class MaintenanceWarrantyService {

    @Autowired
    MaintenanceWarrantyRepository maintenanceWarrantyRepository;

    public MaintenanceWarranty saveUpdateMaintenaceWarranty(MaintenanceWarranty maintenanceWarranty) {
        return maintenanceWarrantyRepository.save(maintenanceWarranty);
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount = maintenanceWarrantyRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = maintenanceWarrantyRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }

    @Transactional
    public List<MaintenanceWarranty> getTransactionNoList(int assetId) {
        return maintenanceWarrantyRepository.getTransactionNoList(assetId);

    }

    @Transactional
    public MaintenanceWarranty getWarrantyDetails(int transactionNo) {
        return maintenanceWarrantyRepository.getWarrantyDetails(transactionNo);
    }
    @Transactional
    public MaintenanceWarranty findById(int insuranceId) {
        return maintenanceWarrantyRepository.findById(insuranceId).get();
    }
    @Transactional
    public List<MaintenanceWarranty> loadData(Date parse, int noDays, int branch) {

        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        c.add(Calendar.DATE,noDays);
        Date date =c.getTime();

        if(branch == 0) {
            return maintenanceWarrantyRepository.loadData(parse,date);
        }else{
            return maintenanceWarrantyRepository.loadData(parse,date,branch);
        }
    }
}
