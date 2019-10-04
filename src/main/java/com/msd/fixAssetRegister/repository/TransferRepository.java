package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Integer> {

    @Query(value = "SELECT COUNT(tfId) FROM Transfer")
    int getNoOfTransferd();

    @Query(value = "SELECT COUNT(tfId) FROM Transfer t where t.asset.locationMaster.sectionMaster.departmentMaster.depId=?1")
    int getNoOfTransferd(int branchId);
}
