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


import com.msd.fixAssetRegister.model.AssetRevaluation;
import com.msd.fixAssetRegister.model.Currency;
import com.msd.fixAssetRegister.repository.AssetRevaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class RevaluationService {


    @Autowired
    AssetRevaluationRepository assetRevaluationRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public String saveRevaluation(AssetRevaluation verification) {
        String[] detailCodes = verification.getDetailCode().split(",");
        String[] detailCodeIds = verification.getDetailId().split(",");
        String[] physicalBalances = verification.getDetailBalance().split(",");
        String[] revalue = verification.getDetailrevalue().split(",");
        String[] currency = verification.getCurrencyId().split(",");
        String[] description = verification.getDescription().split(",");


        assetRevaluationRepository.removeTodayVerifications(verification.getDate(),verification.getLocationId());
        for (int i = 0; i <detailCodes.length; i++) {
            AssetRevaluation revaluation = new AssetRevaluation();
            revaluation.setLocationId(verification.getLocationId ());
            revaluation.setDetailCode(detailCodes[i]);
            revaluation.setDetailId(detailCodeIds[i]);
            revaluation.setDescription(description[i]);
            revaluation.setBalance(Double.parseDouble(physicalBalances[i]));
            revaluation.setRevalue(Double.parseDouble(revalue[i]));
            revaluation.setDate(verification.getDate());
            revaluation.setUserId(verification.getUserId());
            revaluation.setActionTime(verification.getActionTime());
            revaluation.setCurrencyId(currency[i]);
            assetRevaluationRepository.save(revaluation);
        }
        return "Update Successful.";
    }

    @Transactional
    public List<AssetRevaluation> loadVerification(Date parse, int locationId) {
        return assetRevaluationRepository.loadVerification(parse,locationId);
    }
}
