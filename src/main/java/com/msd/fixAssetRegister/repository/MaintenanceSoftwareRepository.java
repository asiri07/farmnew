package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipment;
import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceSoftwareRepository extends JpaRepository<MaintenanceSoftware,Integer> {
    @Query(value = "SELECT dm FROM MaintenanceSoftware dm WHERE dm.assetId = ?1")
    MaintenanceSoftware getSoftwareDetailsByAsset(int assetId);
}
