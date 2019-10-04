package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceOfficeEquipmentRepository extends JpaRepository<MaintenanceOfficeEquipment,Integer> {
    @Query(value = "SELECT dm FROM MaintenanceOfficeEquipment dm WHERE dm.assetId = ?1")
    MaintenanceOfficeEquipment getOfficeEquipmentsDetailsByAsset(int assetId);
}
