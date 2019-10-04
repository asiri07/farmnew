package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceInsuranceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceInsuranceHistoryRepository extends JpaRepository<MaintenanceInsuranceHistory,Integer> {

}
