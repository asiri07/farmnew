package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationMasterRepository extends JpaRepository<LocationMaster,Integer>{

    LocationMaster findByLocCode(String toLoc);
}
