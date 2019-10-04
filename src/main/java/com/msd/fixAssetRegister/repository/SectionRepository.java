package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.SectionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<SectionMaster,Integer> {

    @Query(value = "SELECT sm FROM SectionMaster AS sm WHERE sm.secCode = ?1 AND sm.departmentMaster.depId = ?2")
    SectionMaster getSectionByCode(String secCode, int depaId);

    @Query(value = "SELECT sm FROM SectionMaster AS sm WHERE sm.departmentMaster.depId = ?1")
    List<SectionMaster> findAll(int branch);
}
