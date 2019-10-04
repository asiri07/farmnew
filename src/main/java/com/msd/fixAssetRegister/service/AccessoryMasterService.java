package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AccessoryAssigning;
import com.msd.fixAssetRegister.model.AccessoryMaster;
import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.AssetCatergorySub;
import com.msd.fixAssetRegister.repository.AccessoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccessoryMasterService {

    @Autowired
    AccessoryMasterRepository  accessoryMasterRepository;

    public AccessoryMaster saveUpdateAccessoryMaster(AccessoryMaster accessoryMaster) {
        return accessoryMasterRepository.save(accessoryMaster);
    }
    @Transactional
    public List<AccessoryMaster> findAll() {
        return accessoryMasterRepository.findAll();
    }

    @Transactional
    public int deleteMainCategory(int accerId) {
        int isDelete = 0;
        accessoryMasterRepository.deleteById(accerId);
        return isDelete;
    }
    @Transactional
    public AccessoryMaster findById(int accerId) {
        return accessoryMasterRepository.findById(accerId).get();
    }

    @Transactional
    public AccessoryMaster getAccessoryMasterByCode(String accessoryCode) {
        return accessoryMasterRepository.getAccessoryMasterByCode(accessoryCode);
    }
}
