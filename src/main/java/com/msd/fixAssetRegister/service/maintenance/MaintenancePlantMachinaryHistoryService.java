package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinaryHistory;
import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import com.msd.fixAssetRegister.repository.MaintenancePlantMachinaryHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenancePlantMachinaryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceVehicleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenancePlantMachinaryHistoryService {

    @Autowired
    MaintenancePlantMachinaryHistoryRepository maintenancePlantMachinaryHistoryRepository;

    @Transactional
    public void updateHistory(MaintenancePlantMachinary maintenancePlantMachinary, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenancePlantMachinaryHistory maintenancePlantMachinaryHistory = new MaintenancePlantMachinaryHistory();
        maintenancePlantMachinaryHistory.setPlantId(maintenancePlantMachinary.getPlantId());
        maintenancePlantMachinaryHistory.setAssetId(maintenancePlantMachinary.getAssetId());
        maintenancePlantMachinaryHistory.setTypeFuel(maintenancePlantMachinary.getTypeFuel());
        maintenancePlantMachinaryHistory.setMake(maintenancePlantMachinary.getMake());
        maintenancePlantMachinaryHistory.setCountryOrigin(maintenancePlantMachinary.getCountryOrigin());
        maintenancePlantMachinaryHistory.setModel(maintenancePlantMachinary.getModel());
        maintenancePlantMachinaryHistory.setYearManufacture(maintenancePlantMachinary.getYearManufacture());
        maintenancePlantMachinaryHistory.setWeidth(maintenancePlantMachinary.getWeidth());
        maintenancePlantMachinaryHistory.setLength(maintenancePlantMachinary.getLength());
        maintenancePlantMachinaryHistory.setWeight(maintenancePlantMachinary.getWeight());
        maintenancePlantMachinaryHistory.setHeight(maintenancePlantMachinary.getHeight());
        maintenancePlantMachinaryHistory.setIsWarrenty(maintenancePlantMachinary.getIsWarranty());
        maintenancePlantMachinaryHistory.setIsInsurance(maintenancePlantMachinary.getIsInsurance());
        maintenancePlantMachinaryHistory.setIsServiceAgre(maintenancePlantMachinary.getIsServiceAgre());
        maintenancePlantMachinaryHistory.setComments(maintenancePlantMachinary.getComments());
        maintenancePlantMachinaryHistory.setManufacture(maintenancePlantMachinary.getManufacture());
        maintenancePlantMachinaryHistory.setBayer(maintenancePlantMachinary.getBayer());
        maintenancePlantMachinaryHistory.setAddress(maintenancePlantMachinary.getAddress());
        maintenancePlantMachinaryHistory.setTelephone(maintenancePlantMachinary.getTelephone());
        maintenancePlantMachinaryHistory.setUserId(maintenancePlantMachinary.getUserId());
        maintenancePlantMachinaryHistory.setActionTime(maintenancePlantMachinary.getActionTime());
        maintenancePlantMachinaryHistory.setUpdateUser(userId);
        maintenancePlantMachinaryHistory.setUpdateTime(date);
        maintenancePlantMachinaryHistoryRepository.save(maintenancePlantMachinaryHistory);
    }

}
