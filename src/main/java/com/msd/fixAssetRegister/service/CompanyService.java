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

import com.msd.fixAssetRegister.model.CompanyMaster;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Transactional
    public CompanyMaster getCompanyByCode(String comCode) throws Exception {
        return companyRepository.getCompanyByCode(comCode);
    }

    @Transactional
    public List<CompanyMaster> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public int deleteCompany(int comId) {
        int isDelete = 0;
        companyRepository.deleteById(comId);
        return isDelete;
    }

    @Transactional
    public List<Listing> getCompanyListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<CompanyMaster> comMaster = companyRepository.findAll();
        for (CompanyMaster companyMaster : comMaster) {
            Listing listing = new Listing();
            listing.setListingId(companyMaster.getComId());
            listing.setListingName(companyMaster.getComCode());
            listing.setDescription(companyMaster.getComDes());
            listing.setListingType(4);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getCompanyCatCode(String fromCode) {
        String code = "";
        CompanyMaster companyMaster = companyRepository.getOne(Integer.parseInt(fromCode));
        if (companyMaster != null) {
            code = companyMaster.getComCode();
        }
        return code;
    }

    @Transactional
    public CompanyMaster saveUpdateCompany(CompanyMaster companyMaster) {
        return companyRepository.save(companyMaster);
    }

    @Transactional
    public CompanyMaster findById(int comId) {
        return companyRepository.findById(comId).get();
    }
}
