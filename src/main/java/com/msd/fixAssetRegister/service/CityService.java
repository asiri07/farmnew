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


import com.msd.fixAssetRegister.model.City;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {



    @Autowired
    CityRepository cityRepository;


    @Transactional
    public City getCityByCode(String cityCode) {
        return cityRepository.getCityByCode(cityCode);
    }

    @Transactional
    public String getCityCodeById(String fromCode) {
        String code = "";
        City city = cityRepository.findById(Integer.parseInt(fromCode)).get();
        if (city != null) {
            code = city.getCityCode();
        }
        return code;
    }

    @Transactional
    public List<Listing> getCityListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<City> city = cityRepository.findAll();
        for (City cityCode : city) {
            Listing listing = new Listing();
            listing.setListingId(cityCode.getCityId());
            listing.setListingName(cityCode.getCityCode());
            listing.setDescription(cityCode.getDescription());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public int deleteCity(int cityId) {
        int isDelete = 0;
        cityRepository.deleteById(cityId);
        return isDelete;
    }

    public City saveUpdateCity(City city) {
        return cityRepository.save(city);
    }

    @Transactional
    public List<City> findAll(){
        return cityRepository.findAll();
    }

    @Transactional
    public City findById(int comId) {
        return cityRepository.findById(comId).get();
    }



}
