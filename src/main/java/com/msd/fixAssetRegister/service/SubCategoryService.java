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

import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.MainCategoryRepository;
import com.msd.fixAssetRegister.repository.SubCatergoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class SubCategoryService {

    @Autowired
    SubCatergoryRepository subCatergoryRepository;

    @Autowired
    MainCategoryRepository mainCategoryRepository;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    @Transactional
    public List<AssetCatergorySub> findAll(){
        return subCatergoryRepository.findAll();
    }

    @Transactional
    public AssetCatergorySub findById(int comId) {
        return subCatergoryRepository.findById(comId).get();
    }

    @Transactional
    public Boolean saveSubCatergory(AssetCatergorySub assetCatergorySub) {
        Boolean isSave;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            assetCatergorySub.setActionTime(date);
            AssetCatergoryMain assetCatergoryMain = mainCategoryRepository.findById(assetCatergorySub.getAssetCatergoryMain().getMcatId()).get();
            assetCatergorySub.setAssetCatergoryMain(assetCatergoryMain);
            subCatergoryRepository.save(assetCatergorySub);
            isSave = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }
        return isSave;
    }

    @Transactional
    public List<AssetCatergorySub> getSubCategoryListByMainCat(int mainCat) {
        return mainCategoryRepository.findById(mainCat).get().getAssetCatergorySubs();
    }

    @Transactional
    public AssetCatergorySub getSubCatByCode(String subCode, int mainCat) {
        return subCatergoryRepository.getSubCatByCode(subCode, mainCat);
    }


    @Transactional
    public List<AssetCatergorySub> getSubCatsByMainCodeId(int mainCat) {
        return mainCategoryRepository.findById(mainCat).get().getAssetCatergorySubs();
    }

    @Transactional
    public int deleteSubCategory(int subCat) {
        int isDelete = 0;
        subCatergoryRepository.deleteById(subCat);
        return isDelete;
    }

    @Transactional
    public List<Listing> getSubCatListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<AssetCatergorySub> subList = subCatergoryRepository.findAll();
        for (AssetCatergorySub catergorySub : subList) {
            Listing listing = new Listing();
            listing.setListingId(catergorySub.getScatId());
            listing.setListingName(catergorySub.getScatCode());
            listing.setDescription(catergorySub.getScatDes());
            listing.setListingType(2);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getSubCatCode(String fromCode) {
        String code = "";
        AssetCatergorySub catergorySub = subCatergoryRepository.findById(Integer.parseInt(fromCode)).get();
        if (catergorySub != null) {
            code = catergorySub.getScatCode();
        }
        return code;
    }


    @Transactional
    public void clearSubCats() {
        subCatergoryRepository.deleteAll();
    }

    @Transactional
    public List<AssetCatergorySub> getSubCatsByMainCatId(int mainCat) {
           return subCatergoryRepository.getSubCatsByMainCatId(mainCat);
        }
}
