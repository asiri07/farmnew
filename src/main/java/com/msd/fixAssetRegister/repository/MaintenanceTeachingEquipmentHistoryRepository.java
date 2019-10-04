package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipment;
import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTeachingEquipmentHistoryRepository extends JpaRepository<MaintenanceTeachingEquipmentHistory,Integer> {

//    @Query(value = "SELECT dm FROM MaintenanceTeachingEquipment dm WHERE dm.assetId = ?1")
//    MaintenanceTeachingEquipment getTeachingEquipmentsDetailsByAsset(int assetId);
}
