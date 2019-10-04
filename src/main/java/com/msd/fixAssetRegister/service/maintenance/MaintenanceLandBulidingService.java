package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.MaintenanceLand;
import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.MaintenanceLandBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceLandBulidingService{

    @Autowired
    MaintenanceLandBuildingRepository maintenanceLandBuildingRepository;

    @Autowired
    AssetRepository assetRepository;


    public MaintenanceLandBuliding saveUpdateMaintenaceLandBuilding(MaintenanceLandBuliding maintenanceLandBuliding) {
        return maintenanceLandBuildingRepository.save(maintenanceLandBuliding);
    }

    @Transactional
    public MaintenanceLandBuliding getLandBuildingDetailsByAsset(int assetId) {
        MaintenanceLandBuliding maintenanceLandBuliding = new MaintenanceLandBuliding();
        MaintenanceLandBuliding returnMaintenanceLandBuilding = new MaintenanceLandBuliding();
        Asset asset = new Asset();

        String assetPrice = "0.00";
        String assesmentValueString = "0.00";
        double amount = 0.00;
        double assesmentValueDouble = 0.00;

        maintenanceLandBuliding = maintenanceLandBuildingRepository.getLandBuildingDetailsByAsset(assetId);
        if (maintenanceLandBuliding == null) {
            MaintenanceLandBuliding maintenanceLandBuliding1 = new MaintenanceLandBuliding();
            asset = assetRepository.findById(assetId).get();
            amount = asset.getUnitPrice();
            assetPrice = String.format("%1$,.2f", amount);
            maintenanceLandBuliding1.setAmount(assetPrice);
            maintenanceLandBuliding1.setAssesmentValueDisplay(assesmentValueString);
            returnMaintenanceLandBuilding = maintenanceLandBuliding1;
        } else {
            amount = maintenanceLandBuliding.getDeedValue();
            assetPrice = String.format("%1$,.2f", amount);
            String check=maintenanceLandBuliding.getAssessementValue();
            if (!check.equals("")) {
                assesmentValueDouble = Double.parseDouble(check);
                assesmentValueString = String.format("%1$,.2f", assesmentValueDouble);
            }else{
                assesmentValueString = "0.00";
            }
            maintenanceLandBuliding.setAmount(assetPrice);
            maintenanceLandBuliding.setAssesmentValueDisplay(assesmentValueString);
            returnMaintenanceLandBuilding = maintenanceLandBuliding;
        }

        return returnMaintenanceLandBuilding;
    }

    @Transactional
    public MaintenanceLandBuliding findById(int comId) {
        return maintenanceLandBuildingRepository.findById(comId).get();
    }

}
