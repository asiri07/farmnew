package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Damage;
import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceLeaseAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DamageRepository extends JpaRepository<Damage,Integer> {

    @Query(value = "SELECT d.dmgAsId,d.transactionNo FROM Damage d WHERE d.asset.asId=?1")
    List getTransactionNoList(int assetId);

    @Query(value = "SELECT d.dmgAsId,d.transactionNo FROM Damage d WHERE d.asset.asId=?1 AND d.asset.locationMaster.sectionMaster.departmentMaster.depId = ?2")
    List getTransactionNoList(int assetId, int branch);

    @Query(value = "SELECT COUNT(d) FROM  Damage d ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(d.transactionNo,3,6)) FROM Damage d ORDER BY d.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT d.dmgAsId,d.transactionNo,d.date,d.dmgCost,d.dmgDes FROM Damage d WHERE d.dmgAsId = ?1")
    List getDamageDetails(int damageId);

    @Query(value = "SELECT COUNT(dmgAsId) FROM Damage")
    int getNoOfDamages();

    @Query(value = "SELECT COUNT(d.dmgAsId) FROM Damage d where d.asset.locationMaster.sectionMaster.departmentMaster.depId=?1")
    int getNoOfDamages(int branchId);

}
