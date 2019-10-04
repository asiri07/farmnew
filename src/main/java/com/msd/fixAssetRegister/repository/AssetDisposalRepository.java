package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetDisposalReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetDisposalRepository extends JpaRepository<AssetDisposalReason,Integer> {
}
