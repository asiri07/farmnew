package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.ReportPreparationDetailBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PreparationDetailCatRepository extends JpaRepository<ReportPreparationDetailBalance,Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM report_preparation_detail_balance", nativeQuery = true)
    void clearPreparationDetailCats();

    @Query(value = "SELECT COUNT(asId) FROM Asset WHERE cancel = false AND asDisposed = false AND assetCatergoryDetail.dcatId = ?1 ")
    int getAssetBalance(int dcatId);

}
