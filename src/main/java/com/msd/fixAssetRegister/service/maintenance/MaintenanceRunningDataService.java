package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.model.MaintenanceRunningData;
import com.msd.fixAssetRegister.repository.MaintenanceRunningDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.type.NullType;
import java.util.List;


@Service
public class MaintenanceRunningDataService {

    @Autowired
    MaintenanceRunningDataRepository maintenanceRunningDataRepository;

    @Transactional
    public String saveRunningData(MaintenanceRunningData runningData) {

        // Unable to store values of  MaintD

        String[] runRemark = runningData.getRemark().split(",");
        String[] runDate = runningData.getDate().split(",");
        String[] runTime = runningData.getTime().split(",");
        String[] runMetFroNo = runningData.getDetailMetFroNo().split(",");
        String[] runMetToNo = runningData.getDetailMetToNo().split(",");
        String[] currencyId = runningData.getDetailCurrencyType().split(",");
        String[] runCost = runningData.getDetailCost().split(",");


        for (int i = 0; i < runRemark.length; i++) {
            MaintenanceRunningData maintenanceRunningData = new MaintenanceRunningData();


            maintenanceRunningData.setAssetId(runningData.getAssetId());
            maintenanceRunningData.setTransactionNo(runningData.getTransactionNo());
            maintenanceRunningData.setDate(runDate[i]);
            maintenanceRunningData.setTime(runTime[i]);
            maintenanceRunningData.setMetFroNo(Double.parseDouble(runMetFroNo[i].trim()));
            maintenanceRunningData.setMetToNo(Double.parseDouble(runMetToNo[i].trim()));
            maintenanceRunningData.setCost(Double.parseDouble(runCost[i]));
            maintenanceRunningData.setRemark(runRemark[i]);
            maintenanceRunningData.setCurrencyType(Integer.parseInt(currencyId[i]));
            maintenanceRunningData.setActionTime(runningData.getActionTime());
            maintenanceRunningData.setUserId(runningData.getUserId());

            maintenanceRunningDataRepository.save(maintenanceRunningData);
        }
        return "Update Successful.";
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount = maintenanceRunningDataRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = maintenanceRunningDataRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;

    }
    @Transactional
    public List<MaintenanceRunningData> getTransactionNoList(int assetId) {

        return maintenanceRunningDataRepository.getTransactionNoList(assetId);

    }

    @Transactional
    public List<MaintenanceRunningData> getRunningDataDetails(String  transactionNo) {

        return maintenanceRunningDataRepository.getRunningDataDetails(transactionNo);

    }

    @Transactional
    public String getTransactionNo(int maintenanceId) {
        return maintenanceRunningDataRepository.getTransactionNo(maintenanceId);
    }

    @Transactional
    public void deleteEntry(String transactionNo) {
        maintenanceRunningDataRepository.deleteEntry(transactionNo);
    }

}
