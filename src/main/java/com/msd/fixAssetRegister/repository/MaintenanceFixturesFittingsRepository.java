package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceFixturesFittingsRepository extends JpaRepository<MaintenanceFixturesFittings,Integer> {

    @Query(value = "SELECT dm FROM MaintenanceFixturesFittings dm WHERE dm.assetId = ?1")
    MaintenanceFixturesFittings getFixturesAndFittingsDetails(int assetId);
}
