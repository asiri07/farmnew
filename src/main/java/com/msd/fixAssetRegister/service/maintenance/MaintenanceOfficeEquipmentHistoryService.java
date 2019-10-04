package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipmentHistory;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinaryHistory;
import com.msd.fixAssetRegister.repository.MaintenanceOfficeEquipmentHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceOfficeEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceOfficeEquipmentHistoryService {

    @Autowired
    MaintenanceOfficeEquipmentHistoryRepository maintenanceOfficeEquipmentHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceOfficeEquipment maintenanceOfficeEquipment, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceOfficeEquipmentHistory maintenanceOfficeEquipmentHistory = new MaintenanceOfficeEquipmentHistory();
        maintenanceOfficeEquipmentHistory.setOfficeEquipmentsId(maintenanceOfficeEquipment.getOfficeEquipmentsId());
        maintenanceOfficeEquipmentHistory.setAssetId(maintenanceOfficeEquipment.getAssetId());
        maintenanceOfficeEquipmentHistory.setTypeFuel(maintenanceOfficeEquipment.getTypeFuel());
        maintenanceOfficeEquipmentHistory.setMake(maintenanceOfficeEquipment.getMake());
        maintenanceOfficeEquipmentHistory.setCountryOrigin(maintenanceOfficeEquipment.getCountryOrigin());
        maintenanceOfficeEquipmentHistory.setModel(maintenanceOfficeEquipment.getModel());
        maintenanceOfficeEquipmentHistory.setYearManufacture(maintenanceOfficeEquipment.getYearManufacture());
        maintenanceOfficeEquipmentHistory.setWidth(maintenanceOfficeEquipment.getWidth());
        maintenanceOfficeEquipmentHistory.setLenght(maintenanceOfficeEquipment.getLenght());
        maintenanceOfficeEquipmentHistory.setWeight(maintenanceOfficeEquipment.getWeight());
        maintenanceOfficeEquipmentHistory.setHeight(maintenanceOfficeEquipment.getHeight());
        maintenanceOfficeEquipmentHistory.setColour(maintenanceOfficeEquipment.getColour());
        maintenanceOfficeEquipmentHistory.setIsWarranty(maintenanceOfficeEquipment.getIsWarranty());
        maintenanceOfficeEquipmentHistory.setIsInsurance(maintenanceOfficeEquipment.getIsInsurance());
        maintenanceOfficeEquipmentHistory.setIsServiceAgre(maintenanceOfficeEquipment.getIsServiceAgre());
        maintenanceOfficeEquipmentHistory.setComments(maintenanceOfficeEquipment.getComments());
        maintenanceOfficeEquipmentHistory.setUserId(maintenanceOfficeEquipment.getUserId());
        maintenanceOfficeEquipmentHistory.setActionTime(maintenanceOfficeEquipment.getActionTime());
        maintenanceOfficeEquipmentHistory.setUpdateTime(date);
        maintenanceOfficeEquipmentHistory.setUpdateUser(userId);
        maintenanceOfficeEquipmentHistoryRepository.save(maintenanceOfficeEquipmentHistory);



    }


}
