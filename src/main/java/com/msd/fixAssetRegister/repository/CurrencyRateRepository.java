package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate,Integer> {

    @Query(value = "SELECT cm FROM CurrencyRate cm WHERE cm.date = ?1")
    List<CurrencyRate> findCurrencyRateToday(Date date);

    @Query(value = "SELECT cm FROM CurrencyRate cm WHERE cm.date = ?1 and cm.currencyFrom = ?2 and cm.currencyTo = ?3")
    CurrencyRate findRateByDate(Date date, int currencyFrom, int currencyTo);
}
