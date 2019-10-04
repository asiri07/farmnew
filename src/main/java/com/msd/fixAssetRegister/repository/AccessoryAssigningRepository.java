package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AccessoryAssigning;
import com.msd.fixAssetRegister.model.AccessoryMaster;
import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.msd.fixAssetRegister.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccessoryAssigningRepository extends JpaRepository<AccessoryAssigning,Integer> {

    @Query(value = "SELECT DISTINCT am  FROM AccessoryMaster as am,AccessoryAssigning aa WHERE aa.accessoryId=am.accerId AND aa.assetId= ?1")
    List<AccessoryMaster> getAccessoryName(int assetId);

    @Query(value = "SELECT DISTINCT am FROM AccessoryMaster as am WHERE NOT  am.accerId IN ( SELECT aa.accessoryId FROM AccessoryAssigning as aa WHERE aa.assetId= ?1)")
    List<AccessoryMaster> getAllAccessories(int assetId);

    @Query(value = "SELECT aas FROM AccessoryAssigning aas WHERE aas.accessoryId = ?1")
    List<AccessoryAssigning> getAccessoryAssignnigId(int accerId);

    @Query(value = "SELECT aas FROM AccessoryAssigning aas WHERE aas.assetId = ?1")
    List<AccessoryAssigning> getAccessoryAssignnigAssetId(int accerId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AccessoryAssigning as acs WHERE  acs.assetId=?1")
    int deleteAccessoryAssigingByAssetId(int accessoryAssignningId);
}
