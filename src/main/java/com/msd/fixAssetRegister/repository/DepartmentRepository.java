package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.CompanyMaster;
import com.msd.fixAssetRegister.model.DepartmentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentMaster,Integer> {

    @Query(value = "SELECT dm FROM DepartmentMaster dm WHERE dm.companyMaster.comId = ?1")
    List<DepartmentMaster> findDepartmentByComId(int comId);

    @Query(value = "SELECT dm FROM DepartmentMaster dm WHERE dm.depId = ?1")
    List<DepartmentMaster> findAll(int depId);

    DepartmentMaster findByDepCode(String fromDep);

//    @Query(value = "SELECT dm FROM DepartmentMaster dm WHERE dm.companyMaster.comId = ?1")
//    List<DepartmentMaster> getDepartmentsByComId(int comId);

    @Query(value = "SELECT dm FROM DepartmentMaster dm WHERE dm.companyMaster.comId = ?2 and dm.depCode = ?1 ")
    DepartmentMaster getDepartmentByCode(String depCode, int comId);

}
