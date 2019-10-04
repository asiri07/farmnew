package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AccessoryMaster;
import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.AssetTypeAssing;
import com.msd.fixAssetRegister.repository.AccessoryMasterRepository;
import com.msd.fixAssetRegister.repository.AssetTypeAssingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTypeAssingService {

    @Autowired
    AssetTypeAssingRepository assetTypeAssingRepository;

    public AssetTypeAssing saveUpdateAssetTypeAssingService(AssetTypeAssing assetTypeAssing) {
        return assetTypeAssingRepository.save(assetTypeAssing);
    }

    public void deleteAssetTypeAssing(String mcatCode) {
        assetTypeAssingRepository.deleteAssetTypeAssing(mcatCode);

    }



//    public List<AssetTypeAssing> getMainCodebyLand() {
//        return assetTypeAssingRepository.searchLandSelected();
//    }
}
