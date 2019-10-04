package com.msd.fixAssetRegister.service;


import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.msd.fixAssetRegister.model.AssetCatergorySubHistory;
import com.msd.fixAssetRegister.repository.SubCategoryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class SubCategoryHistoryService {

    @Autowired
    private SubCategoryHistoryRepository subCategoryHistoryRepository;


    @Transactional
    public void updateHistory(AssetCatergorySub catergorySubOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        AssetCatergorySubHistory subHistory = new AssetCatergorySubHistory();
        subHistory.setMcatId(catergorySubOld.getScatId());
        subHistory.setScatCode(catergorySubOld.getScatCode());
        subHistory.setScatDes(catergorySubOld.getScatDes());
        subHistory.setUserId(catergorySubOld.getUserId());
        subHistory.setStatus(status);
        subHistory.setUpdateTime(date);
        subHistory.setUpdateUser(userId);
        subHistory.setMcatId(catergorySubOld.getAssetCatergoryMain().getMcatId());
        subHistory.setActionTime(catergorySubOld.getActionTime());
        //subCategoryHistoryDao.save(subHistory);
        subCategoryHistoryRepository.save(subHistory);
    }
}
