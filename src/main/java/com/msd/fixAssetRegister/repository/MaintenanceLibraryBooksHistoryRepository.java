package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.MaintenanceLibraryBooks;
import com.msd.fixAssetRegister.model.MaintenanceLibraryBooksHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLibraryBooksHistoryRepository extends JpaRepository<MaintenanceLibraryBooksHistory,Integer> {

}
