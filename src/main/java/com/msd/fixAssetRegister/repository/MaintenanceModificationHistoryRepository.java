package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceModificationHistory;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreementsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceModificationHistoryRepository extends JpaRepository<MaintenanceModificationHistory,Integer> {

}
