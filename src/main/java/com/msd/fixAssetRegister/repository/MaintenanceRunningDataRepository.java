package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import com.msd.fixAssetRegister.model.MaintenanceRunningData;
import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MaintenanceRunningDataRepository extends JpaRepository<MaintenanceRunningData,Integer> {
    @Query(value = "SELECT COUNT(mrd) FROM  MaintenanceRunningData mrd ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(mrd.transactionNo,3,6)) FROM MaintenanceRunningData mrd ORDER BY mrd.transactionNo DESC")
    List<String> getMaxTransactionNo();


    @Query(value = "SELECT  distinct mrd.transactionNo FROM MaintenanceRunningData mrd WHERE mrd.assetId=?1 ORDER BY mrd.transactionNo DESC")
    List<MaintenanceRunningData>getTransactionNoList(int assetId);

    @Query(value = "SELECT   mrd FROM MaintenanceRunningData mrd WHERE mrd.transactionNo=?1")
    List<MaintenanceRunningData>getRunningDataDetails(String transactionNo);


    @Query(value = "SELECT   mrd.transactionNo FROM MaintenanceRunningData mrd WHERE mrd.runningId=?1")
    String getTransactionNo(int runningId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM MaintenanceRunningData mrd WHERE mrd.transactionNo=?1")
    public void deleteEntry(String transactionNo) ;

}
