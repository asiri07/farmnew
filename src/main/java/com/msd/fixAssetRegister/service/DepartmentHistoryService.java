package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.DepartmentMaster;
import com.msd.fixAssetRegister.model.DepartmentMasterHistory;
import com.msd.fixAssetRegister.repository.DepartmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class DepartmentHistoryService {

    @Autowired
    DepartmentHistoryRepository departmentHistoryRepository;

    @Transactional
    public void updateHistory(DepartmentMaster departmentMasterOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DepartmentMasterHistory departmentMasterHistory = new DepartmentMasterHistory();
        departmentMasterHistory.setDepId(departmentMasterOld.getDepId());
        departmentMasterHistory.setDepCode(departmentMasterOld.getDepCode());
        departmentMasterHistory.setDepDes(departmentMasterOld.getDepDes());
        departmentMasterHistory.setUserId(departmentMasterOld.getUserId());
        departmentMasterHistory.setActionTime(departmentMasterOld.getActionTime());
        departmentMasterHistory.setComId(departmentMasterOld.getCompanyMaster().getComId());
        departmentMasterHistory.setStatus(status);
        departmentMasterHistory.setUpdateUser(userId);
        departmentMasterHistory.setUpdateTime(date);
//        departmentHistoryDao.save(departmentMasterHistory);
        departmentHistoryRepository.save(departmentMasterHistory);
    }
}
