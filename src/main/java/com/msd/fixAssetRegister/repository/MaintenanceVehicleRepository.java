package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetLocationDetail;
import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceVehicleRepository extends JpaRepository<MaintenanceVehicle,Integer> {

    @Query(value = "SELECT dm FROM MaintenanceVehicle dm WHERE dm.assetId = ?1")
    MaintenanceVehicle getVehilceDetailsByAsset(int assetId);
}
