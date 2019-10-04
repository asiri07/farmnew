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

import com.msd.fixAssetRegister.model.LocationMaster;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class LocationService{

    @Autowired
    LocationRepository locationRepository;

    @Transactional
    public List<LocationMaster> getLocationDetailsBySecId(int secId) {
        return locationRepository.findLocationMastersBySectionMaster(secId);
    }

    @Transactional
    public LocationMaster getLocationByCode(String locCode,int secId) {
        return locationRepository.getLocationByCode(locCode,secId);
    }

    @Transactional
    public int deleteLocation(int locationId) {
        int isDelete=0;
        locationRepository.deleteById(locationId);
        return 0;
    }

    @Transactional
    public List<Listing> getLocationListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<LocationMaster> subList = locationRepository.findAll();
        for (LocationMaster catergorySub:subList) {
            Listing listing = new Listing();
            listing.setListingId(catergorySub.getLocId());
            listing.setListingName(catergorySub.getLocCode());
            listing.setDescription(catergorySub.getLocDes());
            listing.setListingType(7);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getLocationCode(String fromCode) {
        String code = "";
        LocationMaster locationMaster = locationRepository.findById(Integer.parseInt(fromCode)).get();
        if (locationMaster != null) {
            code = locationMaster.getLocCode();
        }
        return code;
    }

    @Transactional
    public List<LocationMaster> getLocationByRoom(int roomId) {
        return locationRepository.findByLocationByRoom(roomId);
    }

    @Transactional
    public String getMaxSerialNo(int roomId, int maxChar) {
        String no = "";
        try {
            List<LocationMaster> locationMasters = locationRepository.findByLocationByRoom(roomId);
            if (locationMasters.size() > 0) {
                LocationMaster last = locationMasters.get(locationMasters.size() - 1);
                String assCode = last.getLocCode();
                //String[] part = assCode.split("(?<=\\D)(?=\\d)");
                no = assCode.substring(assCode.length() - maxChar);
            }
        } catch (Exception e) {
        }
        return no;
    }

    public LocationMaster saveUpdateLocationMaster(LocationMaster locationMaster) {
        return locationRepository.save(locationMaster);
    }

    @Transactional
    public List<LocationMaster> findAll(int branch){
        if (branch == 0) {
            return locationRepository.findAll();
        } else {
            return locationRepository.findAll(branch);
        }
    }

    public List<LocationMaster> findAllByBranch(int branch){
        if (branch == 0) {
            return locationRepository.getAllLocations();
        } else {
            return locationRepository.getLocationsByBranch(branch);
        }
    }

    @Transactional
    public LocationMaster findById(int comId) {
        return locationRepository.findById(comId).get();
    }

    @Transactional
    public List<LocationMaster> getLocationBySectionId(int sectionId) {
        return locationRepository.getLocationBySectionId(sectionId);
    }
}
