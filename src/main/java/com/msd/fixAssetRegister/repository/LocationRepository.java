package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationMaster,Integer> {

    @Query(value = "SELECT lm FROM LocationMaster AS lm WHERE lm.sectionMaster.secId= ?1")
    List<LocationMaster> findLocationMastersBySectionMaster(int secId);

    @Query(value = "SELECT lm FROM LocationMaster lm WHERE lm.room.id = ?1")
    List<LocationMaster> findByLocationByRoom(int roomId);

    @Query(value = "SELECT lm FROM LocationMaster lm WHERE lm.sectionMaster.departmentMaster.depId=1")
    List<LocationMaster> findAll(int branch);

    @Query(value = "SELECT lm.locId,lm.locCode,lm.locDes FROM LocationMaster lm WHERE lm.sectionMaster.departmentMaster.depId=1")
    List<LocationMaster> getLocationsByBranch(int branch);

    @Query(value = "SELECT lm.locId,lm.locCode,lm.locDes FROM LocationMaster lm")
    List<LocationMaster> getAllLocations();

    @Query(value = "SELECT lm FROM LocationMaster lm WHERE lm.sectionMaster.secId = ?1")
    List<LocationMaster> getLocationBySectionId(int sectionId);

    @Query(value = "SELECT fl FROM LocationMaster fl WHERE fl.locCode = ?1 and fl.sectionMaster.secId = ?2 ")
    LocationMaster getLocationByCode(String locCode, int secId);

//    @Query(value = "SELECT lm FROM LocationMaster lm WHERE lm.room.id = ?1")
//    List<LocationMaster> getLocationByRoom(int roomId);
}
