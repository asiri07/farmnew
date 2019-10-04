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
import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.model.form.TransferForm;
import com.msd.fixAssetRegister.repository.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
public class TransferService  {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    LocationMasterRepository locationMasterRepository;

    @Autowired
    AssetLocationDetailRepository assetLocationDetailRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    @Transactional
    public Boolean saveAssetTransfer(TransferForm transferForm) {
        Boolean isSave = false;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            if (transferForm.getIsDepartmentTrasfer() == 1) {
                DepartmentMaster department = departmentRepository.findByDepCode(transferForm.getFromDep());
                List<Integer> tranferAssrts = assetRepository.findAssetsInDepatment(department.getDepId());
                String assrtCodes = "";
                for (Integer id : tranferAssrts) {
                    assrtCodes += id + ",";
                }
                if (StringUtils.isNotEmpty(assrtCodes)) {
                    assrtCodes = assrtCodes.substring(0, assrtCodes.length() - 1);
                    transferForm.setMultiAssets(assrtCodes);
                }
            }
            String FromCode = transferForm.getFromDep() + transferForm.getFromSec() + transferForm.getFromLoc();
            String toCode = transferForm.getToDep() + transferForm.getToSec() + transferForm.getToLoc();

            if (transferForm.getMultiAssets() != null) {
                String[] assetss = transferForm.getMultiAssets().split(",");
                List<String> list = Arrays.asList(assetss);
                Set<String> set = new HashSet<String>(list);

                Stream stream = set.stream();
                stream.forEach((element) -> {
                    Integer assetId = Integer.parseInt(element.toString());
                    Asset newAsset = assetRepository.findById(assetId).get();
                    Transfer transfer = new Transfer();
                    transfer.setActionTime(date);
                    transfer.setUserId(transferForm.getUserId());
                    transfer.setTfFromcode(FromCode);
                    transfer.setTfTocode(toCode);
                    transfer.setFromDep(transferForm.getFromDep());
                    transfer.setFromSec(transferForm.getFromSec());
                    transfer.setFromLoc(transferForm.getFromLoc());
                    transfer.setToDep(transferForm.getToDep());
                    transfer.setToSec(transferForm.getToSec());
                    transfer.setToLoc(transferForm.getToLoc());
                    transfer.setTfDate(transferForm.getDate());
                    transfer.setType(transferForm.getType());
                    transfer.setAsset(newAsset);
                    transfer.setTfUpdateby(transferForm.getUserId());
                    transfer.setIssuedTo(transferForm.getIssuedTo());
                    transfer.setComments(transferForm.getComments());
                    transferRepository.save(transfer);
                    if (transfer.getTfId() > 0) {
                        LocationMaster locationMasterTo = locationMasterRepository.findByLocCode(transferForm.getToLoc());
                        LocationMaster locationMasterFrom = locationMasterRepository.findByLocCode(transferForm.getFromLoc());
                        newAsset.setAsTransfer(true);
                        newAsset.setLocationMaster(locationMasterTo);
                        assetRepository.save(newAsset);
                        AssetLocationDetail assetLocationDetail = new AssetLocationDetail();
                        assetLocationDetail.setAsId(newAsset.getAsId());
                        assetLocationDetail.setLocId(locationMasterFrom.getLocId());
                        assetLocationDetail.setUserId(transferForm.getUserId());
                        assetLocationDetail.setActionTime(date);
                        assetLocationDetailRepository.save(assetLocationDetail);
                    } else {
                        throw new RuntimeException("Asset Update UnSuccessfully.");
                    }
                });
                isSave = true;
            } else {
                Asset asset = assetRepository.findById(transferForm.getAssertId()).get();
                Transfer transfer = new Transfer();
                transfer.setActionTime(date);
                transfer.setUserId(transferForm.getUserId());
                transfer.setTfFromcode(FromCode);
                transfer.setTfTocode(toCode);
                transfer.setFromDep(transferForm.getFromDep());
                transfer.setFromSec(transferForm.getFromSec());
                transfer.setFromLoc(transferForm.getFromLoc());
                transfer.setToDep(transferForm.getToDep());
                transfer.setToSec(transferForm.getToSec());
                transfer.setToLoc(transferForm.getToLoc());
                transfer.setTfDate(transferForm.getDate());
                transfer.setTfDate(transferForm.getDate());
                transfer.setType(transferForm.getType());
                transfer.setAsset(asset);
                transfer.setTfUpdateby(transferForm.getUserId());
                transfer.setIssuedTo(transferForm.getIssuedTo());
                transfer.setComments(transferForm.getComments());
                transferRepository.save(transfer);
                if (transfer.getTfId() > 0) {
                    LocationMaster locationMasterTo = locationMasterRepository.findByLocCode(transferForm.getToLoc());
                    LocationMaster locationMasterFrom = locationMasterRepository.findByLocCode(transferForm.getFromLoc());
                    asset.setAsTransfer(true);
                    asset.setLocationMaster(locationMasterTo);
                    assetRepository.save(asset);
                    AssetLocationDetail assetLocationDetail = new AssetLocationDetail();
                    assetLocationDetail.setAsId(asset.getAsId());
                    assetLocationDetail.setLocId(locationMasterFrom.getLocId());
                    assetLocationDetail.setUserId(transferForm.getUserId());
                    assetLocationDetail.setActionTime(date);
                    assetLocationDetailRepository.save(assetLocationDetail);
                    isSave = true;
                } else {
                    throw new RuntimeException("Asset Update UnSuccessfully.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Update UnSuccessfully : " + e);
        }
        return isSave;
    }

//    @Transactional
//    public String saveAssetTransferFromExcel(TransferForm transferForm) {
//        String msg = "";
//        try {
//            Calendar calendar = Calendar.getInstance();
//            Date date = calendar.getTime();
//            Asset asset = assetDao.get(transferForm.getAssertId());
//
//            // AssetLocationDetail locationDetail = assetDetailDao.getAssetLOcation(asset.getAsId());
//            //String companyCode = locationDetail.getLocationMaster().getSectionMaster().getDepartmentMaster().getCompanyMaster().getComCode();
//            String FromCode = transferForm.getFromDep() + transferForm.getFromSec() + transferForm.getFromLoc() + transferForm.getFromEmpNo();
//            String toCode = transferForm.getToDep() + transferForm.getToSec() + transferForm.getToLoc() + transferForm.getToEmpNo();
//
//            if (transferForm.getMultiAssets() != null) {
//                String[] assetss = transferForm.getMultiAssets().split(",");
//                List<String> list = Arrays.asList(assetss);
//                Set<String> set = new HashSet<String>(list);
//
//                for (int i = 0; i < set.size(); i++) {
//                    int assetId = Integer.parseInt(assetss[i]);
//                    Asset newAsset = assetDao.get(assetId);
//                    Transfer transfer = new Transfer();
//                    transfer.setActionTime(date);
//                    transfer.setUserId(transferForm.getUserId());
//                    transfer.setTfFromcode(FromCode);
//                    transfer.setTfTocode(toCode);
//                    transfer.setFromDep(transferForm.getFromDep());
//                    transfer.setFromSec(transferForm.getFromSec());
//                    transfer.setFromLoc(transferForm.getFromLoc());
//                    transfer.setToDep(transferForm.getToDep());
//                    transfer.setToSec(transferForm.getToSec());
//                    transfer.setToLoc(transferForm.getToLoc());
//                    transfer.setTfDate(transferForm.getDate());
//                    transfer.setType(transferForm.getType());
//                    transfer.setAsset(newAsset);
//                    transfer.setTfUpdateby(transferForm.getUserId());
//                    transfer.setIssuedTo(transferForm.getIssuedTo());
//                    transfer.setComments(transferForm.getComments());
//                    transferDao.save(transfer);
//                    if (transfer.getTfId() > 0) {
//                        LocationMaster locationMaster = locationDao.getLocationByLCode(transferForm.getToLoc());
//                        //assetDetailDao.inActiveCurrentAssetLOcation(newAsset.getAsId());
//                        AssetLocationDetail assetLocationDetail = new AssetLocationDetail();
//                        assetLocationDetail.setIsActive(false);
//                        assetLocationDetail.setAsset(newAsset);
//                        assetLocationDetail.setLocationMaster(locationMaster);
//                        assetLocationDetail.setUserId(transfer.getUserId());
//                        assetLocationDetail.setActionTime(date);
//                        assetDetailDao.save(assetLocationDetail);
//                        asset.setAsTransfer(true);
//                        assetDao.saveOrUpdate(newAsset);
//                    } else {
//                        throw new RuntimeException("Asset Update UnSuccessfully.");
//                    }
//                }
//            } else {
//                Transfer transfer = new Transfer();
//                transfer.setActionTime(date);
//                transfer.setUserId(transferForm.getUserId());
//                transfer.setTfFromcode(FromCode);
//                transfer.setTfTocode(toCode);
//                transfer.setFromDep(transferForm.getFromDep());
//                transfer.setFromSec(transferForm.getFromSec());
//                transfer.setFromLoc(transferForm.getFromLoc());
//                transfer.setToDep(transferForm.getToDep());
//                transfer.setToSec(transferForm.getToSec());
//                transfer.setToLoc(transferForm.getToLoc());
//                transfer.setTfDate(transferForm.getDate());
//                transfer.setTfDate(transferForm.getDate());
//                transfer.setType(transferForm.getType());
//                transfer.setAsset(asset);
//                transfer.setTfUpdateby(transferForm.getUserId());
//                transfer.setIssuedTo(transferForm.getIssuedTo());
//                transfer.setComments(transferForm.getComments());
//                transferDao.save(transfer);
//                if (transfer.getTfId() > 0) {
//                    LocationMaster locationMaster = locationDao.getLocationByLCode(transferForm.getToLoc());
//                    assetDetailDao.inActiveCurrentAssetLOcation(transferForm.getAssertId());
//                    AssetLocationDetail assetLocationDetail = new AssetLocationDetail();
//                    assetLocationDetail.setIsActive(true);
//                    assetLocationDetail.setAsset(asset);
//                    assetLocationDetail.setLocationMaster(locationMaster);
//                    assetLocationDetail.setUserId(transfer.getUserId());
//                    assetLocationDetail.setActionTime(date);
//                    assetDetailDao.save(assetLocationDetail);
//                    asset.setAsTransfer(true);
//                    assetDao.saveOrUpdate(asset);
//                } else {
//                    throw new RuntimeException("Asset Update UnSuccessfully.");
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Update UnSuccessfully : " + e);
//        }
//        return "Update Successful.";
//    }



    public int getNoOfTransferd(int branchId){
        if(branchId==0) {
            return transferRepository.getNoOfTransferd();
        }else{
            return transferRepository.getNoOfTransferd();
        }
    }


}

