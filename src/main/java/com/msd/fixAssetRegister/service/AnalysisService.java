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

import com.msd.fixAssetRegister.model.AnalysisMaster;
import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.AssetDisposalReason;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.AnalysisRepository;
import com.msd.fixAssetRegister.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisService  {




    @Autowired
    AssetRepository assetRepository;

    @Autowired
    AnalysisRepository  analysisRepository;



    @Transactional
    public List<AnalysisMaster> findAll(){
        return analysisRepository.findAll();
    }

    @Transactional
    public AnalysisMaster findById(int comId) {
        return analysisRepository.findById(comId).get();
    }

    @Transactional
    public AnalysisMaster getAnalysisMaster(String anaCode) {
        return analysisRepository.getAnalysisMaster(anaCode);
    }

    @Transactional
    public int deleteAnalysisCode(int id) {
        int isDelete = 0;
        AnalysisMaster analysisMaster = analysisRepository.getOne(id);
        if (analysisMaster != null) {
            List<Asset> asset = assetRepository.getAsset(analysisMaster.getAnaCode());
            if (asset.size() < 1) {
                analysisRepository.delete(analysisMaster);
                isDelete = 1;
            }
        } else {
            isDelete = 2;
        }
        return isDelete;
    }

    public AnalysisMaster saveUpdateAnalysis(AnalysisMaster analysisMaster) {
        return analysisRepository.save(analysisMaster);
    }

    @Transactional
    public int delete(int analysisId) {
        analysisRepository.deleteById(analysisId);
        return 1;
    }

    @Transactional
    public List<Listing> getAssetDisposalReasonListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<AnalysisMaster> analysisMasters = analysisRepository.findAll();
        for (AnalysisMaster analysisMaster : analysisMasters) {
            Listing listing = new Listing();
            listing.setListingId(analysisMaster.getAnaCodeId());
            listing.setListingName(analysisMaster.getAnaCode());
            listing.setDescription(analysisMaster.getDescription());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getAnalysisCodeById(String fromCode) {
        String code = "";
        AnalysisMaster analysisMaster = analysisRepository.findById(Integer.parseInt(fromCode)).get();
        if (analysisMaster != null) {
            code = analysisMaster.getAnaCode();
        }
        return code;
    }

}
