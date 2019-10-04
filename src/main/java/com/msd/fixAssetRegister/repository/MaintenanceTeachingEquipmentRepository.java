package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetLocationDetail;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import com.msd.fixAssetRegister.model.MaintenanceTeachingEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTeachingEquipmentRepository extends JpaRepository<MaintenanceTeachingEquipment,Integer> {

    @Query(value = "SELECT dm FROM MaintenanceTeachingEquipment dm WHERE dm.assetId = ?1")
    MaintenanceTeachingEquipment getTeachingEquipmentsDetailsByAsset(int assetId);
}
