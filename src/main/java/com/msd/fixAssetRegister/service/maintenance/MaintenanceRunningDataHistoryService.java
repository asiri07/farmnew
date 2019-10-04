package com.msd.fixAssetRegister.service.maintenance;



import com.msd.fixAssetRegister.model.MaintenanceRunningData;
import com.msd.fixAssetRegister.model.MaintenanceRunningDataHistory;
import com.msd.fixAssetRegister.repository.MaintenanceRunningDataHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceRunningDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class MaintenanceRunningDataHistoryService {

    @Autowired
    MaintenanceRunningDataHistoryRepository maintenanceRunningDataHistoryRepository;

    @Transactional
    public void updateHistory(List<MaintenanceRunningData> maintenanceRunningData, int status, int userId) {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        int length = maintenanceRunningData.size();


        int i;
        for(i=0;i< length;i++) {
            MaintenanceRunningDataHistory maintenanceRunningDataHistory = new MaintenanceRunningDataHistory();

            maintenanceRunningDataHistory.setRunningId(maintenanceRunningData.get(i).getRunningId());
            maintenanceRunningDataHistory.setAssetId(maintenanceRunningData.get(i).getAssetId());
            maintenanceRunningDataHistory.setDate(maintenanceRunningData.get(i).getDate());
            maintenanceRunningDataHistory.setTransactionNo(maintenanceRunningData.get(i).getTransactionNo());
            maintenanceRunningDataHistory.setTime(maintenanceRunningData.get(i).getTime());
            maintenanceRunningDataHistory.setMetFroNo(maintenanceRunningData.get(i).getMetFroNo());
            maintenanceRunningDataHistory.setMetToNo(maintenanceRunningData.get(i).getMetToNo());
            maintenanceRunningDataHistory.setCost(maintenanceRunningData.get(i).getCost());
            maintenanceRunningDataHistory.setRemark(maintenanceRunningData.get(i).getRemark());

            maintenanceRunningDataHistory.setActionTime(maintenanceRunningData.get(i).getActionTime());
            maintenanceRunningDataHistory.setUpdateUserId(userId);
            maintenanceRunningDataHistory.setUpdateTime(date);

            maintenanceRunningDataHistoryRepository.save(maintenanceRunningDataHistory);
        }}}

