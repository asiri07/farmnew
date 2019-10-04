package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceLabEquipment;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import com.msd.fixAssetRegister.repository.MaintenanceLabEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MaintenanceLabEquipmentsService  {


    @Autowired
    MaintenanceLabEquipmentRepository maintenanceLabEquipmentRepository;

    public MaintenanceLabEquipment saveUpdateLabEquipment(MaintenanceLabEquipment maintenanceLabEquipment) {
        return maintenanceLabEquipmentRepository.save(maintenanceLabEquipment);
    }

    @Transactional
    public MaintenanceLabEquipment getLabEquipmentDetails(int assetId) {
        return maintenanceLabEquipmentRepository.getLabEquipmentDetails(assetId);
    }

    @Transactional
    public MaintenanceLabEquipment findById(int comId) {
        return maintenanceLabEquipmentRepository.findById(comId).get();
    }
}
