package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceFixturesFittingsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceFixturesFittingsHistoryRepository extends JpaRepository<MaintenanceFixturesFittingsHistory,Integer> {

}
