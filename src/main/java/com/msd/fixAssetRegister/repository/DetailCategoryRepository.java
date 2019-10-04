package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.AssetCatergoryDetail;
import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.msd.fixAssetRegister.model.DepartmentMaster;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCategoryRepository extends JpaRepository<AssetCatergoryDetail,Integer> {

    @Query(value = "SELECT dm FROM AssetCatergoryDetail dm WHERE dm.assetCatergorySub.scatId = ?1")
    List<AssetCatergoryDetail> getDetailCatsBySubCatId(int scatId);

    @Query(value = "SELECT dm FROM AssetCatergoryDetail dm WHERE dm.dcatCode = ?1 and dm.assetCatergorySub.scatId = ?2")
    List<AssetCatergoryDetail> getdetailCatByCode(String detailCode, int subCode);

//    @Query(value = "SELECT dm FROM AssetCatergoryDetail dm WHERE dm.assetCatergorySub.scatId = ?1")
//    List<AssetCatergoryDetail> getDetailCatsBySubId(int subCat);

}


