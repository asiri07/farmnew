package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AssetCatergoryDetail;
import com.msd.fixAssetRegister.model.AssetCatergoryDetailHistory;
import com.msd.fixAssetRegister.repository.DetailCategoryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class DetailCategoryHistoryService {

    @Autowired
    DetailCategoryHistoryRepository detailCategoryHistoryRepository;

    @Transactional
    public void updateHistory(AssetCatergoryDetail catergoryDetailOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        AssetCatergoryDetailHistory assetCatergoryDetailHistory = new AssetCatergoryDetailHistory();
        assetCatergoryDetailHistory.setDcatId(catergoryDetailOld.getDcatId());
        assetCatergoryDetailHistory.setDcatCode(catergoryDetailOld.getDcatCode());
        assetCatergoryDetailHistory.setDcatDes(catergoryDetailOld.getDcatDes());
        assetCatergoryDetailHistory.setDepRateAccount(catergoryDetailOld.getDepRateAccount());
        assetCatergoryDetailHistory.setDepRateIncomeTax(catergoryDetailOld.getDepRateIncomeTax());
        assetCatergoryDetailHistory.setUserId(catergoryDetailOld.getUserId());
        assetCatergoryDetailHistory.setStatus(status);
        assetCatergoryDetailHistory.setUpdateUser(userId);
        assetCatergoryDetailHistory.setUpdateTime(date);
        assetCatergoryDetailHistory.setScatId(catergoryDetailOld.getAssetCatergorySub().getScatId());
        assetCatergoryDetailHistory.setActionTime(catergoryDetailOld.getActionTime());
        //detailCategoryHistoryDao.save(assetCatergoryDetailHistory);
        detailCategoryHistoryRepository.save(assetCatergoryDetailHistory);
    }
}
