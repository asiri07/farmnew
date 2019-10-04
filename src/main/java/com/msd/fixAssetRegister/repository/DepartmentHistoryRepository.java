package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.DepartmentMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentHistoryRepository extends JpaRepository<DepartmentMasterHistory,Integer> {
}
