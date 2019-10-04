package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceMaintenanceDataHistory;
import com.msd.fixAssetRegister.model.MaintenanceRunningDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRunningDataHistoryRepository extends JpaRepository<MaintenanceRunningDataHistory,Integer> {

}

