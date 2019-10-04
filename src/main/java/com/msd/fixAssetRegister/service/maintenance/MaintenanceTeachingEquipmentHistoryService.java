package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinaryHistory;
import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipment;
import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipmentHistory;
import com.msd.fixAssetRegister.repository.MaintenancePlantMachinaryHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceTeachingEquipmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceTeachingEquipmentHistoryService {

    @Autowired
    MaintenanceTeachingEquipmentHistoryRepository maintenanceTeachingEquipmentHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceTeachingEquipment maintenanceTeachingEquipment, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceTeachingEquipmentHistory maintenanceTeachingEquipmentHistory = new MaintenanceTeachingEquipmentHistory();
        maintenanceTeachingEquipmentHistory.setTeachingId(maintenanceTeachingEquipment.getTeachingId());
        maintenanceTeachingEquipmentHistory.setAssetId(maintenanceTeachingEquipment.getAssetId());
        maintenanceTeachingEquipmentHistory.setModel(maintenanceTeachingEquipment.getModel());
        maintenanceTeachingEquipmentHistory.setWidth(maintenanceTeachingEquipment.getWidth());
        maintenanceTeachingEquipmentHistory.setLength(maintenanceTeachingEquipment.getLength());
        maintenanceTeachingEquipmentHistory.setWeight(maintenanceTeachingEquipment.getWeight());
        maintenanceTeachingEquipmentHistory.setHeight(maintenanceTeachingEquipment.getHeight());
        maintenanceTeachingEquipmentHistory.setIsWarranty(maintenanceTeachingEquipment.getIsWarranty());
        maintenanceTeachingEquipmentHistory.setIsServiceAgre(maintenanceTeachingEquipment.getIsServiceAgre());
        maintenanceTeachingEquipmentHistory.setIsInsurance(maintenanceTeachingEquipment.getIsInsurance());
        maintenanceTeachingEquipmentHistory.setComments(maintenanceTeachingEquipment.getComments());
        maintenanceTeachingEquipmentHistory.setUserId(userId);
        maintenanceTeachingEquipmentHistory.setUpdateTime(date);
        maintenanceTeachingEquipmentHistoryRepository.save(maintenanceTeachingEquipmentHistory);

    }
}
