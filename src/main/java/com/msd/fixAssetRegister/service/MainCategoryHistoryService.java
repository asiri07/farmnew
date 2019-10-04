package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.AssetCatergoryMainHistory;
import com.msd.fixAssetRegister.repository.MainCategoryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MainCategoryHistoryService {

    @Autowired
    MainCategoryHistoryRepository mainCategoryHistoryRepository;

    @Transactional
    public void updateHistory(AssetCatergoryMain mainCatOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        AssetCatergoryMainHistory assetCatergoryMainHistory = new AssetCatergoryMainHistory();
        assetCatergoryMainHistory.setMcatId(mainCatOld.getMcatId());
        assetCatergoryMainHistory.setMcatCode(mainCatOld.getMcatCode());
        assetCatergoryMainHistory.setMcatDes(mainCatOld.getMcatDes());
        assetCatergoryMainHistory.setUserId(mainCatOld.getUserId());
        assetCatergoryMainHistory.setStatus(status);
        assetCatergoryMainHistory.setUpdateUser(userId);
        assetCatergoryMainHistory.setUpdateTime(date);
        assetCatergoryMainHistory.setActionTime(mainCatOld.getActionTime());
        mainCategoryHistoryRepository.save(assetCatergoryMainHistory);
    }
}
