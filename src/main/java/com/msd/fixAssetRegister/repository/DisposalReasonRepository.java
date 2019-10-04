package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetDisposalReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalReasonRepository extends JpaRepository<AssetDisposalReason,Integer> {
}
