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
import com.msd.fixAssetRegister.model.Building;
import com.msd.fixAssetRegister.model.City;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.BuildingRepository;
import com.msd.fixAssetRegister.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    CityRepository cityRepository;

    @Transactional
    public Building getBuildingByCode(String buildingCode,int cityId) {
        return buildingRepository.getBuildingByCode(buildingCode,cityId);
    }

    @Transactional
    public String getBuildingCodeById(String fromCode) {
        String code = "";
        Building building = buildingRepository.findById(Integer.parseInt(fromCode)).get();
        if (building != null) {
            code = building.getBuildingCode();
        }
        return code;
    }

    @Transactional
    public List<Listing> getBuildingListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<Building> building = buildingRepository.findAll();
        for (Building buildingCode : building) {
            Listing listing = new Listing();
            listing.setListingId(buildingCode.getId());
            listing.setListingName(buildingCode.getBuildingCode());
            listing.setDescription(buildingCode.getDescription());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public List<Building> getBuildingByCity(int cityId) {
        return buildingRepository.findBuildings(cityId);
    }

//    public Building saveUpdateBuilding(Building building) {
//        return buildingRepository.save(building);
//    }

    @Transactional
    public Boolean saveUpdateBuilding(Building building) {
        Boolean isSave;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            building.setActionTime(date);
            City city = cityRepository.findById(building.getCity().getCityId()).get();
            building.setCity(city);
            buildingRepository.save(building);
            isSave = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }
        return isSave;
    }


    @Transactional
    public List<Building> findAll(){
        return buildingRepository.findAll();
    }

    @Transactional
    public Building findById(int comId) {
        return buildingRepository.findById(comId).get();
    }

    @Transactional
    public List<Building> findBuildings(int cityId) {
        return buildingRepository.findBuildings(cityId);
    }

    @Transactional
    public void delete(int buildingId) {
        buildingRepository.deleteById(buildingId);
    }
}


