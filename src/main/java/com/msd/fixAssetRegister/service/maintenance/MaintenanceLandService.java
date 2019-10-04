package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceLand;
import com.msd.fixAssetRegister.model.MaintenanceLeaseAsset;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.MaintenanceLandServiceRepository;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class MaintenanceLandService {


    @Autowired
    MaintenanceLandServiceRepository maintenanceLandServiceRepository;

    @Autowired
    AssetRepository assetRepository;



    @Transactional
    public MaintenanceLand getLandDetailsByAsset(int assetId) {
        MaintenanceLand maintenanceLand = new MaintenanceLand();
        MaintenanceLand returnMaintenanceLand = new MaintenanceLand();
        Asset asset = new Asset();

        String assetPrice = "0.00";
        String assesmentValueString = "0.00";
        double amount = 0.00;
        double assesmentValueDouble = 0.00;

        maintenanceLand = maintenanceLandServiceRepository.getLandDetailsByAsset(assetId);
        if (maintenanceLand == null) {
            asset = assetRepository.findById(assetId).get();
            amount = asset.getUnitPrice();
            assetPrice = String.format("%1$,.2f", amount);
            returnMaintenanceLand.setAmount(assetPrice);;
        } else {
            amount = maintenanceLand.getDeedValue();
            assetPrice = String.format("%1$,.2f", amount);
            String check=maintenanceLand.getAssesmentValue();
            if (!check.equals("")) {
                assesmentValueDouble = Double.parseDouble(maintenanceLand.getAssesmentValue());
                assesmentValueString = String.format("%1$,.2f", assesmentValueDouble);
            }
            maintenanceLand.setAmount(assetPrice);
            maintenanceLand.setAssesmentValueDisplay(assesmentValueString);
            returnMaintenanceLand = maintenanceLand;
        }
        return returnMaintenanceLand;
    }


    public MaintenanceLand saveUpdateMaintenanceLand(MaintenanceLand maintenanceLand) {
        return maintenanceLandServiceRepository.save(maintenanceLand);
    }

    @Transactional
    public MaintenanceLand findById(int comId) {
        return maintenanceLandServiceRepository.findById(comId).get();
    }


    @Transactional
    public int checkDeedSignedDate(Date dat, int assetId) {
        int status = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dat);
            cal.add(Calendar.DATE,1);
            Date date = cal.getTime();
            Asset asset = assetRepository.findById(assetId).get();
            if (asset.getRegDate().after(date) || asset.getRegDate().after(date)) {
                status = 2;
            }


        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }
        return status;
    }


    @Transactional
    public int checkDeedRegisteredDate(Date dat, int assetId) {
        int status = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dat);
            cal.add(Calendar.DATE,1);
            Date date = cal.getTime();
            Asset asset = assetRepository.findById(assetId).get();
            if (asset.getRegDate().after(date) || asset.getRegDate().after(date)) {
                status = 2;
            }


        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }
        return status;
    }
}
