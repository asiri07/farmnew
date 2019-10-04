package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.Building;
import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceComputerHistory;
import com.msd.fixAssetRegister.repository.MaintenanceComputerHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceComputerHistoryService {

    @Autowired
    MaintenanceComputerHistoryRepository maintenanceComputerHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceComputer maintenanceComputer, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceComputerHistory maintenanceComputerHistory = new MaintenanceComputerHistory();
        maintenanceComputerHistory.setComputerId(maintenanceComputer.getComputerId());
        maintenanceComputerHistory.setAssetId(maintenanceComputer.getAssetId());
        maintenanceComputerHistory.setBrand(maintenanceComputer.getBrand());
        maintenanceComputerHistory.setComputerType(maintenanceComputer.getComputerType());
        maintenanceComputerHistory.setProcessor(maintenanceComputer.getProcessor());
        maintenanceComputerHistory.setRamSize(maintenanceComputer.getRamSize());
        maintenanceComputerHistory.setHardDiskCapacity(maintenanceComputer.getHardDiskCapacity());
        maintenanceComputerHistory.setMonitor(maintenanceComputer.getMonitor());
        maintenanceComputerHistory.setSystemType(maintenanceComputer.getSystemType());
        maintenanceComputerHistory.setOperatingSystem(maintenanceComputer.getOperatingSystem());
        maintenanceComputerHistory.setIsWarranty(maintenanceComputer.getIsWarranty());
        maintenanceComputerHistory.setIsInsurance(maintenanceComputer.getIsInsurance());
        maintenanceComputerHistory.setComments(maintenanceComputer.getComments());
        maintenanceComputerHistory.setDeviceName(maintenanceComputer.getDeviceName());
        maintenanceComputerHistory.setComputerColor(maintenanceComputer.getComputerColor());
        maintenanceComputerHistory.setDeviceId(maintenanceComputer.getDeviceId());
        maintenanceComputerHistory.setProductId(maintenanceComputer.getProductId());
        maintenanceComputerHistory.setBattery(maintenanceComputer.getBattery());
        maintenanceComputerHistory.setLength(maintenanceComputer.getLength());
        maintenanceComputerHistory.setWidth(maintenanceComputer.getWidth());
        maintenanceComputerHistory.setHeight(maintenanceComputer.getHeight());
        maintenanceComputerHistory.setWeight(maintenanceComputer.getWeight());
        maintenanceComputerHistory.setBuyer(maintenanceComputer.getBuyer());
        maintenanceComputerHistory.setBuyerAddress(maintenanceComputer.getBuyerAddress());
        maintenanceComputerHistory.setBuyerTel(maintenanceComputer.getBuyerTel());
        maintenanceComputerHistory.setAdapter(maintenanceComputer.getAdapter());
        maintenanceComputerHistory.setUserId(maintenanceComputer.getUserId());
        maintenanceComputerHistory.setActionTime(maintenanceComputer.getActionTime());
        maintenanceComputerHistory.setStatus(status);
        maintenanceComputerHistory.setUpDateUser(userId);
        maintenanceComputerHistory.setUpdateTime(date);
        maintenanceComputerHistoryRepository.save(maintenanceComputerHistory);
    }

}
