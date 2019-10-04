package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.Currency;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Transactional
    public int deleteCurrecy(int currencyId) {
        int isDelete;
        List<Asset> assetList = assetRepository.findAssetsByCurrencyType(currencyId);
        if (assetList.size() == 0) {
            currencyRepository.deleteById(currencyId);
            isDelete = 1;
        }else {
            isDelete = 2;
        }
        return isDelete;
    }

    @Transactional
    public Currency getCurrencyByCode(String currencyCode) {
        return currencyRepository.getCurrencyByCode(currencyCode);
    }

    public Currency saveUpdateCurrency(Currency currency) {
        return currencyRepository.save(currency);
         }

    @Transactional
    public List<Currency> findAll(){
        return currencyRepository.findAll();
    }

    @Transactional
    public Currency findById(int comId) {
        return currencyRepository.findById(comId).get();
    }

    @Transactional
    public String findCurrencyId(int comId) {
        return currencyRepository.getCurrencyById(comId);
    }
}
