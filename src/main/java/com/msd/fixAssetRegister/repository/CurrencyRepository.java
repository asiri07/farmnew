package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    @Query(value = "SELECT cu FROM Currency cu WHERE cu.currencyCode = ?1")
    Currency getCurrencyByCode(String currencyCode);

    @Query(value = "SELECT cu.currencyCode FROM Currency as cu WHERE cu.currencyId = ?1")
    String getCurrencyById(int currencyId);
}
