package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergoryDetail;
import com.msd.fixAssetRegister.model.Floor;
import com.msd.fixAssetRegister.model.FloorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Integer> {

    @Query(value = "SELECT fl FROM Floor fl WHERE fl.building.id = ?1")
    List<Floor> getFlowsByBuildingId(int buildingId);

    @Query(value = "SELECT fl FROM Floor fl WHERE fl.floorCode = ?1 and fl.building.city.cityId = ?2 and fl.building.id = ?3 ")
    List<Floor> getFlowByCodes(String flowCode, int cityId, int buildingId);


    @Query(value = "SELECT dm FROM Floor dm WHERE dm.floorCode = ?1")
    Floor getFloorCodeById(String floorCode);

//    @Query(value = "SELECT fl FROM Floor fl WHERE fl.building.id = ?1")
//    List<Floor> getFlowByBuilding(int buildingId);

}
