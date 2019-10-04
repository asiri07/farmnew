package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Building;
import com.msd.fixAssetRegister.model.DepartmentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {

    @Query(value = "SELECT dm FROM Building dm WHERE dm.city.cityId = ?1")
    List<Building> findBuildings(int cityId);

    @Query(value = "SELECT dm FROM Building dm WHERE dm.buildingCode=?1 and dm.city.cityId = ?2")
    Building getBuildingByCode(String buildingCode, int cityId);

    @Query(value = "SELECT dm FROM Building dm WHERE dm.buildingCode = ?1")
    Building getBuildingCodeById(String buildingCode);

//    @Query(value = "SELECT dm FROM Building dm WHERE dm.city.cityId = ?1")
//    List<Building> getBuildingByCity (int cityId);

}
