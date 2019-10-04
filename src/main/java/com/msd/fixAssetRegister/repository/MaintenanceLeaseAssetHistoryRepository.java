package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLeaseAssetHistory;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreementsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLeaseAssetHistoryRepository extends JpaRepository<MaintenanceLeaseAssetHistory,Integer> {

}
