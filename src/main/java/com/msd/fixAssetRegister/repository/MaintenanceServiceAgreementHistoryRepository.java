package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceServiceAgreementsHistory;
import com.msd.fixAssetRegister.model.MaintenanceWarrantyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceServiceAgreementHistoryRepository extends JpaRepository<MaintenanceServiceAgreementsHistory,Integer> {

}
