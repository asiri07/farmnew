package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Integer> {
}
