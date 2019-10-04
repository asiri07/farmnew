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
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.repository.AssetTypeAssingRepository;
import com.msd.fixAssetRegister.repository.MainCategoryRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
public class MainCategoryService {

    @Autowired
    MainCategoryRepository mainCategoryRepository;

    @Autowired
    AssetTypeAssingRepository assetTypeAssingRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    @Transactional
    public List<AssetCatergoryMain> findAll() {
        return mainCategoryRepository.findAll();
    }

    @Transactional
    public Boolean saveMainCatergory(AssetCatergoryMain assetCatergoryMain) {
        Boolean isSave = false;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            assetCatergoryMain.setActionTime(date);
            mainCategoryRepository.save(assetCatergoryMain);
            isSave = true;
        } catch (Exception e) {
            isSave = false;
        }
        return isSave;
    }

    @Transactional
    public AssetCatergoryMain getMainCatByCode(String mainCode) {
        return mainCategoryRepository.getMainCatByCode(mainCode);
    }

    @Transactional
    public int deleteMainCategory(int mainCat) {
        int isDelete = 0;
        mainCategoryRepository.deleteById(mainCat);
        return 1;
    }

    @Transactional
    public List<Listing> getMainCatListing() {
        List<Listing> listings = new ArrayList<Listing>();
        List<AssetCatergoryMain> mains = mainCategoryRepository.findAll();
        for (AssetCatergoryMain catergoryMain : mains) {
            Listing listing = new Listing();
            listing.setListingId(catergoryMain.getMcatId());
            listing.setListingName(catergoryMain.getMcatCode());
            listing.setDescription(catergoryMain.getMcatDes());
            listing.setListingType(1);
            listings.add(listing);
        }
        return listings;
    }

    @Transactional
    public String getMainCatCode(String fromCode) {
        String code = null;
        AssetCatergoryMain catergoryMain = mainCategoryRepository.findById(Integer.parseInt(fromCode)).get();
        if (catergoryMain != null) {
            code = catergoryMain.getMcatCode();
        }
        return code;
    }

    @Transactional
    public int uploadXMLMainCat(List mainList) throws ParseException {
        int k = 0;
        for (int i = 1; i < mainList.size(); i++) {
            List list = (List) mainList.get(i);
            Object myObj[] = new Object[list.size()];
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
                if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        myObj[j] = cell.getDateCellValue();
                    } else {
                        myObj[j] = cell.getNumericCellValue();
                    }
                } else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                    myObj[j] = cell.getStringCellValue();
                }


            }

            String d1 = "";
            String d2 = "";
            String d3 = "";
            String d4 = "";
            String d5 = "";

            if (myObj[0] != null) {
                d1 = myObj[0].toString();
            }
            if (myObj[1] != null) {
                d2 = myObj[1].toString();
            }
            if (myObj[2] != null) {
                d3 = myObj[2].toString();
            }
            if (myObj[3] != null) {
                d4 = myObj[3].toString();
            }
            if (myObj[4] != null) {
                d5 = myObj[4].toString();
            }
            AssetCatergoryMain catergoryMain = new AssetCatergoryMain();
            if (StringUtils.isNumeric(d2)) {
                Double code = Double.parseDouble(d2);
                Integer codeint = code.intValue();
                d2 = codeint.toString();
                if (d2.length() == 1) {
                    d2 = "0" + d2;
                }
            }
            catergoryMain.setMcatCode(d2);
            catergoryMain.setMcatDes(d3);
            Double dvalue = Double.parseDouble(d4);
            catergoryMain.setUserId(dvalue.intValue());
            Date date = (Date) formatter.parse(d5);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            System.out.println("formatedDate : " + formatedDate);
            catergoryMain.setActionTime(simpleDateFormat.parse(formatedDate));
            mainCategoryRepository.save(catergoryMain);
            k++;
        }
        return k;
    }


    public AssetCatergoryMain saveUpdateMainCatergory(AssetCatergoryMain assetCatergoryMain) {
//        if (assetCatergoryMain.getMultiAssets() != null) {
//            String[] assetss = assetCatergoryMain.getMultiAssets().split(",");
//            List<String> list = Arrays.asList(assetss);
//            Set<String> set = new HashSet<String>(list);
//
//            Stream stream = set.stream();
//            stream.forEach((element) -> {
//                AssetTypeAssing assetTypeAssing = new AssetTypeAssing();
//                assetTypeAssing.setAssetTypeId(assetCatergoryMain.getAssetTypeId());
//                assetTypeAssing.setMcatId(assetCatergoryMain.getMcatId());
//                assetTypeAssingRepository.save(assetTypeAssing);
//            });
//        }else {
//                AssetTypeAssing assetTypeAssing = new AssetTypeAssing();
//                assetTypeAssing.setAssetTypeId(assetCatergoryMain.getAssetTypeId());
//                assetTypeAssing.setMcatId(assetCatergoryMain.getMcatId());
//                assetTypeAssingRepository.save(assetTypeAssing);
//        }
        return mainCategoryRepository.save(assetCatergoryMain);
        }


    @Transactional
    public AssetCatergoryMain findById(int comId) {
        return mainCategoryRepository.findById(comId).get();
    }

    @Transactional
    public void assetUpdate(int noUnit, String mainCode) {
        mainCategoryRepository.assetUpdate(noUnit,mainCode);
    }


}