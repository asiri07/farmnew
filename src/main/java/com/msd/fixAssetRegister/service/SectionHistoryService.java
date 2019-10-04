package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.SectionMaster;
import com.msd.fixAssetRegister.model.SectionMasterHistory;
import com.msd.fixAssetRegister.repository.SectionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class SectionHistoryService {

    @Autowired
    SectionHistoryRepository sectionHistoryRepository;

    @Transactional
    public void updateHistory(SectionMaster sectionMasterOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SectionMasterHistory sectionMasterHistory = new SectionMasterHistory();
        sectionMasterHistory.setSecId(sectionMasterOld.getSecId());
        sectionMasterHistory.setSecCode(sectionMasterOld.getSecCode());
        sectionMasterHistory.setSecDes(sectionMasterOld.getSecDes());
        sectionMasterHistory.setDepId(sectionMasterOld.getDepartmentMaster().getDepId());
        sectionMasterHistory.setUserId(sectionMasterOld.getUserId());
        sectionMasterHistory.setActionTime(sectionMasterOld.getActionTime());
        sectionMasterHistory.setStatus(status);
        sectionMasterHistory.setUpdateUser(userId);
        sectionMasterHistory.setUpdateTime(date);
//        sectionHistoryDao.save(sectionMasterHistory);
        sectionHistoryRepository.save(sectionMasterHistory);
    }
}
