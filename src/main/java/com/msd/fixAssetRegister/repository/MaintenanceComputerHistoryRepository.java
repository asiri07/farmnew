package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceComputerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceComputerHistoryRepository extends JpaRepository<MaintenanceComputerHistory,Integer> {

}
