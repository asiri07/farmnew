package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.MaintenanceLand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceLandServiceRepository extends JpaRepository<MaintenanceLand,Integer> {

    @Query(value = "SELECT dm FROM MaintenanceLand dm WHERE dm.assetId = ?1")
    MaintenanceLand getLandDetailsByAsset(int assetId);

}
