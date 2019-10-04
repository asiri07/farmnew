package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceModification;
import com.msd.fixAssetRegister.model.MaintenanceOfficeEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceModificationRepository extends JpaRepository<MaintenanceModification,Integer> {

    @Query(value = "SELECT COUNT(mm) FROM  MaintenanceModification mm ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(mm.transactionNo,3,6)) FROM MaintenanceModification mm ORDER BY mm.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT mm FROM MaintenanceModification mm WHERE mm.assetId=?1 ORDER BY mm.transactionNo DESC")
    List<MaintenanceModification> getTransactionNoList(int assetId);

    @Query(value = "SELECT mm FROM MaintenanceModification mm WHERE mm.modificationId =?1")
    MaintenanceModification getModificationDetails(int transactionNo);

}
