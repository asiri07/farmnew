package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.City;
import com.msd.fixAssetRegister.model.CityHistory;
import com.msd.fixAssetRegister.repository.CityHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@Service
public class CityHistoryService  {

    @Autowired
    CityHistoryRepository cityHistoryRepository;

    public void updateHistory(City cityOld, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        CityHistory cityHistory = new CityHistory();
        cityHistory.setCityId(cityOld.getCityId());
        cityHistory.setCityCode(cityOld.getCityCode());
        cityHistory.setDescription(cityOld.getDescription());
        cityHistory.setUserId(cityOld.getUserId());
        cityHistory.setActionTime(cityOld.getActionTime());
        cityHistory.setStatus(status);
        cityHistory.setUpdateUser(userId);
        cityHistory.setUpdateTime(date);
        cityHistoryRepository.save(cityHistory);
    }
}
