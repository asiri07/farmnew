package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AJobDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface JobDefineRepository extends JpaRepository<AJobDefine,Integer> {

    @Query(value = "SELECT aj.main_Tab_Id FROM AJobDefine AS aj WHERE aj.ref_User_Type = ?1 GROUP BY aj.main_Tab_Id")
    List<Integer> findByMainTabList(int userId);

    @Query(value = "SELECT aj.sub_Tab_Id FROM AJobDefine AS aj WHERE aj.main_Tab_Id = ?1 AND aj.ref_User_Type = ?2 AND aj.sub_Tab_Id NOT IN (0) GROUP BY aj.sub_Tab_Id")
    List<Integer> findBySubTabList(int main_tab_id, int userId);

    @Query(value = "SELECT aj.sub_Tab_Sub_Id FROM AJobDefine AS aj WHERE aj.sub_Tab_Id = ?1 AND aj.main_Tab_Id = ?2 AND aj.ref_User_Type = ?3 AND aj.sub_Tab_Sub_Id NOT IN (0) GROUP BY aj.sub_Tab_Sub_Id")
    List<Integer> findBySubTabSubList(int sub_tab_no, int main_tab_id, int userId);


    @Query(value = "SELECT aj.main_Tab_Id FROM AJobDefine AS aj WHERE aj.rer_Branch_Id = ?1 GROUP BY aj.main_Tab_Id")
    List<Integer> findByBranchMainTabList(int userId); // need to complet

    @Query(value = "SELECT aj.sub_Tab_Id FROM AJobDefine AS aj WHERE aj.main_Tab_Id = ?1 AND aj.rer_Branch_Id = ?2 AND aj.sub_Tab_Id NOT IN (0) GROUP BY aj.sub_Tab_Id")
    List<Integer> findByBranchSubTabList(int main_tab_id, int userId); // need to complet

    @Query(value = "SELECT aj.sub_Tab_Sub_Id FROM AJobDefine AS aj WHERE aj.sub_Tab_Id = ?1 AND aj.main_Tab_Id = ?2 AND aj.rer_Branch_Id = ?3 AND aj.sub_Tab_Sub_Id NOT IN (0) GROUP BY aj.sub_Tab_Sub_Id")
    List<Integer> findByBranchSubTabSubList(int sub_tab_no, int main_tab_id, int userId); // need to complet
}
