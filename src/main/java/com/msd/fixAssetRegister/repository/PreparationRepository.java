package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.ReportPreparationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PreparationRepository extends JpaRepository<ReportPreparationData,Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ReportPreparationData ")
    void deleteReportPreparationData();
}
