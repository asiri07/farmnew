package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AnalysisMaster;
import com.msd.fixAssetRegister.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisMaster,Integer> {

    @Query(value = "SELECT an FROM AnalysisMaster as an WHERE an.anaCode = ?1")
    AnalysisMaster getAnalysisMaster(String anaCode);

    @Query(value = "SELECT dm FROM AnalysisMaster dm WHERE dm.anaCode = ?1")
    AnalysisMaster getAnalysisCodeById(String anaCode);
}
