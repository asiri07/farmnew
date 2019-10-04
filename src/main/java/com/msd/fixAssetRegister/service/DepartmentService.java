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


import com.msd.fixAssetRegister.model.DepartmentMaster;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {


    @Autowired
    DepartmentRepository departmentRepository;


    @Transactional
    public List<DepartmentMaster> getDepartmentsByComId(int comId) {
        return departmentRepository.findDepartmentByComId(comId);
    }

    @Transactional
    public DepartmentMaster getDepartmentByCode(String depCode,int comId){
        return departmentRepository.getDepartmentByCode(depCode,comId);
    }

    @Transactional
    public int deleteDepartment(int depatId) {
        int isDelete = 0 ;
        departmentRepository.deleteById(depatId);
        return isDelete;
    }

    @Transactional
    public List<Listing> getDepartmentListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<DepartmentMaster> subList = departmentRepository.findAll();
        for (DepartmentMaster catergorySub:subList) {
            Listing listing = new Listing();
            listing.setListingId(catergorySub.getDepId());
            listing.setListingName(catergorySub.getDepCode());
            listing.setDescription(catergorySub.getDepDes());
            listing.setListingType(5);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getDepartmentCode(String fromCode) {
        String code = "";
        DepartmentMaster departmentMaster = departmentRepository.findById(Integer.parseInt(fromCode)).get();
        if(departmentMaster != null) {
            code = departmentMaster.getDepCode();
        }
        return code;
    }

    public DepartmentMaster saveDepartment(DepartmentMaster departmentMaster) {
        return departmentRepository.save(departmentMaster);
    }

    @Transactional
    public List<DepartmentMaster> findAll(int branch){
        if(branch == 0) {
            return departmentRepository.findAll();
        }else{
            return departmentRepository.findAll(branch);
        }
    }

    @Transactional
    public DepartmentMaster findById(int comId) {
        return departmentRepository.findById(comId).get();
    }

    @Transactional
    public List<DepartmentMaster> findDepartmentByComId(int comId) {
        return departmentRepository.findDepartmentByComId(comId);
    }


}
