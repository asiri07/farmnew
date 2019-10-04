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
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.repository.MaintenanceComputerHistoryRepository;
import com.msd.fixAssetRegister.service.*;
import com.msd.fixAssetRegister.service.maintenance.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    UserService userService;
    @Autowired
    MaintenanceLandService maintenanceLandService;
    @Autowired
    AssetService assetService;
    @Autowired
    MaintenanceLandBulidingService maintenanceLandBulidingService;
    @Autowired
    MaintenanceVehicleService maintenanceVehicleService;
    @Autowired
    MaintenanceComputerService maintenanceComputerService;
    @Autowired
    MaintenanceInsuranceService maintenanceInsuranceService;
    @Autowired
    MaintenancePlantMachinaryService maintenancePlantMachinaryService;
    @Autowired
    MaintenanceWarrantyService maintenanceWarrantyService;
    @Autowired
    MaintenanceFixturesAndFittingService maintenanceFixturesAndFittingService;
    @Autowired
    MaintenanceLabEquipmentsService maintenanceLabEquipmentsService;
    @Autowired
    MaintenanceFurnitureService maintenanceFurnitureService;
    @Autowired
    MaintenanceTeachingEquipmentService maintenanceTeachingEquipmentsService;
    @Autowired
    MaintenanceModificationService maintenanceModificationService;
    @Autowired
    MaintenanceOfficeEquipmentService maintenanceOfficeEquipmentService;
    @Autowired
    MaintenanceMaintenanceDataService maintenanceMaintenanceDataService;
    @Autowired
    MaintenanceRunningDataService maintenanceRunningDataService;
    @Autowired
    MaintenanceServiceAgreementService maintenanceServiceAgreementService;
    @Autowired
    MaintenanceLibraryBooksService maintenanceLibraryBooksService;
    @Autowired
    MaintenanceSoftwareService maintenanceSoftwareService;
    @Autowired
    MaintenanceSportEquipmentService maintenanceSportEquipmentService;

    @Autowired
    MaintenanceLeaseAssetService maintenanceLeaseAssetService;

    @Autowired
    MaintenanceComputerHistoryService maintenanceComputerHistoryService;

    @Autowired
    MaintenanceVehicleHistoryService maintenanceVehicleHistoryService;

    @Autowired
    MaintenancePlantMachinaryHistoryService maintenancePlantMachinaryHistoryService;

    @Autowired
    MaintenanceFurnitureHistoryService maintenanceFurnitureHistoryService;

    @Autowired
    MaintenanceOfficeEquipmentHistoryService maintenanceOfficeEquipmentHistoryService;

    @Autowired
    MaintenanceLabEquipmentsHistoryService MaintenanceLabEquipmentsHistoryService;

    @Autowired
    MaintenanceTeachingEquipmentHistoryService maintenanceTeachingEquipmentHistoryService;

    @Autowired
    MaintenanceFixturesAndFittingHistoryService maintenanceFixturesAndFittingHistoryService;

    @Autowired
    MaintenanceLibraryBooksHistoryService maintenanceLibraryBooksHistoryService;

    @Autowired
    MaintenanceSportEquipmentHistoryService maintenanceSportEquipmentHistoryService;

    @Autowired
    MaintenanceMaintenanceDataHistoryService maintenanceMaintenanceDataHistoryService;

    @Autowired
    MaintenanceRunningDataHistoryService maintenanceRunningDataHistoryService;
    @Autowired
    MaintenanceSoftwareHistoryService maintenanceSoftwareHistoryService;
    @Autowired
    MaintenanceInsuranceHistoryService maintenanceInsuranceHistoryService;

    @Autowired
    MaintenanceWarrantyHistoryService maintenanceWarrantyHistoryService;

    @Autowired
    MaintenanceServiceAgreementsHistoryService maintenanceServiceAgreementsHistoryService;

    @Autowired
    MaintenanceModificationHistoryService maintenanceModificationHistoryService;

    @Autowired
    MaintenanceLeaseAssetHistoryService maintenanceLeaseAssetHistoryService;
    @Autowired
    MaintenanceLandHistoryService maintenanceLandHistoryService;

    @Autowired
    MaintenanceLandBuildingHistoryService maintenanceLandBuildingHistoryService;

    @Autowired
    AssetsTypesService assetsTypesService;

    @Autowired
    AssetTypeAssingService assetTypeAssingService;


    @Autowired
    CurrencyService currencyService;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    int MAX_SERIAL = 6;
    String WARRANTY_CODE = "WA";
    String SERV_AGRE_CODE = "SA";
    String MODIFICATION_CODE = "MO";
    String INSURANCE_CODE = "IN";
    String MAINTENANCE_DATA_CODE = "MD";
    String RUNNING_DATA_CODE = "RD";
    String LEASE_ASSET_CODE = "LA";
    @Value("${application.companyName}")
    private String companyName;

    public static String getSerialNo(int num, int digits) {
        String output = Integer.toString(num);
        while (output.length() < digits) output = "0" + output;
        return output;
    }

    @RequestMapping("land")
    public ModelAndView loadLand(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            defineList = (List<JobDefine>) session.getAttribute("jobList");
//            assets = assetService.getAssetByTypeLand(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            model.addAttribute("jobList", defineList);
        }

//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("land", new MaintenanceLand());
        return new ModelAndView("land-maintenance");
    }

    @RequestMapping(value = "saveLandDetail", method = RequestMethod.POST)
    public ModelAndView saveLand(@ModelAttribute("landPrimaryForm") MaintenanceLand maintenanceLand, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = new ArrayList<>();
                defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                maintenanceLand.setActionTime(date);
                maintenanceLand.setUserId(user.getUserId());
                if (maintenanceLand.getLandId() != 0) {
                    MaintenanceLand maintenanceLandOld = maintenanceLandService.findById(maintenanceLand.getLandId());
                    maintenanceLandHistoryService.updateHistory(maintenanceLandOld, 0, user.getUserId());
                    update = 1;
                }
                maintenanceLand.setDeedValue(Double.parseDouble(maintenanceLand.getAmount().toString().replace(",", "")));
                Double var = Double.parseDouble(maintenanceLand.getAssesmentValue().replace(",", ""));
                maintenanceLand.setAssesmentValue(var.toString());
                MaintenanceLand maintenanceLand1 = maintenanceLandService.saveUpdateMaintenanceLand(maintenanceLand);
                if (maintenanceLand1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeLand(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveLand Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("land", new MaintenanceLand());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("land-maintenance");
    }

    @RequestMapping("landBuilding")
    public ModelAndView loadLandBuilding(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            defineList = (List<JobDefine>) session.getAttribute("jobList");
            model.addAttribute("jobList", defineList);
//            assets = assetService.getAssetByTypeBuilding(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("landBuilding", new MaintenanceLandBuliding());
        return new ModelAndView("landBuilding-maintenance");
    }

    @RequestMapping(value = "saveLandBuilding", method = RequestMethod.POST)
    public ModelAndView saveLandBuilding(@ModelAttribute("landBuildingForm") MaintenanceLandBuliding maintenanceLandBuliding, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                maintenanceLandBuliding.setActionTime(date);
                maintenanceLandBuliding.setUserId(user.getUserId());
                if (maintenanceLandBuliding.getLandBuildingID() != 0) {
                    MaintenanceLandBuliding maintenanceLandBuildingOld = maintenanceLandBulidingService.findById(maintenanceLandBuliding.getLandBuildingID());
                    maintenanceLandBuildingHistoryService.updateHistory(maintenanceLandBuildingOld, 0, user.getUserId());
                    update = 1;
                }
                maintenanceLandBuliding.setDeedValue(Double.parseDouble(maintenanceLandBuliding.getAmount().toString().replace(",", "")));
                Double var = Double.parseDouble(maintenanceLandBuliding.getAssessementValue().replace(",", ""));
                maintenanceLandBuliding.setAssessementValue(var.toString());
                MaintenanceLandBuliding maintenanceLandBuliding1 = maintenanceLandBulidingService.saveUpdateMaintenaceLandBuilding(maintenanceLandBuliding);
                if (maintenanceLandBuliding1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeBuilding();
//                assets = assetService.getAssetByTypeBuilding(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveLandAndBuilding Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("landBuilding", new MaintenanceLandBuliding());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("landBuilding-maintenance");
    }

    @RequestMapping("vehicle")
    public ModelAndView loadVehicle(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeVehicle(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadVehicle Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("vehicle", new MaintenanceVehicle());
        return new ModelAndView("vehicle-maintenance");
    }

    @RequestMapping(value = "saveVehicle", method = RequestMethod.POST)
    public ModelAndView saveVehicle(@ModelAttribute("vehicleForm") MaintenanceVehicle maintenanceVehicle, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                maintenanceVehicle.setUserId(user.getUserId());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                maintenanceVehicle.setActionTime(date);
                if (maintenanceVehicle.getVehicleId() != 0) {
                    MaintenanceVehicle maintenanceVehicle1Old = maintenanceVehicleService.findById(maintenanceVehicle.getVehicleId());
                    maintenanceVehicleHistoryService.updateHistory(maintenanceVehicle1Old, 0, user.getUserId());//done
                    update = 1;
                }
                MaintenanceVehicle maintenanceVehicle1 = maintenanceVehicleService.saveUpdateMaintenanceVehicle(maintenanceVehicle);
                if (maintenanceVehicle1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeVehicle(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveVehicle Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("computer", new MaintenanceVehicle());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("vehicle-maintenance");
    }

    @RequestMapping("computer")
    public ModelAndView loadComputer(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeComputer(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadVehicle Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("computer", new MaintenanceComputer());
        return new ModelAndView("computer-maintenance");
    }

    @RequestMapping(value = "saveComputer", method = RequestMethod.POST)
    public ModelAndView saveComputer(@ModelAttribute("computerForm") MaintenanceComputer maintenanceComputer, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceComputer.setUserId(user.getUserId());
                maintenanceComputer.setActionTime(date);
                if (maintenanceComputer.getComputerId() != 0) {
                    MaintenanceComputer maintenanceComputerOld = maintenanceComputerService.findById(maintenanceComputer.getComputerId());
                    maintenanceComputerHistoryService.updateHistory(maintenanceComputerOld, 0, user.getUserId());//done
                    update = 1;
                }
                MaintenanceComputer maintenanceComputer1 = maintenanceComputerService.saveUpdateMaintenanceComputer(maintenanceComputer);
                if (maintenanceComputer1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeComputer(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveComputer Method : " + e.getMessage());
        }

//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("insurance", new MaintenanceInsurance());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("computer-maintenance");
    }

    @RequestMapping("plantMachinery")
    public ModelAndView loadPlantMachinery(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypePlantMachinary(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadPlantMachinery Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("plantMachinery", new MaintenancePlantMachinary());
        return new ModelAndView("plantMachinery-maintenance");
    }

    @RequestMapping(value = "savePlantMachinery", method = RequestMethod.POST)
    public ModelAndView savePlantMachinery(@ModelAttribute("plantMachineryForm") MaintenancePlantMachinary maintenancePlantMachinary, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenancePlantMachinary.setUserId(user.getUserId());
                maintenancePlantMachinary.setActionTime(date);
                if (maintenancePlantMachinary.getPlantId() != 0) {
                    MaintenancePlantMachinary maintenancePlantMachinaryOld = maintenancePlantMachinaryService.findById(maintenancePlantMachinary.getPlantId());
                    maintenancePlantMachinaryHistoryService.updateHistory(maintenancePlantMachinaryOld, 0, user.getUserId());
                    update = 1;
                }
                MaintenancePlantMachinary maintenancePlantMachinary1 = maintenancePlantMachinaryService.saveUpdateMaintenancePlantMachinary(maintenancePlantMachinary);
                if (maintenancePlantMachinary1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypePlantMachinary(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the savePlantMachinery Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("plantMachinery", new MaintenancePlantMachinary());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("plantMachinery-maintenance");
    }

    @RequestMapping("furniture")
    public ModelAndView loadFurniture(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeFurniture(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the furniture Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("furniture", new MaintenancePlantMachinary());
        return new ModelAndView("furniture-maintenance");
    }


    @RequestMapping(value = "saveFurniture", method = RequestMethod.POST)
    public ModelAndView saveFurniture(@ModelAttribute("furnitureForm") MaintenanceFurniture maintenanceFurniture, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model = model.addAttribute("jobList", defineList);
                maintenanceFurniture.setUserId(user.getUserId());
                maintenanceFurniture.setActionTime(date);
                String msg = "";
                int furnitureId;
                MaintenanceFurniture maintenanceFurniture1 = null;

                int asIdFrom = maintenanceFurniture.getAssetIdFrom();
                int asIdTo = maintenanceFurniture.getAssetIdTo();
                furnitureId = maintenanceFurniture.getFurnitureId();
                int maxFunitureId = maintenanceFurnitureService.getMaxFurnitureId();
                if (asIdTo == 0) {
                    asIdTo = asIdFrom;
                }
                int i;
                for (i = asIdFrom; i <= asIdTo; i++) {
                    if (furnitureId <= maxFunitureId && furnitureId != 0) {
                        MaintenanceFurniture maintenanceFurnitureOld = maintenanceFurnitureService.findById(furnitureId);
                        maintenanceFurnitureHistoryService.updateHistory(maintenanceFurnitureOld, 0, user.getUserId());
                        update = 1;
                    }
//                        maintenanceFurnitureService.deleteEntry(i);
                    if (furnitureId == 0) {
                        furnitureId = maxFunitureId + 1;
                    }
                    maintenanceFurniture.setFurnitureId(furnitureId);
                    maintenanceFurniture.setAssetId(i);
                    maintenanceFurniture1 = maintenanceFurnitureService.updateFurniture(maintenanceFurniture);

                    furnitureId++;
                }
                if (maintenanceFurniture1 != null) {
                    isSave = true;
                }

//                assets = assetService.getAssetByTypeFurniture(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveFurniture Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("furniture", new MaintenanceFurniture());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("furniture-maintenance");
    }

    @RequestMapping("officeEquipment")
    public ModelAndView loadOfficeEquipment(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeOfficeEquipment();
//                assets = assetService.getAssetByTypeOfficeEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the officeEquipment Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("officeEquipment", new MaintenanceOfficeEquipment());
        return new ModelAndView("officeEquipment-maintenance");
    }

    @RequestMapping(value = "saveOfficeEquipment", method = RequestMethod.POST)
    public ModelAndView saveOfficeEquipment(@ModelAttribute("officeEquipmentForm") MaintenanceOfficeEquipment maintenanceOfficeEquipment, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceOfficeEquipment.setUserId(user.getUserId());
                maintenanceOfficeEquipment.setActionTime(date);

                if (maintenanceOfficeEquipment.getOfficeEquipmentsId() != 0) {
                    MaintenanceOfficeEquipment maintenanceOfficeEquipmentOld = maintenanceOfficeEquipmentService.findById(maintenanceOfficeEquipment.getOfficeEquipmentsId());
                    maintenanceOfficeEquipmentHistoryService.updateHistory(maintenanceOfficeEquipmentOld, 0, user.getUserId());//done
                    update = 1;
                }

                MaintenanceOfficeEquipment maintenanceOfficeEquipment1 = maintenanceOfficeEquipmentService.saveUpdateOfficeEquipment(maintenanceOfficeEquipment);
                if (maintenanceOfficeEquipment1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeOfficeEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveOfficeEquipment Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("officeEquipment", new MaintenanceOfficeEquipment());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("officeEquipment-maintenance");
    }

    @RequestMapping("labEquipment")
    public ModelAndView loadLabEquipment(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeLabEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the labEquipment Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("labEquipment", new MaintenancePlantMachinary());
        return new ModelAndView("labEquipment-maintenance");
    }

    @RequestMapping(value = "saveLabEquipment", method = RequestMethod.POST)
    public ModelAndView saveLabEquipment(@ModelAttribute("labEquipmentForm") MaintenanceLabEquipment maintenanceLabEquipment, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceLabEquipment.setUserId(user.getUserId());
                maintenanceLabEquipment.setActionTime(date);
                if (maintenanceLabEquipment.getEquipmentId() != 0) {
                    MaintenanceLabEquipment maintenanceLabEquipmentOld = maintenanceLabEquipmentsService.findById(maintenanceLabEquipment.getEquipmentId());
                    MaintenanceLabEquipmentsHistoryService.updateHistory(maintenanceLabEquipmentOld, 0, user.getUserId());//done
                    update = 1;
                }
                MaintenanceLabEquipment maintenanceLabEquipment1 = maintenanceLabEquipmentsService.saveUpdateLabEquipment(maintenanceLabEquipment);
                if (maintenanceLabEquipment1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeLabEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveLabEquipment Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("labEquipment", new MaintenanceLabEquipment());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("labEquipment-maintenance");
    }

    @RequestMapping("teachingEquipment")
    public ModelAndView loadTeachingEquipment(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeTeachingEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadTeachingEquipment Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("teachingEquipment", new MaintenanceTeachingEquipment());
        return new ModelAndView("teachingEquipment-maintenance");
    }

    @RequestMapping(value = "saveTeachingEquipment", method = RequestMethod.POST)
    public ModelAndView saveTeachingEquipment(@ModelAttribute("teachingEquipmentForm") MaintenanceTeachingEquipment maintenanceTeachingEquipment, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = userService.findJobList(user.getUserType().getUserTypeId());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model = model.addAttribute("jobList", defineList);
                maintenanceTeachingEquipment.setUserId(user.getUserId());
                maintenanceTeachingEquipment.setActionTime(date);
                if (maintenanceTeachingEquipment.getTeachingId() != 0) {
                    MaintenanceTeachingEquipment maintenanceTeachingEquipmentOld = maintenanceTeachingEquipmentsService.findById(maintenanceTeachingEquipment.getTeachingId());
                    maintenanceTeachingEquipmentHistoryService.updateHistory(maintenanceTeachingEquipmentOld, 0, user.getUserId());//done
                    update = 1;
                }
                MaintenanceTeachingEquipment maintenanceTeachingEquipment1 = maintenanceTeachingEquipmentsService.saveUpdateTeachingEquipment(maintenanceTeachingEquipment);
                if (maintenanceTeachingEquipment1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeTeachingEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveTeachingEquipment Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("teachingEquipment", new MaintenanceTeachingEquipment());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("teachingEquipment-maintenance");
    }

    @RequestMapping("fixturesAndFitting")
    public ModelAndView loadFixturesAndFitting(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();

            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeFixtures();
//                assets = assetService.getAssetByTypeFixtures(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the fixturesAndFitting Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("fixturesAndFitting", new MaintenanceFixturesFittings());
        return new ModelAndView("fixturesAndFitting-maintenance");
    }

    @RequestMapping(value = "saveFixturesAndFitting", method = RequestMethod.POST)
    public ModelAndView saveFixturesAndFitting(@ModelAttribute("fixturesAndFittingForm") MaintenanceFixturesFittings maintenanceFixturesFittings, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceFixturesFittings.setUserId(user.getUserId());
                maintenanceFixturesFittings.setActionTime(date);

                if (maintenanceFixturesFittings.getFittingsId() != 0) {
                    MaintenanceFixturesFittings maintenanceFixturesFittingsOld = maintenanceFixturesAndFittingService.findById(maintenanceFixturesFittings.getFittingsId());
                    maintenanceFixturesAndFittingHistoryService.updateHistory(maintenanceFixturesFittingsOld, 0, user.getUserId());
                    update = 1;
                }

                MaintenanceFixturesFittings maintenanceFixturesFittings1 = maintenanceFixturesAndFittingService.saveUpdateFixturesFitting(maintenanceFixturesFittings);
                if (maintenanceFixturesFittings1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeFixtures(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the savefixturesAndFitting Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("fixturesAndFitting", new MaintenanceFixturesFittings());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("fixturesAndFitting-maintenance");
    }

    @RequestMapping("libraryBooks")
    public ModelAndView loadLibraryBooks(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                  assets = assetService.getAssetByTypeLibraryBooks(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadLibraryBooks Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("libraryBooks", new MaintenanceLibraryBooks());
        return new ModelAndView("libraryBooks-maintenance");
    }

    @RequestMapping(value = "saveLibraryBooks", method = RequestMethod.POST)
    public ModelAndView saveLibraryBooks(@ModelAttribute("libraryBooksForm") MaintenanceLibraryBooks maintenanceLibraryBooks, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceLibraryBooks.setUserId(user.getUserId());
                maintenanceLibraryBooks.setActionTime(date);

                if (maintenanceLibraryBooks.getLibraryBookId() != 0) {
                    MaintenanceLibraryBooks maintenanceLibraryBooksOld = maintenanceLibraryBooksService.findById(maintenanceLibraryBooks.getLibraryBookId());
                    maintenanceLibraryBooksHistoryService.updateHistory(maintenanceLibraryBooksOld, 0, user.getUserId());
                    update = 1;
                }

                MaintenanceLibraryBooks maintenanceLibraryBooks1 = maintenanceLibraryBooksService.saveUpdateMaintenanceLibraryBooks(maintenanceLibraryBooks);
                if (maintenanceLibraryBooks1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeLibraryBooks(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveLibraryBooks Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("libraryBooks", new MaintenanceLibraryBooks());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("libraryBooks-maintenance");
    }

    @RequestMapping("sportEquipment")
    public ModelAndView loadSportEquipment(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeSportEquipment();
//                assets = assetService.getAssetByTypeSportEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the runningData Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("sportEquipment", new MaintenanceServiceAgreements());
        return new ModelAndView("sportEquipment-maintenance");
    }

    @RequestMapping(value = "saveSportEquipment", method = RequestMethod.POST)
    public ModelAndView saveSportEquipment(@ModelAttribute("sportEquipmentForm") MaintenanceSportEquipment maintenanceSportEquipment, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceSportEquipment.setUserId(user.getUserId());
                maintenanceSportEquipment.setActionTime(date);
                if (maintenanceSportEquipment.getSportId() != 0) {
                    MaintenanceSportEquipment maintenanceSportEquipmentOld = maintenanceSportEquipmentService.findById(maintenanceSportEquipment.getSportId());
                    maintenanceSportEquipmentHistoryService.updateHistory(maintenanceSportEquipmentOld, 0, user.getUserId());
                    update = 1;
                }
                MaintenanceSportEquipment maintenanceSportEquipment1 = maintenanceSportEquipmentService.saveUpdateMaintenanceSportEquipment(maintenanceSportEquipment);
                if (maintenanceSportEquipment1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeSportEquipment(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the save sportEquipment Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("sportEquipment", new MaintenanceSportEquipment());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("sportEquipment-maintenance");
    }

    @RequestMapping("checkRegisterdYear")
    @ResponseBody
    public int checkRegisterdYear(@RequestParam("year") int year, @RequestParam("assetId") int assetId) {
        int status = 0;
        try {
            status = assetService.checkRegisterdYear(year, assetId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkRegisterdYear Method : " + e.getMessage());
            status = 0;
        }
        return status;
    }

    @RequestMapping("software")
    public ModelAndView loadSoftware(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<Asset>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetByTypeSoftware();
//                assets = assetService.getAssetByTypeSoftware(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the runningData Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        model.addAttribute("software", new MaintenanceSoftware());
        return new ModelAndView("software-maintenance");
    }

    @RequestMapping(value = "saveSoftware", method = RequestMethod.POST)
    public ModelAndView saveSoftware(@ModelAttribute("softwareForm") MaintenanceSoftware maintenanceSoftware, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        int update = 0;
//        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceSoftware.setUserId(user.getUserId());
                maintenanceSoftware.setActionTime(date);
                if (maintenanceSoftware.getSoftwareId() != 0) {
                    MaintenanceSoftware maintenanceSoftwareOld = maintenanceSoftwareService.findById(maintenanceSoftware.getSoftwareId());
                    maintenanceSoftwareHistoryService.updateHistory(maintenanceSoftwareOld, 0, user.getUserId());
                    update = 1;
                }
                MaintenanceSoftware maintenanceSoftware1 = maintenanceSoftwareService.saveUpdateMaintenanceSoftware(maintenanceSoftware);
                if (maintenanceSoftware1 != null) {
                    isSave = true;
                }
//                assets = assetService.getAssetByTypeSoftware(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the save Software Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("update", update);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("software", new MaintenanceSoftware());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("software-maintenance");
    }

    @RequestMapping("insurance")
    public ModelAndView loadInsurance(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<Asset> assets = new ArrayList<>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
                currencyTypes = currencyService.findAll();
//                assets = assetService.getAssetList();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadInsurance Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("companyName", companyName);
        model.addAttribute("insurance", new MaintenanceInsurance());
        return new ModelAndView("insurance-maintenance");
    }

    @RequestMapping("warranty")
    public ModelAndView loadWarranty(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the loadWarranty Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("warranty", new MaintenanceWarranty());
        return new ModelAndView("warranty-maintenance");
    }

    @RequestMapping(value = "saveInsurance", method = RequestMethod.POST)
    public ModelAndView saveInsurance(@ModelAttribute("insuranceForm") MaintenanceInsurance maintenanceInsurance, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        String tempTransactionNo = "", transactionNo = "";
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            currencyTypes = currencyService.findAll();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);


                if (maintenanceInsurance.getInsuranceID() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceInsuranceService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = INSURANCE_CODE + tempTransactionNo;
                    maintenanceInsurance.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                } else {
                    MaintenanceInsurance maintenanceInsuranceOld = maintenanceInsuranceService.findById(maintenanceInsurance.getInsuranceID());
                    maintenanceInsuranceOld.setTransactionNo(maintenanceInsurance.getTransactionNo());
                    maintenanceInsuranceHistoryService.updateHistory(maintenanceInsuranceOld, 0, user.getUserId());
                }

                maintenanceInsurance.setPremium(Double.parseDouble(maintenanceInsurance.getPremiumDisplay().toString().replace(",", "")));
                maintenanceInsurance.setInsuranceValue(Double.parseDouble(maintenanceInsurance.getInsuredValueDisplay().toString().replace(",", "")));

                maintenanceInsurance.setUserId(user.getUserId());
                maintenanceInsurance.setActionTime(date);

                MaintenanceInsurance maintenanceInsurance1 = maintenanceInsuranceService.saveUpdateMaintenanceInsurance(maintenanceInsurance);
                if (maintenanceInsurance1 != null) {
                    isSave = true;
                }

            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveInsurance Method : " + e.getMessage());
        }
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("transactionNo", transactionNo);
        model.addAttribute("insurance", new MaintenanceInsurance());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("insurance-maintenance");
    }

    @RequestMapping(value = "saveWarranty", method = RequestMethod.POST)
    public ModelAndView saveWarranty(@ModelAttribute("warrantyForm") MaintenanceWarranty maintenanceWarranty, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        String tempTransactionNo = "", transactionNo = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                maintenanceWarranty.setUserID(user.getUserId());
                maintenanceWarranty.setActionTime(date);


                if (maintenanceWarranty.getWarrantyId() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceWarrantyService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = WARRANTY_CODE + tempTransactionNo;
                    maintenanceWarranty.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                } else {
                    MaintenanceWarranty maintenanceWarrantyOld = maintenanceWarrantyService.findById(maintenanceWarranty.getWarrantyId());
                    maintenanceWarrantyOld.setTransactionNo(maintenanceWarranty.getTransactionNo());
                    maintenanceWarrantyOld.setWarrantyId(maintenanceWarranty.getWarrantyId());
                    maintenanceWarrantyHistoryService.updateHistory(maintenanceWarrantyOld, 0, user.getUserId());
                }
                model.addAttribute("jobList", defineList);
                maintenanceWarranty.setUserID(user.getUserId());
                maintenanceWarranty.setActionTime(date);

                MaintenanceWarranty maintenanceWarranty1 = maintenanceWarrantyService.saveUpdateMaintenaceWarranty(maintenanceWarranty);
                if (maintenanceWarranty1 != null) {
                    isSave = true;
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveWarranty Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("warranty", new MaintenanceWarranty());
        model.addAttribute("companyName", companyName);

        return new ModelAndView("warranty-maintenance");
    }


    @RequestMapping("modification")
    public ModelAndView loadModification(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
                currencyTypes = currencyService.findAll();
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the modification Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("companyName", companyName);
        model.addAttribute("modification", new MaintenanceModification());
        return new ModelAndView("modification-maintenance");
    }

    @RequestMapping(value = "saveModification", method = RequestMethod.POST)
    public ModelAndView saveModification(@ModelAttribute("modificationForm") MaintenanceModification maintenanceModification, HttpServletRequest request, Model model) {
        Boolean isSave = false;
//        List<Asset> assets = new ArrayList<>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        String tempTransactionNo = "", transactionNo = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                if (maintenanceModification.getModificationId() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceModificationService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = MODIFICATION_CODE + tempTransactionNo;
                    maintenanceModification.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                } else {
                    MaintenanceModification maintenanceModificationOld = maintenanceModificationService.findById(maintenanceModification.getModificationId());
                    maintenanceModificationOld.setTransactionNo(maintenanceModification.getTransactionNo());
                    maintenanceModificationHistoryService.updateHistory(maintenanceModificationOld, 0, user.getUserId());
                }
                model.addAttribute("jobList", defineList);
                currencyTypes = currencyService.findAll();
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));

                maintenanceModification.setUserId(user.getUserId());
                maintenanceModification.setActionTime(date);
                maintenanceModification.setModiCost(Double.parseDouble(maintenanceModification.getCostDisplay().toString().replace(",", "")));

                MaintenanceModification maintenanceModification1 = maintenanceModificationService.saveUpdateMaintenanceModification(maintenanceModification);
                if (maintenanceModification1 != null) {
                    isSave = true;
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveModification Method : " + e.getMessage());
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("modification", new MaintenanceModification());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("modification-maintenance");
    }

    @RequestMapping("maintenanceData")
    public ModelAndView loadMaintenanceData(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
//        List<Asset> assets = new ArrayList<>();
        List<Currency> currencyTypes = new ArrayList<>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                currencyTypes = currencyService.findAll();

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the maintenanceData Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
//        model.addAttribute("assets", assets);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("companyName", companyName);
        model.addAttribute("maintenanceData", new MaintenanceMaintenanceData());
        return new ModelAndView("maintenanceData-maintenance");
    }

    @RequestMapping(value = "saveMaintenanceData", method = RequestMethod.POST)
    public ModelAndView saveMaintenanceData(MaintenanceMaintenanceData maintenanceMaintenanceData, Model model, HttpServletRequest request) {

        Boolean isSave = false;
        String tempTransactionNo = "MD", transactionNo = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                if (maintenanceMaintenanceData.getMaintenanceDataId() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceMaintenanceDataService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = MAINTENANCE_DATA_CODE + tempTransactionNo;
                    maintenanceMaintenanceData.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                } else {
                    String tNo = maintenanceMaintenanceDataService.getTransactionNo(maintenanceMaintenanceData.getMaintenanceDataId());

                    List<MaintenanceMaintenanceData> maintenanceDataOld = maintenanceMaintenanceDataService.getMaintenanceDataDetails(tNo);
                    maintenanceMaintenanceDataHistoryService.updateHistory(maintenanceDataOld, 0, user.getUserId());
                    maintenanceMaintenanceData.setTransactionNo(tNo);
                    maintenanceMaintenanceDataService.deleteEntry(tNo);
                }
                maintenanceMaintenanceData.setUserId(user.getUserId());
                maintenanceMaintenanceData.setActionTime(date);
                String msg = maintenanceMaintenanceDataService.saveMaintenanceData(maintenanceMaintenanceData);
                if (msg.equals("Update Successful.")) {
                    isSave = true;
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMaintenanceData Method " + e.getMessage());
        }

        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("maintenanceData", new MaintenanceMaintenanceData());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("maintenanceData-maintenance");
    }

    @RequestMapping("getAccessoryByAssetId/{assetId}")
    @ResponseBody
    public List<String> loadAccessoryByAssetId(@PathVariable("assetId") int assetId) {
        return maintenanceMaintenanceDataService.getAccessoryName(assetId);
    }

    @RequestMapping("runningData")
    public ModelAndView loadRunningData(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the runningData Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("runningData", new MaintenanceRunningData());
        return new ModelAndView("runningData-maintenance");
    }

    @RequestMapping(value = "saveRunningData", method = RequestMethod.POST)
    public ModelAndView saveRunningData(MaintenanceRunningData maintenanceRunningData, Model model, HttpServletRequest request) {

        Boolean isSave = false;
        String tempTransactionNo = "RD", transactionNo = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                if (maintenanceRunningData.getRunningId() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceRunningDataService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = RUNNING_DATA_CODE + tempTransactionNo;
                    maintenanceRunningData.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                } else {
                    String tNo = maintenanceRunningDataService.getTransactionNo(maintenanceRunningData.getRunningId());

                    List<MaintenanceRunningData> runningDataOld = maintenanceRunningDataService.getRunningDataDetails(tNo);
                    maintenanceRunningDataHistoryService.updateHistory(runningDataOld, 0, user.getUserId());
                    maintenanceRunningData.setTransactionNo(tNo);
                    maintenanceRunningDataService.deleteEntry(tNo);

                }

                maintenanceRunningData.setUserId(user.getUserId());
                maintenanceRunningData.setActionTime(date);

                String msg = maintenanceRunningDataService.saveRunningData(maintenanceRunningData);
                if (msg.equals("Update Successful.")) {
                    isSave = true;
                }


            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveRunningData Method " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("runningData", new MaintenanceRunningData());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("runningData-maintenance");
    }

    @RequestMapping("serviceAgreement")
    public ModelAndView loadServiceAgreement(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        List<Currency> currencyTypes = new ArrayList<Currency>();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
                currencyTypes = currencyService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the runningData Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }

        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("companyName", companyName);
        model.addAttribute("serviceAgreement", new MaintenanceServiceAgreements());
        return new ModelAndView("serviceAgreement-maintenance");
    }

    @RequestMapping(value = "saveServiceAgreement", method = RequestMethod.POST)
    public ModelAndView saveServiceAgreement(@ModelAttribute("serviceAgreementForm") MaintenanceServiceAgreements maintenanceServiceAgreements, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        String tempTransactionNo = "", transactionNo = "";
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                if (maintenanceServiceAgreements.getAgreeId() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceServiceAgreementService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = SERV_AGRE_CODE + tempTransactionNo;
                    maintenanceServiceAgreements.setTransactionNo(transactionNo);
                } else {
                    MaintenanceServiceAgreements maintenanceServiceAgreementsOld = maintenanceServiceAgreementService.findById(maintenanceServiceAgreements.getAgreeId());
                    maintenanceServiceAgreementsOld.setTransactionNo(maintenanceServiceAgreements.getTransactionNo());
                    maintenanceServiceAgreementsHistoryService.updateHistory(maintenanceServiceAgreementsOld, 0, user.getUserId());
                }
                model.addAttribute("jobList", defineList);
                maintenanceServiceAgreements.setUserId(user.getUserId());
                maintenanceServiceAgreements.setActionTime(date);
                maintenanceServiceAgreements.setAgreeCost(Double.parseDouble(maintenanceServiceAgreements.getCostDisplay().toString().replace(",", "")));

                MaintenanceServiceAgreements maintenanceServiceAgreements1 = maintenanceServiceAgreementService.saveUpdateMaintenanceServiceAgreement(maintenanceServiceAgreements);
                currencyTypes = currencyService.findAll();
                if (maintenanceServiceAgreements1 != null) {
                    isSave = true;
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the save ServiceAgreement Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("pageType", 1);
        model.addAttribute("serviceAgreement", new MaintenanceServiceAgreements());
        model.addAttribute("transactionNo", transactionNo);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("serviceAgreement-maintenance");
    }

    @RequestMapping("leaseAsset")
    public ModelAndView loadLeaseAsset(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<JobDefine> defineList = new ArrayList<>();
            try {
                defineList = (List<JobDefine>) session.getAttribute("jobList");
                    } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error occurred while calling the leaseAsset Method : " + e.getMessage());
            }
            model.addAttribute("jobList", defineList);
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("leaseAsset", new MaintenanceMaintenanceData());
        return new ModelAndView("leaseAsset-maintenance");
    }

    @RequestMapping(value = "saveLeaseAsset", method = RequestMethod.POST)
    public ModelAndView saveLeaseAsset(@ModelAttribute("leaseAssetForm") MaintenanceLeaseAsset maintenanceLeaseAsset, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        String tempTransactionNo = "", transactionNo = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);

                if (maintenanceLeaseAsset.getLeaseID() <= 0) {
                    tempTransactionNo = getSerialNo(maintenanceLeaseAssetService.generateTransactionNo(), MAX_SERIAL);
                    transactionNo = LEASE_ASSET_CODE + tempTransactionNo;
                    maintenanceLeaseAsset.setTransactionNo(transactionNo);
                    model.addAttribute("transactionNo", transactionNo);
                } else {
                    MaintenanceLeaseAsset maintenanceLeaseAssetOld = maintenanceLeaseAssetService.findById(maintenanceLeaseAsset.getLeaseID());
                    maintenanceLeaseAssetOld.setTransactionNo(maintenanceLeaseAsset.getTransactionNo());
                    maintenanceLeaseAssetHistoryService.updateHistory(maintenanceLeaseAssetOld, 0, user.getUserId());
                }
                maintenanceLeaseAsset.setUserId(user.getUserId());
                maintenanceLeaseAsset.setActionTime(date);
                maintenanceLeaseAsset.setLeaseTot(Double.parseDouble(maintenanceLeaseAsset.getDisplayLeaseTot().toString().replace(",", "")));
                maintenanceLeaseAsset.setLeasePremium(Double.parseDouble(maintenanceLeaseAsset.getDisplayLeasePremium().toString().replace(",", "")));
                MaintenanceLeaseAsset maintenanceLeaseAsset1 = maintenanceLeaseAssetService.saveUpdateMaintenanceLeaseAsset(maintenanceLeaseAsset);
                if (maintenanceLeaseAsset1 != null) {
                    isSave = true;
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveLeaseAsset Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("transactionNo", transactionNo);
        model.addAttribute("leaseAsset", new MaintenanceLeaseAsset());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("leaseAsset-maintenance");
    }


    @RequestMapping("getAssetByLease/{userBranch}")
    @ResponseBody
    public List loadAsset(@PathVariable("userBranch") int branch) {
        return assetService.getAssetByLease(branch);
    }

    @RequestMapping("loadLandDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceLand loadLandDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceLandService.getLandDetailsByAsset(assetId);
    }

    @RequestMapping("getCurrencyName/{currencyId}")
    @ResponseBody
    public Currency getCurrencyName(@PathVariable("currencyId") int currencyId) {
        return currencyService.findById(currencyId);
    }

    @RequestMapping("loadLandBuildingDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceLandBuliding loadLandBuildingDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceLandBulidingService.getLandBuildingDetailsByAsset(assetId);
    }

    @RequestMapping("loadComputerDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceComputer loadComputerDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceComputerService.getComputerDetailsByAsset(assetId);
    }

    @RequestMapping("loadVehicleDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceVehicle loadVehicleDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceVehicleService.getVehilceDetailsByAsset(assetId);
    }

    @RequestMapping("loadPlantMachineryDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenancePlantMachinary loadPlantMachineryDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenancePlantMachinaryService.getPlantMachineryDetailsByAsset(assetId);
    }

    @RequestMapping("loadFurnitureDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceFurniture loadFurnitureDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceFurnitureService.getFurnitureDetailsByAsset(assetId);
    }

    @RequestMapping("loadLibraryBooksDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceLibraryBooks loadLibraryBooksDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceLibraryBooksService.getLibraryBooksDetailsByAsset(assetId);
    }

    @RequestMapping("loadOfficeEquipmentsDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceOfficeEquipment loadOfficeEquipmentsDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceOfficeEquipmentService.getOfficeEquipmentsDetailsByAsset(assetId);
    }

    @RequestMapping("loadTeachingEquipmentsDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceTeachingEquipment loadTeachingEquipmentsDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceTeachingEquipmentsService.getTeachingEquipmentsDetailsByAsset(assetId);
    }

    @RequestMapping("loadSoftwareDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceSoftware loadSoftwareDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceSoftwareService.getSoftwareDetailsByAsset(assetId);
    }

    @RequestMapping("loadFixturesAndFittingsDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceFixturesFittings loadFixturesAndFittingsDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceFixturesAndFittingService.gettFixturesAndFittingsDetails(assetId);
    }

    @RequestMapping("loadLabEquipmentsDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceLabEquipment loadLabEquipmentsDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceLabEquipmentsService.getLabEquipmentDetails(assetId);
    }

    @RequestMapping("loadSportEquipmentsDetailsByAsset/{assetId}")
    @ResponseBody
    public MaintenanceSportEquipment loadSportEquipmentsDetailsByAsset(@PathVariable("assetId") int assetId) {
        return maintenanceSportEquipmentService.getSportEquipmentDetails(assetId);
    }

    @RequestMapping("loadInsuranceNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceInsurance> loadInsuranceNoList(@PathVariable("assetId") int assetId) {
        return maintenanceInsuranceService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadInsuranceDetailsByInsuranceId/{transactionNo}")
    @ResponseBody
    public MaintenanceInsurance loadInsuranceDetailsByInsuranceId(@PathVariable("transactionNo") int transactionNo) {
        return maintenanceInsuranceService.getInsuranceDetails(transactionNo);
    }

    @RequestMapping("loadWarrantyNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceWarranty> loadWarrantyNoList(@PathVariable("assetId") int assetId) {
        return maintenanceWarrantyService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadWarrantyDetailsByWarrantyId/{transactionNo}")
    @ResponseBody
    public MaintenanceWarranty loadWarrantyDetailsByWarrantyId(@PathVariable("transactionNo") int transactionNo) {
        return maintenanceWarrantyService.getWarrantyDetails(transactionNo);
    }

    @RequestMapping("loadServiceAgreeNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceServiceAgreements> loadServiceAgreeNoList(@PathVariable("assetId") int assetId) {
        return maintenanceServiceAgreementService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadServiceAgreementDetailsByServiceAgreeId/{transactionNo}")
    @ResponseBody
    public MaintenanceServiceAgreements loadServiceAgreementDetailsByServiceAgreeId(@PathVariable("transactionNo") int transactionNo) {
        return maintenanceServiceAgreementService.getServiceAgreeDetails(transactionNo);
    }

    @RequestMapping("loadModificationNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceModification> loadModificationNoList(@PathVariable("assetId") int assetId) {
        return maintenanceModificationService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadModificationDetailsByModificationId/{transactionNo}")
    @ResponseBody
    public MaintenanceModification loadModificationDetailsByModificationId(@PathVariable("transactionNo") int transactionNo) {
        return maintenanceModificationService.getModificationDetails(transactionNo);
    }


    @RequestMapping("getCurrencyCodes")
    @ResponseBody
    public List<Currency> getCurrencyCodes() {
        return currencyService.findAll();
    }

    @RequestMapping("loadLeaseAssetNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceLeaseAsset> loadLeaseAssetNoList(@PathVariable("assetId") int assetId) {
        return maintenanceLeaseAssetService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadLeaseAssetDetailsByLeaseId/{transactionNo}")
    @ResponseBody
    public MaintenanceLeaseAsset loadLeaseAssetDetailsByLeaseId(@PathVariable("transactionNo") int transactionNo) {
        return maintenanceLeaseAssetService.getLeaseAssetDetails(transactionNo);
    }

    @RequestMapping("loadMaintenanceDataNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceMaintenanceData> loadMaintenanceDataNoList(@PathVariable("assetId") int assetId) {
        return maintenanceMaintenanceDataService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadMaintenanceDataByTno/{transactionNo}")
    @ResponseBody
    public List<MaintenanceMaintenanceData> loadMaintenanceDataByTno(@PathVariable("transactionNo") String transactionNo) {
        return maintenanceMaintenanceDataService.getMaintenanceDataDetails(transactionNo);
    }

    @RequestMapping("loadRunningDataNoList/{assetId}")
    @ResponseBody
    public List<MaintenanceRunningData> loadRunningDataNoList(@PathVariable("assetId") int assetId) {
        return maintenanceRunningDataService.getTransactionNoList(assetId);
    }

    @RequestMapping("loadRunningDataByTno/{transactionNo}")
    @ResponseBody
    public List<MaintenanceRunningData> loadRunningDataByTno(@PathVariable("transactionNo") String transactionNo) {
        return maintenanceRunningDataService.getRunningDataDetails(transactionNo);
    }

//    @RequestMapping("loadAssetByType/{type}/{userBranch}")
//    @ResponseBody
//    public List<Asset> loadAssetByType(@PathVariable("type") int type, @PathVariable("userBranch") int branch) {
//        if (type == 1) { // Land
//            return assetService.getAssetByLand(branch);
//        } else if (type == 2) { // Land & Building
//            return assetService.getAssetByBuilding(branch);
//        } else if (type == 3) { // Vehicle
//            return assetService.getAssetByVehicle(branch);
//        } else if (type == 4) { // Computer
//            return assetService.getAssetByComputer(branch);
//        } else if (type == 5) { // Plant & Machinary
//            return assetService.getAssetByPlantMachinary(branch);
//        } else if (type == 6) { // Furniture
//            return assetService.getAssetByFurniture(branch);
//        } else if (type == 7) { // Office Equipment
//            return assetService.getAssetByOfficeEquipment(branch);
//        } else if (type == 8) { // LAB Equipment
//            return assetService.getAssetByLabEquipment(branch);
//        } else if (type == 9) { // Teaching Equipment
//            return assetService.getAssetByTeachingEquipment(branch);
//        } else if (type == 10) { // Fixtures & Fittings
//            return assetService.getAssetByFixtures(branch);
//        } else if (type == 11) { // Library Books
//            return assetService.getAssetByLibraryBooks(branch);
//        } else if (type == 12) { // Sport Equipment
//            return assetService.getAssetBySportEquipment(branch);
//        } else if (type == 13) { // Software
//            return assetService.getAssetBySoftware(branch);
//        }
//        return null;
//    }

    @RequestMapping("loadAssetByTypeInsurance/{type}/{userBranch}")
    @ResponseBody
    public List<Asset> loadAssetByTypeInsurance(@PathVariable("type") String type, @PathVariable("userBranch") int branch) {
        if (type.equals("0")) { // All
//            return assetService.getAssetList();
        } else if (type.equals("Land")) { // Land
            return assetService.getAssetByTypeLandInsurance(branch);
        } else if (type.equals("Building")) { // Building
            return assetService.getAssetByTypeBuildingInsurance(branch);
        } else if (type.equals("Vehicle")) { // Vehicle
            return assetService.getAssetByTypeVehicleInsurance(branch);
        } else if (type.equals("Computer")) { // Computer
            return assetService.getAssetByTypeComputerInsurance(branch);
        } else if (type.equals("Plant")) { // Plant
            return assetService.getAssetByTypePlantInsurance(branch);
        } else if (type.equals("Furniture")) { // Furniture
            return assetService.getAssetByTypeFurnitureInsurance(branch);
        } else if (type.equals("Office")) { // Office
            return assetService.getAssetByTypeOfficeInsurance(branch);
        } else if (type.equals("Lab")) { // Lab
            return assetService.getAssetByTypeLabInsurance(branch);
        } else if (type.equals("Teaching")) { // Teaching
            return assetService.getAssetByTypeTeachingInsurance(branch);
        } else if (type.equals("Fixtures")) { // Fixtures
            return assetService.getAssetByTypeFixturesInsurance(branch);
        } else if (type.equals("Sports")) { // Sports
            return assetService.getAssetByTypeSportsInsurance(branch);
        }
        return null;
    }

    @RequestMapping("loadAssetByTypeWarranty/{type}/{userBranch}")
    @ResponseBody
    public List<Asset> loadAssetByTypeWarranty(@PathVariable("type") String type, @PathVariable("userBranch") int branch) {
        if (type.equals("0")) { // All
//            return assetService.getAssetList();
        } else if (type.equals("Vehicle")) { // Vehicle
            return assetService.getAssetByTypeVehicleWarranty(branch);
        } else if (type.equals("Computer")) { // Computer
            return assetService.getAssetByTypeComputerWarranty(branch);
        } else if (type.equals("Plant")) { // Plant
            return assetService.getAssetByTypePlantWarranty(branch);
        } else if (type.equals("Furniture")) { // Furniture
            return assetService.getAssetByTypeFurnitureWarranty(branch);
        } else if (type.equals("Office")) { // Office
            return assetService.getAssetByTypeOfficeWarranty(branch);
        } else if (type.equals("Lab")) { // Lab
            return assetService.getAssetByTypeLabWarranty(branch);
        } else if (type.equals("Teaching")) { // Teaching
            return assetService.getAssetByTypeTeachingWarranty(branch);
        } else if (type.equals("Fixtures")) { // Fixture
            return assetService.getAssetByTypeFixturesWarranty(branch);
        } else if (type.equals("Sports")) { // Sports
            return assetService.getAssetByTypeSportsWarranty(branch);
        }
        return null;
    }


    @RequestMapping("loadAssetByTypeServiceAgree/{type}/{userBranch}")
    @ResponseBody
    public List<Asset> loadAssetByTypeServiceAgree(@PathVariable("type") String type, @PathVariable("userBranch") int branch) {
        if (type.equals("0")) { // All
//            return assetService.getAssetList(session.getAttribute("userBranch").toString());
        } else if (type.equals("Plant")) { // Plant
            return assetService.getAssetByTypePlantServiceAgree(branch);
        } else if (type.equals("Office")) { // Office
            return assetService.getAssetByTypeOfficeServiceAgree(branch);
        } else if (type.equals("Lab")) { // Lab
            return assetService.getAssetByTypeLabServiceAgree(branch);
        } else if (type.equals("Teaching")) { // Teaching
            return assetService.getAssetByTypeTeachingServiceAgree(branch);
        } else if (type.equals("Sports")) { // Sports
            return assetService.getAssetByTypeSportsServiceAgree(branch);
        } else if (type.equals("Software")) {
            return assetService.getAssetByTypeSoftwareServiceAgree(branch);
        }
        return null;
    }



    @RequestMapping("loadTypeOfAsset")
    @ResponseBody
    public int[] loadComputerDetailsByAsset() {
        int[] dashValue = new int[7];
        dashValue[0] = assetsTypesService.getNoOfAssectByType(5).getQty();//Furniture & Fittings
        dashValue[1] = assetsTypesService.getNoOfAssectByType(3).getQty();//Plant & Machinary
        dashValue[2] = assetsTypesService.getNoOfAssectByType(4).getQty();//Lab Equipments
        dashValue[3] = assetsTypesService.getNoOfAssectByType(16).getQty();//Sport Equipments
        dashValue[4] = assetsTypesService.getNoOfAssectByType(10).getQty();//Vehicle
        dashValue[5] = assetsTypesService.getNoOfAssectByType(9).getQty();//Land
        dashValue[6] = assetsTypesService.getNoOfOtherAssect(5, 4, 16, 10, 3, 9);//Other


        return dashValue;
    }

    @RequestMapping("checkDeedSignedDate")
    @ResponseBody
    public int checkDeedSignedDate(@RequestParam("date") String date, @RequestParam("assetId") int assetId) {
        int status = 1;
        try {
            status = maintenanceLandService.checkDeedSignedDate(simpleDateFormat.parse(date), assetId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDeedSignedDate Method : " + e.getMessage());
            status = 1;
        }
        return status;
    }

    @RequestMapping("checkDeedRegisteredDate")
    @ResponseBody
    public int checkDeedRegisteredDate(@RequestParam("dateRegistered") String date, @RequestParam("assetId") int assetId) {
        int status = 1;
        try {
            status = maintenanceLandService.checkDeedRegisteredDate(simpleDateFormat.parse(date), assetId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDeedRegisteredDate Method : " + e.getMessage());
            status = 1;
        }
        return status;
    }

    @RequestMapping("loadToDate")
    @ResponseBody
    public Date loadToDate(@RequestParam("date") String date, @RequestParam("period") int period) {
        Date dateReturn = null;
        try {
            dateReturn = maintenanceInsuranceService.loadToDate(simpleDateFormat.parse(date), period);
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadToDate Method : " + e.getMessage());

        }
        return dateReturn;

    }

    @RequestMapping("getAssetData/{assetId}")
    @ResponseBody
    public Date getAssetData(@PathVariable("assetId") int assetId) {
        Asset asset = assetService.getAsset(assetId);
        Date assetDate = asset.getRegDate();
        return assetDate;
    }
}
