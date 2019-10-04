package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AccessoryAssigning;
import com.msd.fixAssetRegister.model.AccessoryMaster;
import com.msd.fixAssetRegister.model.LeaseAssetReminders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LeaseAssetRemindersRepository extends JpaRepository<LeaseAssetReminders,Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM LeaseAssetReminders as lar")
    public void deleteAll();

}
