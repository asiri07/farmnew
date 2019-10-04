package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintenanceInsuranceRepository extends JpaRepository<MaintenanceInsurance,Integer> {

    @Query(value="SELECT COUNT(mi) FROM MaintenanceInsurance mi")
    int getCount();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(mi.transactionNo,3,6)) FROM MaintenanceInsurance mi ORDER BY mi.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT mi FROM MaintenanceInsurance mi WHERE mi.assetId=?1 ORDER BY mi.transactionNo DESC")
    List<MaintenanceInsurance>getTransactionNoList(int assetId);

    @Query(value = "SELECT mi FROM MaintenanceInsurance mi WHERE mi.insuranceID = ?1")
    MaintenanceInsurance getInsuranceDetails(int transactionNo);


    @Query(value = "SELECT ins FROM MaintenanceInsurance AS ins WHERE  ins.insurancePeriodTo >?1 AND ins.insurancePeriodTo<?2")
    List<MaintenanceInsurance> loadData(Date date1,Date date2);

    @Query(value = "SELECT ins FROM MaintenanceInsurance AS ins , Asset AS ass WHERE ass.asId = ins.assetId AND ins.insurancePeriodTo >?1 AND ins.insurancePeriodTo<?2 AND ass.locationMaster.sectionMaster.departmentMaster.depId=?3")
    List<MaintenanceInsurance> loadData(Date date1,Date date2, int branch);




}
