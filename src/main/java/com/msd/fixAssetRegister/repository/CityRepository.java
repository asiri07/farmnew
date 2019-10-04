package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Building;
import com.msd.fixAssetRegister.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    @Query(value = "SELECT dm FROM City dm WHERE dm.cityCode = ?1")
    City getCityByCode(String cityCode);

    @Query(value = "SELECT dm FROM City dm WHERE dm.cityId = ?1")
    City getCityCodeById(String cityCode);


}
