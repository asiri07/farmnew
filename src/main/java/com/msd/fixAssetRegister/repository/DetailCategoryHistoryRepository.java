package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergoryDetailHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCategoryHistoryRepository extends JpaRepository<AssetCatergoryDetailHistory,Integer> {
}
