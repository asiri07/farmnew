package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceComputerHistory;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceDataHistory;
import com.msd.fixAssetRegister.repository.MaintenanceComputerHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceMaintenanceDataHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceMaintenanceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceMaintenanceDataHistoryService {


    @Autowired
    MaintenanceMaintenanceDataHistoryRepository maintenanceMaintenanceDataHistoryRepository ;

    @Transactional
    public void updateHistory(List<MaintenanceMaintenanceData> maintenanceMaintenanceData, int status, int userId) {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        int length = maintenanceMaintenanceData.size();


        int i;
        for(i=0;i< length;i++) {
            MaintenanceMaintenanceDataHistory maintenanceMaintenanceDataHistory = new MaintenanceMaintenanceDataHistory();

        maintenanceMaintenanceDataHistory.setMaintenanceDataId(maintenanceMaintenanceData.get(i).getMaintenanceDataId());
        maintenanceMaintenanceDataHistory.setAssetId(maintenanceMaintenanceData.get(i).getAssetId());
        maintenanceMaintenanceDataHistory.setMaintEntryDa(maintenanceMaintenanceData.get(i).getMaintEntryDa());
        maintenanceMaintenanceDataHistory.setTransactionNo(maintenanceMaintenanceData.get(i).getTransactionNo());
        maintenanceMaintenanceDataHistory.setMaintDa(maintenanceMaintenanceData.get(i).getMaintDa());
        maintenanceMaintenanceDataHistory.setMaintWork(maintenanceMaintenanceData.get(i).getMaintWork());
        maintenanceMaintenanceDataHistory.setMaintMeter(maintenanceMaintenanceData.get(i).getMaintMeter());
        maintenanceMaintenanceDataHistory.setMaintMaster(maintenanceMaintenanceData.get(i).getMaintMaster());
        maintenanceMaintenanceDataHistory.setMaintCost(maintenanceMaintenanceData.get(i).getMaintCost());
        maintenanceMaintenanceDataHistory.setMaintAcCode(maintenanceMaintenanceData.get(i).getMaintAcCode());
        maintenanceMaintenanceDataHistory.setCurrencyType(maintenanceMaintenanceData.get(i).getCurrencyType());
        maintenanceMaintenanceDataHistory.setUserId(maintenanceMaintenanceData.get(i).getUserId());
        maintenanceMaintenanceDataHistory.setActionTime(maintenanceMaintenanceData.get(i).getActionTime());
        maintenanceMaintenanceDataHistory.setUpdateUserId(userId);
        maintenanceMaintenanceDataHistory.setUpdateTime(date);

        maintenanceMaintenanceDataHistoryRepository.save(maintenanceMaintenanceDataHistory);
    }}}




