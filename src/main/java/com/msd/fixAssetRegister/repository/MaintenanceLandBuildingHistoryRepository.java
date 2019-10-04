package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLandBulidingHistory;
import com.msd.fixAssetRegister.model.MaintenanceLandHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLandBuildingHistoryRepository extends JpaRepository<MaintenanceLandBulidingHistory,Integer> {

}
