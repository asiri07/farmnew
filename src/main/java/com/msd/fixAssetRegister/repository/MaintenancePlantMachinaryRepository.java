package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenancePlantMachinary;
import com.msd.fixAssetRegister.model.MaintenanceRunningData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenancePlantMachinaryRepository extends JpaRepository<MaintenancePlantMachinary,Integer> {

    @Query(value = "SELECT dm FROM MaintenancePlantMachinary dm WHERE dm.assetId = ?1")
    MaintenancePlantMachinary getPlantMachineryDetailsByAsset(int assetId);
}
