package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceServiceAgreements;
import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import com.msd.fixAssetRegister.model.MaintenanceWarranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintenanceServiceAgreementRepository extends JpaRepository<MaintenanceServiceAgreements,Integer> {

    @Query(value = "SELECT COUNT(msa) FROM  MaintenanceServiceAgreements msa ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(msa.transactionNo,3,6)) FROM MaintenanceServiceAgreements msa ORDER BY msa.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT msa FROM MaintenanceServiceAgreements msa WHERE msa.assetId=?1 ORDER BY msa.transactionNo DESC")
    List<MaintenanceServiceAgreements>getTransactionNoList(int assetId);

    @Query(value = "SELECT msa FROM MaintenanceServiceAgreements msa WHERE msa.agreeId = ?1")
    MaintenanceServiceAgreements getServiceAgreeDetails(int transactionNo);

    @Query(value = "SELECT msa FROM MaintenanceServiceAgreements AS msa WHERE  msa.agreeTo> ?1 AND msa.agreeTo<?2")
    List<MaintenanceServiceAgreements> loadData(Date date1,Date date2);

    @Query(value = "SELECT msa FROM MaintenanceServiceAgreements AS msa  , Asset AS ass WHERE ass.asId = msa.assetId AND msa.agreeTo> ?1 AND msa.agreeTo<?2  AND ass.locationMaster.sectionMaster.departmentMaster.depId=?3")
    List<MaintenanceServiceAgreements> loadData(Date date1,Date date2, int branch);
}
