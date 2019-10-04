package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceSportEquipment;
import com.msd.fixAssetRegister.model.MaintenanceSportEquipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceSportEquipmentHistoryRepository extends JpaRepository<MaintenanceSportEquipmentHistory,Integer> {

}
