package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Disposal;
import com.msd.fixAssetRegister.model.MaintenanceInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal,Integer> {

    @Query(value = "SELECT dis.dpId,dis.transactionNo,dis.asset.asId FROM Disposal dis where dis.asset.locationMaster.sectionMaster.departmentMaster.depId = ?1 ")
    List getTransactionNoList(int branch);

    @Query(value = "SELECT dis.dpId,dis.transactionNo,dis.asset.asId FROM Disposal dis")
    List getTransactionNoList();

    @Query(value = "SELECT COUNT(dis) FROM  Disposal dis ")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(dis.transactionNo,3,6)) FROM Disposal dis ORDER BY dis.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT COUNT(dpId) FROM Disposal")
    int getNoOfDisposed();

    @Query(value = "SELECT COUNT(d.dpId) FROM Disposal d where d.asset.locationMaster.sectionMaster.departmentMaster.depId=?1")
    int getNoOfDisposed(int branchId);

    @Query(value = "SELECT mi.dpId, mi.transactionNo, mi.dpDes, mi.currencyValue, mi.currencyType, mi.dpReason, mi.dpDate, mi.asset.asId, mi.asset.asDes, mi.asset.asCode FROM Disposal as mi WHERE mi.dpId = ?1")
    List<Disposal> loadDisposalByTRNo(int transactionNo);

}
