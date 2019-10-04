package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetImprovement;
import com.msd.fixAssetRegister.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImprovementRepository extends JpaRepository<AssetImprovement,Integer> {
    @Query(value = "SELECT COUNT(ai) FROM  AssetImprovement ai ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(ai.transactionNo,3,6)) FROM AssetImprovement ai ORDER BY ai.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT ai.assetImpId,ai.transactionNo FROM AssetImprovement ai WHERE ai.asset.asId=?1")
    List getTransactionNoList(int assetId);

    @Query(value = "SELECT ai.assetImpId,ai.transactionNo FROM AssetImprovement ai WHERE ai.asset.asId=?1 AND ai.asset.locationMaster.sectionMaster.departmentMaster.depId = ?2")
    List getTransactionNoList(int assetId, int branch);

    @Query(value = "SELECT ai.assetImpId,ai.transactionNo,ai.impDate,ai.currencyValue,ai.description,ai.currencyType  FROM AssetImprovement ai WHERE ai.assetImpId = ?1")
    List getImprovementDetails(int improvementId);



}
