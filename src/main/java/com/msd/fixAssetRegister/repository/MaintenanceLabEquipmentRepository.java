package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceLabEquipment;
import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLabEquipmentRepository extends JpaRepository<MaintenanceLabEquipment,Integer> {
    @Query(value = "SELECT dm FROM MaintenanceLabEquipment dm WHERE dm.assetId = ?1")
    MaintenanceLabEquipment getLabEquipmentDetails(int assetId);
}
