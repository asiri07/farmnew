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

import com.msd.fixAssetRegister.model.AssetCatergoryDetail;
import com.msd.fixAssetRegister.model.ReportPreparationDetailBalance;
import com.msd.fixAssetRegister.repository.DetailCategoryRepository;
import com.msd.fixAssetRegister.repository.PreparationDetailCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PreparationDetailCatService  {

    @Autowired
    PreparationDetailCatRepository preparationDetailCatRepository;

    @Autowired
    DetailCategoryRepository detailCategoryRepository;

    @Transactional
    public Boolean getDetailCatBalance() {
        Boolean isDo = false;
        preparationDetailCatRepository.clearPreparationDetailCats();
        List<AssetCatergoryDetail> assetCatergoryDetails = detailCategoryRepository.findAll();
        for (AssetCatergoryDetail detail : assetCatergoryDetails) {
            int count = preparationDetailCatRepository.getAssetBalance(detail.getDcatId());
            ReportPreparationDetailBalance detailBalance = new ReportPreparationDetailBalance();
            detailBalance.setDetailCodeId(detail.getDcatId());
            detailBalance.setBalance(count);
            preparationDetailCatRepository.save(detailBalance);
            isDo = true;
        }
        return isDo;
    }

}
