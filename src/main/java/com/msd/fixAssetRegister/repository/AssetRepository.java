package com.msd.fixAssetRegister.repository;

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    @Query(value = "SELECT a1.asId FROM Asset AS a1 WHERE a1.locationMaster.sectionMaster.departmentMaster.depId = ?1")
    List<Integer> findAssetsInDepatment(int depId);

    @Query(value = "SELECT dm FROM Asset as dm WHERE  dm.asCode = ?1")
    Asset getAssetCode(String assetCode);

    @Query(value = "SELECT dm FROM Asset as dm WHERE  dm.transactionNo = ?1 group by dm.transactionNo")
    Asset getAssetTrNo(String trNo);

    @Query(value = "SELECT dm FROM Asset dm WHERE dm.assetCatergoryDetail.dcatId = ?1")
    List<Asset> getAssetByDetailCat(int detailCat);

    @Query(value = "SELECT dm FROM Asset dm WHERE dm.analysisCode = ?1")
    List<Asset> getAsset(String anaCode);

//    @Query(value = "SELECT nullif( MAX(ass.transactionNo),0) FROM Asset ass")
//    Object getMaxTransactionNo();

    @Query(value = "SELECT TRIM(LEADING '0' FROM SUBSTRING(ass.transactionNo,3,6)) FROM Asset ass ORDER BY ass.transactionNo DESC")
    List<String> getMaxTransactionNo();

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.asDisposed= ?1 order by ast.asId ")
    List<Asset> findByAsDisposedNot(Boolean isDiposal);

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId = ?1 and ast.asDisposed= ?2 order by ast.asId ")
    List<Asset> findByAsDisposedNot(int branch, Boolean isDiposal);

    List<Asset> findByAsCodeStartingWith(String mainCode);

    List<Asset> findByTransactionNo(int trNo);

    @Query(value = "SELECT ass.assetCatergoryDetail.dcatId,ass.asCode,ass.asDes FROM Asset ass WHERE ass.locationMaster.locId = ?1 GROUP BY ass.assetCatergoryDetail.dcatId")
    List<Asset> findByLocation(int locId);

    @Query(value = "SELECT ass.assetCatergoryDetail.dcatId,ass.asCode,ass.asDes FROM Asset ass WHERE ass.locationMaster.locId = ?1 and ass.asCode like ?2% GROUP BY ass.assetCatergoryDetail.dcatId")
    List<Asset> findByLocation(int locId,String assetCode);

    @Query(value = "SELECT ast FROM Asset AS ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId = ?1 AND ast.asDisposed =?2 GROUP BY ast.asId")
    List<Asset> findAssetsByDepatment1(int depId, Boolean isDisposal);

    @Query(value = "SELECT ast FROM Asset AS ast WHERE ast.assetCatergoryDetail.dcatId = ?1 AND ast.asDisposed =?2 GROUP BY ast.asId")
    List<Asset> findAssetsByDepatment2(int detailId, boolean b);

    @Query(value = "SELECT ast FROM Asset AS ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId = ?1 AND ast.assetCatergoryDetail.dcatId = ?2 AND ast.asDisposed =?3 GROUP BY ast.asId")
    List<Asset> findAssetsByDepatment3(int depId, int detailId, boolean b);

    @Query(value = "SELECT ast FROM Asset AS ast WHERE ast.purDate BETWEEN ?1 AND ?2 AND ast.asDisposed =?3 GROUP BY ast.asId")
    List<Asset> findAssetsByDepatment4(Date fromDate, Date toDate, boolean b);

    @Query(value = "SELECT ast FROM Asset AS ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.purDate BETWEEN ?2 AND ?3 AND  ast.asDisposed =?4 GROUP BY ast.asId")
    List<Asset> findAssetsByDepatment5(int depId, Date fromDate, Date toDate, Boolean isDisposal);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset AS ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId = ?1 AND ast.asDisposed = ?2 GROUP BY ast.asId")
    List<Asset> getAssetsByDepatment(int depId, Boolean isDisposal);

    @Query(value = "SELECT ast FROM Asset ast GROUP BY ast.transactionNo")
    List<Asset> findAssetByTranactionNo();

    @Query(value = "SELECT ast FROM Asset ast where ast.locationMaster.sectionMaster.departmentMaster.depId = ?1")
    List<Asset> findAll(int branch);

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId = ?1 GROUP BY ast.transactionNo ORDER BY ast.transactionNo DESC")
    List<Asset> findAssetByTransactionNoDESC(int branch);

    @Query(value = "SELECT ast FROM Asset ast GROUP BY ast.transactionNo ORDER BY ast.transactionNo DESC")
    List<Asset> findAssetByTransactionNoDESC();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast WHERE ast.isLeasing=?1 AND ast.asDisposed= false ")
    List getAssetByLease(int number);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast WHERE ast.isLeasing=?1 AND ast.locationMaster.sectionMaster.departmentMaster.depId =?2 and ast.asDisposed= false")
    List getAssetByLease(int number, int branch);

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.currencyType=?1")
    List<Asset> findAssetsByCurrencyType(int currencyType);

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.asId=?1 and ast.asDisposed= false")
    Asset getAsset(int asId);

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.asId=?1 ")
    Asset getAllAsset(int asId);

    @Query(value = "SELECT ast FROM Asset ast WHERE ast.purDate < ?1")
    List<Asset> findByPurDate(Date toDate);

    @Query(value = "SELECT COUNT(asId) FROM Asset")
    int getNoOfAssets();

    @Query(value = "SELECT COUNT(ast.asId) FROM Asset ast WHERE ast.locationMaster.sectionMaster.departmentMaster.depId=?1")
    int getNoOfAssets(int branch);

    @Modifying
    @Transactional
    @Query(value = "update Asset ast set ast.qrPath =?1 where ast.asId=?2 ")
    int saveAssetsQr(String Qrpath, int assetId);


    @Query(value = "SELECT ast.asCode FROM Asset ast WHERE ast.asId =?1")
    String getAssetCodes(int assetId);

    @Query(value = "SELECT ast.unitPrice FROM Asset ast WHERE ast.asCode =?1")
    double getAssetUnitPrice(String assetId);


    //Asset search for maintain by id
    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLand=true")
    List<Asset> getAssetByTypeLand();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLand=true")
    List<Asset> getAssetByTypeLand(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true")
    List<Asset> getAssetByTypeBuilding();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true")
    List<Asset> getAssetByTypeBuilding(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true")
    List<Asset> getAssetByTypeVehicle();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true")
    List<Asset> getAssetByTypeVehicle(int branch);

    @Query(value = "SELECT ast  FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true")
    List<Asset> getAssetByTypeComputer();

    @Query(value = "SELECT ast  FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true")
    List<Asset> getAssetByTypeComputer(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true")
    List<Asset> getAssetByTypePlantMachinary();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true")
    List<Asset> getAssetByTypePlantMachinary(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true")
    List<Asset> getAssetByTypeFurniture();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true")
    List<Asset> getAssetByTypeFurniture(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true")
    List<Asset> getAssetByTypeOfficeEquipment();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true")
    List<Asset> getAssetByTypeOfficeEquipment(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true")
    List<Asset> getAssetByTypeLabEquipment();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true")
    List<Asset> getAssetByTypeLabEquipment(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true")
    List<Asset> getAssetByTypeTeachingEquipment();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true")
    List<Asset> getAssetByTypeTeachingEquipment(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true")
    List<Asset> getAssetByTypeFixtures();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true")
    List<Asset> getAssetByTypeFixtures(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true")
    List<Asset> getAssetByTypeLibraryBooks();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true")
    List<Asset> getAssetByTypeLibraryBooks(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true")
    List<Asset> getAssetByTypeSportEquipment();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where   ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true")
    List<Asset> getAssetByTypeSportEquipment(int branch);

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true")
    List<Asset> getAssetByTypeSoftware();

    @Query(value = "SELECT ast FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true")
    List<Asset> getAssetByTypeSoftware(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLand as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeLandInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLand as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeLandInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLandBuliding as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeBuildingInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLandBuliding as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeBuildingInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceVehicle as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeVehicleInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceVehicle as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeVehicleInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceComputer as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeComputerInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceComputer as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeComputerInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenancePlantMachinary as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypePlantInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenancePlantMachinary as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypePlantInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFurniture as ass where ast.asId=ass.assetId and ass.isInsurnce=1")
    List<Asset> getAssetByTypeFurnitureInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFurniture as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurnce=1")
    List<Asset> getAssetByTypeFurnitureInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceOfficeEquipment as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeOfficeInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceOfficeEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeOfficeInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLabEquipment as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeLabInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLabEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeLabInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceTeachingEquipment as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeTeachingInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceTeachingEquipment as ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeTeachingInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFixturesFittings as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeFixturesInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFixturesFittings as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeFixturesInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSportEquipment as ass where ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeSportsInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSportEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isInsurance=1")
    List<Asset> getAssetByTypeSportsInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceVehicle as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeVehicleWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceVehicle as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeVehicleWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceComputer as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeComputerWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceComputer as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeComputerWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenancePlantMachinary as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypePlantWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenancePlantMachinary as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypePlantWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFurniture as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeFurnitureWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFurniture as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeFurnitureWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceOfficeEquipment as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeOfficeWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceOfficeEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeOfficeWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLabEquipment as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeLabWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLabEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeLabWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceTeachingEquipment as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeTeachingWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceTeachingEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeTeachingWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFixturesFittings as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeFixturesWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceFixturesFittings as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeFixturesWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSportEquipment as ass where ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeSportsWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSportEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isWarranty=1")
    List<Asset> getAssetByTypeSportsWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenancePlantMachinary as ass where ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypePlantServiceAgree();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenancePlantMachinary as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypePlantServiceAgree(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceOfficeEquipment as ass where ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeOfficeServiceAgree();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceOfficeEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeOfficeServiceAgree(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLabEquipment as ass where ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeLabServiceAgree();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLabEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeLabServiceAgree(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceTeachingEquipment as ass where ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeTeachingServiceAgree();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceTeachingEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeTeachingServiceAgree(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSportEquipment as ass where ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeSportsServiceAgree();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSportEquipment as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeSportsServiceAgree(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSoftware as ass where ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeSoftwareServiceAgree();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceSoftware as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId and ass.isServiceAgre=1")
    List<Asset> getAssetByTypeSoftwareServiceAgree(int branch);

    // maintain transaction report
    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceInsurance as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByInsurance();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceInsurance as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByInsurance(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceWarranty as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByWarranty();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceWarranty as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByWarranty(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceServiceAgreements as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByServiceAgreement();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceServiceAgreements as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByServiceAgreement(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceModification as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByModification();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceModification as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByModification(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceMaintenanceData as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByMaintenanceData();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceMaintenanceData as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByMaintenanceData(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceRunningData as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByRunningData();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceRunningData as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByRunningData(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLeaseAsset as ass where ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByLeaseAsset();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset as ast, MaintenanceLeaseAsset as ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
    List<Asset> getAssetByLeaseAsset(int branch);


    // maintain master listing by id
    @Query(value = "select ast.asId,ast.asCode,ast.asDes FROM Asset ast where ast.asCode like ?1% and ast.asDisposed = false order by ast.asId ")
    List<Asset> loadAssetByAssetCode(String type);

    @Query(value = "select ast.asId,ast.asCode,ast.asDes FROM Asset ast where ast.asCode like ?1% and ast.asDisposed = false AND ast.locationMaster.sectionMaster.departmentMaster.depId=?2 order by ast.asId ")
    List<Asset> loadAssetByAssetCode(String type, int branch);

    @Query(value = "select ast.asId,ast.asCode,ast.asDes FROM Asset ast where ast.asDisposed = false order by ast.asId")
    List<Asset> loadAssetByAssetCode();

    @Query(value = "select ast.asId,ast.asCode,ast.asDes FROM Asset ast where ast.asDisposed = false and ast.locationMaster.sectionMaster.departmentMaster.depId=?1 order by ast.asId ")
    List<Asset> loadAssetByAssetCode(int branch);

    @Query(value = "select ast.asId,ast.asCode,ast.asDes,ast.transactionNo FROM Asset ast where ast.asDisposed = false group by ast.transactionNo order by ast.transactionNo")
    List<Asset> loadAssetByTrNo();

    @Query(value = "select ast.asId,ast.asCode,ast.asDes,ast.transactionNo FROM Asset ast where ast.asDisposed = false and ast.locationMaster.sectionMaster.departmentMaster.depId=?1 order by ast.transactionNo ")
    List<Asset> loadAssetByTrNo(int branch);

    @Query(value = "select ast.asId,ast.asCodeOld,ast.asDes FROM Asset ast where ast.asDisposed = false order by ast.asId")
    List<Asset> loadAssetByOldAssetCode();

    @Query(value = "select ast.asId,ast.asCodeOld,ast.asDes FROM Asset ast where ast.asDisposed = false and ast.locationMaster.sectionMaster.departmentMaster.depId=?1 order by ast.asId ")
    List<Asset> loadAssetByOldAssetCode(int branch);

//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, MaintenanceLand ass where  ast.asId=ass.assetId order by ast.asId")
//    List<Asset> getAssetByLand();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, MaintenanceLand ass where ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asId=ass.assetId order by ast.asId")
//    List<Asset> getAssetByLand(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLand=true order by ast.asId")
    List<Asset> getAssetByLand();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLand=true order by ast.asId")
    List<Asset> getAssetByLand(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLand=true order by ast.asId")
    List<Asset> getAssetByLand(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLand=true order by ast.asId")
    List<Asset> getAssetByLand(int branch, String assetCodeIdval);

//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true order by ast.asId")
//    List<Asset> getAssetByBuilding();
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true order by ast.asId")
//    List<Asset> getAssetByBuilding(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true order by ast.asId")
    List<Asset> getAssetByBuilding();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true order by ast.asId")
    List<Asset> getAssetByBuilding(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true order by ast.asId")
    List<Asset> getAssetByBuilding(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLandBuilding=true order by ast.asId")
    List<Asset> getAssetByBuilding(int branch, String assetCodeIdval);


//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true order by ast.asId")
//    List<Asset> getAssetByVehicle();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.getAssetByVehicle=true order by ast.asId")
//    List<Asset> getAssetByVehicle(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true order by ast.asId")
    List<Asset> getAssetByVehicle();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true order by ast.asId")
    List<Asset> getAssetByVehicle(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true order by ast.asId")
    List<Asset> getAssetByVehicle(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeVehicle=true order by ast.asId")
    List<Asset> getAssetByVehicle(int branch, String assetCodeIdval);
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true order by ast.asId")
//    List<Asset> getAssetByComputer();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true order by ast.asId")
//    List<Asset> getAssetByComputer(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true order by ast.asId")
    List<Asset> getAssetByComputer();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true order by ast.asId")
    List<Asset> getAssetByComputer(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true order by ast.asId")
    List<Asset> getAssetByComputer(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeComputer=true order by ast.asId")
    List<Asset> getAssetByComputer(int branch, String assetCodeIdval);

//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true order by ast.asId")
//    List<Asset> getAssetByPlantMachinary();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true order by ast.asId")
//    List<Asset> getAssetByPlantMachinary(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true order by ast.asId")
    List<Asset> getAssetByPlantMachinary();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true order by ast.asId")
    List<Asset> getAssetByPlantMachinary(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true order by ast.asId")
    List<Asset> getAssetByPlantMachinary(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typePlantMachinary=true order by ast.asId")
    List<Asset> getAssetByPlantMachinary(int branch, String assetCodeIdval);


//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true order by ast.asId")
//    List<Asset> getAssetByFurniture();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true order by ast.asId")
//    List<Asset> getAssetByFurniture(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true order by ast.asId")
    List<Asset> getAssetByFurniture();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true order by ast.asId")
    List<Asset> getAssetByFurniture(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true order by ast.asId")
    List<Asset> getAssetByFurniture(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFurniture=true order by ast.asId")
    List<Asset> getAssetByFurniture(int branch, String assetCodeIdval);


//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true order by ast.asId")
//    List<Asset> getAssetByOfficeEquipment();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true order by ast.asId")
//    List<Asset> getAssetByOfficeEquipment(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true order by ast.asId")
    List<Asset> getAssetByOfficeEquipment();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true order by ast.asId")
    List<Asset> getAssetByOfficeEquipment(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true order by ast.asId")
    List<Asset> getAssetByOfficeEquipment(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeOfficeEquipment=true order by ast.asId")
    List<Asset> getAssetByOfficeEquipment(int branch, String assetCodeIdval);
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true order by ast.asId")
//    List<Asset> getAssetByLabEquipment();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true order by ast.asId")
//    List<Asset> getAssetByLabEquipment(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true order by ast.asId")
    List<Asset> getAssetByLabEquipment();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true order by ast.asId")
    List<Asset> getAssetByLabEquipment(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true order by ast.asId")
    List<Asset> getAssetByLabEquipment(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLabEquipment=true order by ast.asId")
    List<Asset> getAssetByLabEquipment(int branch, String assetCodeIdval);

//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true order by ast.asId")
//    List<Asset> getAssetByTeachingEquipment();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true order by ast.asId")
//    List<Asset> getAssetByTeachingEquipment(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true order by ast.asId")
    List<Asset> getAssetByTeachingEquipment();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true order by ast.asId")
    List<Asset> getAssetByTeachingEquipment(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true order by ast.asId")
    List<Asset> getAssetByTeachingEquipment(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeTeachingEquipment=true order by ast.asId")
    List<Asset> getAssetByTeachingEquipment(int branch, String assetCodeIdval);

//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true order by ast.asId")
//    List<Asset> getAssetByFixtures();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true order by ast.asId")
//    List<Asset> getAssetByFixtures(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true order by ast.asId")
    List<Asset> getAssetByFixtures();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true order by ast.asId")
    List<Asset> getAssetByFixtures(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true order by ast.asId")
    List<Asset> getAssetByFixtures(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeFixtures=true order by ast.asId")
    List<Asset> getAssetByFixtures(int branch, String assetCodeIdval);

//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true order by ast.asId")
//    List<Asset> getAssetByLibraryBooks();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true order by ast.asId")
//    List<Asset> getAssetByLibraryBooks(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true order by ast.asId")
    List<Asset> getAssetByLibraryBooks();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true order by ast.asId")
    List<Asset> getAssetByLibraryBooks(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true order by ast.asId")
    List<Asset> getAssetByLibraryBooks(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeLibraryBooks=true order by ast.asId")
    List<Asset> getAssetByLibraryBooks(int branch, String assetCodeIdval);

//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true order by ast.asId")
//    List<Asset> getAssetBySportEquipment();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true order by ast.asId")
//    List<Asset> getAssetBySportEquipment(int branch);


    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true order by ast.asId")
    List<Asset> getAssetBySportEquipment();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true order by ast.asId")
    List<Asset> getAssetBySportEquipment(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true order by ast.asId")
    List<Asset> getAssetBySportEquipment(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSportEquipment=true order by ast.asId")
    List<Asset> getAssetBySportEquipment(int branch, String assetCodeIdval);

    @Query(value = "SELECT acs.scatCode from AssetCatergorySub acs where acs.scatId=?1 ")
    String getSubCode(int subId);

//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true order by ast.asId")
//    List<Asset> getAssetBySoftware();
//
//    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true order by ast.asId")
//    List<Asset> getAssetBySoftware(int branch);

    @Query(value = "SELECT ast.transactionNo FROM Asset ast where ast.asId=?1")
    Integer getTransactionNo(int assetId);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true order by ast.asId")
    List<Asset> getAssetBySoftware();

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true order by ast.asId")
    List<Asset> getAssetBySoftware(int branch);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where ast.asCode like ?1% AND  ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true order by ast.asId")
    List<Asset> getAssetBySoftware(String assetCodeIdval);

    @Query(value = "SELECT ast.asId,ast.asCode,ast.asDes FROM Asset ast, AssetTypeAssing ass where  ast.locationMaster.sectionMaster.departmentMaster.depId=?1 AND ast.asCode like ?2% AND ast.assetCatergoryDetail.assetCatergorySub.assetCatergoryMain.mcatCode=ass.mcatId AND ass.typeSoftware=true order by ast.asId")
    List<Asset> getAssetBySoftware(int branch, String assetCodeIdval);

    @Query(value = "SELECT ast FROM Asset ast where ast.asId=?1")
    List<Asset> getAssetDetails(int assetId);

}