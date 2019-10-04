/*
 *
 *       Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *       *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *         This software product contains information which is proprietary to
 *          and considered a trade secret MsSoftIT Solution .
 *          It is expressly agreed that it shall not be reproduced in whole or part,
 *          disclosed, divulged or otherwise made available to any third party directly
 *          or indirectly.  Reproduction of this product for any purpose is prohibited
 *          without written authorisation from the The MsSoftIT Solution
 *          All Rights Reserved.
 *
 *          E-Mail mssoftit@gmail.com
 *          URL : mssoftit.lk
 *          Created By : Mahendra Sri Dayarathna
 *
 */
package com.msd.fixAssetRegister.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.AssetDetailRepository;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AssetService {

    private static final Logger logger = LoggerFactory.getLogger(AssetService.class);
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    AssetDetailRepository assetDetailRepository;
    @Value("${application.qrPath}")
    private String qrPath;
    @Value("${application.photoPath}")
    private String photoPath;

    @Transactional
    public Boolean saveAssetDetails(Asset asset, MultipartFile file) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        List<Asset> assets = new ArrayList<>();
        String fileName = getFilePath(file);
        for (int i = 0; i < asset.getNoOfUnit(); i++) {
            Asset newAsset = new Asset();
            String qrCodeName = genarateAssetCode(asset.getAsCode(), i);
            newAsset.setAsCode(qrCodeName);
            newAsset.setActionTime(date);
            newAsset.setAsCodeOld(asset.getAsCodeOld());
            newAsset.setAsDes(asset.getAsDes());
            newAsset.setAnalysisCode(asset.getAnalysisCode());
            newAsset.setAuthPerson(asset.getAuthPerson());
            newAsset.setManufacturer(asset.getManufacturer());
            newAsset.setOriginalCost(asset.getOriginalCost());
            newAsset.setFundingSource(asset.getFundingSource());
            //newAsset.setDepRate(asset.getDepRate());
            newAsset.setLedgerCode(asset.getLedgerCode());
            newAsset.setLifeTime(asset.getLifeTime());
            newAsset.setManRegNo(asset.getManRegNo());
            newAsset.setNoOfUnit(asset.getNoOfUnit());
            newAsset.setPurDate(asset.getPurDate());
            newAsset.setRegDate(asset.getRegDate());
            newAsset.setUnitPrice(asset.getUnitPrice());
            newAsset.setAssetCatergoryDetail(asset.getAssetCatergoryDetail());
            newAsset.setPoNo(asset.getPoNo());
            newAsset.setPoDate(asset.getPoDate());
            newAsset.setGrnNo(asset.getGrnNo());
            newAsset.setGrnDate(asset.getGrnDate());
            newAsset.setIssueNoteNo(asset.getIssueNoteNo());
            newAsset.setIssueNoteDate(asset.getIssueNoteDate());
            newAsset.setSuppliersInvoiceNo(asset.getSuppliersInvoiceNo());
            newAsset.setInvoiceDate(asset.getInvoiceDate());
            newAsset.setSupplierName(asset.getSupplierName());
            newAsset.setAddress(asset.getAddress());
            newAsset.setIsLeasing(asset.getIsLeasing());
            newAsset.setTelephoneNo(asset.getTelephoneNo());
            newAsset.setTransactionNo(asset.getTransactionNo());
            newAsset.setCurrencyType(asset.getCurrencyType());
            newAsset.setQrPath(generateQRCode(qrCodeName));
            newAsset.setFilePath(fileName);
            newAsset.setLocationMaster(asset.getLocationMaster());
            assets.add(newAsset);
        }
        List<Asset> assetList = assetRepository.saveAll(assets);
        if (!assetList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean saveQrPath() {
        int no = 0;
        List<Asset> subList = assetRepository.findAll();
        for (Asset asset : subList) {
            String qrCodeName = generateQRCode(asset.getAsCode());
            no = assetRepository.saveAssetsQr(qrCodeName, asset.getAsId());
        }
        if (no == 1) {
            return true;
        }
        return false;
    }

    @Transactional
    public List<Asset> getAll(int branch) {
        if (branch == 0) {
            return assetRepository.findAll();
        } else {
            return assetRepository.findAll(branch);
        }

    }

    private String generateQRCode(String asCode) {
        Path path = null;
        try {
            File directory = new File(qrPath);
            if (!directory.exists()) {
                directory.mkdir();
                if (logger.isDebugEnabled()) {
                    logger.info("Path is Created" + qrPath);
                }
            }
            QRCodeWriter qrCodeWriter;
            qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(asCode, BarcodeFormat.QR_CODE, 350, 350);

            path = FileSystems.getDefault().getPath(qrPath + "/" + asCode + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            if (logger.isDebugEnabled()) {
                logger.info(" Created File" + asCode);
            }
        } catch (WriterException | IOException e) {
            logger.error("Error occurred while calling the generateQRCode Method : " + e.getMessage());
        }
        return path.toString();
    }

    private String getFilePath(MultipartFile file) {
        Path path = null;
        if (file.isEmpty()) {
            return "";
        } else {

            try {
                String date = simpleDateFormat.format(new Date());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String time = timestamp.getTime() + "";
                byte[] bytes = file.getBytes();
                path = Paths.get(photoPath + date + " " + time + " " + file.getOriginalFilename());
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.toString();
    }

    @Transactional
    public int generateTransactionNo() {
//        int maxNo = 0;
//        Object object = assetRepository.getMaxTransactionNo();
//        if (object != null) {
//            maxNo = (int) object;
//        }
//        return ++maxNo;

        int rowCount =assetRepository.getNoOfAssets();
        int maxNoInt;
        if(rowCount==0){
            maxNoInt=0;
        }else{
            List<String> numbers = assetRepository.getMaxTransactionNo();
            String maxNoString=numbers.get(0);

            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;

    }

    private String genarateAssetCode(String asCode, int j) {
        String assetCode = "";
        try {
            String no = asCode.substring(asCode.length() - 5);
            int serial = Integer.parseInt(no);
            if (j != 0) {
                serial = serial + j;
            }
            String newSerial = String.valueOf(serial);
            int length = no.length() - newSerial.length();
            String zo = "";
            for (int i = 0; i < length; i++) {
                zo += "0";
            }
            zo = zo + newSerial;
            assetCode = asCode.replace(no, zo);
        } catch (Exception e) {
        }
        return assetCode;
    }

    @Transactional
    public List<Asset> getAssetList(int branch) {
        if (branch == 0) {
            return assetRepository.findByAsDisposedNot(false);
        } else {
            return assetRepository.findByAsDisposedNot(branch, false);
        }
    }

    @Transactional
    public List<Asset> getAssetByTransactionNo(int branch) {
        if (branch == 0) {
            return assetRepository.findAssetByTransactionNoDESC();
        } else {
            return assetRepository.findAssetByTransactionNoDESC(branch);
        }
    }

    public List<Asset> getAssetInsuranceList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByInsurance();
        } else {
            return assetRepository.getAssetByInsurance(branch);
        }
    }

    public List<Asset> getAssetWarantyList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByWarranty();
        } else {
            return assetRepository.getAssetByWarranty(branch);
        }
    }

    public List<Asset> getAssetServiceAgreementList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByServiceAgreement();
        } else {
            return assetRepository.getAssetByServiceAgreement(branch);
        }
    }

    public List<Asset> getAssetModificationList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByModification();
        } else {
            return assetRepository.getAssetByModification(branch);
        }
    }

    public List<Asset> getAssetMaintenanceDataList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByMaintenanceData();
        } else {
            return assetRepository.getAssetByMaintenanceData(branch);
        }
    }

    public List<Asset> getAssetRunningDataList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByRunningData();
        } else {
            return assetRepository.getAssetByRunningData(branch);
        }
    }

    public List<Asset> getAssetLeaseAssetList(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByLeaseAsset();
        } else {
            return assetRepository.getAssetByLeaseAsset(branch);
        }
    }

    @Transactional
    public String getAssetAlocation(int assetId) {
        String allocations = "";
        Asset assetDetail = assetRepository.findById(assetId).get();
        if (assetDetail != null) {
            String companyCode = assetDetail.getLocationMaster().getSectionMaster().getDepartmentMaster().getCompanyMaster().getComCode();
            String departmentCode = assetDetail.getLocationMaster().getSectionMaster().getDepartmentMaster().getDepCode();
            String sectionCode = assetDetail.getLocationMaster().getSectionMaster().getSecCode();
            String locationCode = assetDetail.getLocationMaster().getLocCode();
            allocations = companyCode + "-" + departmentCode + "-" + sectionCode + "-" + locationCode;
        }
        return allocations;
    }

    @Transactional
    public String getMaxSerialNo(int code, int maxChar) {
        String no = "";
        try {
            List<Asset> assets = assetRepository.getAssetByDetailCat(code);
            if (assets.size() > 0) {
                Asset last = assets.get(assets.size() - 1);
                String assCode = last.getAsCode();
                //String[] part = assCode.split("(?<=\\D)(?=\\d)");
                no = assCode.substring(assCode.length() - maxChar);
            }
        } catch (Exception e) {
        }
        return no;
    }


    @Transactional
    public List<Asset> getAssetByMainCode(String mainCode) throws Exception {
        return assetRepository.findByAsCodeStartingWith(mainCode);
    }

    @Transactional
    public Asset getAsset(int assetId) {
        return assetRepository.findById(assetId).get();
    }
    @Transactional
    public List<Asset> getAssetDetals(int assetId) {
        return assetRepository.getAssetDetails(assetId);
    }

    @Transactional
    public String getSubCode(int subId) {
        return assetRepository.getSubCode(subId);
    }

    @Transactional
    public Asset getAssetCodes(String assetId) {
        return assetRepository.getAssetCode(assetId);
    }

    @Transactional
    public Asset getAssetTrNo(String trNo) {
        return assetRepository.getAssetTrNo(trNo);
    }

    @Transactional
    public LocationMaster getAssetLocation(int assetId) {
        return assetRepository.findById(assetId).get().getLocationMaster();
    }

    @Transactional
    public int deleteAssets(int trNo) {
        int isDelete = 0;
        List<Asset> assets = assetRepository.findByTransactionNo(trNo);
        for (Asset asset : assets) {
            if (!asset.getAsDamage() && !asset.getAsDisposed() && !asset.getAsTransfer()) {
                isDelete = 1;
            } else {
                isDelete = 2;
                break;
            }
        }
        if (isDelete == 1) {
            for (Asset asset : assets) {
                assetRepository.delete(asset);
            }
        }
        return isDelete;
    }

//    @Transactional
//    public AssetLocationDetail getCurrentssetLocation(int assertId) {
//        return assetDetailDao.getAssetLOcation(assertId);
//    }

//    @Transactional
//    public List<AssetLocationDetail> getAssetListByLOcation(int locId) {
//        return assetDetailDao.getAssetListByLOcation(locId);
//    }

    @Transactional
    public List<Listing> getAssetListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<Asset> subList = assetRepository.findAll();
        for (Asset catergorySub : subList) {
            Listing listing = new Listing();
            listing.setListingId(catergorySub.getAsId());
            listing.setListingName(catergorySub.getAsCode());
            listing.setDescription(catergorySub.getAsDes());
            listing.setListingType(8);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getAssetCode(String fromCode) {
        String code = "";
        Asset asset = assetRepository.findById(Integer.parseInt(fromCode)).get();
        if (asset != null) {
            code = asset.getAsCode();
        }
        return code;
    }

    @Transactional
    public List<Asset> getAssetDetails(int locId) {
        return assetRepository.findByLocation(locId);
    }

//    @Transactional
//    public List<Asset> getAssetDetailsForAssetRegister(int assetId) {
//        return assetRepository.getAssetDetails(assetId);
//    }

    @Transactional
    public List<Asset> getAssetDetailsSearch(int locId, String assetCode) {
        return assetRepository.findByLocation(locId, assetCode);
    }


////    @Transactional
////    public List<AssetLocationDetail> getCurrentAssetLocationList() {
////        return assetDao.getCurrentAssetLocationList();
//
//    }

    @Transactional
    public List<Asset> getAssetsByDepatment(int depId, int detailId, String fromDate, String toDate) {
        try {
            if (depId > 0 && detailId == 0) {
                return assetRepository.findAssetsByDepatment5(depId, simpleDateFormat.parse(fromDate), simpleDateFormat.parse(toDate), false);
            } else if (detailId > 0 && depId == 0) {
                return assetRepository.findAssetsByDepatment2(detailId, false);
            } else if (depId > 0 && detailId > 0) {
                return assetRepository.findAssetsByDepatment3(depId, detailId, false);
            } else {
                return assetRepository.findAssetsByDepatment4(simpleDateFormat.parse(fromDate), simpleDateFormat.parse(toDate), false);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Asset> getAssetsByDepatment(int depId, int detailId) {
        try {
            if (depId > 0 && detailId == 0) {
                return assetRepository.findAssetsByDepatment1(depId, false);
            } else if (detailId > 0 && depId == 0) {
                return assetRepository.findAssetsByDepatment2(detailId, false);
            } else {
                return assetRepository.findAssetsByDepatment3(depId, detailId, false);
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Asset> getAssetsByDepatment(int depId) {
        return assetRepository.getAssetsByDepatment(depId, false);
    }

    @Transactional
    public List<Asset> findAssetsByTransactionNo() {
        return assetRepository.findAssetByTranactionNo();
    }

    @Transactional
    public List<String> getAssetByLease(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByLease(1);
        } else {
            return assetRepository.getAssetByLease(1, branch);
        }
    }

    @Transactional
    public List<Asset> getAssetByDetailCat(int detailCat) {
        return assetRepository.getAssetByDetailCat(detailCat);
    }

    @Transactional
    public Asset findAssetByTransactionNo(int transactionNo) {
        return assetRepository.findByTransactionNo(transactionNo).get(0);
    }

    @Transactional
    public String getAssetCodes(int assetId) {
        return assetRepository.getAssetCodes(assetId);
    }

    public int getNoOfAssets(int branch) {
        if (branch == 0) {
            return assetRepository.getNoOfAssets();
        } else {
            return assetRepository.getNoOfAssets(branch);
        }

    }

    //Asset search for maintain by id
    public List<Asset> getAssetByTypeLand(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLand();
        } else {
            return assetRepository.getAssetByTypeLand(branch);
        }
    }

    public List<Asset> loadAssetByAssetCode(String type, int branch) {// only not disposed assets
        if (branch == 0) {
            return assetRepository.loadAssetByAssetCode(type);
        } else {
            return assetRepository.loadAssetByAssetCode(type, branch);
        }
    }

    public List<Asset> loadAssetByAssetCode(int branch) {// only not disposed assets
        if (branch == 0) {
            return assetRepository.loadAssetByAssetCode();
        } else {
            return assetRepository.loadAssetByAssetCode(branch);
        }
    }

    public List<Asset> loadAssetByTrNo(int branch) {// only not disposed assets tr No
        if (branch == 0) {
            return assetRepository.loadAssetByTrNo();
        } else {
            return assetRepository.loadAssetByTrNo(branch);
        }
    }

    public List<Asset> loadAssetByOldAssetCode(int branch) {// only not disposed assets
        if (branch == 0) {
            return assetRepository.loadAssetByOldAssetCode();
        } else {
            return assetRepository.loadAssetByOldAssetCode(branch);
        }

    }

//    Load non disposed asset for Maintenance

    public List<Asset> getAssetByType(int branch, int type) {
        if (type == 1) { // Land
            if (branch == 0) {
                return assetRepository.getAssetByLand();
            } else {
                return assetRepository.getAssetByLand(branch);
            }
        } else if (type == 2) { // Land & Building
            if (branch == 0) {
                return assetRepository.getAssetByBuilding();
            } else {
                return assetRepository.getAssetByBuilding(branch);
            }
        } else if (type == 3) { // Vehicle
            if (branch == 0) {
                return assetRepository.getAssetByVehicle();
            } else {
                return assetRepository.getAssetByVehicle(branch);
            }
        } else if (type == 4) { // Computer
            if (branch == 0) {
                return assetRepository.getAssetByComputer();
            } else {
                return assetRepository.getAssetByComputer(branch);
            }
        } else if (type == 5) { // Plant & Machinary
            if (branch == 0) {
                return assetRepository.getAssetByPlantMachinary();
            } else {
                return assetRepository.getAssetByPlantMachinary(branch);
            }
        } else if (type == 6) { // Furniture
            if (branch == 0) {
                return assetRepository.getAssetByFurniture();
            } else {
                return assetRepository.getAssetByFurniture(branch);
            }
        } else if (type == 7) { // Office Equipment
            if (branch == 0) {
                return assetRepository.getAssetByOfficeEquipment();
            } else {
                return assetRepository.getAssetByOfficeEquipment(branch);
            }
        } else if (type == 8) { // LAB Equipment
            if (branch == 0) {
                return assetRepository.getAssetByLabEquipment();
            } else {
                return assetRepository.getAssetByLabEquipment(branch);
            }
        } else if (type == 9) { // Teaching Equipment
            if (branch == 0) {
                return assetRepository.getAssetByTeachingEquipment();
            } else {
                return assetRepository.getAssetByTeachingEquipment(branch);
            }
        } else if (type == 10) { // Fixtures & Fittings
            if (branch == 0) {
                return assetRepository.getAssetByFixtures();
            } else {
                return assetRepository.getAssetByFixtures(branch);
            }
        } else if (type == 11) { // Library Books
            if (branch == 0) {
                return assetRepository.getAssetByLibraryBooks();
            } else {
                return assetRepository.getAssetByLibraryBooks(branch);
            }
        } else if (type == 12) { // Sport Equipment
            if (branch == 0) {
                return assetRepository.getAssetBySportEquipment();
            } else {
                return assetRepository.getAssetBySportEquipment(branch);
            }
        } else if (type == 13) { // Software
            if (branch == 0) {
                return assetRepository.getAssetBySoftware();
            } else {
                return assetRepository.getAssetBySoftware(branch);
            }
        } else {
            return null;
        }

    }


    //    public List<Asset> getAssetByFurniture(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetByFurniture();
//        } else {
//            return assetRepository.getAssetByFurniture(branch);
//        }
//    }
    public List<Asset> getAssetByFurniture(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByFurniture(assetCodeIdval);
            } else {
                return assetRepository.getAssetByFurniture();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByFurniture(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetByFurniture(branch);
            }
        }
    }

    public List<Asset> getAssetByTypeOfficeEquipment(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeOfficeEquipment();
        } else {
            return assetRepository.getAssetByTypeOfficeEquipment(branch);
        }
    }

//    public List<Asset> getAssetByOfficeEquipment(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetByOfficeEquipment();
//        } else {
//            return assetRepository.getAssetByOfficeEquipment(branch);
//        }
//    }

    public List<Asset> getAssetByOfficeEquipment(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByOfficeEquipment(assetCodeIdval);
            } else {
                return assetRepository.getAssetByOfficeEquipment();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByOfficeEquipment(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetByOfficeEquipment(branch);
            }
        }
    }

    public List<Asset> getAssetByTypeLabEquipment(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLabEquipment();
        } else {
            return assetRepository.getAssetByTypeLabEquipment(branch);
        }
    }

//    public List<Asset> getAssetByLabEquipment(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetByLabEquipment();
//        } else {
//            return assetRepository.getAssetByLabEquipment(branch);
//        }
//    }

    public List<Asset> getAssetByLabEquipment(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByLabEquipment(assetCodeIdval);
            } else {
                return assetRepository.getAssetByLabEquipment();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByLabEquipment(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetByLabEquipment(branch);
            }
        }
    }

    public List<Asset> getAssetByTypeTeachingEquipment(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeTeachingEquipment();
        } else {
            return assetRepository.getAssetByTypeTeachingEquipment(branch);
        }
    }

    public List<Asset> getAssetByTeachingEquipment(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByTeachingEquipment(assetCodeIdval);
            } else {
                return assetRepository.getAssetByTeachingEquipment();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByTeachingEquipment(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetByTeachingEquipment(branch);
            }
        }
    }

//    public List<Asset> getAssetByTeachingEquipment(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetByTeachingEquipment();
//        } else {
//            return assetRepository.getAssetByTeachingEquipment(branch);
//        }
//    }

    public List<Asset> getAssetByTypeFixtures(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeFixtures();
        } else {
            return assetRepository.getAssetByTypeFixtures(branch);
        }
    }
//
//    public List<Asset> getAssetByFixtures(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetByFixtures();
//        } else {
//            return assetRepository.getAssetByFixtures(branch);
//        }
//    }

    public List<Asset> getAssetByFixtures(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByFixtures(assetCodeIdval);
            } else {
                return assetRepository.getAssetByFixtures();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByFixtures(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetByFixtures(branch);
            }
        }
    }

    public List<Asset> getAssetByTypeLibraryBooks(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLibraryBooks();
        } else {
            return assetRepository.getAssetByTypeLibraryBooks(branch);
        }
    }

    //    public List<Asset> getAssetByLibraryBooks(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetByLibraryBooks();
//        } else {
//            return assetRepository.getAssetByLibraryBooks(branch);
//        }
//    }
    public List<Asset> getAssetByLibraryBooks(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByLibraryBooks(assetCodeIdval);
            } else {
                return assetRepository.getAssetByLibraryBooks();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetByLibraryBooks(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetByLibraryBooks(branch);
            }
        }
    }

    public List<Asset> getAssetByTypeSportEquipment(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeSportEquipment();
        } else {
            return assetRepository.getAssetByTypeSportEquipment(branch);
        }
    }

    //    public List<Asset> getAssetBySportEquipment(int branch) {
//        if (branch == 0) {
//            return assetRepository.getAssetBySportEquipment();
//        } else {
//            return assetRepository.getAssetBySportEquipment(branch);
//        }
//    }
    public List<Asset> getAssetBySportEquipment(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetBySportEquipment(assetCodeIdval);
            } else {
                return assetRepository.getAssetBySportEquipment();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetBySportEquipment(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetBySportEquipment(branch);
            }
        }
    }

    public List<Asset> getAssetByTypeSoftware(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeSoftware();
        } else {
            return assetRepository.getAssetByTypeSoftware(branch);
        }
    }

    public List<Asset> getAssetBySoftware(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetBySoftware();
        } else {
            return assetRepository.getAssetBySoftware(branch);
        }
    }

    public List<Asset> getAssetBySoftware(int branch, String assetCodeIdval) {
        if (branch == 0) {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetBySoftware(assetCodeIdval);
            } else {
                return assetRepository.getAssetBySoftware();
            }
        } else {
            if (assetCodeIdval.compareTo("0") != 0) {
                return assetRepository.getAssetBySoftware(branch, assetCodeIdval);
            } else {
                return assetRepository.getAssetBySoftware(branch);
            }
        }
    }

    public Double getAssetUnitPrice(String assetCode) {
        return assetRepository.getAssetUnitPrice(assetCode);
    }


    public List<Asset> getAssetByTypeLandInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLandInsurance();
        } else {
            return assetRepository.getAssetByTypeLandInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeBuildingInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeBuildingInsurance();
        } else {
            return assetRepository.getAssetByTypeBuildingInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeVehicleInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeVehicleInsurance();
        } else {
            return assetRepository.getAssetByTypeVehicleInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeComputerInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeComputerInsurance();
        } else {
            return assetRepository.getAssetByTypeComputerInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypePlantInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypePlantInsurance();
        } else {
            return assetRepository.getAssetByTypePlantInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeFurnitureInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeFurnitureInsurance();
        } else {
            return assetRepository.getAssetByTypeFurnitureInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeOfficeInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeOfficeInsurance();
        } else {
            return assetRepository.getAssetByTypeOfficeInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeLabInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLabInsurance();
        } else {
            return assetRepository.getAssetByTypeLabInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeTeachingInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeTeachingInsurance();
        } else {
            return assetRepository.getAssetByTypeTeachingInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeFixturesInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeFixturesInsurance();
        } else {
            return assetRepository.getAssetByTypeFixturesInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeSportsInsurance(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeSportsInsurance();
        } else {
            return assetRepository.getAssetByTypeSportsInsurance(branch);
        }
    }

    public List<Asset> getAssetByTypeVehicleWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeVehicleWarranty();
        } else {
            return assetRepository.getAssetByTypeVehicleWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeComputerWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeComputerWarranty();
        } else {
            return assetRepository.getAssetByTypeComputerWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypePlantWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypePlantWarranty();
        } else {
            return assetRepository.getAssetByTypePlantWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeFurnitureWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeFurnitureWarranty();
        } else {
            return assetRepository.getAssetByTypeFurnitureWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeOfficeWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeOfficeWarranty();
        } else {
            return assetRepository.getAssetByTypeOfficeWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeLabWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLabWarranty();
        } else {
            return assetRepository.getAssetByTypeLabWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeTeachingWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeTeachingWarranty();
        } else {
            return assetRepository.getAssetByTypeTeachingWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeFixturesWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeFixturesWarranty();
        } else {
            return assetRepository.getAssetByTypeFixturesWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypeSportsWarranty(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeSportsWarranty();
        } else {
            return assetRepository.getAssetByTypeSportsWarranty(branch);
        }
    }

    public List<Asset> getAssetByTypePlantServiceAgree(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypePlantServiceAgree();
        } else {
            return assetRepository.getAssetByTypePlantServiceAgree(branch);
        }
    }

    public List<Asset> getAssetByTypeOfficeServiceAgree(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeOfficeServiceAgree();
        } else {
            return assetRepository.getAssetByTypeOfficeServiceAgree(branch);
        }
    }

    public List<Asset> getAssetByTypeLabServiceAgree(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeLabServiceAgree();
        } else {
            return assetRepository.getAssetByTypeLabServiceAgree(branch);
        }
    }

    public List<Asset> getAssetByTypeTeachingServiceAgree(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeTeachingServiceAgree();
        } else {
            return assetRepository.getAssetByTypeTeachingServiceAgree(branch);
        }
    }

    public List<Asset> getAssetByTypeSportsServiceAgree(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeSportsServiceAgree();
        } else {
            return assetRepository.getAssetByTypeSportsServiceAgree(branch);
        }
    }

    public List<Asset> getAssetByTypeSoftwareServiceAgree(int branch) {
        if (branch == 0) {
            return assetRepository.getAssetByTypeSoftwareServiceAgree();
        } else {
            return assetRepository.getAssetByTypeSoftwareServiceAgree(branch);
        }
    }

    @Transactional
    public int checkRegisterdYear(int year, int assetId) {
        int status = 0;
        Asset asset = assetRepository.findById(assetId).get();

        Calendar cal = Calendar.getInstance();
        cal.setTime(asset.getRegDate());
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        int yearOfRegisterd = cal.get(Calendar.YEAR);

        if (yearOfRegisterd > year) { // check the year before the registered date
            status = 1;
        }

        return status;
    }

    @Transactional
    public Asset findById(int assetId) {
        return assetRepository.findById(assetId).get();
    }


    public Integer getTransactionNo(int assetId) {
       int tNo=assetRepository.getTransactionNo(assetId);
       return  tNo;
    }

}
