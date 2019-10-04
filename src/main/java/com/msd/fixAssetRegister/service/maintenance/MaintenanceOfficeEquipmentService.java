package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.repository.MaintenanceOfficeEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceOfficeEquipmentService{


    @Autowired
    MaintenanceOfficeEquipmentRepository maintenanceOfficeEquipmentRepository;

    public MaintenanceOfficeEquipment saveUpdateOfficeEquipment(MaintenanceOfficeEquipment maintenanceOfficeEquipment) {
        return maintenanceOfficeEquipmentRepository.save(maintenanceOfficeEquipment);
    }

    @Transactional
    public MaintenanceOfficeEquipment getOfficeEquipmentsDetailsByAsset(int assetId) {
        return maintenanceOfficeEquipmentRepository.getOfficeEquipmentsDetailsByAsset(assetId);
    }

    @Transactional
    public MaintenanceOfficeEquipment findById(int comId) {
        return maintenanceOfficeEquipmentRepository.findById(comId).get();
    }
}
