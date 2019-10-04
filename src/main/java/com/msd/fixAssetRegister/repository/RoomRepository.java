package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    @Query(value = "SELECT r FROM Room AS r WHERE r.roomCode = ?1 AND r.floor.id = ?4 AND r.floor.building.id =?3 AND r.floor.building.city.cityId = ?2")
    Room findByRoomByCodes(String roomCode, int cityId, int buildingId, int flowId);

    @Query(value = "SELECT dm FROM Room dm WHERE dm.roomCode = ?1")
    Room getRoomCodeById(String roomCode);
}
