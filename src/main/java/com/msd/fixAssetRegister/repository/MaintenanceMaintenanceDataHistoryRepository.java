package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceMaintenanceDataHistoryRepository extends JpaRepository<MaintenanceMaintenanceDataHistory,Integer> {

}

