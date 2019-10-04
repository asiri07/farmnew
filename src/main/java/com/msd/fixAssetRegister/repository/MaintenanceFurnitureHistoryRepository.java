package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceFurnitureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceFurnitureHistoryRepository extends JpaRepository<MaintenanceFurnitureHistory,Integer> {


}
