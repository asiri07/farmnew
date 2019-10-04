package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetLocationDetail;
import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceWarranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintenanceWarrantyRepository extends JpaRepository<MaintenanceWarranty,Integer> {

    @Query(value = "SELECT COUNT(mw) FROM  MaintenanceWarranty mw ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(mw.transactionNo,3,6)) FROM MaintenanceWarranty mw ORDER BY mw.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT mw FROM MaintenanceWarranty mw WHERE mw.assetId=?1 ORDER BY mw.transactionNo DESC")
    List<MaintenanceWarranty> getTransactionNoList(int assetId);

    @Query(value = "SELECT mw FROM MaintenanceWarranty mw WHERE mw.warrantyId = ?1")
    MaintenanceWarranty getWarrantyDetails(int transactionNo);

    @Query(value = "SELECT mw FROM MaintenanceWarranty AS mw WHERE mw.warrantyPeriodTo >?1  And mw.warrantyPeriodTo < ?2")
    List<MaintenanceWarranty> loadData(Date date1,Date date2);

    @Query(value = "SELECT mw FROM MaintenanceWarranty AS mw , Asset AS ass WHERE ass.asId = mw.assetId AND mw.warrantyPeriodTo >?1  And mw.warrantyPeriodTo < ?2 AND ass.locationMaster.sectionMaster.departmentMaster.depId=?3")
    List<MaintenanceWarranty> loadData(Date date1,Date date2, int branch);

}
