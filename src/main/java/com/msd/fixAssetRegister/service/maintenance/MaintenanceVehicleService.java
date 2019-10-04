package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import com.msd.fixAssetRegister.repository.MaintenanceVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceVehicleService  {

    @Autowired
    MaintenanceVehicleRepository maintenanceVehicleRepository;

    @Transactional
    public MaintenanceVehicle getVehilceDetailsByAsset(int assetId) {
        return maintenanceVehicleRepository.getVehilceDetailsByAsset(assetId);
    }

    public MaintenanceVehicle saveUpdateMaintenanceVehicle(MaintenanceVehicle maintenanceVehicle) {
        return maintenanceVehicleRepository.save(maintenanceVehicle);
    }

    @Transactional
    public MaintenanceVehicle findById(int comId) {
        return maintenanceVehicleRepository.findById(comId).get();
    }
}
