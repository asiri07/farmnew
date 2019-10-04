package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.AssetLocationDetail;
import com.msd.fixAssetRegister.model.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AssetTypesRepository extends JpaRepository<AssetType,Integer> {


//    @Query(value = "SELECT SUM(AssetType.qty) FROM AssetType WHERE AssetType.id = ?1 and AssetType.id = ?2 and AssetType.id = ?3 and AssetType.id = ?4 ")

    @Query(value = "SELECT sum(ast.qty) FROM AssetCatergoryMain ast WHERE ast.mcatId <> ?1 and ast.mcatId<> ?2 and ast.mcatId <> ?3 and ast.mcatId <> ?4 and ast.mcatId <> ?5 and ast.mcatId <> ?6")
    int getNoOfOtherAsset(int a,int b,int c,int d,int e,int f);


    @Query(value = "SELECT acm.assetTypeId FROM AssetCatergoryMain acm where acm.mcatCode=?1")
    int getAssetTypeId(String mainCode);


    @Modifying
    @Transactional
    @Query(value = "UPDATE AssetType ast  set ast.qty=ast.qty + ?1 where  ast.id=?2")
    public void assetUpdate(int noUnit,int assetTypeId) ;

}
