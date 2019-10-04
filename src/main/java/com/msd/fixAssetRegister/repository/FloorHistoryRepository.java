package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetImprovement;
import com.msd.fixAssetRegister.model.FloorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorHistoryRepository extends JpaRepository<FloorHistory,Integer> {
}
