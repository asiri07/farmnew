package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.CurrencyRate;
import com.msd.fixAssetRegister.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class CurrencyRateService {

    @Autowired
    CurrencyRateRepository currencyRateRepository;

    @Transactional
    public List<CurrencyRate> findCurrencyRateByDate(Date date) {
        return currencyRateRepository.findCurrencyRateToday(date);
    }

    @Transactional
    public CurrencyRate findRateByDate(Date dpDate, int currencyTypeFrom, int currencyTypeTo) {
        return currencyRateRepository.findRateByDate(dpDate, currencyTypeFrom, currencyTypeTo);
    }

    @Transactional
    public String saveCurrencyRate(CurrencyRate currencyRate) {
        String msg = "";
        CurrencyRate currencyRateOld = currencyRateRepository.findRateByDate(currencyRate.getDate(),currencyRate.getCurrencyFrom(),currencyRate.getCurrencyTo());
        if(currencyRateOld != null) {
            currencyRateOld.setExchangeRate(currencyRate.getExchangeRate());
            currencyRateOld.setActionTime(currencyRate.getActionTime());
            currencyRateOld.setUserId(currencyRate.getUserId());
            currencyRateRepository.save(currencyRateOld);
            msg = "Update Successful.";
        }else {
            currencyRateRepository.save(currencyRate);
            msg = "Update Successful.";
        }

        return msg;
    }
}
