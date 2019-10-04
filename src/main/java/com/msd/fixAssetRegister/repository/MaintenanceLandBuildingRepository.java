package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLand;
import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLandBuildingRepository extends JpaRepository<MaintenanceLandBuliding,Integer> {

    @Query(value = "SELECT dm FROM MaintenanceLandBuliding dm WHERE dm.assetId = ?1")
    MaintenanceLandBuliding getLandBuildingDetailsByAsset(int assetId);

}
