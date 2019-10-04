package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AJobDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AJobDefineRepository extends JpaRepository<AJobDefine,Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AJobDefine WHERE ref_User_Type = ?1")
    void deleteJobs(int assetId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AJobDefine WHERE rer_Branch_Id = ?1")
    void deleteJobsbyBranch(int assetId);


}
