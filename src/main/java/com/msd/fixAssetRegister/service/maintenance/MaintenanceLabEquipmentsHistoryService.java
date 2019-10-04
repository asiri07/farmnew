package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceLabEquipment;
import com.msd.fixAssetRegister.model.MaintenanceLabEquipmentHistory;
import com.msd.fixAssetRegister.repository.MaintenanceLabEquipmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceLabEquipmentsHistoryService {

    @Autowired
    MaintenanceLabEquipmentHistoryRepository maintenanceLabEquipmentHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceLabEquipment maintenanceLabEquipment, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceLabEquipmentHistory maintenanceLabEquipmentHistory = new MaintenanceLabEquipmentHistory();
        maintenanceLabEquipmentHistory.setEquipmentId(maintenanceLabEquipment.getEquipmentId());
        maintenanceLabEquipmentHistory.setAssetId(maintenanceLabEquipment.getAssetId());
        maintenanceLabEquipmentHistory.setTypeFuel(maintenanceLabEquipment.getTypeFuel());
        maintenanceLabEquipmentHistory.setMake(maintenanceLabEquipment.getMake());
        maintenanceLabEquipmentHistory.setCountryOrigin(maintenanceLabEquipment.getCountryOrigin());
        maintenanceLabEquipmentHistory.setModel(maintenanceLabEquipment.getModel());
        maintenanceLabEquipmentHistory.setYearManufacture(maintenanceLabEquipment.getYearManufacture());
        maintenanceLabEquipmentHistory.setWidth(maintenanceLabEquipment.getWidth());
        maintenanceLabEquipmentHistory.setLenght(maintenanceLabEquipment.getLength());
        maintenanceLabEquipmentHistory.setWeight(maintenanceLabEquipment.getWeight());
        maintenanceLabEquipmentHistory.setHeight(maintenanceLabEquipment.getHeight());
        maintenanceLabEquipmentHistory.setIsWarranty(maintenanceLabEquipment.getIsWarranty());
        maintenanceLabEquipmentHistory.setIsInsurance(maintenanceLabEquipment.getIsInsurance());
        maintenanceLabEquipmentHistory.setIsServiceAgre(maintenanceLabEquipment.getIsServiceAgre());
        maintenanceLabEquipmentHistory.setComments(maintenanceLabEquipment.getComments());
        maintenanceLabEquipmentHistory.setUserId(maintenanceLabEquipment.getUserId());
        maintenanceLabEquipmentHistory.setActionTime(maintenanceLabEquipment.getActionTime());
        maintenanceLabEquipmentHistory.setUpdateTime(date);
        maintenanceLabEquipmentHistory.setUpdateUser(userId);
        maintenanceLabEquipmentHistoryRepository.save(maintenanceLabEquipmentHistory);

    }
}
