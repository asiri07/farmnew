package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetDisposalReason;
import com.msd.fixAssetRegister.model.AssetPriorReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetPriorRegRepository extends JpaRepository<AssetPriorReg,Integer> {
}
