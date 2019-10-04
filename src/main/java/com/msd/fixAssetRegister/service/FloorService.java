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


import com.msd.fixAssetRegister.model.Floor;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class FloorService {


    @Autowired
    FloorRepository floorRepository;

    @Transactional
    public List<Floor> getFlowsByBuildingId(int buildingId) {
        return floorRepository.getFlowsByBuildingId(buildingId);
    }

    @Transactional
    public List<Floor> getFloorByCodes(String flowCode, int cityId, int buildingId) {
        return floorRepository.getFlowByCodes(flowCode,cityId,buildingId);
    }

    @Transactional
    public String getFloorCodeById(String fromCode) {
        String code = "";
        Floor floor = floorRepository.findById(Integer.parseInt(fromCode)).get();
        if (floor != null) {
            code = floor.getFloorCode();
        }
        return code;
    }

    @Transactional
    public List<Listing> getFloorListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<Floor> floor = floorRepository.findAll();
        for (Floor floorCode : floor) {
            Listing listing = new Listing();
            listing.setListingId(floorCode.getId());
            listing.setListingName(floorCode.getFloorCode());
            listing.setDescription(floorCode.getDescription());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public List<Floor> getFlowByBuilding(int buildingId) {
        return floorRepository.getFlowsByBuildingId(buildingId);
    }


    @Transactional
    public Floor  saveUpdateFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    @Transactional
    public List<Floor> findAll(){
        return floorRepository.findAll();
    }

    @Transactional
    public Floor findById(int comId) {
        return floorRepository.findById(comId).get();
    }

    @Transactional
    public void delete(int floorId) {
        floorRepository.deleteById(floorId);
    }
}
