package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintenanceLeaseAssetRepository extends JpaRepository<MaintenanceLeaseAsset,Integer> {
    @Query(value = "SELECT COUNT(mla) FROM  MaintenanceLeaseAsset mla ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(mla.transactionNo,3,6)) FROM MaintenanceLeaseAsset mla ORDER BY mla.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT mla FROM MaintenanceLeaseAsset mla WHERE mla.assetId=?1 ")
    List<MaintenanceLeaseAsset> getTransactionNoList(int assetId);

    @Query(value = "SELECT mla FROM MaintenanceLeaseAsset mla WHERE mla.leaseID = ?1")
    MaintenanceLeaseAsset getLeaseAssetDetails(int transactionNo);

    @Query(value = "SELECT mla FROM MaintenanceLeaseAsset AS mla WHERE  mla.leasePremiumTo> ?1 AND mla.leasePremiumDate< ?2 ")
    List<MaintenanceLeaseAsset> loadData(Date parse,int premiumDate);

    @Query(value = "SELECT mla FROM MaintenanceLeaseAsset AS mla WHERE  mla.leasePremiumTo>= ?1 ")
    List<MaintenanceLeaseAsset> loadDueAsset(Date parse);

    @Query(value = "SELECT mla FROM MaintenanceLeaseAsset AS mla , Asset AS ass WHERE ass.asId = mla.assetId AND mla.leasePremiumTo>= ?1 AND ass.locationMaster.sectionMaster.departmentMaster.depId=?2")
    List<MaintenanceLeaseAsset> loadDueAsset(Date parse,int branch);

    @Query(value = "SELECT mla FROM MaintenanceLeaseAsset AS mla , Asset AS ass WHERE ass.asId = mla.assetId AND mla.leasePremiumTo> ?1 AND mla.leasePremiumDate< ?2 AND ass.locationMaster.sectionMaster.departmentMaster.depId=?3")
    List<MaintenanceLeaseAsset> loadData(Date parse,int premiumDate, int branch);

}
