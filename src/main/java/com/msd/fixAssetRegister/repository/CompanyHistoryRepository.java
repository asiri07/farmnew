package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.CompanyMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyHistoryRepository extends JpaRepository<CompanyMasterHistory,Integer> {
}
