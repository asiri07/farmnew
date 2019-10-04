package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLandBuliding;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.model.MaintenanceMaintenanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLibraryBooksRepository extends JpaRepository<MaintenanceLibraryBooks,Integer> {

    @Query(value = "SELECT mlb FROM MaintenanceLibraryBooks mlb WHERE mlb.assetId = ?1")
    MaintenanceLibraryBooks getLibraryBooksDetailsByAsset(int assetId);


}
