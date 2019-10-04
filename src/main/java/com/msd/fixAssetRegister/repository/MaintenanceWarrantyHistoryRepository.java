package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceInsuranceHistory;
import com.msd.fixAssetRegister.model.MaintenanceWarrantyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceWarrantyHistoryRepository extends JpaRepository<MaintenanceWarrantyHistory,Integer> {

}
