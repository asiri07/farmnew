package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.City;
import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.repository.MaintenanceComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaintenanceComputerService  {
    @Autowired
    MaintenanceComputerRepository maintenanceComputerRepository;



    public MaintenanceComputer saveUpdateMaintenanceComputer(MaintenanceComputer maintenanceComputer) {
        return maintenanceComputerRepository.save(maintenanceComputer);
    }

    @Transactional
    public MaintenanceComputer getComputerDetailsByAsset(int assetId) {
        return maintenanceComputerRepository.getComputerDetailsByAsset(assetId);
    }

    @Transactional
    public List<MaintenanceComputer> findAll(){
        return maintenanceComputerRepository.findAll();
    }

    @Transactional
    public MaintenanceComputer findById(int comId) {
        return maintenanceComputerRepository.findById(comId).get();
    }

}
