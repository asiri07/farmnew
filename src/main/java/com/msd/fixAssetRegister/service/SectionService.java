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

import com.msd.fixAssetRegister.model.SectionMaster;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.DepartmentRepository;
import com.msd.fixAssetRegister.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional
    public List<SectionMaster> getSectionBydepId(int depId) throws Exception {
        return departmentRepository.findById(depId).get().getSectionMasters();
    }

    @Transactional
    public SectionMaster getSectionByCode(String secCode,int depaId) {
        return sectionRepository.getSectionByCode(secCode, depaId);
    }

    @Transactional
    public int deleteSection(int sectionId) {
        sectionRepository.deleteById(sectionId);
        return 1;
    }

    @Transactional
    public List<Listing> getSectionListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<SectionMaster> subList = sectionRepository.findAll();
        for (SectionMaster catergorySub:subList) {
            Listing listing = new Listing();
            listing.setListingId(catergorySub.getSecId());
            listing.setListingName(catergorySub.getSecCode());
            listing.setDescription(catergorySub.getSecDes());
            listing.setListingType(6);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getSectionCode(String fromCode) {
        SectionMaster sectionMaster = sectionRepository.findById(Integer.parseInt(fromCode)).get();
        return sectionMaster.getSecCode();
    }

    public SectionMaster saveUpdateSection(SectionMaster sectionMaster) {
        return sectionRepository.save(sectionMaster);
    }

    @Transactional
    public List<SectionMaster> findAll(int branch){
        if (branch == 0) {
            return sectionRepository.findAll();
        } else {
            return sectionRepository.findAll(branch);
        }
    }

    @Transactional
    public SectionMaster findById(int comId) {
        return sectionRepository.findById(comId).get();
    }
}
