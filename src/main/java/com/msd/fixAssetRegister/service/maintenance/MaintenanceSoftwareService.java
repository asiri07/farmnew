package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import com.msd.fixAssetRegister.repository.MaintenanceSoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceSoftwareService  {

    @Autowired
    MaintenanceSoftwareRepository maintenanceSoftwareRepository;

    public MaintenanceSoftware saveUpdateMaintenanceSoftware(MaintenanceSoftware maintenanceServiceAgreements) {
        return maintenanceSoftwareRepository.save(maintenanceServiceAgreements);
    }

    @Transactional
    public MaintenanceSoftware getSoftwareDetailsByAsset(int assetId) {
        return maintenanceSoftwareRepository.getSoftwareDetailsByAsset(assetId);
    }

    @Transactional
    public MaintenanceSoftware findById(int comId) {
        return maintenanceSoftwareRepository.findById(comId).get();
    }
}
