package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.model.form.AccessoryAssigningGridLoad;
import com.msd.fixAssetRegister.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccessoryAssigningService  {



    @Autowired
    AccessoryAssigningRepository accessoryAssigningRepository;

    @Autowired
    AccessoryAssigningHistoryRepository accessoryAssigningHistoryRepository;

    @Autowired
    AccessoryMasterRepository accessoryMasterRepository;


    @Autowired
    AssetRepository assetRepository;


    @Transactional
    public List<AccessoryMaster> getAccessoryName(int assetId) {
        return accessoryAssigningRepository.getAccessoryName(assetId);
    }

    @Transactional
    public List<AccessoryMaster> getAllAccessories(int assetId) {
        return accessoryAssigningRepository.getAllAccessories(assetId);
    }

    @Transactional
    public String saveAccessoryAssigning(AccessoryAssigning verification) {
        String[] detailAccessoryId = verification.getDetailAccessoryId().split(",");

        for (int i = 0; i <detailAccessoryId.length; i++) {
            AccessoryAssigning accessoryAssigning = new AccessoryAssigning();
            accessoryAssigning.setAssetId(verification.getAssetId());
            accessoryAssigning.setAccessoryAssigningId(verification.getAccessoryAssigningId());
            accessoryAssigning.setAccessoryId(Integer.parseInt(detailAccessoryId[i]));
            accessoryAssigning.setUserId(verification.getUserId());
            accessoryAssigning.setActionTime(verification.getActionTime());
//            accessoryAssigningDao.save(accessoryAssigning);
            accessoryAssigningRepository.save(accessoryAssigning);
        }
        return "Save Successful.";
    }
    @Transactional
    public List<AccessoryAssigning> findAll() {
        return accessoryAssigningRepository.findAll();
    }

    @Transactional
    public List<AccessoryAssigning> getAccessoryAssignnigId(int accerId) {
        return accessoryAssigningRepository.getAccessoryAssignnigId(accerId);
    }
    @Transactional
    public int deleteAccessoryAssigning(int accessoryAssignningId) {
        int isDelete = 0;
        accessoryAssigningRepository.deleteById(accessoryAssignningId);
        return isDelete;
    }

    @Transactional
    public int deleteAccessoryAssign(int accessoryAssignningId) {
        int isDelete = 0;
        int no = accessoryAssigningRepository.deleteAccessoryAssigingByAssetId(accessoryAssignningId);
        return isDelete;
    }

    @Transactional
    public AccessoryAssigning findById(int accessoryAssignningId) {
        return accessoryAssigningRepository.findById(accessoryAssignningId).get();
    }

    @Transactional
    public List<AccessoryAssigning> findByAssetId(int accessoryAssignningId) {
        return accessoryAssigningRepository.getAccessoryAssignnigAssetId(accessoryAssignningId);
    }

    @Transactional
    public List<AccessoryAssigningGridLoad> loadAccessoryAssigningGrid() {

        List<AccessoryAssigningGridLoad> accessoryAssigningGridLoads = new ArrayList<AccessoryAssigningGridLoad>();
        List<AccessoryAssigning> accessoryAssignings = new ArrayList<AccessoryAssigning>();
        accessoryAssignings= accessoryAssigningRepository.findAll();


        for(int i=0;i<accessoryAssignings.size();i++){
            AccessoryAssigningGridLoad accessoryAssigningGridLoad =new AccessoryAssigningGridLoad();
            int assetId=accessoryAssignings.get(i).getAssetId();
            int accerId=accessoryAssignings.get(i).getAccessoryId();
            int id=accessoryAssignings.get(i).getAccessoryAssigningId();

            Asset asset = assetRepository.findById(assetId).get();
            AccessoryMaster am = accessoryMasterRepository.findById(accerId).get();
            accessoryAssigningGridLoad.setAccessoryId(id);
            accessoryAssigningGridLoad.setAssetCode(asset.getAsCode());
            accessoryAssigningGridLoad.setAccessoryCode(am.getAccerCode());
            accessoryAssigningGridLoad.setAccessoryName(am.getAccerName());
            accessoryAssigningGridLoads.add(accessoryAssigningGridLoad);
        }
        return  accessoryAssigningGridLoads;
    }


    @Transactional
    public String updateHistory(AccessoryAssigning accessoryAssigning) {
        String[] detailAccessoryId = accessoryAssigning.getDetailAccessoryId().split(",");

        for (int i = 0; i <detailAccessoryId.length; i++) {
            AccessoryAssigningHistory accessoryAssigningHistory = new AccessoryAssigningHistory();
            accessoryAssigningHistory.setAssetId(accessoryAssigning.getAssetId());
            accessoryAssigningHistory.setAccessoryAssigningId(accessoryAssigning.getAccessoryAssigningId());
            accessoryAssigningHistory.setAccessoryId(Integer.parseInt(detailAccessoryId[i]));
            accessoryAssigningHistory.setUserId(accessoryAssigning.getUserId());
            accessoryAssigningHistory.setActionTime(accessoryAssigning.getActionTime());
//            accessoryAssigningDao.save(accessoryAssigning);
            accessoryAssigningHistoryRepository.save(accessoryAssigningHistory);
        }
        return "Save Successful.";
    }


}
