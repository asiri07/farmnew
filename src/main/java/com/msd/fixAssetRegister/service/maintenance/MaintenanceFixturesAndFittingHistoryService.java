package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceComputerHistory;
import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceFixturesFittingsHistory;
import com.msd.fixAssetRegister.repository.MaintenanceFixturesFittingsHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceFixturesFittingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceFixturesAndFittingHistoryService {

    @Autowired
    MaintenanceFixturesFittingsHistoryRepository maintenanceFixturesFittingsHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceFixturesFittings maintenanceFixturesFittings, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceFixturesFittingsHistory maintenanceFixturesFittingsHistory = new MaintenanceFixturesFittingsHistory();
        maintenanceFixturesFittingsHistory.setFittingsId(maintenanceFixturesFittings.getFittingsId());
        maintenanceFixturesFittingsHistory.setAssetId(maintenanceFixturesFittings.getFittingsId());
        maintenanceFixturesFittingsHistory.setWidth(maintenanceFixturesFittings.getWidth());
        maintenanceFixturesFittingsHistory.setLength(maintenanceFixturesFittings.getLength());
        maintenanceFixturesFittingsHistory.setWeight(maintenanceFixturesFittings.getWeight());
        maintenanceFixturesFittingsHistory.setHeight(maintenanceFixturesFittings.getHeight());
        maintenanceFixturesFittingsHistory.setIsWarranty(maintenanceFixturesFittings.getIsWarranty());
        maintenanceFixturesFittingsHistory.setIsInsurance(maintenanceFixturesFittings.getIsInsurance());
        maintenanceFixturesFittingsHistory.setComments(maintenanceFixturesFittings.getComments());
        maintenanceFixturesFittingsHistory.setUserId(maintenanceFixturesFittings.getUserId());
        maintenanceFixturesFittingsHistory.setActionTime(maintenanceFixturesFittings.getActionTime());
        maintenanceFixturesFittingsHistory.setUpdateUser(userId);
        maintenanceFixturesFittingsHistory.setUpdateTime(date);
        maintenanceFixturesFittingsHistoryRepository.save(maintenanceFixturesFittingsHistory);

    }


}
