package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceComputerHistory;
import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import com.msd.fixAssetRegister.model.MaintenanceVehicleHistory;
import com.msd.fixAssetRegister.repository.MaintenanceComputerHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceVehicleHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceVehicleHistoryService {

    @Autowired
    MaintenanceVehicleHistoryRepository maintenanceVehicleHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceVehicle maintenanceVehicle, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        MaintenanceVehicleHistory maintenanceVehicleHistory = new MaintenanceVehicleHistory();
        maintenanceVehicleHistory.setAssetId(maintenanceVehicle.getAssetId());
        maintenanceVehicleHistory.setRegiNo(maintenanceVehicle.getRegiNo());
        maintenanceVehicleHistory.setVehicleClass(maintenanceVehicle.getVehicleClass());
        maintenanceVehicleHistory.setTypeFuel(maintenanceVehicle.getTypeFuel());
        maintenanceVehicleHistory.setMake(maintenanceVehicle.getMake());
        maintenanceVehicleHistory.setCountryOrigin(maintenanceVehicle.getCountryOrigin());
        maintenanceVehicleHistory.setModel(maintenanceVehicle.getCountryOrigin());
        maintenanceVehicleHistory.setYearManufactute(maintenanceVehicle.getYearManufactute());
        maintenanceVehicleHistory.setColour(maintenanceVehicle.getColour());
        maintenanceVehicleHistory.setChassisNo(maintenanceVehicle.getChassisNo());
        maintenanceVehicleHistory.setEngineNo(maintenanceVehicle.getEngineNo());
        maintenanceVehicleHistory.setComments(maintenanceVehicle.getComments());
        maintenanceVehicleHistory.setCyCapacyty(maintenanceVehicle.getCyCapacyty());
        maintenanceVehicleHistory.setTaxClass(maintenanceVehicle.getTaxClass());
        maintenanceVehicleHistory.setRegiStatus(maintenanceVehicle.getRegiStatus());
        maintenanceVehicleHistory.setPreviOwnerNo(maintenanceVehicle.getPreviOwnerNo());
        maintenanceVehicleHistory.setSeatCapacity(maintenanceVehicle.getSeatCapacity());
        maintenanceVehicleHistory.setTyreSizeFront(maintenanceVehicle.getTyreSizeFront());
        maintenanceVehicleHistory.setTyreSizeRear(maintenanceVehicle.getTyreSizeRear());
        maintenanceVehicleHistory.setLength(maintenanceVehicle.getLength());
        maintenanceVehicleHistory.setWeight(maintenanceVehicle.getWeight());
        maintenanceVehicleHistory.setHeight(maintenanceVehicle.getHeight());
        maintenanceVehicleHistory.setWidth(maintenanceVehicle.getWidth());
        maintenanceVehicleHistory.setSellerName(maintenanceVehicle.getSellerName());
        maintenanceVehicleHistory.setSellerAddress(maintenanceVehicle.getSellerAddress());
        maintenanceVehicleHistory.setSellerTelephoneNo(maintenanceVehicle.getSellerTelephoneNo());
        maintenanceVehicleHistory.setRegProvincial(maintenanceVehicle.getRegProvincial());
        maintenanceVehicleHistory.setFirstRegistrationDate(maintenanceVehicle.getRegFirstDa());
        maintenanceVehicleHistory.setDaLicence(maintenanceVehicle.getDaLicence());
        maintenanceVehicleHistory.setServiceInterval(maintenanceVehicle.getServiceInterval());
        maintenanceVehicleHistory.setIsWarranty(maintenanceVehicle.getIsWarranty());
        maintenanceVehicleHistory.setIsInsurance(maintenanceVehicle.getIsInsurance());
        maintenanceVehicleHistory.setUpdateUser(userId);
        maintenanceVehicleHistory.setUpdateTime(date);
        maintenanceVehicleHistory.setUserId(maintenanceVehicle.getUserId());
        maintenanceVehicleHistory.setActionTime(maintenanceVehicle.getActionTime());
        maintenanceVehicleHistoryRepository.save(maintenanceVehicleHistory);

    }

}
