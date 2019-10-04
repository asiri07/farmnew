package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetRevaluation;
import com.msd.fixAssetRegister.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AssetRevaluationRepository extends JpaRepository<AssetRevaluation,Integer> {

    @Query(value = "SELECT ar FROM AssetRevaluation as ar WHERE  ar.date = ?1 and ar.locationId = ?2" )
    List<AssetRevaluation> loadVerification(Date parse, int locationId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AssetRevaluation WHERE date = ?1 and locationId=?2")
    void removeTodayVerifications(Date date,int locationId);
}
