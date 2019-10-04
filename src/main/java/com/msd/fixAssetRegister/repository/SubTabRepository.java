package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.ASubtab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTabRepository extends JpaRepository<ASubtab,Integer> {

    @Query(value = "SELECT ass FROM ASubtab AS ass WHERE ass.main_Tab_ID = ?1 AND ass.isActive=true ORDER BY ass.ordertab ASC")
    List<ASubtab> findSubTabs(int main_tab_id);
}
