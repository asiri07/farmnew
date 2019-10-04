package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.AssetCatergoryMainHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryHistoryRepository extends JpaRepository<AssetCatergoryMainHistory,Integer> {
}
