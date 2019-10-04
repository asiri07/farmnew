package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MaintenanceMaintenanceDataRepository extends JpaRepository<MaintenanceMaintenanceData,Integer> {

    @Query(value = "SELECT DISTINCT am.accerName  FROM AccessoryMaster as am,AccessoryAssigning as aa WHERE am.accerId=aa.accessoryId AND aa.assetId  =?1")
    List<String> getAccessoryName(int assetId);

    @Query(value = "SELECT COUNT(mw) FROM  MaintenanceMaintenanceData mw ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(mmd.transactionNo,3,6)) FROM MaintenanceMaintenanceData mmd ORDER BY mmd.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT  distinct mmd.transactionNo FROM MaintenanceMaintenanceData mmd WHERE mmd.assetId=?1 ORDER BY mmd.transactionNo DESC")
    List<MaintenanceMaintenanceData>getTransactionNoList(int assetId);

    @Query(value = "SELECT   mmd FROM MaintenanceMaintenanceData mmd WHERE mmd.transactionNo=?1")
    List<MaintenanceMaintenanceData>getMaintenanceDataDetails(String transactionNo);


    @Query(value = "SELECT   mmd.transactionNo FROM MaintenanceMaintenanceData mmd WHERE mmd.maintenanceDataId=?1")
    String getTransactionNo(int maintenanceDataId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM MaintenanceMaintenanceData mmd WHERE mmd.transactionNo=?1")
    public void deleteEntry(String transactionNo) ;
}

