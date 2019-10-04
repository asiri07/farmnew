package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import com.msd.fixAssetRegister.repository.MaintenancePlantMachinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenancePlantMachinaryService  {

    @Autowired
    MaintenancePlantMachinaryRepository maintenancePlantMachinaryRepository;

    public MaintenancePlantMachinary saveUpdateMaintenancePlantMachinary(MaintenancePlantMachinary maintenancePlantMachinary) {
        return maintenancePlantMachinaryRepository.save(maintenancePlantMachinary);
    }

    @Transactional
    public MaintenancePlantMachinary getPlantMachineryDetailsByAsset(int assetId) {
        return maintenancePlantMachinaryRepository.getPlantMachineryDetailsByAsset(assetId);
    }

    @Transactional
    public MaintenancePlantMachinary findById(int comId) {
        return maintenancePlantMachinaryRepository.findById(comId).get();
    }

}
