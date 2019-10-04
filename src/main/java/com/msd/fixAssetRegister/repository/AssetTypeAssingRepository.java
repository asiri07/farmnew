package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.AssetTypeAssing;
import com.msd.fixAssetRegister.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AssetTypeAssingRepository extends JpaRepository<AssetTypeAssing,Integer> {

    @Query(value = "SELECT dm FROM AssetTypeAssing dm WHERE dm.mcatId = ?1")
    AssetTypeAssing findmcatCode(String mcatCode);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AssetTypeAssing mmd WHERE mmd.mcatId=?1")
    public void deleteAssetTypeAssing(String transactionNo) ;

//    @Query(value = "SELECT dm FROM AssetTypeAssing dm WHERE dm.typeLand = true ")
//    List<AssetTypeAssing> searchLandSelected();
}
