package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreements;
import com.msd.fixAssetRegister.repository.MaintenanceMaintenanceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaintenanceMaintenanceDataService {


    @Autowired
    MaintenanceMaintenanceDataRepository maintenanceMaintenanceDataRepository;


    @Transactional
    public String saveMaintenanceData(MaintenanceMaintenanceData maintData) {
        String[] maintMaintDa = maintData.getMaintDa().split(",");
        String[] maintWork = maintData.getMaintWork().split(",");
        String[] maintMaster = maintData.getMaintMaster().split(",");
        String[] maintMeter = maintData.getDetailMaintMeter().split(",");
        String[] maintCurrency = maintData.getDetailCurrencyType().split(",");
        String[] maintCost = maintData.getDetailMaintCost().split(",");
        String[] maintAcCode = maintData.getMaintAcCode().split(",");


        for (int i = 0; i < maintMaster.length; i++) {
            MaintenanceMaintenanceData maintenanceMaintenanceData = new MaintenanceMaintenanceData();

            maintenanceMaintenanceData.setAssetId(maintData.getAssetId());
            maintenanceMaintenanceData.setTransactionNo(maintData.getTransactionNo());
            maintenanceMaintenanceData.setMaintEntryDa(maintData.getMaintEntryDa());
            maintenanceMaintenanceData.setMaintDa(maintMaintDa[i]);
            maintenanceMaintenanceData.setMaintWork(maintWork[i]);
            maintenanceMaintenanceData.setMaintMeter(Double.parseDouble(maintMeter[i]));
            maintenanceMaintenanceData.setMaintMaster(maintMaster[i]);
            maintenanceMaintenanceData.setCurrencyType(Integer.parseInt(maintCurrency[i]));
            maintenanceMaintenanceData.setMaintCost(Double.parseDouble(maintCost[i]));
            maintenanceMaintenanceData.setMaintAcCode(maintAcCode[i]);
            maintenanceMaintenanceData.setUserId(maintData.getUserId());
            maintenanceMaintenanceData.setActionTime(maintData.getActionTime());

            maintenanceMaintenanceDataRepository.save(maintenanceMaintenanceData);
        }
        return "Update Successful.";
    }


    @Transactional
    public List<String> getAccessoryName(int assetId) {
        return maintenanceMaintenanceDataRepository.getAccessoryName(assetId);
    }

    public int generateTransactionNo() {
        int rowCount = maintenanceMaintenanceDataRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = maintenanceMaintenanceDataRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;

    }

    @Transactional
    public List<MaintenanceMaintenanceData> getTransactionNoList(int assetId) {

        return maintenanceMaintenanceDataRepository.getTransactionNoList(assetId);

    }

    @Transactional
    public List<MaintenanceMaintenanceData> getMaintenanceDataDetails(String  transactionNo) {

        return maintenanceMaintenanceDataRepository.getMaintenanceDataDetails(transactionNo);

    }

    @Transactional
    public String getTransactionNo(int maintenanceId) {
        return maintenanceMaintenanceDataRepository.getTransactionNo(maintenanceId);
    }

    @Transactional
    public void deleteEntry(String transactionNo) {
         maintenanceMaintenanceDataRepository.deleteEntry(transactionNo);
    }

}

