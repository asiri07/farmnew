package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetSearchHistory;
import com.msd.fixAssetRegister.model.PhysicalVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssetSearchHistoryRepository extends JpaRepository<AssetSearchHistory,Integer> {

    @Query(value = "SELECT ah.asCode,ast.asDes,ast.asId,count(ah.asCode) as qty FROM AssetSearchHistory ah, Asset ast where ast.asCode = ah.asCode and ast.locationMaster.locId = ?2 and ah.asAtDate = ?1  group by ast.assetCatergoryDetail.dcatId  order by ah.asCode")
    List findByDate(Date date, int locationId);

}
