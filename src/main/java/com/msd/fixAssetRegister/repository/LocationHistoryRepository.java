package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.LocationMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationHistoryRepository extends JpaRepository<LocationMasterHistory,Integer> {
}
