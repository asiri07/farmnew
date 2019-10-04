package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.PhysicalVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<PhysicalVerification, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PhysicalVerification WHERE date = ?1")
    void removeTodayVerifications(Date date);

    @Query(value = "SELECT ph FROM PhysicalVerification AS ph WHERE  ph.date = ?1 and ph.locationId =?2")
    List<PhysicalVerification> findByDate(Date parse, int locationId);

}
