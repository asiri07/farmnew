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


import com.msd.fixAssetRegister.model.Room;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.FloorRepository;
import com.msd.fixAssetRegister.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    FloorRepository floorRepository;

    @Transactional
    public List<Room> getRoomByFloorId(int flowId) {
        return floorRepository.findById(flowId).get().getRooms();
    }

    @Transactional
    public Room getRoomByCodes(String roomCode, int cityId, int buildingId, int flowId) {
        return roomRepository.findByRoomByCodes(roomCode, cityId, buildingId, flowId);
    }

    @Transactional
    public String getRoomCodeById(String fromCode) {
        String code = "";
        Room room = roomRepository.findById(Integer.parseInt(fromCode)).get();
        if (room != null) {
            code = room.getRoomCode();
        }
        return code;
    }

    @Transactional
    public List<Listing> getRoomListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<Room> room = roomRepository.findAll();
        for (Room roomCode : room) {
            Listing listing = new Listing();
            listing.setListingId(roomCode.getId());
            listing.setListingName(roomCode.getRoomCode());
            listing.setDescription(roomCode.getDescription());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public List<Room> getRoomByFlow(int flowId) {
        return floorRepository.findById(flowId).get().getRooms();
    }

    @Transactional
    public Room saveUpdateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    @Transactional
    public Optional<Room> findById(int comId) {
        return roomRepository.findById(comId);
    }

    @Transactional
    public void delete(int roomId) {
        roomRepository.deleteById(roomId);
    }
}
