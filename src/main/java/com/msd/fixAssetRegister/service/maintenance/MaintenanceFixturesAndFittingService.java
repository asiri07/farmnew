package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.MaintenanceComputer;
import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.repository.MaintenanceFixturesFittingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceFixturesAndFittingService  {

    @Autowired
    MaintenanceFixturesFittingsRepository maintenanceFixturesFittingsRepository;

    public MaintenanceFixturesFittings saveUpdateFixturesFitting(MaintenanceFixturesFittings maintenanceFixturesFittings) {
        return maintenanceFixturesFittingsRepository.save(maintenanceFixturesFittings);
    }

    @Transactional
    public MaintenanceFixturesFittings gettFixturesAndFittingsDetails(int assetId) {
        return maintenanceFixturesFittingsRepository.getFixturesAndFittingsDetails(assetId);
    }

    @Transactional
    public MaintenanceFixturesFittings findById(int comId) {
        return maintenanceFixturesFittingsRepository.findById(comId).get();
    }

}
