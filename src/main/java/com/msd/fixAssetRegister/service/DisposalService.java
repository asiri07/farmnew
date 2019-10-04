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
import com.msd.fixAssetRegister.model.Disposal;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.DisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisposalService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    DisposalRepository disposalRepository;

    @Transactional
    public String addAssetDisposal(Disposal disposal) {
        try {
            disposalRepository.save(disposal);
            if (disposal.getDpId() > 0) {
                Asset asset = assetRepository.getAllAsset(disposal.getAsset().getAsId());
                if(asset.getAsDisposed()==false){
                asset.setAsDisposed(true);
                //assetDao.saveOrUpdate(asset);
                assetRepository.save(asset);}

            } else {
                throw new RuntimeException("Asset Update UnSuccessfully.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Update UnSuccessfully.");
        }
        return "Update Successful.";
    }

    @Transactional
    public List<Disposal> findAll() {
        return disposalRepository.findAll();
    }

    @Transactional
    public List<Disposal> getTransactionNoList(int branch) {
        if (branch == 0) {
            return disposalRepository.getTransactionNoList();
        } else {
            return disposalRepository.getTransactionNoList(branch);
        }
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount = disposalRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = disposalRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }


    public int getNoOfDisposed(int branchId) {
        if(branchId==0) {
            return disposalRepository.getNoOfDisposed();
        }else{
            return disposalRepository.getNoOfDisposed(branchId);
            }
    }

    public List<Disposal> loadDisposalByTRNo(int transactionNo) {
        return disposalRepository.loadDisposalByTRNo(transactionNo);

    }
}
