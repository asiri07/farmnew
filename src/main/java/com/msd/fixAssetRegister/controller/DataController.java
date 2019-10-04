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
 *
 *        Modification :
 */
package com.msd.fixAssetRegister.controller;

import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.model.Currency;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.model.form.TransferForm;
import com.msd.fixAssetRegister.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    @Autowired
    MainCategoryService mainCategoryService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    DetailCategoryService detailCategoryService;
    @Autowired
    CompanyService companyService;
    @Autowired
    LocationService locationService;
    @Autowired
    AssetService assetService;
    @Autowired
    DisposalService disposalService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    SectionService sectionService;
    @Autowired
    DamageService damageService;
    @Autowired
    TransferService transferService;
    @Autowired
    IncomeTaxService incomeTaxService;
    @Autowired
    AssetPriorRegService assetPriorRegService;
    @Autowired
    DisposalCategoryService disposalCategoryService;
    @Autowired
    ImprovementService improvementService;
    @Autowired
    AnalysisService analysisService;
    @Autowired
    VerificationService verificationService;
    @Autowired
    UserService userService;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    CurrencyRateService currencyRateService;
    @Autowired
    RevaluationService revaluationService;
    @Autowired
    AssetSearchHistoryService assetSearchHistoryService;

    @Autowired
    AssetsTypesService assetsTypesService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int MAX_SERIAL = 6;
    String DAMAGE_CODE = "DA";
    String DISPOSAL_CODE = "AD";
    String IMPROVEMENT_CODE = "AI";
    String ASSET_CODE = "TR";
    @Value("${application.companyName}")
    private String companyName;

    public static String getSerialNo(int num, int digits) {
        String output = Integer.toString(num);
        while (output.length() < digits) output = "0" + output;
        return output;
    }

    @RequestMapping("asset")
    public ModelAndView loadAsset(Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        List<AssetCatergoryDetail> assetCatergoryDetails = new ArrayList<AssetCatergoryDetail>();
        List<AnalysisMaster> analysiCodes = new ArrayList<AnalysisMaster>();
        List<AssetLocationDetail> assetLocationDetails = new ArrayList<AssetLocationDetail>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
//        List<Asset> assets = new ArrayList<Asset>();

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assetCatergoryMains = mainCategoryService.findAll();
                companyMasters = companyService.findAll();
                locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                assetCatergoryDetails = detailCategoryService.findAll();
                analysiCodes = analysisService.findAll();
                currencyTypes = currencyService.findAll();
//                assets = assetService.getAssetByTransactionNo(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while calling the loadAsset Method : " + e.getMessage());
        }
        model.addAttribute("detailCategory", assetCatergoryDetails);
        model.addAttribute("locations", locationMasters);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("analysiCodes", analysiCodes);
        model.addAttribute("currencyTypes", currencyTypes);
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("asset-data");

    }

    @RequestMapping(value = "saveAsset", method = RequestMethod.POST)
    public ModelAndView saveAsset(Asset assetForm, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
//        List<Asset> assets = new ArrayList<Asset>();
        List<AssetCatergoryDetail> assetCatergoryDetails = new ArrayList<AssetCatergoryDetail>();
//        List<AssetLocationDetail> assetLocationDetails = new ArrayList<AssetLocationDetail>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        Boolean isSave = false;
        String transactionNo = "";
        String tempTransactionNo = "";
        int noUnit = 0;
        String mainCode;
        try {
            assetCatergoryMains = mainCategoryService.findAll();
            companyMasters = companyService.findAll();
            locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            noUnit = assetForm.getNoOfUnit();
            mainCode = assetForm.getAsCode().substring(0, 3);

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assetForm.setUserId(user.getUserId());


                tempTransactionNo = getSerialNo(assetService.generateTransactionNo(), MAX_SERIAL);
                transactionNo = ASSET_CODE + tempTransactionNo;
                assetForm.setTransactionNo(transactionNo);
                model.addAttribute("transactionNo", transactionNo);


//                transactionNo = assetService.generateTransactionNo();
//                assetForm.setTransactionNo(transactionNo);


                isSave = assetService.saveAssetDetails(assetForm, file);
//                if (isSave == true) {
//                    mainCategoryService.assetUpdate(noUnit, mainCode);
//                }
            }
//            assets = assetService.getAssetByTransactionNo(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            assetCatergoryDetails = detailCategoryService.findAll();
            currencyTypes = currencyService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while calling the saveAsset Method : " + e.getMessage());
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("detailCategory", assetCatergoryDetails);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
//        model.addAttribute("assets", assets);
        //model.addAttribute("locations", assetLocationDetails);
        model.addAttribute("companyName", companyName);
        model.addAttribute("transactionNo", assetForm.getTransactionNo());
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("asset-data");

    }

    @RequestMapping(value = "deleteAsset/{trNo}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteMainCategory(@PathVariable("trNo") int trNo) {
        int isDelete = 0;
        try {
            isDelete = assetService.deleteAssets(trNo);
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteMainCategory Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "editAsset/{transactionNo}", method = RequestMethod.GET)
    public ModelAndView editAsset(@PathVariable("transactionNo") int transactionNo, Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        List<Asset> assets = new ArrayList<Asset>();
        List<AssetCatergoryDetail> assetCatergoryDetails = new ArrayList<AssetCatergoryDetail>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        Asset asset = null;
        LocationMaster assetLocation = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assetCatergoryMains = mainCategoryService.findAll();
                companyMasters = companyService.findAll();
                locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                assets = assetService.getAssetByTransactionNo(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                assetCatergoryDetails = detailCategoryService.findAll();
                asset = assetService.findAssetByTransactionNo(transactionNo);
                assetLocation = assetService.getAssetLocation(asset.getAsId());
                currencyTypes = currencyService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the editAsset Method : " + e.getMessage());
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("detailCategory", assetCatergoryDetails);
        model.addAttribute("asset", asset);
        model.addAttribute("assets", assets);
        model.addAttribute("assetLocation", assetLocation);
        model.addAttribute("companyName", companyName);
        model.addAttribute("transactionNo", asset.getTransactionNo());
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("asset-data");
    }

    @RequestMapping("disposal")
    public ModelAndView loadDisposal(Model model, HttpServletRequest request) {
//        List<Asset> assets = new ArrayList<Asset>();
        List<AssetDisposalReason> disposalReason = new ArrayList<AssetDisposalReason>();
        List<Disposal> disposals = new ArrayList<Disposal>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            disposalReason = disposalCategoryService.findAll();
            disposals = disposalService.findAll();
            currencyTypes = currencyService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDisposal Method : " + e.getMessage());
        }

//        model.addAttribute("assets", assets);
        model.addAttribute("reasons", disposalReason);
        model.addAttribute("disposals", disposals);
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("disposal-data");

    }

    @RequestMapping("getAssetAlocation/{assetId}")
    @ResponseBody
    public String getAssetAlocation(@PathVariable("assetId") int assetId, Model model) {
        String assetAllocation = "";
        try {
            assetAllocation = assetService.getAssetAlocation(assetId);
        } catch (Exception e) {
        }
        return assetAllocation;

    }

    @RequestMapping(value = "saveAssetDisposal", method = RequestMethod.POST)
    public ModelAndView saveDisposal(@ModelAttribute("disposalForm") Disposal disposal, Model model, HttpServletRequest request) {
//        List<Asset> assets = new ArrayList<Asset>();
        Boolean isSave = false;
        Boolean isRate = false;
        String tempTransactionNo = "", transactionNo = "";

        List<AssetDisposalReason> disposalReason = new ArrayList<AssetDisposalReason>();
        List<Disposal> disposals = new ArrayList<Disposal>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                disposal.setUserId(user.getUserId());
                disposal.setActionTime(date);
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                if (disposal.getDpId() <= 0) {
                    tempTransactionNo = getSerialNo(disposalService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = DISPOSAL_CODE + tempTransactionNo;
                    disposal.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                }
                Asset asset = assetService.getAsset(disposal.getAsset().getAsId());
                if (asset.getCurrencyType() == disposal.getCurrencyType()) { //check currency type with asset currency type
                    disposal.setDp_value(disposal.getCurrencyValue());
                    isRate = true;
                } else {// currency type not match
                    CurrencyRate currencyRate = currencyRateService.findRateByDate(disposal.getDpDate(), disposal.getCurrencyType(), asset.getCurrencyType());
                    if (currencyRate != null) { //is has currency in that id and Date
                        disposal.setDp_value(disposal.getCurrencyValue() * currencyRate.getExchangeRate());
                        isRate = true;
                    } else {
                        isRate = false;
                    }
                }

                if (isRate) { //currency type is matching
                    String msg = disposalService.addAssetDisposal(disposal);
                    if (msg.equals("Update Successful.")) {
                        isSave = true;
                    }
                }
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
            disposals = disposalService.findAll();
            disposalReason = disposalCategoryService.findAll();
            currencyTypes = currencyService.findAll();


        } catch (Exception e) {
            logger.error("Error occurred while calling the saveDisposal Method : " + e.getMessage());
            // TODO: handle exception
        }
        model.addAttribute("isRate", isRate);
        model.addAttribute("reasons", disposalReason);
//        model.addAttribute("assets", assets);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("disposals", disposals);
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("disposal-data");

    }

    @RequestMapping("transfers")
    public ModelAndView loadTransfers(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadTransfers Method : " + e.getMessage());
        }
        model.addAttribute("assets", assets);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("transfers-data");

    }

    @RequestMapping("loadBulkTransfer")
    public ModelAndView loadBulkTransfer(@RequestParam("assetId") int assetId, Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        Asset asset = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Asset asset1 = assetService.getAsset(assetId);
                assets = asset1.getLocationMaster().getAssets();
                departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
//                asset = assetService.getAsset(assetId);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadBulkTransfer Method : " + e.getMessage());
        }
        model.addAttribute("assets", assets);
        model.addAttribute("asset", asset);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("bulkTransfer-data");
    }

    @RequestMapping(value = "saveTransfers", method = RequestMethod.POST)
    public ModelAndView saveTransfers(TransferForm transferForm, Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        Boolean isSave = false;
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                transferForm.setUserId(user.getUserId());
                isSave = transferService.saveAssetTransfer(transferForm);
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveTransfers Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("assets", assets);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("transfers-data");
    }

    @RequestMapping("damage")
    public ModelAndView loadDamage(Model model, HttpServletRequest request) {
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDamage Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("damage-data");
    }

//    Asset Registration---> Asset Details View

    @RequestMapping("loadAssetByOldAssetCode/{userBranch}")
    @ResponseBody
    public List<Asset> loadAssetByOldAssetCode(@PathVariable("userBranch") int branch) {

        return assetService.loadAssetByOldAssetCode(branch);// only, not disposed assets

    }

    @RequestMapping("loadAssetByAssetCode/{userBranch}")
    @ResponseBody
    public List<Asset> loadAssetByAssetCode(@PathVariable("userBranch") int branch) {
        return assetService.loadAssetByAssetCode(branch);// only, not disposed assets
    }

//    @RequestMapping("loadAssetById/{trNo}")
//    @ResponseBody
//    public MaintenanceTeachingEquipment loadAssetById(@PathVariable("trNo") int trNo) {
//        return maintenanceTeachingEquipmentsService.getTeachingEquipmentsDetailsByAsset(trNo);
//    }


    //load assets tr no
    @RequestMapping("loadAssetByTrNo/{userBranch}")
    @ResponseBody
    public List<Asset> loadAssetByTrNo(@PathVariable("userBranch") int branch) {
        return assetService.loadAssetByTrNo(branch);// only, not disposed assets
    }

//    Asset code loading for maintenance

    @RequestMapping("loadAssetByTypes/{userBranch}/{type}")
    @ResponseBody
    public List<Asset> loadAssetByTypes(@PathVariable("userBranch") int branch, @PathVariable("type") int type) {
        return assetService.getAssetByType(branch, type);

    }

    @RequestMapping(value = "saveAssetDamage", method = RequestMethod.POST)
    public ModelAndView saveAssetDamage(@ModelAttribute("damageForm") Damage damage, Model model, HttpServletRequest request) {
//        List<Asset> assets = new ArrayList<Asset>();
        Boolean isSave = false;
        String tempTransactionNo = "", transactionNo = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                damage.setUserId(user.getUserId());
                damage.setUpdatedby(user.getUserId());

                if (damage.getDmgAsId() <= 0) {
                    tempTransactionNo = getSerialNo(damageService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = DAMAGE_CODE + tempTransactionNo;
                    damage.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                }

                String msg = damageService.addAssetDamage(damage);//done
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                if (msg.equals("Update Successful.")) {
                    isSave = true;
                }
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the saveAssetDamage Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("damage", new Damage());
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("damage-data");
    }

    @RequestMapping("incomeTax")
    public ModelAndView loadIncomeTax(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        List<Incometax> incometaxes = new ArrayList<Incometax>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            incometaxes = incomeTaxService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("assets", assets);
        model.addAttribute("incomTaxs", incometaxes);
        model.addAttribute("incomTax", new Incometax());
        return new ModelAndView("incomeTax-data");

    }

    @RequestMapping(value = "saveIncomeTax", method = RequestMethod.POST)
    public ModelAndView saveIncomeTax(@ModelAttribute("incomeTaxForm") Incometax incometax, Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        List<Incometax> incometaxes = new ArrayList<Incometax>();
        Boolean isSave = false;
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
//                String msg = incomeTaxService.add(incometax);
                Incometax incometaxs = incomeTaxService.saveUpdateTax(incometax);//done
                if (incometaxs != null) {
                    isSave = true;
                }
                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                incometaxes = incomeTaxService.findAll();
//                if (msg.equals("Update Successful.")) {
//                    isSave = true;
//                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveIncomeTax Method : " + e.getMessage());
        }
        model.addAttribute("assets", assets);
        model.addAttribute("incomTaxs", incometaxes);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("incomeTax-data");

    }

    @RequestMapping("editIncomTax/{taxId}")
    public ModelAndView editIncomTax(@PathVariable("taxId") int taxId, Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        List<Incometax> incometaxes = new ArrayList<Incometax>();
        Incometax incometax = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                incometax = incomeTaxService.findById(taxId);
                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                incometaxes = incomeTaxService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("assets", assets);
        model.addAttribute("incomTaxs", incometaxes);
        model.addAttribute("incomTax", incometax);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("incomeTax-data");
    }

    @RequestMapping("deleteIncomTax/{taxId}")
    @ResponseBody
    public int deleteIncomTax(@PathVariable("taxId") int taxId) {
        int isDelete = 0;
        try {
            incomeTaxService.delete(taxId);
            isDelete = 1;
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteIncomTax Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping("priorRegister")
    public ModelAndView loadPriorRegister(Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            assetCatergoryMains = mainCategoryService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadPriorRegister Method : " + e.getMessage());
        }
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("assetPriorRegister-data");

    }

    @RequestMapping(value = "savePriorReg", method = RequestMethod.POST)
    public ModelAndView savePriorReg(@ModelAttribute("priorRegForm") AssetPriorReg assetPriorReg, Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                assetPriorReg.setUserId(user.getUserId());
                assetPriorReg.setActionTime(date);
                AssetPriorReg assetPriorReg1 = assetPriorRegService.saveUpdateAssetPriReg(assetPriorReg);//done
                if (assetPriorReg1 != null) {
                    isSave = true;
                }
                assetCatergoryMains = mainCategoryService.findAll();

                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the savePriorReg Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("mainCategory", assetCatergoryMains);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("assetPriorRegister-data");

    }

    @RequestMapping("improvements")
    public ModelAndView improvement(Model model, HttpServletRequest request) {
//        List<Asset> assets = new ArrayList<Asset>();
//        List<AssetImprovement> assetImprovements = new ArrayList<AssetImprovement>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
//                assetImprovements = improvementService.findAll();
                currencyTypes = currencyService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("imp", new AssetImprovement());
//        model.addAttribute("assets", assets);
//        model.addAttribute("imps", assetImprovements);
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("improvement-data");
    }

    @RequestMapping("editImprovement/{impId}")
    public ModelAndView editImprovement(@PathVariable("impId") int impId, Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        List<AssetImprovement> assetImprovements = new ArrayList<AssetImprovement>();
        AssetImprovement assetImprovement = new AssetImprovement();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                assetImprovements = improvementService.findAll();
                assetImprovement = improvementService.findById(impId);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the editImprovement Method : " + e.getMessage());
        }
        model.addAttribute("imp", assetImprovement);
        model.addAttribute("assets", assets);
        model.addAttribute("imps", assetImprovements);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("improvement-data");
    }

    @RequestMapping(value = "saveImprovement", method = RequestMethod.POST)
    public ModelAndView saveImprovement(@ModelAttribute("improvementForm") AssetImprovement assetImprovement, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        Boolean isRate = false;
        String tempTransactionNo = "", transactionNo = "";
        List<AssetImprovement> assetImprovements = new ArrayList<AssetImprovement>();
        List<Asset> assets = new ArrayList<Asset>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                assetImprovement.setUserId(user.getUserId());
                assetImprovement.setActionTime(date);
                if (assetImprovement.getAssetImpId() <= 0) {
                    tempTransactionNo = getSerialNo(improvementService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = IMPROVEMENT_CODE + tempTransactionNo;
                    assetImprovement.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                }
                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                Asset asset = assetService.getAsset(assetImprovement.getAsset().getAsId());
                if (asset.getCurrencyType() == assetImprovement.getCurrencyType()) {
                    assetImprovement.setValue(assetImprovement.getCurrencyValue());
                    isRate = true;
                } else {
                    CurrencyRate currencyRate = currencyRateService.findRateByDate(assetImprovement.getImpDate(), assetImprovement.getCurrencyType(), asset.getCurrencyType());
                    if (currencyRate != null) {
                        assetImprovement.setValue(assetImprovement.getCurrencyValue() * currencyRate.getExchangeRate());
                    } else {
                        isRate = false;
                    }
                }
                if (isRate) {
                    AssetImprovement assetImprovement1 = improvementService.saveUpdateAssetImpro(assetImprovement);//done
                    if (assetImprovement1 != null) {
                        isSave = true;
                    }
                }
                assetImprovements = improvementService.findAll();

                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            currencyTypes = currencyService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveImprovement Method : " + e.getMessage());
        }
        model.addAttribute("isRate", isRate);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("imps", assetImprovements);
        model.addAttribute("imp", new AssetImprovement());
        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("improvement-data");
    }

    @RequestMapping("deleteImprovement/{impId}")
    @ResponseBody
    public int deleteImprovement(@PathVariable("impId") int impId) {
        int isDelete = 0;
        try {
            improvementService.delete(impId);
            isDelete = 1;
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteImprovement Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping("checkDisposalDate")
    @ResponseBody
    public int checkDisposalDate(@RequestParam("date") String date, @RequestParam("assetId") int assetId) {
        int status = 1;
        try {
            status = disposalCategoryService.checkDisposalCode(simpleDateFormat.parse(date), assetId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDisposalDate Method : " + e.getMessage());
            status = 1;
        }
        return status;
    }

    @RequestMapping("checkImprovementDate")
    @ResponseBody
    public int checkImprovementDate(@RequestParam("date") String date, @RequestParam("assetId") int assetId) {
        int status = 1;
        try {
            status = improvementService.checkDisposalCode(simpleDateFormat.parse(date), assetId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDisposalDate Method : " + e.getMessage());
            status = 1;
        }
        return status;
    }

    @RequestMapping(value = "getAssetsBySameLoacation/{assetId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getAssetsBySameLoacation(@PathVariable("assetId") int assetId) {
        List<String> list = new ArrayList<String>();
        try {
            Asset asset = assetService.getAsset(assetId);
            for (Asset asset1 : asset.getLocationMaster().getAssets()) {
                list.add(asset1.getAsCode());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getAssetsBySameLocation Method : " + e.getMessage());
        }
        return list;
    }

    @RequestMapping("physicalVerification")
    public ModelAndView physicalVerification(Model model, HttpServletRequest request) {
//        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            locationMasters = locationService.findAllByBranch(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the physicalVerification Method : " + e.getMessage());
        }
//        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("physicalVerification-data");
    }

    @RequestMapping(value = "loadAssetByDepartmentId/{depId}/{detailId}/{fromDate}/{toDate}/{userBranch}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> loadAssetByDepartmentId(@PathVariable("depId") int depId, @PathVariable("detailId") int detailId, @PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate, @PathVariable("userBranch") int userBranch) {
        List<Asset> locationCodes = new ArrayList<Asset>();
        List<String> locationCodes1 = new ArrayList<String>();
        try {
            if (userBranch == 0) {
                locationCodes = assetService.getAssetsByDepatment(depId, detailId, fromDate, toDate);
            } else {
                locationCodes = assetService.getAssetsByDepatment(userBranch, detailId, fromDate, toDate);
            }
            for (Iterator<Asset> iterator = locationCodes.iterator(); iterator.hasNext(); ) {
                Asset asset = iterator.next();
                locationCodes1.add(asset.getAsId() + "-" + asset.getAsCode() + "-" + asset.getAsDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDisposalDate Method : " + e.getMessage());
            // status = 1;
        }
        return locationCodes1;
    }

    @RequestMapping(value = "loadAssetByDepartmentId/{depId}/{detailId}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> loadAssetByDepartmentId(@PathVariable("depId") int depId, @PathVariable("detailId") int detailId) {
        List<Asset> locationCodes = new ArrayList<Asset>();
        List<String> locationCodes1 = new ArrayList<String>();
        try {
            locationCodes = assetService.getAssetsByDepatment(depId, detailId);
            for (Iterator<Asset> iterator = locationCodes.iterator(); iterator.hasNext(); ) {
                Asset asset = iterator.next();
                locationCodes1.add(asset.getAsId() + "-" + asset.getAsCode() + "-" + asset.getAsDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDisposalDate Method : " + e.getMessage());
            // status = 1;
        }
        return locationCodes1;
    }

    @RequestMapping("getAssetByLocation/{locId}")
    @ResponseBody
    public List<Asset> loadAssetByLocation(@PathVariable("locId") int locId) {
        return assetService.getAssetDetails(locId);
    }

    @RequestMapping("getAssetByLocation/{locId}/{assetCodeIdval}")
    @ResponseBody
    public List<Asset> loadAssetByLocation(@PathVariable("locId") int locId, @PathVariable("assetCodeIdval") String assetCodeIdval) {
        if (assetCodeIdval.equals("0")) {
            return assetService.getAssetDetails(locId);
        } else {
            return assetService.getAssetDetailsSearch(locId, assetCodeIdval);
        }
    }

    @RequestMapping("getAssetByDep/{depId}")
    @ResponseBody
    public List<Asset> getAssetByDep(@PathVariable("depId") int depId) {
        List<Asset> assets = new ArrayList<Asset>();
        try {
            assets = assetService.getAssetsByDepatment(depId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the getAssetByDep Method : " + e.getMessage());
        }
        return assets;
    }

    @RequestMapping("getAssetDetailCat/{detailCat}")
    @ResponseBody
    public String getAssetDetailCat(@PathVariable("detailCat") int detailCat) {
        String description = "";
        try {
            AssetCatergoryDetail assetCatergoryDetail = detailCategoryService.findById(detailCat);
            if (assetCatergoryDetail != null) {
                description = assetCatergoryDetail.getDcatDes();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getAssetDetailCat Method : " + e.getMessage());
        }
        return description;
    }

    @RequestMapping("getAssetDetailCat/{detailCat}/{assetId}")
    @ResponseBody
    public Asset getAssetDetailCat(@PathVariable("detailCat") int detailCat, @PathVariable("assetId") String assetCode) {
        Asset asset = new Asset();
        double unitPrice = 0;
        try {
            AssetCatergoryDetail assetCatergoryDetail = detailCategoryService.findById(detailCat);
            if (assetCatergoryDetail != null) {
                asset.setAsDes(assetCatergoryDetail.getDcatDes());
            }
            Asset assets = assetService.getAssetCodes(assetCode);
            if (assets != null) {
                unitPrice = assets.getUnitPrice();
                Currency currency = currencyService.findById(assets.getCurrencyType());
                if (currency != null) {
                    asset.setCurrencyCode(currency.getCurrencyCode());
                }
                asset.setCurrencyType(assets.getCurrencyType());
            }

            asset.setUnitPrice(unitPrice);

        } catch (Exception e) {
            logger.error("Error occurred while calling the getAssetDetailCat Method : " + e.getMessage());
        }
        return asset;
    }

    @RequestMapping(value = "saveVerification", method = RequestMethod.POST)
    public ModelAndView savePhysicalVerification(PhysicalVerification verification, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                verification.setUserId(user.getUserId());
                verification.setActionTime(date);
                String msg = verificationService.saveVerification(verification);//done
                if (msg.equals("Update Successful.")) {
                    isSave = true;
                }
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            locationMasters = locationService.findAllByBranch(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the savePhysicalVerification Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("physicalVerification-data");
    }

    @RequestMapping("loadExitsVerifications/{date}/{locCodeId}")
    @ResponseBody
    public List<PhysicalVerification> loadExitsVerifications(@PathVariable("date") String date, @PathVariable("locCodeId") String locationId) {
        List<PhysicalVerification> physicalVerifications = new ArrayList<PhysicalVerification>();
        try {
            physicalVerifications = verificationService.loadVerification(simpleDateFormat.parse(date), Integer.parseInt(locationId.trim()));
        } catch (Exception e) {
        }
        return physicalVerifications;
    }

    @RequestMapping("loadAssetSearchData/{date}/{locCodeId}")
    @ResponseBody
    public List loadAssetSearchData(@PathVariable("date") String date, @PathVariable("locCodeId") String locationId) {
        try {
            return assetSearchHistoryService.loadVerification(simpleDateFormat.parse(date), Integer.parseInt(locationId.trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("registerExistingAsset")
    public ModelAndView registerExistingAsset(Model model, HttpServletRequest request) {
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the registerExistingAsset Method : " + e.getMessage());
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("registerExistingAsset-data");
    }

    @RequestMapping("viewAsset")
    public ModelAndView viewAssets(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            assets = assetService.getAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            for (Iterator<Asset> iterator = assets.iterator(); iterator.hasNext(); ) {
                Asset asset = iterator.next();
                asset.setCurrencyCode(String.format("%1$,.2f", asset.getUnitPrice())); //[1].setCurrencyCode("");

            }


        } catch (Exception e) {
            logger.error("Error occurred while calling the viewAssets Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("assets", assets);
        return new ModelAndView("viewAsset-data");
    }

    @RequestMapping("assetRevaluation")
    public ModelAndView assetRevaluation(Model model, HttpServletRequest request) {
//        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            locationMasters = locationService.findAllByBranch(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the Asset Revaluation Method : " + e.getMessage());
        }
//        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("assetRevaluation-data");
    }

//    @RequestMapping("deleteAnalysisData/{impId}")
//    @ResponseBody
//    public int deleteAnalysisData(@PathVariable("impId") int impId) {
//        int isDelete = 0;
//        try {
//            analysisService.delete(impId);
//            isDelete = 1;
//        } catch (Exception e) {
//            logger.error("Error occurred while calling the deleteAnalysis Method : " + e.getMessage());
//            isDelete = 0;
//        }
//        return isDelete;
//    }

    @RequestMapping("deleteAnalysisData/{impId}")
    @ResponseBody
    public int deleteAnalysisData(@PathVariable("impId") int impId) {
        int isDelete = 0;
        try {
            analysisService.delete(impId);
            isDelete = 1;
        } catch (Exception e) {
            logger.error("Error occurred while calling the delete AnalysisData Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "saveAssetRevaluation", method = RequestMethod.POST)
    public ModelAndView saveAssetRevaluation(AssetRevaluation assetRevaluation, Model model, HttpServletRequest request) {
        Boolean isSave = false;
//        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                assetRevaluation.setUserId(user.getUserId());
                assetRevaluation.setActionTime(date);
                String msg = revaluationService.saveRevaluation(assetRevaluation);//done
                if (msg.equals("Update Successful.")) {
                    isSave = true;
                }
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            locationMasters = locationService.findAllByBranch(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveAssetRevaluation Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
//        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("assetRevaluation-data");
    }

    @RequestMapping("loadExitsRevaluation/{date}/{locCodeId}")
    @ResponseBody
    public List<AssetRevaluation> loadExitsRevaluation(@PathVariable("date") String date, @PathVariable("locCodeId") String locationId) {
        List<AssetRevaluation> assetRevaluations = new ArrayList<AssetRevaluation>();
        try {
            assetRevaluations = revaluationService.loadVerification(simpleDateFormat.parse(date), Integer.parseInt(locationId));
        } catch (Exception e) {
        }
        return assetRevaluations;
    }

    @RequestMapping("loadDamageNoList/{assetId}/{userBranch}")
    @ResponseBody
    public List loadDamageNoList(@PathVariable("assetId") int assetId, @PathVariable("userBranch") int branch) {
        return damageService.getTransactionNoList(assetId, branch);
    }

    @RequestMapping("loadDamageDetailsByDamageId/{damageId}")
    @ResponseBody
    public List loadDamageDetailsByDamageId(@PathVariable("damageId") int damageId) {
        return damageService.getDamageDetails(damageId);
    }


    @RequestMapping("loadDisposeNoList/{userBranch}")
    @ResponseBody
    public List loadDisposeNoList(@PathVariable("userBranch") int branch) {
        return disposalService.getTransactionNoList(branch);
    }

    @RequestMapping("loadImprovementNoList/{assetId}/{userBranch}")
    @ResponseBody
    public List loadImprovementNoList(@PathVariable("assetId") int assetId, @PathVariable("userBranch") int branch) {
        return improvementService.getTransactionNoList(assetId, branch);
    }

    @RequestMapping("loadImprovementDetailsByImprovementId/{improvementId}")
    @ResponseBody
    public List loadImprovementDetailsByImprovementId(@PathVariable("improvementId") int improvementId) {
        return improvementService.getImprovementDetails(improvementId);
    }

    @RequestMapping("loadNoDataForDashBord/{branchId}")
    @ResponseBody
    public int[] loadComputerDetailsByAsset(@PathVariable("branchId") int branchId) {
        int[] dashValue = new int[4];
        dashValue[0] = assetService.getNoOfAssets(branchId);
        dashValue[1] = damageService.getNoOfDamages(branchId);
        dashValue[2] = disposalService.getNoOfDisposed(branchId);
        dashValue[3] = transferService.getNoOfTransferd(branchId);


        return dashValue;
    }

    @RequestMapping("viewAssetData/{assetId}")
    public ModelAndView loadViewAssetData(@PathVariable("assetId") int assetId, Model model, HttpServletRequest request) {
        String assetCode = "";
        String oldAssetCode = "";
        String assetDescription = "";
        String assetPrice = "0.00";
        Date assetRegisterdDate;
        String assetRegDate = "";
        String qrPath = "";
        String DocPath = "";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Asset asset = assetService.getAsset(assetId);
                assetCode = asset.getAsCode();
                oldAssetCode = asset.getAsCodeOld();
                assetDescription = asset.getAsDes();
                assetPrice = String.format("%1$,.2f", asset.getUnitPrice());
                assetRegisterdDate = asset.getRegDate();
                assetRegDate = simpleDateFormat.format(assetRegisterdDate);
                qrPath = asset.getQrPath();
                DocPath = asset.getFilePath();

                //write image
                try {
                    String imgName = qrPath;
                    BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bImage, "jpg", baos);
                    baos.flush();
                    byte[] imageInByteArray = baos.toByteArray();
                    baos.close();
                    qrPath = DatatypeConverter.printBase64Binary(imageInByteArray);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }

                //write image
                try {
                    String imgName = DocPath;
                    BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bImage, "jpg", baos);
                    baos.flush();
                    byte[] imageInByteArray = baos.toByteArray();
                    baos.close();
                    DocPath = DatatypeConverter.printBase64Binary(imageInByteArray);
//                    %>
//                    <img  class="img-responsive" src="data:image/jpg;base64, <%=b64%>"/>
//                    <%
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }

            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDisposal Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("assetCode", assetCode);
        model.addAttribute("oldAssetCode", oldAssetCode);
        model.addAttribute("assetDescription", assetDescription);
        model.addAttribute("assetPrice", assetPrice);
        model.addAttribute("assetRegisterdDate", assetRegDate);
        model.addAttribute("qrPath", qrPath);
        model.addAttribute("DocPath", DocPath);
        return new ModelAndView("viewAssetData-data");
    }

    @RequestMapping("loadDisposalByTRNo/{disposalTrNo}")
    @ResponseBody
    public List loadDisposalByTRNo(@PathVariable("disposalTrNo") int transactionNo) {
        return disposalService.loadDisposalByTRNo(transactionNo);
    }

    @RequestMapping("loadCurrencyCode/{currencyId}")
    @ResponseBody
    public String loadCurrencyCode(@PathVariable("currencyId") int currencyId) {
        String ss = currencyService.findCurrencyId(currencyId);
        return ss;
    }

    @RequestMapping("getCurrencyCodes")
    @ResponseBody
    public List<Currency> getCurrencyCodes() {
        return currencyService.findAll();
    }

    @RequestMapping("checkAssetCodeValid/{assetCode}")
    @ResponseBody
    public int checkAssetCodeValid(@PathVariable("assetCode") String assetCode) {
        Asset asset = assetService.getAssetCodes(assetCode);
        if (asset == null) {
            return 0;
        } else {
            return asset.getAsId();
        }

    }

    @RequestMapping("checkAssetTrNoValid/{trNo}")
    @ResponseBody
    public int checkAssetTrNoValid(@PathVariable("trNo") String trNo) {
        Asset asset = assetService.getAssetTrNo(trNo);
        if (asset == null) {
            return 0;
        } else {
            return asset.getAsId();
        }

    }

    @RequestMapping("loadLocationCode/{userBranch}")
    @ResponseBody
    public List<LocationMaster> loadLocationCode(@PathVariable("userBranch") int branch) {
        return locationService.findAllByBranch(branch);// only not disposed assets
    }


    @RequestMapping("loadAssetDetail/{assetId}")
    @ResponseBody
    public List<String> loadAssetDetail(@PathVariable("assetId") int assetId) {
        List<String> assetDetails = new ArrayList<String>();
        Asset asset = assetService.findById(assetId);

        System.out.println("assetId : " + assetId);
        assetDetails.add(0, asset.getAsCode() == null ? "" : asset.getAsCode());
        assetDetails.add(1, asset.getAsCodeOld() == null ? "" : asset.getAsCodeOld());
        assetDetails.add(2, asset.getAsDes() == null ? "" : asset.getAsDes());
        assetDetails.add(3, asset.getAnalysisCode() == null ? "" : asset.getAnalysisCode());
        assetDetails.add(4, String.valueOf(asset.getAssetCatergoryDetail().getAssetCatergorySub().getAssetCatergoryMain().getMcatId()));

        assetDetails.add(5, String.valueOf(asset.getAssetCatergoryDetail().getAssetCatergorySub().getScatId()));
        assetDetails.add(6, String.valueOf(asset.getAssetCatergoryDetail().getAssetCatergorySub().getScatCode()));
        assetDetails.add(7, String.valueOf(asset.getAssetCatergoryDetail().getAssetCatergorySub().getScatDes()));

        assetDetails.add(8, String.valueOf(asset.getAssetCatergoryDetail().getDcatId()));
        assetDetails.add(9, String.valueOf(asset.getAssetCatergoryDetail().getDcatCode()));
        assetDetails.add(10, String.valueOf(asset.getAssetCatergoryDetail().getDcatDes()));

        assetDetails.add(11, asset.getPoNo() == null ? "" : asset.getPoNo());
        assetDetails.add(12, asset.getPoDate() == null ? "" : asset.getPoDate());

        assetDetails.add(13, asset.getGrnNo() == null ? "" : asset.getGrnNo());
        assetDetails.add(14, asset.getGrnDate() == null ? "" : asset.getGrnDate());

        assetDetails.add(15, asset.getIssueNoteNo() == null ? "" : asset.getIssueNoteNo());
        assetDetails.add(16, asset.getIssueNoteDate() == null ? "" : asset.getIssueNoteDate());

        assetDetails.add(17, asset.getSuppliersInvoiceNo() == null ? "" : asset.getSuppliersInvoiceNo());
        assetDetails.add(18, asset.getInvoiceDate() == null ? "" : asset.getInvoiceDate());

        assetDetails.add(19, asset.getSupplierName() ==null?"":asset.getSupplierName());
        assetDetails.add(20, asset.getAddress() == null?"":asset.getAddress());
        assetDetails.add(21, asset.getTelephoneNo() == null?"": asset.getTelephoneNo());

        assetDetails.add(22, String.valueOf(asset.getPurDate()==null?"":asset.getPurDate()));
        assetDetails.add(23, String.valueOf(asset.getRegDate()==null?"":asset.getRegDate()));
        assetDetails.add(24, String.valueOf(asset.getNoOfUnit()));

        assetDetails.add(25, String.valueOf(asset.getCurrencyType()));

        assetDetails.add(26, String.valueOf(asset.getUnitPrice()));
        assetDetails.add(27, String.valueOf(asset.getAmount()));
        assetDetails.add(28, String.valueOf(asset.getManRegNo()==null?"":asset.getManRegNo()));
        assetDetails.add(29, asset.getManufacturer()==null?"":asset.getManufacturer());
        assetDetails.add(30, String.valueOf(asset.getOriginalCost()==null?"":asset.getOriginalCost()));
        assetDetails.add(31, asset.getFundingSource()==null?"":asset.getFundingSource());

        assetDetails.add(32, asset.getLedgerCode()==null?"":asset.getLedgerCode());
        assetDetails.add(33, asset.getLifeTime()==null?"": asset.getLifeTime());

        assetDetails.add(34, String.valueOf(asset.getLocationMaster().getSectionMaster().getDepartmentMaster().getCompanyMaster().getComId()));

        assetDetails.add(35, String.valueOf(asset.getLocationMaster().getSectionMaster().getDepartmentMaster().getDepId()));
        assetDetails.add(36, String.valueOf(asset.getLocationMaster().getSectionMaster().getDepartmentMaster().getDepCode()));
        assetDetails.add(37, String.valueOf(asset.getLocationMaster().getSectionMaster().getDepartmentMaster().getDepDes()));

        assetDetails.add(38, String.valueOf(asset.getLocationMaster().getSectionMaster().getSecId()));
        assetDetails.add(39, String.valueOf(asset.getLocationMaster().getSectionMaster().getSecCode()));
        assetDetails.add(40, String.valueOf(asset.getLocationMaster().getSectionMaster().getSecDes()));

        assetDetails.add(41, String.valueOf(asset.getLocationMaster().getLocId()));
        assetDetails.add(42, String.valueOf(asset.getLocationMaster().getLocCode()));
        assetDetails.add(43, String.valueOf(asset.getLocationMaster().getLocDes()));

        assetDetails.add(44, asset.getAuthPerson()==null?"":asset.getAuthPerson());
        assetDetails.add(45, String.valueOf(asset.getIsLeasing()));

//        assetDetails.add(46,asset.getFilePath());

        String DocPath = asset.getFilePath();
        System.out.println("assetDetails : " + asset.getFilePath());


        try {
            String imgName = DocPath;
            BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", baos);
            baos.flush();
            byte[] imageInByteArray = baos.toByteArray();
            baos.close();
            DocPath = DatatypeConverter.printBase64Binary(imageInByteArray);
//                    %>
//                    <img  class="img-responsive" src="data:image/jpg;base64, <%=b64%>"/>
//                    <%
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        assetDetails.add(46,DocPath);
        System.out.println("DocPath : " + DocPath);

        return assetDetails;
    }


//
//    @RequestMapping("getSubCode/{id}")
//    @ResponseBody
//    public String getSubCode(@PathVariable("id") int subId) {
//        return assetService.getSubCode(subId);
//    }


//    @RequestMapping("edit/{assetId}")
//    public ModelAndView edit(@PathVariable("assetId") int assetId, Model model, HttpServletRequest request) {
//
//        Asset asset = new Asset();
//        try {
//            HttpSession session = request.getSession();
//            if (session.getAttribute("jobList") != null) {
//                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
//                model.addAttribute("jobList", defineList);
//              ;
//                asset = assetService.findById(assetId);
//            }
//        } catch (Exception e) {
//            logger.error("Error occurred while calling the editImprovement Method : " + e.getMessage());
//        }
//        model.addAttribute("asset", asset);
////        model.addAttribute("imps", assetImprovements);
//        model.addAttribute("companyName", companyName);
//
//        return new ModelAndView("asset-data");
//    }

    @RequestMapping("loadTransactionNo/{assetIdFrom}/{assetIdTo}")
    @ResponseBody
    public Integer loadTransactionNo(@PathVariable("assetIdFrom") int id1, @PathVariable("assetIdTo") int id2) {
        int Tno1 = assetService.getTransactionNo(id1);
        int Tno2 = assetService.getTransactionNo(id2);
        int state = 0;

        if (Tno1 == Tno2) {
            state = 1;
        }
        return state;
    }
}