package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AMaintab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainTabRepository extends JpaRepository<AMaintab,Integer> {

    @Query(value = "SELECT am FROM AMaintab AS am WHERE am.isActive = true ORDER BY am.order_ID ASC")
    List<AMaintab> findMainTabs();
}
