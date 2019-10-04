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

import com.msd.fixAssetRegister.model.AssetType;
import com.msd.fixAssetRegister.model.AssetTypeAssing;
import com.msd.fixAssetRegister.model.City;
import com.msd.fixAssetRegister.repository.AssetTypeAssingRepository;
import com.msd.fixAssetRegister.repository.AssetTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssetsTypesService {

    @Autowired
    AssetTypesRepository assetTypesRepository;

    @Autowired
    AssetTypeAssingRepository assetTypeAssingRepository;

    @Transactional
    public List<AssetType> loadAssetTypes() {
        return assetTypesRepository.findAll();//getAssetTypes();
    }

    public AssetType getNoOfAssectByType(int id){
        return assetTypesRepository.getOne(id);
    }

    public int getNoOfOtherAssect(int a,int b,int c, int d,int e,int f){
        int count = assetTypesRepository.getNoOfOtherAsset(a,b,c,d,e,f);
        return count;
    }


    @Transactional
    public void assetUpdate(int noUnit, String mainCode) {

        int assetTypeId=assetTypesRepository.getAssetTypeId(mainCode);
        assetTypesRepository.assetUpdate(noUnit,assetTypeId);
    }

    @Transactional
    public AssetTypeAssing findBymcatCodeAssetTypeAssing(String mcatCode){
        return assetTypeAssingRepository.findmcatCode(mcatCode);
    }

}
