package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.ASubtabSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubSubTabRepository extends JpaRepository<ASubtabSub,Integer> {

    @Query(value = "SELECT ss FROM ASubtabSub AS ss WHERE ss.main_Tab_ID= ?1 AND ss.sub_Tab_ID = ?2 AND ss.isActive = true ORDER BY ss.ordertab ASC ")
    List<ASubtabSub> findSubSubTabs(int main_tab_id, int sub_tab_no);
}
