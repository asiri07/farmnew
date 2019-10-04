/*
 *
 *       Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *       *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *         This software product contains information which is proprietary to
 *          and considered a trade secret MsSoftIT Solution .
 *          It is expressly agreed that it shall not be reproduced in whole or part,
 *          disclosed, divulged or otherwise made available to any third party directly
 *          or indirectly.  Reproduction of this product for any purpose is prohibited
 *          without written authorisation from the The MsSoftIT Solution
 *          All Rights Reserved.
 *
 *          E-Mail mssoftit@gmail.com
 *          URL : mssoftit.lk
 *          Created By : Mahendra Sri Dayarathna
 *
 */
package com.msd.fixAssetRegister.service;


import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.Damage;
import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DamageService {

    @Autowired
    DamageRepository damageRepository;

    @Autowired
    AssetRepository assetRepository;

    @Transactional
    public String addAssetDamage(Damage damage) {

        try {
            damageRepository.save(damage);
            if(damage.getDmgAsId() > 0) {
                Asset asset = assetRepository.getAsset(damage.getAsset().getAsId());
                asset.setAsDamage(true);
                assetRepository.save(asset);
            }else {
                throw new RuntimeException("Asset Update UnSuccessfully.");
            }



        }catch (Exception e) {
            throw new RuntimeException("Update UnSuccessfully.");
        }
        return "Update Successful.";
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount = damageRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = damageRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }


    @Transactional
    public List<Damage> getTransactionNoList(int assetId, int  branch) {
        if (branch == 0) {
            return damageRepository.getTransactionNoList(assetId);
        } else {
            return damageRepository.getTransactionNoList(assetId,branch);
        }

    }

    @Transactional
    public List<Damage> getDamageDetails(int damageId) {
        return damageRepository.getDamageDetails(damageId);
    }

    public int getNoOfDamages(int brancId){
        if (brancId == 0) {
            return damageRepository.getNoOfDamages();
        }else{
            return damageRepository.getNoOfDamages(brancId);
        }
    }

}
