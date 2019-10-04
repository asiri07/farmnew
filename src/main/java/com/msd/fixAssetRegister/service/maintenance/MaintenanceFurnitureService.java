package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.repository.MaintenanceFurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceFurnitureService {


    @Autowired
    MaintenanceFurnitureRepository maintenanceFurnitureRepository;

    public MaintenanceFurniture updateFurniture(MaintenanceFurniture maintenanceFurniture) {
        return maintenanceFurnitureRepository.save(maintenanceFurniture);
    }

//
//    public String saveUpdateFurniture(MaintenanceFurniture maintenanceFurniture) {
//        int asIdFrom = maintenanceFurniture.getAssetIdFrom();
//        int asIdTo = maintenanceFurniture.getAssetIdTo();
//        if(asIdTo==0){
//            asIdTo=asIdFrom;
//        }
//        int i;
//        for (i = asIdFrom; i <= asIdTo; i++) {
//
//            MaintenanceFurniture maintenanceFurniture1 = new MaintenanceFurniture();
//
//            maintenanceFurniture1.setAssetId(i);
//            maintenanceFurniture1.setIsInsurnce(maintenanceFurniture.getIsInsurnce());
//            maintenanceFurniture1.setIsWarranty(maintenanceFurniture.getIsWarranty());
//            maintenanceFurniture1.setUserId(maintenanceFurniture.getUserId());
//            maintenanceFurniture1.setActionTime(maintenanceFurniture.getActionTime());
//            maintenanceFurnitureRepository.save(maintenanceFurniture1);
//        }
//        return "Update Successful.";
//    }
    @Transactional
    public MaintenanceFurniture getFurnitureDetailsByAsset(int assetId) {
        return maintenanceFurnitureRepository.getFurnitureDetailsByAsset(assetId);
    }

    @Transactional
    public void deleteEntry(int assetId) {
        maintenanceFurnitureRepository.deleteEntry(assetId);
    }

    @Transactional
    public Integer getMaxFurnitureId() {
        int rowCount = maintenanceFurnitureRepository.getCount();
        int maxFurnitureId;
        if (rowCount == 0) {
            maxFurnitureId = 0;
        } else {

            maxFurnitureId = maintenanceFurnitureRepository.getMaxFurnitureId();
        }
        return maxFurnitureId;
    }

    @Transactional
    public MaintenanceFurniture findById(int comId) {
        return maintenanceFurnitureRepository.findById(comId).get();
    }
}
