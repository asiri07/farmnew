package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MainCategoryRepository extends JpaRepository<AssetCatergoryMain,Integer> {

    @Query(value = "SELECT fl FROM AssetCatergoryMain fl WHERE fl.mcatCode = ?1")
    AssetCatergoryMain getMainCatByCode(String mainCode);


    @Modifying
    @Transactional
    @Query(value = "UPDATE AssetCatergoryMain acm  set acm.qty=acm.qty + ?1 where  acm.mcatCode=?2")
    public void assetUpdate(int noUnit,String mainCode) ;

}


