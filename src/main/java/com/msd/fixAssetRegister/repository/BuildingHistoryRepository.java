package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.BuildingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingHistoryRepository extends JpaRepository<BuildingHistory,Integer> {
}
