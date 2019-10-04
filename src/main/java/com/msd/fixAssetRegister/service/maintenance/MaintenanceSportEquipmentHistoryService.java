package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooksHistory;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipment;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipmentHistory;
import com.msd.fixAssetRegister.repository.MaintenanceLibraryBooksHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceSportEquipmentHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceSportEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceSportEquipmentHistoryService {


    @Autowired
    MaintenanceSportEquipmentHistoryRepository maintenanceSportEquipmentHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceSportEquipment maintenanceSportEquipment, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceSportEquipmentHistory maintenanceSportEquipmentHistory = new MaintenanceSportEquipmentHistory();
        maintenanceSportEquipmentHistory.setSportId(maintenanceSportEquipment.getSportId());
        maintenanceSportEquipmentHistory.setAssetId(maintenanceSportEquipment.getAssetId());
        maintenanceSportEquipmentHistory.setTypeFuel(maintenanceSportEquipment.getTypeFuel());
        maintenanceSportEquipmentHistory.setMake(maintenanceSportEquipment.getMake());
        maintenanceSportEquipmentHistory.setCountryOrigin(maintenanceSportEquipment.getCountryOrigin());
        maintenanceSportEquipmentHistory.setModel(maintenanceSportEquipment.getModel());
        maintenanceSportEquipmentHistory.setYearManufacture(maintenanceSportEquipment.getYearManufacture());
        maintenanceSportEquipmentHistory.setWidth(maintenanceSportEquipment.getWidth());
        maintenanceSportEquipmentHistory.setLength(maintenanceSportEquipment.getLength());
        maintenanceSportEquipmentHistory.setWeight(maintenanceSportEquipment.getWeight());
        maintenanceSportEquipmentHistory.setHeight(maintenanceSportEquipment.getHeight());
        maintenanceSportEquipmentHistory.setIsWarranty(maintenanceSportEquipment.getIsWarranty());
        maintenanceSportEquipmentHistory.setIsInsurance(maintenanceSportEquipment.getIsInsurance());
        maintenanceSportEquipmentHistory.setIsServiceAgre(maintenanceSportEquipment.getIsServiceAgre());
        maintenanceSportEquipmentHistory.setComments(maintenanceSportEquipment.getComments());
        maintenanceSportEquipmentHistory.setUserId(maintenanceSportEquipment.getUserId());
        maintenanceSportEquipmentHistory.setActionTime(maintenanceSportEquipment.getActionTime());
        maintenanceSportEquipmentHistory.setUpdatedUser(userId);
        maintenanceSportEquipmentHistory.setUpdatedTime(date);
        maintenanceSportEquipmentHistoryRepository.save(maintenanceSportEquipmentHistory);

    }

}
