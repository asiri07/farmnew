package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceVehicle;
import com.msd.fixAssetRegister.model.MaintenanceVehicleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceVehicleHistoryRepository extends JpaRepository<MaintenanceVehicleHistory,Integer> {

}
