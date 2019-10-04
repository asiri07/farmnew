package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceSoftware;
import com.msd.fixAssetRegister.model.MaintenanceSoftwareHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceSoftwareHistoryRepository extends JpaRepository<MaintenanceSoftwareHistory,Integer> {

}
