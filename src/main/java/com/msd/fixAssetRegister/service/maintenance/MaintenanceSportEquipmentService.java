package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceLabEquipment;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipment;
import com.msd.fixAssetRegister.repository.MaintenanceSportEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MaintenanceSportEquipmentService{

    @Autowired
    MaintenanceSportEquipmentRepository maintenanceSportEquipmentRepository;

    public MaintenanceSportEquipment saveUpdateMaintenanceSportEquipment(MaintenanceSportEquipment maintenanceSportEquipment) {
        return maintenanceSportEquipmentRepository.save(maintenanceSportEquipment);
    }

    @Transactional
    public MaintenanceSportEquipment getSportEquipmentDetails(int assetId) {
        return maintenanceSportEquipmentRepository.getSportEquipmentDetails(assetId);
    }

    @Transactional
    public MaintenanceSportEquipment findById(int comId) {
        return maintenanceSportEquipmentRepository.findById(comId).get();
    }

}
