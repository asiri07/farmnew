package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AJobDefine;
import com.msd.fixAssetRegister.model.RoomHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomHistoryRepository extends JpaRepository<RoomHistory,Integer> {
}
