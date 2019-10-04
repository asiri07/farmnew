/*
 *     Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *     *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *        This software product contains information which is proprietary to
 *        and considered a trade secret MsSoftIT Solution .
 *        It is expressly agreed that it shall not be reproduced in whole or part,
 *        disclosed, divulged or otherwise made available to any third party directly
 *        or indirectly.  Reproduction of this product for any purpose is prohibited
 *        without written authorisation from the The MsSoftIT Solution
 *        All Rights Reserved.
 *
 *        E-Mail mssoftit@gmail.com
 *        URL : mssoftit.lk
 *        Created By : Mahendra Sri Dayarathna
 */
package com.msd.fixAssetRegister.controller;

import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.model.form.AccessoryAssigningGridLoad;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.model.form.MainCategoryForm;
import com.msd.fixAssetRegister.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    MainCategoryService mainCategoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    DetailCategoryService detailCategoryService;

    @Autowired
    AssetService assetService;

    @Autowired
    UserService userService;

    @Autowired
    MainCategoryHistoryService mainCategoryHistoryService;

    @Autowired
    AccessoryMasterHistoryService accessoryMasterHistoryService;

    @Autowired
    SubCategoryHistoryService subCategoryHistoryService;

    @Autowired
    DetailCategoryHistoryService detailCategoryHistoryService;

    @Autowired
    DisposalCategoryService disposalCategoryService;

    @Autowired
    DisposalService disposalService;

    @Autowired
    AnalysisService analysisService;

    @Autowired
    AccessoryMasterService accessoryMasterService;

    @Autowired
    AccessoryAssigningService accessoryAssigningService;

    @Autowired
    AssetsTypesService assetsTypesService;

    @Autowired
    AssetTypeAssingService assetTypeAssingService;


    @Value("${application.companyName}")
    private String companyName;

    @RequestMapping("mainCategory")
    public ModelAndView loadCatergory(Model model, HttpServletRequest request) {
        List<AssetType> assetsTypes = new ArrayList<AssetType>();
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = null;
            defineList = (List<JobDefine>) session.getAttribute("jobList");
            model.addAttribute("jobList", defineList);
            assetCatergoryMains = mainCategoryService.findAll();
//            assetsTypesService.loadAssetTypes();
            assetsTypes = assetsTypesService.loadAssetTypes();

        }
        model.addAttribute("assets", assetsTypes);
        model.addAttribute("typeid", 0);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("mainCat", new AssetCatergoryMain());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("mainCategory-category");
    }

    @RequestMapping(value = "saveMainCatergory", method = RequestMethod.POST)
    public ModelAndView saveMainCatergory(@ModelAttribute("mainCatergoryForm") MainCategoryForm mainCategoryForm, Model model, BindingResult brequest, HttpServletRequest request) {
        Boolean isSave = false;
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        User user;
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();

                AssetCatergoryMain assetCatergoryMain = new AssetCatergoryMain();
                assetCatergoryMain.setUserId(user.getUserId());
                assetCatergoryMain.setActionTime(date);
                assetCatergoryMain.setMcatDes(mainCategoryForm.getMcatDes());
                assetCatergoryMain.setQty(0);
                assetCatergoryMain.setMcatId(mainCategoryForm.getMcatId());
                assetCatergoryMain.setMcatCode(mainCategoryForm.getMcatCode().toUpperCase());

                AssetTypeAssing assetTypeAssing = new AssetTypeAssing();
                assetTypeAssing.setMcatId(mainCategoryForm.getMcatCode().toUpperCase());
                assetTypeAssing.setId(mainCategoryForm.getTypeid());
                assetTypeAssing.setTypeLand(mainCategoryForm.isTypeLand());
                assetTypeAssing.setTypeLandBuilding(mainCategoryForm.isTypeLandBuilding());
                assetTypeAssing.setTypeVehicle(mainCategoryForm.isTypeVehicle());
                assetTypeAssing.setTypeComputer(mainCategoryForm.isTypeComputer());
                assetTypeAssing.setTypePlantMachinary(mainCategoryForm.isTypePlantMachinary());
                assetTypeAssing.setTypeFurniture(mainCategoryForm.isTypeFurniture());
                assetTypeAssing.setTypeOfficeEquipment(mainCategoryForm.isTypeOfficeEquipment());
                assetTypeAssing.setTypeLabEquipment(mainCategoryForm.isTypeLabEquipment());
                assetTypeAssing.setTypeTeachingEquipment(mainCategoryForm.isTypeTeachingEquipment());
                assetTypeAssing.setTypeFixtures(mainCategoryForm.isTypeFixtures());
                assetTypeAssing.setTypeLibraryBooks(mainCategoryForm.isTypeLibraryBooks());
                assetTypeAssing.setTypeSportEquipment(mainCategoryForm.isTypeSportEquipment());
                assetTypeAssing.setTypeSoftware(mainCategoryForm.isTypeSoftware());
//                assetTypeAssing.setId(mainCategoryForm.getId());

                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                if (mainCategoryForm.getMcatId() > 0) {
                    AssetCatergoryMain mainCatOld = mainCategoryService.findById(assetCatergoryMain.getMcatId());
                    mainCategoryHistoryService.updateHistory(mainCatOld, 0, user.getUserId());


                }

                assetTypeAssingService.saveUpdateAssetTypeAssingService(assetTypeAssing);

                AssetCatergoryMain catergoryMain = mainCategoryService.saveUpdateMainCatergory(assetCatergoryMain);
                if (catergoryMain != null) {
                    isSave = true;
                }
            }
            assetCatergoryMains = mainCategoryService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("mainCat", new AssetCatergoryMain());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("mainCategory-category");
    }

    @RequestMapping("editMainCategory/{mainCatId}")
    public ModelAndView editMainCategory(@PathVariable("mainCatId") int mainCatId, Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        AssetTypeAssing assetTypeAssings = new AssetTypeAssing();
        AssetCatergoryMain assetCatergoryMain = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            assetCatergoryMains = mainCategoryService.findAll();
            assetCatergoryMain = mainCategoryService.findById(mainCatId);
            assetTypeAssings = assetsTypesService.findBymcatCodeAssetTypeAssing(assetCatergoryMain.getMcatCode());
        } catch (Exception e) {
            logger.error("Invalid Main Category Code" + e.getMessage());
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("mainCat", assetCatergoryMain);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);


        model.addAttribute("typeid", assetTypeAssings.getId());
        model.addAttribute("typeLand", assetTypeAssings.isTypeLand() ? "checked" : "unchecked");
        model.addAttribute("typeLandBuilding", assetTypeAssings.isTypeLandBuilding() ? "checked" : "unchecked");
        model.addAttribute("typeVehicle", assetTypeAssings.isTypeVehicle() ? "checked" : "unchecked");
        model.addAttribute("typeComputer", assetTypeAssings.isTypeComputer() ? "checked" : "unchecked");
        model.addAttribute("typeFurniture", assetTypeAssings.isTypeFurniture() ? "checked" : "unchecked");
        model.addAttribute("typePlantMachinary", assetTypeAssings.isTypePlantMachinary() ? "checked" : "unchecked");
        model.addAttribute("typeLabEquipment", assetTypeAssings.isTypeLabEquipment() ? "checked" : "unchecked");
        model.addAttribute("typeOfficeEquipment", assetTypeAssings.isTypeOfficeEquipment() ? "checked" : "unchecked");
        model.addAttribute("typeTeachingEquipment", assetTypeAssings.isTypeTeachingEquipment() ? "checked" : "unchecked");
        model.addAttribute("typeFixtures", assetTypeAssings.isTypeFixtures() ? "checked" : "unchecked");
        model.addAttribute("typeLibraryBooks", assetTypeAssings.isTypeLibraryBooks() ? "checked" : "unchecked");
        model.addAttribute("typeSportEquipment", assetTypeAssings.isTypeSportEquipment() ? "checked" : "unchecked");
        model.addAttribute("typeSoftware", assetTypeAssings.isTypeSoftware() ? "checked" : "unchecked");
        model.addAttribute("id", assetTypeAssings.getId());

        return new ModelAndView("mainCategory-category");

    }


    @RequestMapping("checkAccessory/{AccessoryCode}")
    @ResponseBody
    public Boolean checkAccessory(@PathVariable("AccessoryCode") String accessoryCode, Model model) {
        Boolean isCheck = true;
        try {
            AccessoryMaster accessoryMaster = accessoryMasterService.getAccessoryMasterByCode(accessoryCode);
            if (accessoryMaster != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isCheck;
    }

    @RequestMapping("checkMainCode/{mainCode}")
    @ResponseBody
    public Boolean checkMainCode(@PathVariable("mainCode") String mainCode, Model model) {
        Boolean isCheck = true;
        try {
            AssetCatergoryMain catergoryMain = mainCategoryService.getMainCatByCode(mainCode);
            if (catergoryMain != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isCheck;
    }

    @RequestMapping("checkSubCode/{subCode}/{mainCat}")
    @ResponseBody
    public Boolean checkSubCode(@PathVariable("subCode") String subCode, @PathVariable("mainCat") int mainCat) {
        Boolean isCheck = true;
        try {
            AssetCatergorySub catergorySub = subCategoryService.getSubCatByCode(subCode, mainCat);
            if (catergorySub != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isCheck;
    }

    @RequestMapping("checkDetailCode/{detailCode}/{subCode}")
    @ResponseBody
    public Boolean checkDetailCode(@PathVariable("detailCode") String detailCode, @PathVariable("subCode") int subCode) {
        Boolean isCheck = true;
        try {
            List<AssetCatergoryDetail> catergoryDetail = detailCategoryService.getdetailCatByCode(detailCode, subCode);
            if (catergoryDetail.size() > 0) {
                isCheck = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isCheck;
    }


    @RequestMapping("editableToMainCode/{mainCode}")
    @ResponseBody
    public Boolean editableMainCode(@PathVariable("mainCode") String mainCode) {
        Boolean isEnable = true;
        try {
            List<Asset> asset = assetService.getAssetByMainCode(mainCode);
            if (asset != null) {
                isEnable = false;
            }
        } catch (Exception e) {
            logger.error("Invalid Main Category Code" + e.getMessage());
        }
        return isEnable;
    }


    @RequestMapping("subCategory")
    public ModelAndView loadSubCatergory(Model model, HttpServletRequest request) {
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        HttpSession session = request.getSession();
        if (session.getAttribute("jobList") != null) {
            List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
            model.addAttribute("jobList", defineList);
        }
        assetCatergorySubs = subCategoryService.findAll();
        assetCatergoryMains = mainCategoryService.findAll();

        model.addAttribute("subCategory", assetCatergorySubs);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("subCat", new AssetCatergorySub());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("subCategory-category");
    }


    @RequestMapping("detailCategory")
    public ModelAndView loadDetailCatergory(Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<AssetCatergoryDetail> assetCatergoryDetails = new ArrayList<AssetCatergoryDetail>();
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = null;
            defineList = (List<JobDefine>) session.getAttribute("jobList");
            model.addAttribute("jobList", defineList);
            assetCatergoryMains = mainCategoryService.findAll();
            assetCatergoryDetails = detailCategoryService.findAll();
            assetCatergorySubs = subCategoryService.findAll();
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("detailCategory", assetCatergoryDetails);
        model.addAttribute("subCategory", assetCatergorySubs);
        model.addAttribute("detailCat", new AssetCatergoryDetail());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("detailCategory-category");

    }

    @RequestMapping("editDetailCategory/{detailCat}")
    public ModelAndView editDetailCategory(@PathVariable("detailCat") int detailCat, Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<AssetCatergoryDetail> assetCatergoryDetails = new ArrayList<AssetCatergoryDetail>();
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        AssetCatergoryDetail catergoryDetail = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = null;
                defineList = defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assetCatergoryMains = mainCategoryService.findAll();
                assetCatergoryDetails = detailCategoryService.findAll();
                assetCatergorySubs = subCategoryService.findAll();
                catergoryDetail = detailCategoryService.findById(detailCat);
            }
        } catch (Exception e) {
            logger.error("Invalid Detail category Code" + e.getMessage());
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("detailCategory", assetCatergoryDetails);
        model.addAttribute("subCategory", assetCatergorySubs);
        model.addAttribute("detailCat", catergoryDetail);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("detailCategory-category");

    }


    @RequestMapping(value = "saveSubCategory", method = RequestMethod.POST)
    public ModelAndView saveSubCatergory(@ModelAttribute("subCatergoryForm") AssetCatergorySub assetCatergorySub, Model model, BindingResult brequest, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assetCatergorySub.setUserId(user.getUserId());
                assetCatergorySub.setScatCode(assetCatergorySub.getScatCode().toUpperCase());
                if (assetCatergorySub.getScatId() > 0) {
                    AssetCatergorySub catergorySubOld = subCategoryService.findById(assetCatergorySub.getScatId());
                    subCategoryHistoryService.updateHistory(catergorySubOld, 0, user.getUserId());//done
                }
                isSave = subCategoryService.saveSubCatergory(assetCatergorySub);//done
            }
            assetCatergoryMains = mainCategoryService.findAll();
            assetCatergorySubs = subCategoryService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveSubCategory Method : " + e.getMessage());
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("subCategory", assetCatergorySubs);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("subCat", new AssetCatergorySub());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("subCategory-category");
    }

    @RequestMapping("editSubCategory/{subCatId}")
    public ModelAndView editSubCategory(@PathVariable("subCatId") int subCatId, Model model, HttpServletRequest request) {
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        AssetCatergorySub assetCatergorySub = null;
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = null;

                defineList = (List<JobDefine>) session.getAttribute("jobList");

                model.addAttribute("jobList", defineList);
                assetCatergorySubs = subCategoryService.findAll();
                assetCatergoryMains = mainCategoryService.findAll();
                assetCatergorySub = subCategoryService.findById(subCatId);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the editSubCategory Method : " + e.getMessage());
        }
        model.addAttribute("subCategory", assetCatergorySubs);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("subCat", assetCatergorySub);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("subCategory-category");
    }

    @RequestMapping(value = "saveDeatailCatergory", method = RequestMethod.POST)
    public ModelAndView saveDetailCatergory(@ModelAttribute("detailCatergoryForm") AssetCatergoryDetail assetCatergoryDetail, Model model, BindingResult brequest, HttpServletRequest request) {
        Boolean isSave = false;
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<AssetCatergoryDetail> assetCatergoryDetails = new ArrayList<AssetCatergoryDetail>();
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                assetCatergoryDetail.getDepRateAccount();

                if (assetCatergoryDetail.getDepRateAccount() == null) {
                    assetCatergoryDetail.setDepRateAccount(0.00);
                }

                if (assetCatergoryDetail.getDepRateIncomeTax() == null) {
                    assetCatergoryDetail.setDepRateIncomeTax(0.00);
                }

                assetCatergoryDetail.setUserId(user.getUserId());
                assetCatergoryDetail.setDcatCode(assetCatergoryDetail.getDcatCode().toUpperCase());
                if (assetCatergoryDetail.getDcatId() > 0) {
                    AssetCatergoryDetail catergoryDetailOld = detailCategoryService.findById(assetCatergoryDetail.getDcatId());
                    detailCategoryHistoryService.updateHistory(catergoryDetailOld, 0, user.getUserId());//done
                }
                isSave = detailCategoryService.saveDetailCatergory(assetCatergoryDetail);//done
                List<JobDefine> defineList = defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            assetCatergoryMains = mainCategoryService.findAll();
            assetCatergoryDetails = detailCategoryService.findAll();
            assetCatergorySubs = subCategoryService.findAll();

        } catch (Exception e) {
            logger.error("Error occurred while calling the saveDeatailCatergory Method : " + e.getMessage());
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("detailCategory", assetCatergoryDetails);
        model.addAttribute("subCategory", assetCatergorySubs);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("detailCat", new AssetCatergoryDetail());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("detailCategory-category");
    }

    @RequestMapping(value = "getSubCategoryList/{mainCategory}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getMainCategory(@PathVariable("mainCategory") int mainCat) {
        List<AssetCatergorySub> assetCatergorySubs = new ArrayList<AssetCatergorySub>();
        List<String> subCat = new ArrayList<String>();
        try {

            assetCatergorySubs = subCategoryService.getSubCatsByMainCodeId(mainCat);
            for (Iterator<AssetCatergorySub> iterator = assetCatergorySubs.iterator(); iterator.hasNext(); ) {
                AssetCatergorySub assetCatergorySub = iterator.next();
                subCat.add(assetCatergorySub.getScatId() + "-" + assetCatergorySub.getScatCode() + "-" + assetCatergorySub.getScatDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getSubCategoryList Method : " + e.getMessage());
        }
        return subCat;
    }


    @RequestMapping(value = "getDetailCategoryList/{subCategory}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getDetailCategoryList(@PathVariable("subCategory") int subCat) {
        List<String> detailCat = new ArrayList<String>();
        try {

            List<AssetCatergoryDetail> assetCatergoryDetails = detailCategoryService.getDetailCatsBySubId(subCat);

            for (Iterator<AssetCatergoryDetail> iterator = assetCatergoryDetails.iterator(); iterator.hasNext(); ) {
                AssetCatergoryDetail assetCatergoryDetail = iterator.next();
                detailCat.add(assetCatergoryDetail.getDcatId() + "-" + assetCatergoryDetail.getDcatCode() + "-" + assetCatergoryDetail.getDcatDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getDetailCategoryList Method : " + e.getMessage());
        }
        return detailCat;
    }

    @RequestMapping(value = "getDetailsCatSerial/{detailCat}", method = RequestMethod.GET)
    public @ResponseBody
    String getDetailsCatSerial(@PathVariable("detailCat") int detailsCatCode) {
        return genarateSerialCode(detailsCatCode);
    }

    private String genarateSerialCode(int deCode) {
        String serialNo = "";
        int maxChar = 5;
        try {
            String maxNo = assetService.getMaxSerialNo(deCode, maxChar);
            String code = "";

            if (maxNo.equals("")) {
                // 00001
                for (int i = 0; i < maxChar - 1; i++) {
                    code = code + "0";
                }
                code = code + "1";
                serialNo = code;
            } else {
                int no = Integer.parseInt(maxNo);
                int nextNo = no + 1;
                int noCount = String.valueOf(nextNo).length();
                for (int i = 0; i < maxChar - noCount; i++) {
                    code = code + "0";
                }
                code = code + nextNo;
                serialNo = code;
                System.out.println(serialNo);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the genarateSerialCode Method : " + e.getMessage());
        }
        return serialNo;
    }


    @RequestMapping(value = "deleteMainCategory/{mainCat}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteMainCategory(@PathVariable("mainCat") int mainCat, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<AssetCatergorySub> subList = subCategoryService.getSubCatsByMainCatId(mainCat);
                if (subList.size() == 0) {
                    AssetCatergoryMain mainCatOld = mainCategoryService.findById(mainCat);
                    mainCategoryHistoryService.updateHistory(mainCatOld, 1, user.getUserId());
                    isDelete = mainCategoryService.deleteMainCategory(mainCat);
                    assetTypeAssingService.deleteAssetTypeAssing(mainCatOld.getMcatCode());
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
//                if (isDelete == 1) {
//                    AssetCatergoryMain mainCatOld = mainCategoryService.get(mainCat);
//                    mainCategoryHistoryService.updateHistory(mainCatOld, 0, user.getUserId());
//                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "deleteSubCategory/{subCat}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteSubCategory(@PathVariable("subCat") int subCat, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<AssetCatergoryDetail> subList = detailCategoryService.getDetailCatsBySubCatId(subCat);
                if (subList.size() == 0) {
                    AssetCatergorySub catergorySubOld = subCategoryService.findById(subCat);
                    subCategoryHistoryService.updateHistory(catergorySubOld, 1, user.getUserId());//done
                    isDelete = subCategoryService.deleteSubCategory(subCat);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
//                if (isDelete == 1) {
//                    AssetCatergorySub catergorySubOld = subCategoryService.get(subCat);
//                    subCategoryHistoryService.updateHistory(catergorySubOld, 1, user.getUserId());
//                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteSubCategory Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "deleteDetailCategory/{detailCat}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteDetailCategory(@PathVariable("detailCat") int detailCat, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");

                List<Asset> assetCatergoryDetails = assetService.getAssetByDetailCat(detailCat);
                if (assetCatergoryDetails.size() == 0) {
                    AssetCatergoryDetail catergoryDetailOld = detailCategoryService.findById(detailCat);
                    detailCategoryHistoryService.updateHistory(catergoryDetailOld, 1, user.getUserId());
                    isDelete = detailCategoryService.deleteDetailCategory(detailCat);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }

//                if (isDelete == 1) {
//                    AssetCatergoryDetail catergoryDetailOld = detailCategoryService.get(detailCat);
//                    detailCategoryHistoryService.updateHistory(catergoryDetailOld, 1, user.getUserId());
//                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "getDetailCatbySub/{subCat}", method = RequestMethod.GET)
    public @ResponseBody
    String getDetailCatbySub(@PathVariable("subCat") int subCat) {
        String newDetailCode;
        try {
            newDetailCode = detailCategoryService.genarateNewDetailCatBySubCode(subCat);
        } catch (NumberFormatException e) {
            newDetailCode = "";
        }
        return newDetailCode;
    }


    @RequestMapping("disposalCategory")
    public ModelAndView disposalResaon(Model model, HttpServletRequest request) {
        List<AssetDisposalReason> assetDisposalReasons = new ArrayList<AssetDisposalReason>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assetDisposalReasons = disposalCategoryService.findAll();
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the disposalResaon Method : " + e.getMessage());

        }
        model.addAttribute("rs", new AssetDisposalReason());
        model.addAttribute("reasons", assetDisposalReasons);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("disposalCategory-category");
    }

    @RequestMapping(value = "saveReason", method = RequestMethod.POST)
    public ModelAndView saveReason(@ModelAttribute("reasonForm") AssetDisposalReason assetDisposalReason, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<AssetDisposalReason> assetDisposalReasons = new ArrayList<AssetDisposalReason>();
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                assetDisposalReason.setUserId(user.getUserId());
                assetDisposalReason.setActionTime(date);
                AssetDisposalReason disposalReason = disposalCategoryService.saveUpdatedisposalReason(assetDisposalReason); //done
                if (disposalReason != null) {
                    isSave = true;
                }

                assetDisposalReasons = disposalCategoryService.findAll();
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveReason Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("reasons", assetDisposalReasons);
        model.addAttribute("rs", new AssetDisposalReason());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("disposalCategory-category");

    }

    @RequestMapping("editReason/{rsId}")
    public ModelAndView editReason(@PathVariable("rsId") int rsId, Model model, HttpServletRequest request) {
        AssetDisposalReason assetDisposalReason = null;
        List<AssetDisposalReason> assetDisposalReasons = new ArrayList<AssetDisposalReason>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            assetDisposalReason = disposalCategoryService.findById(rsId);
            assetDisposalReasons = disposalCategoryService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the editReason Method : " + e.getMessage());
        }
        model.addAttribute("rs", assetDisposalReason);
        model.addAttribute("reasons", assetDisposalReasons);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("disposalCategory-category");
    }

    @RequestMapping("deleteReason/{rsId}")
    @ResponseBody
    public int deleteReason(@PathVariable("rsId") int rsId) {
        int isDelete = 0;
        try {
            disposalCategoryService.delete(rsId);
            isDelete = 1;
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteReason Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }


    @RequestMapping("analysisData")
    public ModelAndView analysisData(Model model, HttpServletRequest request) {
        List<AnalysisMaster> analysisMasters = new ArrayList<AnalysisMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            analysisMasters = analysisService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the analysisData Method : " + e.getMessage());
        }
        model.addAttribute("analysis", new AnalysisMaster());
        model.addAttribute("analysisData", analysisMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("analysisMaster-category");
    }

    @RequestMapping(value = "saveAnalysisData", method = RequestMethod.POST)
    public ModelAndView saveAnalysisData(@ModelAttribute("analysisDataForm") AnalysisMaster analysisMaster, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<AnalysisMaster> analysisMasters = new ArrayList<AnalysisMaster>();
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                analysisMaster.setUserId(user.getUserId());
                analysisMaster.setActionTime(date);
                analysisMaster.setAnaCode(analysisMaster.getAnaCode().toUpperCase());

//                String msg = analysisService.add(analysisMaster);

                AnalysisMaster catergoryAnalysis = analysisService.saveUpdateAnalysis(analysisMaster); //done
                if (catergoryAnalysis != null) {
                    isSave = true;
                }
                analysisMasters = analysisService.findAll();
//                if (msg.equals("Update Successful.")) {
//                    isSave = true;
//                }
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the saveAnalysisData Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("analysisData", analysisMasters);
        model.addAttribute("analysis", new AnalysisMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("analysisMaster-category");

    }

    @RequestMapping("editAnalysisData/{id}")
    public ModelAndView editAnalysisData(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        List<AnalysisMaster> analysisMasters = new ArrayList<AnalysisMaster>();
        AnalysisMaster analysisMaster = null;
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                analysisMasters = analysisService.findAll();
                analysisMaster = analysisService.findById(id);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the editAnalysisData Method : " + e.getMessage());
        }
        model.addAttribute("analysisData", analysisMasters);
        model.addAttribute("analysis", analysisMaster);
        model.addAttribute("isEdit", 1);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("analysisMaster-category");
    }

    @RequestMapping("deleteAnalysisData/{id}")
    @ResponseBody
    public int deleteAnalysisData(@PathVariable("id") int id) {
        int isDelete = 0;
        try {
            isDelete = analysisService.deleteAnalysisCode(id);
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteAnalysisData Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping("checkAnalysisCode/{anaCode}")
    @ResponseBody
    public Boolean checkAnalysisCode(@PathVariable("anaCode") String anaCode, Model model) {
        Boolean isCheck = true;
        try {
            AnalysisMaster analysisMaster = analysisService.getAnalysisMaster(anaCode);
            if (analysisMaster != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkAnalysisCode Method : " + e.getMessage());
        }
        return isCheck;
    }

    @RequestMapping("accessoryMaster")
    public ModelAndView loadAccessoryMaster(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<AccessoryMaster> accessoryDetails = new ArrayList<AccessoryMaster>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
                 accessoryDetails = accessoryMasterService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadAccessory Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 0);
        model.addAttribute("accessoryDetails", accessoryDetails);
        model.addAttribute("companyName", companyName);
        model.addAttribute("accessoryMaster", new AccessoryMaster());
        return new ModelAndView("accessoryMaster-category");
    }

    @RequestMapping("accessoryAssigning")
    public ModelAndView loadAccessoryAssigning(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<AccessoryAssigningGridLoad> accessoryDetails = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                accessoryDetails = accessoryAssigningService.loadAccessoryAssigningGrid();

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadAccessory Method : " + e.getMessage());
            }

            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("accessoryDetails", accessoryDetails);
        model.addAttribute("companyName", companyName);
        model.addAttribute("accessoryAssigning", new AccessoryAssigning());
        return new ModelAndView("accessoryAssigning-category");
    }


    @RequestMapping(value = "saveAccessoryMaster", method = RequestMethod.POST)
    public ModelAndView saveAccessoryMaster(@ModelAttribute("accessoryMasterForm") AccessoryMaster accessoryMaster, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        List<AccessoryMaster> accessoryDetails = new ArrayList<AccessoryMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                accessoryMaster.setActionTime(date);
                accessoryMaster.setUserId(user.getUserId());
                accessoryMaster.setAccerCode(accessoryMaster.getAccerCode().toUpperCase());
                if (accessoryMaster.getAccerId() > 0) {
                    AccessoryMaster accessoryMaster1 = accessoryMasterService.findById(accessoryMaster.getAccerId());
                    accessoryMasterHistoryService.updateHistory(accessoryMaster1, 0, user.getUserId());
                }
//                String msg = accessoryMasterService.add(accessoryMaster);

                AccessoryMaster catergoryAccessoryMaster = accessoryMasterService.saveUpdateAccessoryMaster(accessoryMaster); //done
                if (catergoryAccessoryMaster != null) {
                    isSave = true;
                }
                accessoryDetails = accessoryMasterService.findAll();

//                if (msg.equals("Update Successful.")) {
//                    isSave = true;
//                }
            }
        } catch (Exception e) {

        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("accessoryDetails", accessoryDetails);
        model.addAttribute("accessoryMaster", new AccessoryMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("accessoryMaster-category");
    }

    @RequestMapping("getAssetByLocation/{locId}")
    @ResponseBody
    public List<Asset> loadAssetByLocation(@PathVariable("locId") int locId) {
        return assetService.getAssetDetails(locId);
    }

    @RequestMapping(value = "saveAccessoryAssigning", method = RequestMethod.POST)
    public ModelAndView saveAccessoryAssigning(AccessoryAssigning accessoryAssigning, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<AccessoryAssigningGridLoad> accessoryDetails = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                accessoryAssigning.setUserId(user.getUserId());
                accessoryAssigning.setActionTime(date);
                List<AccessoryAssigning> accessoryAssignings = accessoryAssigningService.findByAssetId(accessoryAssigning.getAssetId());
                if (accessoryAssignings != null) {
                    accessoryAssigningService.deleteAccessoryAssign(accessoryAssigning.getAssetId());
                }

                String msg = accessoryAssigningService.saveAccessoryAssigning(accessoryAssigning);//done
                if (msg.equals("Save Successful.")) {
                    isSave = true;
                }
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                accessoryDetails = accessoryAssigningService.loadAccessoryAssigningGrid();
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the saveAssetRevaluation Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
//        model.addAttribute("assets", assets);
        model.addAttribute("pageType", 1);
        model.addAttribute("accessoryDetails", accessoryDetails);
        model.addAttribute("accessoryAssigning", new AccessoryAssigning());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("accessoryAssigning-category");
    }


    @RequestMapping("getAccessory/{assetId}")
    @ResponseBody
    public List<AccessoryMaster> loadAccessoryByAssetId(@PathVariable("assetId") int assetId) {
        return accessoryAssigningService.getAccessoryName(assetId);
    }


    @RequestMapping("getAllAccessories/{assetId}")
    @ResponseBody
    public List<AccessoryMaster> loadAllAccessories(@PathVariable("assetId") int assetId) {
        return accessoryAssigningService.getAllAccessories(assetId);
    }

    @RequestMapping("loadAssetType")
    @ResponseBody
    public List<AssetType> loadAssetTypes() {
        return assetsTypesService.loadAssetTypes();
    }

    @RequestMapping("loadTypeOfMainCode")
    @ResponseBody
    public int[] loadComputerDetailsByAsset() {
        int[] dashValue = new int[7];
        dashValue[0] = mainCategoryService.findById(5   ).getQty();//Furniture & Fittings
        dashValue[1] = mainCategoryService.findById(3).getQty();//Plant & Machinary
        dashValue[2] = mainCategoryService.findById(4).getQty();//Lab Equipments
        dashValue[3] = mainCategoryService.findById(16).getQty();//Sport Equipments
        dashValue[4] = mainCategoryService.findById(10).getQty();//Vehicle
        dashValue[5] = mainCategoryService.findById(9).getQty();//Land
        dashValue[6] = assetsTypesService.getNoOfOtherAssect(5, 4, 16, 10,3,9);//Other

        return dashValue;
    }


    @RequestMapping("editAccessory/{accessoryId}")
    public ModelAndView editAccessory(@PathVariable("accessoryId") int accessoryId, Model model, HttpServletRequest request) {
        List<AccessoryMaster> accessoryMasters = new ArrayList<AccessoryMaster>();
        AccessoryMaster accessoryMaster = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            accessoryMasters = accessoryMasterService.findAll();
            accessoryMaster = accessoryMasterService.findById(accessoryId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("accessoryDetails", accessoryMasters);
        model.addAttribute("accessory", accessoryMaster);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("accessoryMaster-category");

    }


    @RequestMapping(value = "deleteAccessoryMaster/{accerId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteAccessoryMaster(@PathVariable("accerId") int accerId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<AccessoryAssigning> accessoryAssignings = accessoryAssigningService.getAccessoryAssignnigId(accerId);
                if (accessoryAssignings.size() == 0) {
                    AccessoryMaster accessoryMaster = accessoryMasterService.findById(accerId);
                    // mainCategoryHistoryService.updateHistory(accessoryMaster, 1, user.getUserId());
                    isDelete = accessoryMasterService.deleteMainCategory(accerId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
//                if (isDelete == 1) {
//                    AssetCatergoryMain mainCatOld = mainCategoryService.get(mainCat);
//                    mainCategoryHistoryService.updateHistory(mainCatOld, 0, user.getUserId());
//                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "deleteAccessoryAssigning/{accessoryAssignningId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteAccessoryAssigning(@PathVariable("accessoryAssignningId") int accessoryAssignningId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");

                AccessoryAssigning accessoryAssigning = accessoryAssigningService.findById(accessoryAssignningId);
                accessoryAssigningService.updateHistory(accessoryAssigning);
                isDelete = accessoryAssigningService.deleteAccessoryAssigning(accessoryAssignningId);
                isDelete = 1;

//                if (isDelete == 1) {
//                    AssetCatergoryMain mainCatOld = mainCategoryService.get(mainCat);
//                    mainCategoryHistoryService.updateHistory(mainCatOld, 0, user.getUserId());
//                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

}
