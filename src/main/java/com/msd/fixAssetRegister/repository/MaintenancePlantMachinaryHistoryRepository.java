package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceComputerHistory;
import com.msd.fixAssetRegister.model.MaintenancePlantMachinaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenancePlantMachinaryHistoryRepository extends JpaRepository<MaintenancePlantMachinaryHistory,Integer> {

}
