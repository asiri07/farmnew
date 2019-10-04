package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceFurniture;
import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MaintenanceFurnitureRepository extends JpaRepository<MaintenanceFurniture,Integer> {
    @Query(value = "SELECT mf FROM MaintenanceFurniture mf WHERE mf.assetId = ?1")
    MaintenanceFurniture getFurnitureDetailsByAsset(int assetId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM MaintenanceFurniture mf WHERE mf.assetId=?1")
    public void deleteEntry(int assetId) ;

    @Query(value = "SELECT Max(mf.furnitureId) FROM MaintenanceFurniture mf")
    Integer   getMaxFurnitureId();

    @Query(value = "SELECT COUNT(mf) FROM  MaintenanceFurniture mf ")
    int getCount();


}
