package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipmentHistory;
import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import com.msd.fixAssetRegister.model.MaintenanceSoftwareHistory;
import com.msd.fixAssetRegister.repository.MaintenanceOfficeEquipmentHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceSoftwareHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceSoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceSoftwareHistoryService {

    @Autowired
    MaintenanceSoftwareHistoryRepository maintenanceSoftwareHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceSoftware maintenanceSoftware, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceSoftwareHistory maintenanceSoftwareHistory = new MaintenanceSoftwareHistory();
        maintenanceSoftwareHistory.setSoftwareId(maintenanceSoftware.getSoftwareId());
        maintenanceSoftwareHistory.setAssetId(maintenanceSoftware.getAssetId());
        maintenanceSoftwareHistory.setDescription(maintenanceSoftware.getDescription());
        maintenanceSoftwareHistory.setSellerDealer(maintenanceSoftware.getSellerDealer());
        maintenanceSoftwareHistory.setSellerAdd(maintenanceSoftware.getSellerAdd());
        maintenanceSoftwareHistory.setSoftOwnerName(maintenanceSoftware.getSoftOwnerName());
        maintenanceSoftwareHistory.setProductKey(maintenanceSoftware.getProductKey());
        maintenanceSoftwareHistory.setNoUsers(maintenanceSoftware.getNoUsers());
        maintenanceSoftwareHistory.setIsServiceAgre(maintenanceSoftware.getIsServiceAgre());
        maintenanceSoftwareHistory.setComments(maintenanceSoftware.getComments());
        maintenanceSoftwareHistory.setUserId(maintenanceSoftware.getUserId());
        maintenanceSoftwareHistory.setActionTime(maintenanceSoftware.getActionTime());
        maintenanceSoftwareHistory.setUpdatedUser(userId);
        maintenanceSoftwareHistory.setUpdatedTime(date);
        maintenanceSoftwareHistoryRepository.save(maintenanceSoftwareHistory);

    }

    }
