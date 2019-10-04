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
import com.msd.fixAssetRegister.model.AssetImprovement;
import com.msd.fixAssetRegister.model.Damage;
import com.msd.fixAssetRegister.model.Disposal;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.ImprovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class ImprovementService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    ImprovementRepository improvementRepository;

    @Transactional
    public int checkDisposalCode(Date parse, int assetId) {
        int status = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.DATE, 1);
            Date date = cal.getTime();
            Asset asset = assetRepository.findById(assetId).get();
            if (asset.getRegDate().after(date) || asset.getRegDate().after(date)) {
                status = 2;
            }
            if (asset.getAsTransfer() && asset.getTransfers().get(asset.getTransfers().size() - 1).getTfDate().after(date)) {
                status = 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }
        return status;
    }

    public AssetImprovement saveUpdateAssetImpro(AssetImprovement assetImprovement) {
        return improvementRepository.save(assetImprovement);
    }

    @Transactional
    public List<AssetImprovement> findAll() {
        return improvementRepository.findAll();
    }

    @Transactional
    public AssetImprovement findById(int impId) {
        return improvementRepository.findById(impId).get();
    }

    @Transactional
    public void delete(int impId) {
        improvementRepository.deleteById(impId);
    }


    @Transactional
    public int generateTransactionNo() {
        int rowCount = improvementRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = improvementRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }


    @Transactional
    public List<AssetImprovement> getTransactionNoList(int assetId, int branch) {
        if (branch == 0) {
            return improvementRepository.getTransactionNoList(assetId);
        } else {
            return improvementRepository.getTransactionNoList(assetId, branch);
        }
    }


    @Transactional
    public List<AssetImprovement> getImprovementDetails(int improvementId) {
        return improvementRepository.getImprovementDetails(improvementId);

    }


}
