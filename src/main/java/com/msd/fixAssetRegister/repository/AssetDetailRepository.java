package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetLocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetDetailRepository extends JpaRepository<AssetLocationDetail,Integer> {
}
