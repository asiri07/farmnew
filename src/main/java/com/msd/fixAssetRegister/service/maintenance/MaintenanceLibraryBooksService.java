package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.repository.MaintenanceLibraryBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceLibraryBooksService {

    @Autowired
    MaintenanceLibraryBooksRepository maintenanceLibraryBooksRepository;

    public MaintenanceLibraryBooks saveUpdateMaintenanceLibraryBooks(MaintenanceLibraryBooks maintenanceLibraryBooks) {
        return maintenanceLibraryBooksRepository.save(maintenanceLibraryBooks);
    }

    @Transactional
    public MaintenanceLibraryBooks getLibraryBooksDetailsByAsset(int assetId) {
        return maintenanceLibraryBooksRepository.getLibraryBooksDetailsByAsset(assetId);
    }

    @Transactional
    public MaintenanceLibraryBooks findById(int comId) {
        return maintenanceLibraryBooksRepository.findById(comId).get();
    }
}
