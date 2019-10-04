package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.MaintenanceFixturesFittings;
import com.msd.fixAssetRegister.model.MaintenanceFixturesFittingsHistory;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooksHistory;
import com.msd.fixAssetRegister.repository.MaintenanceFixturesFittingsHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceLibraryBooksHistoryRepository;
import com.msd.fixAssetRegister.repository.MaintenanceLibraryBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class MaintenanceLibraryBooksHistoryService {

    @Autowired
    MaintenanceLibraryBooksHistoryRepository maintenanceLibraryBooksHistoryRepository;

    @Transactional
    public void updateHistory(MaintenanceLibraryBooks maintenanceLibraryBooks, int status, int userId) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        MaintenanceLibraryBooksHistory maintenanceLibraryBooksHistory = new MaintenanceLibraryBooksHistory();
        maintenanceLibraryBooksHistory.setLibraryBookId(maintenanceLibraryBooks.getLibraryBookId());
        maintenanceLibraryBooksHistory.setAssetId(maintenanceLibraryBooks.getAssetId());
        maintenanceLibraryBooksHistory.setAuthor(maintenanceLibraryBooks.getAuthor());
        maintenanceLibraryBooksHistory.setEditors(maintenanceLibraryBooks.getEditors());
        maintenanceLibraryBooksHistory.setPlaceOfPublication(maintenanceLibraryBooks.getPlaceOfPublication());
        maintenanceLibraryBooksHistory.setPublisher(maintenanceLibraryBooks.getPublisher());
        maintenanceLibraryBooksHistory.setDateOfPublication(maintenanceLibraryBooks.getDateOfPublication());
        maintenanceLibraryBooksHistory.setSeriesNo(maintenanceLibraryBooks.getSeriesNo());
        maintenanceLibraryBooksHistory.setEdition(maintenanceLibraryBooks.getEdition());
        maintenanceLibraryBooksHistory.setIsbmNo(maintenanceLibraryBooks.getIsbmNo());
        maintenanceLibraryBooksHistory.setContents(maintenanceLibraryBooks.getContents());
        maintenanceLibraryBooksHistory.setComments(maintenanceLibraryBooks.getComments());
        maintenanceLibraryBooksHistory.setUserId(maintenanceLibraryBooks.getUserId());
        maintenanceLibraryBooksHistory.setActionTime(maintenanceLibraryBooks.getActionTime());
        maintenanceLibraryBooksHistoryRepository.save(maintenanceLibraryBooksHistory);

    }

}
