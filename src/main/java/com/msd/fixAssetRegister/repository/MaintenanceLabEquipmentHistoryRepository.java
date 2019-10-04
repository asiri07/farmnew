package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLabEquipment;
import com.msd.fixAssetRegister.model.MaintenanceLabEquipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLabEquipmentHistoryRepository extends JpaRepository<MaintenanceLabEquipmentHistory,Integer> {

}
