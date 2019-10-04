package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AccessoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryMasterRepository extends JpaRepository<AccessoryMaster,Integer> {

    @Query(value = "SELECT fl FROM AccessoryMaster fl WHERE fl.accerCode = ?1")
    AccessoryMaster getAccessoryMasterByCode(String accessoryCode);
}
