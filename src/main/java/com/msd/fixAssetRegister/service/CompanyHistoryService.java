package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.CompanyMaster;
import com.msd.fixAssetRegister.model.CompanyMasterHistory;
import com.msd.fixAssetRegister.repository.CompanyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class CompanyHistoryService {

    @Autowired
    CompanyHistoryRepository companyHistoryRepository;

    @Transactional
    public void updateHistory(CompanyMaster companyMasterOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        CompanyMasterHistory companyMasterHistory = new CompanyMasterHistory();
        companyMasterHistory.setComId(companyMasterOld.getComId());
        companyMasterHistory.setComCode(companyMasterOld.getComCode());
        companyMasterHistory.setComDes(companyMasterOld.getComDes());
        companyMasterHistory.setUserId(companyMasterOld.getUserId());
        companyMasterHistory.setActionTime(companyMasterOld.getActionTime());
        companyMasterHistory.setStatus(status);
        companyMasterHistory.setUpdateUser(userId);
        companyMasterHistory.setUpdateTime(date);
        companyHistoryRepository.save(companyMasterHistory);
    }
}
