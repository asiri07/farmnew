package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.CompanyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyMaster,Integer> {

    @Query(value = "SELECT cm FROM CompanyMaster cm WHERE cm.comCode = ?1")
    CompanyMaster getCompanyByCode(String comCode);
}
