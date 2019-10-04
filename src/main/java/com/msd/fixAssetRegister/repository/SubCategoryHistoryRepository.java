package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergorySubHistory;
import com.msd.fixAssetRegister.model.AssetLocationDetail;
import com.msd.fixAssetRegister.model.SectionMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryHistoryRepository extends JpaRepository<AssetCatergorySubHistory,Integer> {
}
