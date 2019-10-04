package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipment;
import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import com.msd.fixAssetRegister.repository.MaintenanceTeachingEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceTeachingEquipmentService  {


    @Autowired
    MaintenanceTeachingEquipmentRepository maintenanceTeachingEquipmentRepository;

    public MaintenanceTeachingEquipment saveUpdateTeachingEquipment(MaintenanceTeachingEquipment maintenanceTeachingEquipment) {
        return maintenanceTeachingEquipmentRepository.save(maintenanceTeachingEquipment);
    }
    @Transactional
    public MaintenanceTeachingEquipment getTeachingEquipmentsDetailsByAsset(int assetId) {
        return maintenanceTeachingEquipmentRepository.getTeachingEquipmentsDetailsByAsset(assetId);
    }

    @Transactional
    public MaintenanceTeachingEquipment findById(int comId) {
        return maintenanceTeachingEquipmentRepository.findById(comId).get();
    }

}
