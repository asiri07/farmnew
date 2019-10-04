package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLandHistory;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLandHistoryRepository extends JpaRepository<MaintenanceLandHistory,Integer> {

}
