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
import com.msd.fixAssetRegister.model.AssetDisposalReason;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.DisposalReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class DisposalCategoryService {


    @Autowired
    AssetRepository assetRepository;

    @Autowired
    DisposalReasonRepository disposalReasonRepository;

    @Transactional
    public List<AssetDisposalReason> findAll(){
        return disposalReasonRepository.findAll();
    }

    @Transactional
    public AssetDisposalReason findById(int comId) {
        return disposalReasonRepository.findById(comId).get();
    }

    @Transactional
    public int checkDisposalCode(Date dat, int assetId) {
        int status = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dat);
//            cal.add(Calendar.DATE,1);
            Date date = cal.getTime();
            Asset asset = assetRepository.findById(assetId).get();
//            Date check=asset.getRegDate();
            if (date.compareTo(asset.getRegDate()) < 0) {
                status = 2;
            }
            if (asset.getAsTransfer() && asset.getTransfers().get(asset.getTransfers().size() -1).getTfDate().after(date)) {
                status = 3;
            }
            if (asset.getAsDamage()==true)
                if(date.compareTo(asset.getDamages().get(asset.getDamages().size() - 1).getDate()) < 0) {
                status = 4;
            }
            if (asset.getAssetImprovements().size() > 0 && asset.getAssetImprovements().get(asset.getAssetImprovements().size() - 1).getImpDate().after(date)) {
                status = 5;
            }

        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }
        return status;
    }

    public AssetDisposalReason saveUpdatedisposalReason(AssetDisposalReason assetDisposalReason) {
        return disposalReasonRepository.save(assetDisposalReason);
    }

    public void delete(int rsId) {
        disposalReasonRepository.deleteById(rsId);
    }

    @Transactional
    public List<Listing> getAssetDisposalReasonListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<AssetDisposalReason> assetDisposalReasons = disposalReasonRepository.findAll();
        for (AssetDisposalReason assetDisposalReason : assetDisposalReasons) {
            Listing listing = new Listing();
            listing.setListingId(assetDisposalReason.getReasonId());
            listing.setListingName(String.valueOf(assetDisposalReason.getReasonId()));
            listing.setDescription(assetDisposalReason.getReason());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }
}
