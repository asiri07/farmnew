package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetLocationDetail;
import com.msd.fixAssetRegister.model.SectionMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionHistoryRepository extends JpaRepository<SectionMasterHistory,Integer> {
}
