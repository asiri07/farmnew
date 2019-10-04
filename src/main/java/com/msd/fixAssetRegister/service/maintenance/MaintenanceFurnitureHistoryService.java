package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceFurnitureHistory;
import com.msd.fixAssetRegister.repository.MaintenanceFurnitureHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceFurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceFurnitureHistoryService {

    @Autowired
    MaintenanceFurnitureHistoryRepository maintenanceFurnitureHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceFurniture maintenanceFurniture, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceFurnitureHistory maintenanceFurnitureHistory= new MaintenanceFurnitureHistory();
        maintenanceFurnitureHistory.setFurnitureId(maintenanceFurniture.getFurnitureId());
        maintenanceFurnitureHistory.setAssetId(maintenanceFurniture.getAssetId());
        maintenanceFurnitureHistory.setIsWarranty(maintenanceFurniture.getIsWarranty());
        maintenanceFurnitureHistory.setIsInsurnce(maintenanceFurniture.getIsInsurnce());
        maintenanceFurnitureHistory.setComments(maintenanceFurniture.getComments());
        maintenanceFurnitureHistory.setUserId(maintenanceFurnitureHistory.getUserId());
        maintenanceFurnitureHistory.setActionTime(maintenanceFurnitureHistory.getActionTime());
        maintenanceFurnitureHistory.setUpdateUser(userId);
        maintenanceFurnitureHistory.setUpdateTime(date);
        maintenanceFurnitureHistoryRepository.save(maintenanceFurnitureHistory);

    }

}
