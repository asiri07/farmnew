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
import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.DetailCategoryRepository;
import com.msd.fixAssetRegister.repository.SubCatergoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DetailCategoryService {


    @Autowired
    SubCatergoryRepository subCatergoryRepository;

    @Autowired
    DetailCategoryRepository detailCategoryRepository;

    @Transactional
    public List<AssetCatergoryDetail> findAll() {
        return detailCategoryRepository.findAll();
    }


    @Transactional
    public Boolean saveDetailCatergory(AssetCatergoryDetail assetCatergoryDetail) {
        Boolean isSave = false;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        assetCatergoryDetail.setActionTime(date);
        detailCategoryRepository.save(assetCatergoryDetail);
        isSave = true;
        return isSave;
    }

    @Transactional
    public List<AssetCatergoryDetail> getDetailCatsBySubId(int subCat) {
        return detailCategoryRepository.getDetailCatsBySubCatId(subCat);
    }

    @Transactional
    public List<AssetCatergoryDetail> getdetailCatByCode(String detailCode, int subCode) {
        return detailCategoryRepository.getdetailCatByCode(detailCode, subCode);
    }

    @Transactional
    public List<AssetCatergoryDetail> getDetailCatsBySubCatId(int subCat) {
        return detailCategoryRepository.getDetailCatsBySubCatId(subCat);
    }

    @Transactional
    public int deleteDetailCategory(int detailCat) {
        int isDelete = 0;
        detailCategoryRepository.deleteById(detailCat);
        return isDelete;
    }

    @Transactional
    public List<Listing> getDetailCatListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<AssetCatergoryDetail> subList = detailCategoryRepository.findAll();
        for (AssetCatergoryDetail catergorySub : subList) {
            Listing listing = new Listing();
            listing.setListingId(catergorySub.getDcatId());
            listing.setListingName(catergorySub.getDcatCode());
            listing.setDescription(catergorySub.getDcatDes());
            listing.setListingType(3);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getDetailCatCode(String fromCode) {
        String code = "";
        AssetCatergoryDetail catergoryDetail = detailCategoryRepository.findById(Integer.parseInt(fromCode)).get();
        if (catergoryDetail != null) {
            code = catergoryDetail.getDcatCode();
        }
        return code;
    }

    @Transactional
    public String genarateNewDetailCatBySubCode(int subCat) {
        String newDetailCode = "";
        AssetCatergorySub catergorySub = subCatergoryRepository.findById(subCat).get();
        if (catergorySub.getAssetCatergoryDetails().size() > 0) {
            AssetCatergoryDetail detail = catergorySub.getAssetCatergoryDetails().get(catergorySub.getAssetCatergoryDetails().size() - 1);
            String code = detail.getDcatCode();
            Integer noCode = Integer.parseInt(code);
            if (noCode < 9) {
                newDetailCode = "0" + noCode++;
            } else {
                newDetailCode = noCode++ + "";
            }
        } else {
            newDetailCode = "01";
        }

        return newDetailCode;
    }

    @Transactional
    public AssetCatergoryDetail findById(int detailcat) {
        return detailCategoryRepository.findById(detailcat).get();
    }

}
