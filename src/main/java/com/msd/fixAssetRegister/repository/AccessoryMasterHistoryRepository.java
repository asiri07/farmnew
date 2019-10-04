package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AccessoryAssigning;
import com.msd.fixAssetRegister.model.AccessoryMaster;
import com.msd.fixAssetRegister.model.AccessoryMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryMasterHistoryRepository extends JpaRepository<AccessoryMasterHistory,Integer> {



}
