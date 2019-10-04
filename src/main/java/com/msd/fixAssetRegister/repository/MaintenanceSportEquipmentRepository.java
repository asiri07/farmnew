package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLabEquipment;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipment;
import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceSportEquipmentRepository extends JpaRepository<MaintenanceSportEquipment,Integer> {
    @Query(value = "SELECT dm FROM MaintenanceSportEquipment dm WHERE dm.assetId = ?1")
    MaintenanceSportEquipment getSportEquipmentDetails(int assetId);
}
