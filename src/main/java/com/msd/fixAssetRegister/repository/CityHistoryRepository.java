package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.CityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityHistoryRepository extends JpaRepository<CityHistory,Integer> {
}
