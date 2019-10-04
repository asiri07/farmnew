package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergorySub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCatergoryRepository extends JpaRepository<AssetCatergorySub,Integer> {

    @Query(value = "SELECT dm FROM AssetCatergorySub dm WHERE dm.assetCatergoryMain.mcatId = ?1")
     List<AssetCatergorySub> getSubCatsByMainCatId(int mcatId);

    @Query(value = "SELECT cs FROM AssetCatergorySub AS cs WHERE cs.scatCode = ?1 AND cs.assetCatergoryMain.mcatId = ?2")
    AssetCatergorySub getSubCatByCode(String subCode, int mainCat);
}
