package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceComputerRepository extends JpaRepository<MaintenanceComputer,Integer> {

    @Query(value = "SELECT dm FROM MaintenanceComputer dm WHERE dm.assetId = ?1")
    MaintenanceComputer getComputerDetailsByAsset(int assetId);
}
