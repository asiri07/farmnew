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
import com.msd.fixAssetRegister.model.form.Listing;
import com.msd.fixAssetRegister.model.form.ReminderPage;
import com.msd.fixAssetRegister.reportManager.DBFacade;
import com.msd.fixAssetRegister.service.*;
import com.msd.fixAssetRegister.service.maintenance.MaintenanceInsuranceService;
import com.msd.fixAssetRegister.service.maintenance.MaintenanceLeaseAssetService;
import com.msd.fixAssetRegister.service.maintenance.MaintenanceServiceAgreementService;
import com.msd.fixAssetRegister.service.maintenance.MaintenanceWarrantyService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URL;
import java.rmi.dgc.Lease;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.msd.fixAssetRegister.controller.FileUploadController.db;

@Controller
@RequestMapping("/report")
public class ReportController {


    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    MainCategoryService mainCategoryService;
    @Autowired
    BuildingService buildingService;
    @Autowired
    FloorService floorService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    DetailCategoryService detailCategoryService;
    @Autowired
    CompanyService companyService;
    @Autowired
    RoomService roomService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    SectionService sectionService;
    @Autowired
    LocationService locationService;
    @Autowired
    AssetService assetService;
    @Autowired
    PreparationService preparationService;
    @Autowired
    UserService userService;
    @Autowired
    CityService cityService;
    @Autowired
    MaintenanceInsuranceService maintenanceInsuranceService;
    @Autowired
    MaintenanceWarrantyService maintenanceWarrantyService;
    @Autowired
    MaintenanceServiceAgreementService maintenanceServiceAgreementService;
    @Autowired
    MaintenanceLeaseAssetService maintenanceLeaseAssetService;
    @Autowired
    DisposalCategoryService disposalCategoryService;
    @Autowired
    AnalysisService analysisService;
    @Autowired
    LeaseAssetRemindersService leaseAssetRemindersService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Value("${application.companyName}")
    private String companyName;


    @RequestMapping("labelPrint")
    public ModelAndView labelPrint(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the labelPrint Method : " + e.getMessage());
        }


        List<DepartmentMaster> departments = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        List<AssetCatergoryDetail> catergoryDetails = detailCategoryService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("catergoryDetails", catergoryDetails);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("labelPrintReport-reports");

    }

    @RequestMapping("masterListing")
    public ModelAndView loadMasterListing(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadMasterListing Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("masterListingReport-reports");

    }

    @RequestMapping("assetDepreciate")
    public ModelAndView loadAssetDepreciate(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadAssetDepreciate Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("assetDepreciateReport-reports");

    }

    @RequestMapping("companyWiseAsset")
    public ModelAndView loadCompanyWiseAsset(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadCompanyWiseAsset Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("companyWiseAssetReport-reports");

    }

    @RequestMapping("departmentWiseAsset")
    public ModelAndView loadDepartmentWiseAssetReport(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDepartmentWiseAssetReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("departmentWiseAssetReport-reports");

    }

    @RequestMapping("sectionWiseAsset")
    public ModelAndView loadSectionWiseAssetReport(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadSectionWiseAssetReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("sectionWiseAssetReport-reports");

    }

    @RequestMapping("locationWiseAsset")
    public ModelAndView loadLocationWiseAssetReport(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadLocationWiseAssetReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("locationWiseAssetReport-reports");
    }

    @RequestMapping("assetListing")
    public ModelAndView loadAssetListingReport(Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<SectionMaster> sectionMasters = new ArrayList<SectionMaster>();
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));


            sectionMasters = sectionService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadAssetListingReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("sections", sectionMasters);
        model.addAttribute("locations", locationMasters);
        return new ModelAndView("assetListingReport-reports");

    }

    @RequestMapping("assetListingByQuantity")
    public ModelAndView loadAssetListingByQuantityReport(Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<SectionMaster> sectionMasters = new ArrayList<SectionMaster>();
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));


            sectionMasters = sectionService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadAssetListingReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("sections", sectionMasters);
        model.addAttribute("locations", locationMasters);
        return new ModelAndView("assetListingByQuantityReport-reports");

    }

    @RequestMapping("disposalListing")
    public ModelAndView loadDisposalReport(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDisposalReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("disposalListingReport-reports");

    }

    @RequestMapping("damagedListing")
    public ModelAndView loaDamagedDReport(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the loaDamagedDReport Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("damagedListing-reports");

    }

    @RequestMapping("finalSummary")
    public ModelAndView finalSummary(Model model, HttpServletRequest request) {
        List<AssetCatergoryMain> assetCatergoryMains = new ArrayList<AssetCatergoryMain>();
        String fromDate = "", toDate = "";
        Boolean doPreparate = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                assetCatergoryMains = mainCategoryService.findAll();
                ReportPreparationData preparationData = preparationService.getPreparationData();
                if (preparationData != null) {
                    fromDate = simpleDateFormat.format(preparationData.getStartDate());
                    toDate = simpleDateFormat.format(preparationData.getEndDate());
                } else {
                    doPreparate = true;
                }
            }
        } catch (Exception e) {
        }
        model.addAttribute("mainCats", assetCatergoryMains);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("doPreparate", doPreparate);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("finalSummary-reports");

    }


    @RequestMapping("withDepreciation")
    public ModelAndView withDepreciation(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        String fromDate = "", toDate = "";
        Boolean doPreparate = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                //assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                ReportPreparationData preparationData = preparationService.getPreparationData();
                if (preparationData != null) {
                    fromDate = simpleDateFormat.format(preparationData.getStartDate());
                    toDate = simpleDateFormat.format(preparationData.getEndDate());
                } else {
                    doPreparate = true;
                }
            }
        } catch (Exception e) {


        }
        model.addAttribute("assets", assets);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("doPreparate", doPreparate);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("assetDepreciateReport-reports");

    }

    @RequestMapping("locationSummary")
    public ModelAndView locationSummary(Model model, HttpServletRequest request) {
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        String fromDate = "", toDate = "";
        Boolean doPreparate = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                ReportPreparationData preparationData = preparationService.getPreparationData();
                if (preparationData != null) {
                    fromDate = simpleDateFormat.format(preparationData.getStartDate());
                    toDate = simpleDateFormat.format(preparationData.getEndDate());
                } else {
                    doPreparate = true;
                }
            }
        } catch (Exception e) {
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("doPreparate", doPreparate);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("locationSummary-reports");

    }

    @RequestMapping("departmentSummary")
    public ModelAndView departmentSummary(Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        String fromDate = "", toDate = "";
        Boolean doPreparate = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                ReportPreparationData preparationData = preparationService.getPreparationData();
                if (preparationData != null) {
                    fromDate = simpleDateFormat.format(preparationData.getStartDate());
                    toDate = simpleDateFormat.format(preparationData.getEndDate());
                } else {
                    doPreparate = true;
                }
            }
        } catch (Exception e) {
        }
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("doPreparate", doPreparate);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("departmentSummary-reports");

    }

    @RequestMapping("sectionSummary")
    public ModelAndView sectionSummary(Model model, HttpServletRequest request) {
        List<SectionMaster> sectionMasters = new ArrayList<SectionMaster>();
        String fromDate = "", toDate = "";
        Boolean doPreparate = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                sectionMasters = sectionService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                ReportPreparationData preparationData = preparationService.getPreparationData();
                if (preparationData != null) {
                    fromDate = simpleDateFormat.format(preparationData.getStartDate());
                    toDate = simpleDateFormat.format(preparationData.getEndDate());
                } else {
                    doPreparate = true;
                }
            }
        } catch (Exception e) {
        }
        model.addAttribute("sections", sectionMasters);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("doPreparate", doPreparate);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("sectionSummary-reports");

    }

    @RequestMapping("transferListing")
    public ModelAndView transferListing(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        String fromDate = "", toDate = "";
        Boolean doPreparate = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

//                assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                ReportPreparationData preparationData = preparationService.getPreparationData();
                if (preparationData != null) {
                    fromDate = simpleDateFormat.format(preparationData.getStartDate());
                    toDate = simpleDateFormat.format(preparationData.getEndDate());
                } else {
                    doPreparate = true;
                }
            }
        } catch (Exception e) {
        }
        model.addAttribute("assets", assets);
        model.addAttribute("doPreparate", doPreparate);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("transferListing-reports");
    }

    @RequestMapping("reminder")
    public ModelAndView loadReminder(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadMasterListing Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("reminder-reports");

    }


    @RequestMapping("individualAsset")
    public ModelAndView individualAsset(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<Asset>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            assets = assetService.getAssetList(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
        }
        model.addAttribute("assets", assets);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("individualAsset-reports");
    }

    @RequestMapping("loadListing/{listingId}")
    @ResponseBody
    public List<Listing> loadListing(@PathVariable("listingId") int listingId) {
        List<Listing> listing = new ArrayList<Listing>();
        try {
            switch (listingId) {
                case 1:

                    listing = mainCategoryService.getMainCatListing();
                    break;
                case 2:
                    listing = subCategoryService.getSubCatListing();
                    break;
                case 3:

                    listing = detailCategoryService.getDetailCatListing();
                    break;
                case 4:
                    listing = companyService.getCompanyListing();
                    break;
                case 5:

                    listing = departmentService.getDepartmentListing();
                    break;
                case 6:

                    listing = sectionService.getSectionListing();
                    break;
                case 7:

                    listing = locationService.getLocationListing();
                    break;
                case 8:

                    listing = assetService.getAssetListing();
                    break;

                case 9:
                    listing = cityService.getCityListing();
                    break;

                case 10:
                    listing = buildingService.getBuildingListing();
                    break;

                case 11:
                    listing = floorService.getFloorListing();
                    break;

                case 12:
                    listing = roomService.getRoomListing();
                    break;

                case 13:
                    listing = disposalCategoryService.getAssetDisposalReasonListing();
                    break;
                case 14:
                    listing = analysisService.getAssetDisposalReasonListing();
                    break;
            }
        } catch (Exception e) {
        }
        return listing;
    }


    @RequestMapping("genarateMasterListing")
    public void genarateMasterListing(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromCode");
        String toCode = request.getParameter("toCode");
        int listingType = Integer.parseInt(request.getParameter("listingType"));
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/listing");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            String fromCodeShow = "";
            String toCodeShow = "";
            switch (listingType) {
                case 1:
                    fromCodeShow = mainCategoryService.getMainCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = mainCategoryService.getMainCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/mainCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    break;
                case 2:
                    fromCodeShow = subCategoryService.getSubCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = subCategoryService.getSubCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream2 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/subCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream2, outStream, params, c);
                    break;
                case 3:
                    fromCodeShow = detailCategoryService.getDetailCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = detailCategoryService.getDetailCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream3 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/detailCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream3, outStream, params, c);
                    break;
                case 4:
                    fromCodeShow = companyService.getCompanyCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = companyService.getCompanyCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream4 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/CompanyListing.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream4, outStream, params, c);
                    break;
                case 5:
                    fromCodeShow = departmentService.getDepartmentCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = departmentService.getDepartmentCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream5 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/departmentDetails.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream5, outStream, params, c);
                    break;
                case 6:
                    fromCodeShow = sectionService.getSectionCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = sectionService.getSectionCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream6 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/sectionCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream6, outStream, params, c);
                    break;
                case 7:
                    fromCodeShow = locationService.getLocationCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = locationService.getLocationCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream7 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/locationCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream7, outStream, params, c);
                    break;
                case 8:
                    fromCodeShow = assetService.getAssetCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = assetService.getAssetCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream8 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/assetCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream8, outStream, params, c);
                    break;

                case 9:
                    fromCodeShow = cityService.getCityCodeById(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = cityService.getCityCodeById(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream9 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/CityListing.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream9, outStream, params, c);
                    break;


                case 10:
                    fromCodeShow = buildingService.getBuildingCodeById(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = buildingService.getBuildingCodeById(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream10 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/Building.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream10, outStream, params, c);
                    break;

                case 11:
                    fromCodeShow = floorService.getFloorCodeById(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = floorService.getFloorCodeById(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream11 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/Floor.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream11, outStream, params, c);
                    break;

                case 12:
                    fromCodeShow = roomService.getRoomCodeById(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = roomService.getRoomCodeById(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream12 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/Room.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream12, outStream, params, c);
                    break;
                case 13:
                    params.put("fromCodeShow", fromCode);
                    params.put("toCodeShow", toCode);
                    InputStream areportstream13 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/Disposal.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream13, outStream, params, c);
                    break;
                case 14:
                    fromCodeShow = analysisService.getAnalysisCodeById(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
                    toCodeShow = analysisService.getAnalysisCodeById(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream14 = this.getClass().getClassLoader().getResourceAsStream("reports/listing/Analysis.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream14, outStream, params, c);
                    break;
            }
            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateFinalSummary")
    public void genarateFinalSummary(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromMainCode");
        String toCode = request.getParameter("toMainCode");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        int groupType = Integer.parseInt(request.getParameter("groupType"));
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("FromDate", fromDate);
            params.put("ToDate", toDate);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/finalSummary");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = " AND DEP.DEP_ID=" + depId;
                params.put("query", query);
            }

            String fromCodeShow = "";
            String toCodeShow = "";
            fromCodeShow = mainCategoryService.getMainCatCode(fromCode);
            toCodeShow = mainCategoryService.getMainCatCode(toCode);
            switch (groupType) {
                case 1:
//                    fromCodeShow = mainCategoryService.getMainCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
//                    toCodeShow = mainCategoryService.getMainCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/finalSummary/finalSummarymain.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    break;
                case 2:
//                    fromCodeShow = subCategoryService.getSubCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
//                    toCodeShow = subCategoryService.getSubCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream2 = this.getClass().getClassLoader().getResourceAsStream("reports/finalSummary/finalSummarycategorycode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream2, outStream, params, c);
                    break;
                case 3:
                    fromCodeShow = mainCategoryService.getMainCatCode(fromCode);
//                    fromCodeShow = detailCategoryService.getDetailCatCode(fromCode);
                    params.put("fromCodeShow", fromCodeShow);
//                    toCodeShow = detailCategoryService.getDetailCatCode(toCode);
                    params.put("toCodeShow", toCodeShow);
                    InputStream areportstream3 = this.getClass().getClassLoader().getResourceAsStream("reports/finalSummary/finalSummaryDetailCode.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream3, outStream, params, c);
            }
            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateAssetListing")
    public void genarateAssetListing(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        HttpSession session = request.getSession();
        int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
        String branchFrom = request.getParameter("branchFrom");
        String branchTo = request.getParameter("branchTo");
        String sectionFrom = request.getParameter("secFrom");
        String sectionTo = request.getParameter("secTo");
        String locationFrom = request.getParameter("locFrom");
        String locationTo = request.getParameter("locTo");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String dateWise = request.getParameter("dateWise");
        String btnDetails = request.getParameter("btnDetails");
        User user = null;
        try {
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/asset_details");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

//            if (btnDetails.equals("1")) {
                if (dateWise == null) {
                    params.put("BranchFrom", Integer.parseInt(branchFrom));
                    params.put("BranchTo", Integer.parseInt(branchTo));
                    params.put("SecFrom", Integer.parseInt(sectionFrom));
                    params.put("SecTo", Integer.parseInt(sectionTo));
                    params.put("LocFrom", Integer.parseInt(locationFrom));
                    params.put("LocTo", Integer.parseInt(locationTo));
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListning.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                } else {
                    params.put("Fromdate", fromDate);
                    params.put("Todate", toDate);
                    if (depId == 0) {
                        params.put("query", " ");
                    } else {
                        String query = " AND DM.DEP_ID=" + depId;
                        params.put("query", query);
                    }
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningDateRange.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                }
//            } else {
//                if (dateWise == null) {
//                    params.put("BranchFrom", Integer.parseInt(branchFrom));
//                    params.put("BranchTo", Integer.parseInt(branchTo));
//                    params.put("SecFrom", Integer.parseInt(sectionFrom));
//                    params.put("SecTo", Integer.parseInt(sectionTo));
//                    params.put("LocFrom", Integer.parseInt(locationFrom));
//                    params.put("LocTo", Integer.parseInt(locationTo));
//                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntity.jasper");
//                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
//                } else {
//                    params.put("Fromdate", fromDate);
//                    params.put("Todate", toDate);
//                    if (depId == 0) {
//                        params.put("query", " ");
//                    } else {
//                        String query = "AND DM.DEP_ID=" + depId;
//                        params.put("query", query);
//                    }
//                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntityDateRange.jasper");
//                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
//                }
//            }
            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateAssetListingByQuantity")
    public void genarateAssetListingByQuantity(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        HttpSession session = request.getSession();
        int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
        String branchFrom = request.getParameter("branchFrom");
        String branchTo = request.getParameter("branchTo");
        String sectionFrom = request.getParameter("secFrom");
        String sectionTo = request.getParameter("secTo");
        String locationFrom = request.getParameter("locFrom");
        String locationTo = request.getParameter("locTo");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String dateWise = request.getParameter("dateWise");
        int groupId = Integer.parseInt(request.getParameter("groupType"));

        User user = null;
        try {
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/asset_details");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int stage=0;
            if (dateWise == null) {
                if (branchFrom != "") {
                    if (sectionFrom != "") {
                        if (locationFrom != "")
                            stage = 1;
                        else
                            stage = 2;
                    } else {
                        stage = 3;
                    }
                }
            } else {
                stage = 4;
            }
//             Only given Department Codes... Write if statementt for stage=4,stage=2 And use separate report for date

            if (dateWise == null) {
                if (stage == 1) {
                    int secFrom = Integer.parseInt(sectionFrom);
                    int secTo = Integer.parseInt(sectionTo);
                    int locFrom = Integer.parseInt(locationFrom);
                    int locTo = Integer.parseInt(locationTo);

                    params.put("BranchFrom", Integer.parseInt(branchFrom));
                    params.put("BranchTo", Integer.parseInt(branchTo));
                    params.put("SecFrom", Integer.parseInt(sectionFrom));
                    params.put("SecTo", Integer.parseInt(sectionTo));
                    params.put("LocFrom", Integer.parseInt(locationFrom));
                    params.put("LocTo", Integer.parseInt(locationTo));


//                String query = " AND SM.SEC_ID BETWEEN " + secFrom+" AND "+secTo+ " AND LM.LOC_ID BETWEEN " + locFrom+" AND "+locTo+"";
//                params.put("query", query);
                    if (groupId == 1) {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntityAllBymain1.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    } else if (groupId == 2) {
                        InputStream areportstream2 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntityAllBysub.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream2, outStream, params, c);
                    } else {
                        InputStream areportstream3 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntityAllBydetail.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream3, outStream, params, c);
                    }
                } else if (stage == 2) {
                    int secFrom = Integer.parseInt(sectionFrom);
                    int secTo = Integer.parseInt(sectionTo);
                    params.put("BranchFrom", Integer.parseInt(branchFrom));
                    params.put("BranchTo", Integer.parseInt(branchTo));
                    params.put("SecFrom", Integer.parseInt(sectionFrom));
                    params.put("SecTo", Integer.parseInt(sectionTo));

                    if (groupId == 1) {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntityBydepANDsecBymain.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    } else if (groupId == 2) {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntityBydepANDsecBysub.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    } else {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningquntitylBydepANDsecBydetail.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    }

                }else  if (stage == 3){
                    params.put("BranchFrom", Integer.parseInt(branchFrom));
                    params.put("BranchTo", Integer.parseInt(branchTo));

                    if (groupId == 1) {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningByQuantityMain.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    } else if (groupId == 2) {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningByQuantitySub.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    } else {
                        InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningByQuantityDetail.jasper");
                        JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                    }
                }

            } else{

                params.put("Fromdate", fromDate);
                params.put("Todate", toDate);

//                Report is not supported beause of followings.
//                if (depId == 0) {
//                    params.put("query", " ");
//                } else {
//                    String query = " AND DM.DEP_ID= " + 1+"";
//                    params.put("query",query);
//                }


                if (groupId == 1) {
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningByQuantityByDateMain.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                } else if (groupId == 2) {
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningByQuantityByDateSub.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                } else {
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/asset_details/assetListningByQuantityByDatedetail.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                }

            }

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("genarateWithDepreciation")
    public void genarateWithDepreciation(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = simpleDateFormat.parse(request.getParameter("Fromdate"));
            toDate = simpleDateFormat.parse(request.getParameter("Todate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            String fromCodeShow = "";
            String toCodeShow = "";
//            Asset assetFrom = assetService.getAsset(Integer.parseInt(fromCode));
//            Asset assetTo = assetService.getAsset(Integer.parseInt(toCode));
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(simpleDateFormat.parse(fromDate));
//            int daysToDecrement = -1;
//            cal.add(Calendar.DATE, daysToDecrement);
//            Date bfDate = cal.getTime(); // again get back your date object

            params.put("bf_Date", fromDate);
            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("Fromdate", fromDate);
            params.put("Todate", toDate);
            params.put("CompanyName", companyName);
            params.put("fromCodeShow", fromCodeShow);
            params.put("toCodeShow", toCodeShow);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/assetWthDepreciation");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);
            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
                params.put("queryInner", " ");
            } else {
                String queryInnner = "INNER JOIN location_master AS l ON (l.LOC_ID = asset.LOC_ID)" +
                        " INNER JOIN section_master AS sm ON (l.SEC_ID=sm.SEC_ID)" +
                        " INNER JOIN department_master AS dm ON (sm.DEP_ID=dm .DEP_ID)";
                String query = "AND dm.DEP_ID=" + depId + " GROUP BY asset.AS_CODE";
                params.put("query", query);
                params.put("queryInner", queryInnner);
            }

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/assetWthDepreciation/assetwithDepreciation.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateLocationSummary")
    public void genarateLocationSummary(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        String fromDate = request.getParameter("Fromdate");
        String toDate = request.getParameter("Todate");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            String fromCodeShow = "";
            String toCodeShow = "";

            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("FromDate", fromDate);
            params.put("ToDate", toDate);
            params.put("CompanyName", companyName);
            params.put("fromCodeShow", fromCodeShow);
            params.put("toCodeShow", toCodeShow);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/locationSummary");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = "AND dep.DEP_ID=" + depId;
                params.put("query", query);
            }

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/locationSummary/finalLocationWise.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateBranchSummary")
    public void genarateBranchSummary(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        String fromDate = request.getParameter("Fromdate");
        String toDate = request.getParameter("Todate");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            String fromCodeShow = "";
            String toCodeShow = "";

            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("FromDate", fromDate);
            params.put("ToDate", toDate);
            params.put("CompanyName", companyName);
            params.put("fromCodeShow", fromCodeShow);
            params.put("toCodeShow", toCodeShow);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/departmentSummary");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = "AND dm.DEP_ID=" + depId;
                params.put("query", query);
            }
            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/departmentSummary/departmentWise.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateSectionSummary")
    public void genarateSectionSummary(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        String fromDate = request.getParameter("Fromdate");
        String toDate = request.getParameter("Todate");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            String fromCodeShow = "";
            String toCodeShow = "";

            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("FromDate", fromDate);
            params.put("ToDate", toDate);
            params.put("CompanyName", companyName);
            params.put("fromCodeShow", fromCodeShow);
            params.put("toCodeShow", toCodeShow);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/sectionSummary");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = "AND dm.DEP_ID=" + depId;
                params.put("query", query);
            }
            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/sectionSummary/sectionWise.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateTransferListing")
    public void genarateTransferListing(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String type = request.getParameter("btnDetails");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            String fromCodeShow = "";
            String toCodeShow = "";


            params.put("CompanyName", companyName);
            params.put("fromCodeShow", fromCodeShow);
            params.put("toCodeShow", toCodeShow);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/transferListing");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            if (type.equals("1")) {
                params.put("fromCodeShow", fromDate);
                params.put("toCodeShow", toDate);
                params.put("FromDate", fromDate);
                params.put("ToDate", toDate);
                InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/transferListing/transfersListing.jasper");
                JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
            } else {
                Asset asset1 = assetService.getAsset(Integer.parseInt(fromCode));
                params.put("fromCodeShow", asset1.getAsCode());
                Asset asset2 = assetService.getAsset(Integer.parseInt(toCode));
                params.put("toCodeShow", asset2.getAsCode());
                params.put("FromDate", fromCode);
                params.put("ToDate", toCode);
                InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/transferListing/transfersListingOrderByAssetID.jasper");
                JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
            }

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateIndividualAsset")
    public void genarateIndividualAsset(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/detailsOfIndividualAsset/");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("queryAND", " ");
            } else {
                String query = "AND dep.DEP_ID=" + depId;
                params.put("queryAND", query);
            }

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/detailsOfIndividualAsset/DetailofIndividualAsset.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateDisposalListing")
    public void genarateDisposalListing(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromcord");
        String toCode = request.getParameter("tocord");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("FromDate", fromCode);
            params.put("ToDate", toCode);
            params.put("fromCodeShow", fromCode);
            params.put("toCodeShow", toCode);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/disposalListing");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = "AND dm.DEP_ID=" + depId;
                params.put("query", query);
            }

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/disposalListing/DisposalListing.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateDamagedListing")
    public void genarateDamagedListing(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromDate");
        String toCode = request.getParameter("toDate");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("FromDate", fromCode);
            params.put("ToDate", toCode);
            params.put("fromCodeShow", fromCode);
            params.put("toCodeShow", toCode);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/damageListing");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = "AND dep.DEP_ID=" + depId;
                params.put("query", query);
            }

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/damageListing/damageListing.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genarateLabelPrint")
    public void genarateLabelPrint(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String fromCode = request.getParameter("fromCode");
        String toCode = request.getParameter("toCode");
        String depId = request.getParameter("depId");
        String detailId = request.getParameter("detailId");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        User user = null;
        try {
            HttpSession session = request.getSession();
            int departId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("fromcord", fromCode);
            params.put("tocord", toCode);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/lablePrint");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            if (!depId.equals("0")) {
                params.put("depId", Integer.parseInt(depId));
                if (StringUtils.isNotEmpty(fromDate) && StringUtils.isNotEmpty(toDate)) {
                    params.put("query", "AND sm.DEP_ID='" + Integer.parseInt(depId) + "' AND  asset.PUR_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "'");
                    InputStream areportstream5 = this.getClass().getClassLoader().getResourceAsStream("reports/lablePrint/lableprintDepartment.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream5, outStream, params, c);
                } else if (!detailId.equals("0")) {
                    params.put("depId", Integer.parseInt(detailId));
                    params.put("query", "AND sm.DEP_ID='" + Integer.parseInt(depId) + "' AND asset.AS_DCAT_ID='" + Integer.parseInt(detailId) + "'");
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/lablePrint/lableprintDepartment.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                } else {
                    params.put("query", "AND sm.DEP_ID='" + Integer.parseInt(depId) + "' AND  asset.AS_ID BETWEEN '" + fromCode + "' AND '" + toCode + "' ");
                    InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/lablePrint/lableprintDepartment.jasper");
                    JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
                }
            } else if (!detailId.equals("0")) {
                params.put("depId", Integer.parseInt(detailId));
                InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/lablePrint/lableprintDetailCode.jasper");
                JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
            } else if (StringUtils.isNotEmpty(fromCode) && StringUtils.isNotEmpty(toCode)) {
                params.put("fromDate", fromDate);
                params.put("toDate", toDate);
                if (departId == 0) {
                    params.put("query", " ");
                } else {
                    params.put("query", "AND dep.DEP_ID=" + departId);
                }
                InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/lablePrint/lableprintDate.jasper");
                JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
            } else {
                InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/lablePrint/lableprint.jasper");
                JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
            }


            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genaratePhysicalVerification")
    public void genaratePhysicalVerification(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String locationId = request.getParameter("locationId");
        String locationName = request.getParameter("locationName");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("locationId", Integer.parseInt(locationId));
            params.put("locationName", locationName);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/physicalVerification");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/physicalVerification/PhysicalVerification.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            logger.error("Error occurred while calling the genaratePhysicalVerification Method : " + e.getMessage());
        }
    }

    @RequestMapping("genarateBeforePhysicalVerification")
    public void genarateBeforePhysicalVerification(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String locationId = request.getParameter("locationId");
        String locationName = request.getParameter("locationName");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("locationId", Integer.parseInt(locationId));
            params.put("locationName", locationName);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/physicalVerification");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/physicalVerification/physicalVerificationBeforePrint.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            logger.error("Error occurred while calling the genarateBeforePhysicalVerification Method : " + e.getMessage());
        }
    }

    @RequestMapping("genarateAssetRevaluation")
    public void genarateAssetRevaluation(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        int locationId = Integer.parseInt(request.getParameter("locationId2"));
        String locationName = request.getParameter("locationName");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("locationId", locationId);
            params.put("locationName", locationName);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/assetRevaluation");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/assetRevaluation/assetRevaluation.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            logger.error("Error occurred while calling the genarateAssetRevaluation Method : " + e.getMessage());
        }
    }

    @RequestMapping("genarateAccessoryAssigning")
    public void genarateAccessoryAssigning(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        int assetId = Integer.parseInt(request.getParameter("assetId"));
        String assetDesc = request.getParameter("assetDesc");
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("assetId", assetId);
            params.put("assetDesc", assetDesc);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/accessoryAssigning");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/accessoryAssigning/accessoriesAssigning.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            logger.error("Error occurred while calling the genarateAccessoryAssigning Method : " + e.getMessage());
        }
    }
//    @RequestMapping("genarateMaintenanceDataReport")
//    public void genarateMaintenanceDataReport(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/pdf");
//
//        int assetId = Integer.parseInt(request.getParameter("assetId2"));
//        String assetDesc = request.getParameter("assetDesc");
//        User user = null;
//        try {
//            HttpSession session = request.getSession();
//            if (session.getAttribute("user") != null) {
//                user = (User) session.getAttribute("user");
//            }
//            Connection c = new DBFacade().connect();
//            ServletOutputStream outStream = response.getOutputStream();
//            Map<String, Object> params = new HashMap<String, Object>();
//
//            params.put("assetId", assetId);
//                params.put("assetDesc",assetDesc);
//            params.put("CompanyName", companyName);
//            params.put("userName", user.getUserName());
//            params.put("REPORT_CONNECTION", c);
//
//            URL url = this.getClass().getClassLoader().getResource("reports/maintenanceData");
//            String path = url.toURI().toString();//getPath() + "/";
//            params.put("SUBREPORT_DIR", path);
//
//            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenanceData/MAINTENANCE_DATA_REPORT.jasper");
//            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
//
//            c.close();
//            c = null;
//            params = null;
//            outStream.flush();
//            outStream.close();
//        } catch (Exception e) {
//            logger.error( "Error occurred while calling the genarateMaintenanceDataReport Method : " + e.getMessage() );
//        }
//    }
//    @RequestMapping("genarateRunningDataReport")
//    public void genarateRunningDataReport(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/pdf");
//
//        int assetId = Integer.parseInt(request.getParameter("assetId2"));
//        String assetDesc = request.getParameter("assetDesc");
//        User user = null;
//        try {
//            HttpSession session = request.getSession();
//            if (session.getAttribute("user") != null) {
//                user = (User) session.getAttribute("user");
//            }
//            Connection c = new DBFacade().connect();
//            ServletOutputStream outStream = response.getOutputStream();
//            Map<String, Object> params = new HashMap<String, Object>();
//
//            params.put("assetId", assetId);
//            params.put("assetDesc",assetDesc);
//            params.put("CompanyName", companyName);
//            params.put("userName", user.getUserName());
//            params.put("REPORT_CONNECTION", c);
//
//            URL url = this.getClass().getClassLoader().getResource("reports/runningData");
//            String path = url.toURI().toString();//getPath() + "/";
//            params.put("SUBREPORT_DIR", path);
//
//            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/runningData/RUNNING_DATA_REPORT.jasper");
//            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
//
//            c.close();
//            c = null;
//            params = null;
//            outStream.flush();
//            outStream.close();
//        } catch (Exception e) {
//            logger.error( "Error occurred while calling the genarateRunningDataReport Method : " + e.getMessage() );
//        }
//    }


    //************* Maintenance Reports ************************

    @RequestMapping("generateLandPrimaryData")
    public void generateLandPrimaryData(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        User user = null;
        try {
            int assetFrom = Integer.parseInt(request.getParameter("assetFrom"));
            int assetTo = Integer.parseInt(request.getParameter("assetTo"));
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("headerName", "");
            params.put("fromValue", assetFrom);
            params.put("toValue", assetTo);
            params.put("company", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/maintenance");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            InputStream areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LAND_PRIMARY_DATA.jasper");
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while calling the generateLandPrimaryData Method : " + e.getMessage());
        }
    }

    @RequestMapping("maintenanceMasterListingReport")
    public ModelAndView maintenanceMasterListingReport(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the maintenanceMasterListingReport Method : " + e.getMessage());
        }

        model.addAttribute("companyName", companyName);
        model.addAttribute("assets", assets);
        return new ModelAndView("maintenanceMasterListingReport-reports");

    }

    @RequestMapping("generateMaintenanceMasterListingReport")
    public void generateLandSecondaryData(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        HttpSession session = request.getSession();
        int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
        User user = null;
        try {
            int assetFrom = Integer.parseInt(request.getParameter("assetFrom"));
            int assetTo = Integer.parseInt(request.getParameter("assetTo"));
            int listing = Integer.parseInt(request.getParameter("listingType"));
            int dataType = Integer.parseInt(request.getParameter("dataType"));
            String assetCodeFrom = assetService.getAssetCodes(assetFrom);
            String assetCodeTo = assetService.getAssetCodes(assetTo);

            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("headerName", "");
            params.put("fromValue", assetFrom);
            params.put("toValue", assetTo);
            params.put("assetCodeFrom", assetCodeFrom);
            params.put("assetCodeTo", assetCodeTo);

            params.put("company", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/maintenance");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            if (depId == 0) {
                params.put("queryJoin", " ");
                params.put("queryAND", " ");
            } else {
                String queryJoin = "INNER JOIN location_master loc ON loc.LOC_ID = A.LOC_ID\n" +
                        "INNER JOIN section_master sec ON sec.SEC_ID = loc.SEC_ID\n" +
                        "INNER JOIN department_master dep ON dep.DEP_ID = sec.DEP_ID";
                params.put("queryJoin", queryJoin);

                String queryAND = "AND dep.DEP_ID = " + depId;
                params.put("queryAND", queryAND);
            }

            InputStream areportstream1 = null;
            switch (listing) {
                case 1:
                    if (dataType == 1) {
                        areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LAND_PRIMARY_DATA.jasper");
                    } else {
                        areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LAND_SECONDARY_DATA.jasper");
                    }
                    break;
                case 2:
                    if (dataType == 1) {
                        areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LAND_BUILDING_PRIMARY_DATA.jasper");
                    } else {
                        areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LAND_BUILDING_SECONDARY_DATA.jasper");
                    }
                    break;
                case 3:
                    if (dataType == 1) {
                        areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/MOTOR_VEHICLES_PRIMARY_DATA.jasper");
                    } else {
                        areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/MOTOR_VEHICLES_SECONDARY_DATA.jasper");
                    }
                    break;
                case 4:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/COMPUTERS_PRIMARY_DATA.jasper");
                    break;
                case 5:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/PLANT_MACHINERY_PRIMARY_DATA.jasper");
                    break;
                case 6:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/FURNITURE_PRIMARY_DATA.jasper");
                    break;
                case 7:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/OFFICE_EQUIPMENTS_PRIMARY_DATA.jasper");
                    break;
                case 8:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LAB_EQUIPMENTS_PRIMARY_DATA.jasper");
                    break;
                case 9:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/TEACHING_EQUIPMENTS_PRIMARY_DATA.jasper");
                    break;
                case 10:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/FIXTURES_FITTINGS_PRIMARY_DATA.jasper");
                    break;
                case 11:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LIBRARY_BOOKS_PRIMARY_DATA.jasper");
                    break;
                case 12:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/SPORTS_EQUIPMENTS_PRIMARY_DATA.jasper");
                    break;
                case 13:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/SOFTWARE_PRIMARY_DATA.jasper");
                    break;
            }
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while calling the generateLandSecondaryData Method : " + e.getMessage());
        }
    }

    @RequestMapping("maintenanceTransactionListingReport")
    public ModelAndView maintenanceTransactionListingReport(Model model, HttpServletRequest request) {
        List<Asset> assets = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
//            assets = assetService.getAssetInsuranceList();
        } catch (Exception e) {
            logger.error("Error occurred while calling the maintenanceTransactionListingReport Method : " + e.getMessage());
        }

        model.addAttribute("companyName", companyName);
//        model.addAttribute("assets", assets);
        return new ModelAndView("maintenanceTransactionListingReport-reports");

    }

    @RequestMapping("generateMaintenanceTransactionListingReport")
    public void generateMaintenanceTransactionListingReport(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        HttpSession session = request.getSession();
        int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
        User user = null;
        try {
            int assetFrom = Integer.parseInt(request.getParameter("assetFrom"));
            int assetTo = Integer.parseInt(request.getParameter("assetTo"));
            int listing = Integer.parseInt(request.getParameter("listingType"));
            String fromCode = request.getParameter("fromAssetCode");
            String toCode = request.getParameter("toAssetCode");
            String assetCodeFrom = assetService.getAssetCodes(assetFrom);
            String assetCodeTo = assetService.getAssetCodes(assetTo);
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("headerName", "");
            params.put("fromValue", assetFrom);
            params.put("toValue", assetTo);
            params.put("fromCode", assetCodeFrom);
            params.put("assetCodeFrom", assetCodeFrom);
            params.put("assetCodeTo", assetCodeTo);
            params.put("toCode", assetCodeTo);
            params.put("company", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/maintenance");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            if (depId == 0) {
                params.put("queryJoin", " ");
                params.put("queryAND", " ");
            } else {
                String queryJoin = "INNER JOIN location_master loc ON loc.LOC_ID = A.LOC_ID\n" +
                        "INNER JOIN section_master sec ON sec.SEC_ID = loc.SEC_ID\n" +
                        "INNER JOIN department_master dep ON dep.DEP_ID = sec.DEP_ID";
                params.put("queryJoin", queryJoin);

                String queryAND = "AND dep.DEP_ID = " + depId;
                params.put("queryAND", queryAND);
            }

            InputStream areportstream1 = null;
            switch (listing) {
                case 1:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/INSURANCE.jasper");
                    break;
                case 2:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/WARRANTY.jasper");
                    break;
                case 3:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/SERVICE_AGREEMENT.jasper");
                    break;
                case 4:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/MODIFICATION.jasper");
                    break;
                case 5:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/MAINTENANCE_DATA.jasper");
                    break;
                case 6:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/RUNNING_DATA.jasper");
                    break;
                case 7:
                    areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/maintenance/LEASE_ASSET.jasper");

            }
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while calling the generateTransactionListings Method : " + e.getMessage());
        }
    }

    @RequestMapping("qrPrint")
    public ModelAndView qrPrint(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            List<DepartmentMaster> departments = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            List<AssetCatergoryDetail> catergoryDetails = detailCategoryService.findAll();
            model.addAttribute("departments", departments);
            model.addAttribute("catergoryDetails", catergoryDetails);
            model.addAttribute("companyName", companyName);
        } catch (Exception e) {
            logger.error("Error occurred while calling the labelPrint Method : " + e.getMessage());
        }
        return new ModelAndView("qrPrintReport-reports");

    }

    @RequestMapping("genarateQRPrint")
    public void genarateQRPrint(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        int fromCode = NumberUtils.toInt(request.getParameter("fromCode"));
        int toCode = NumberUtils.toInt(request.getParameter("toCode"));
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        int department = NumberUtils.toInt(request.getParameter("depId"));


        User user = null;
        try {
            HttpSession session = request.getSession();
            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("from", fromCode);
            params.put("to", toCode);
            params.put("CompanyName", companyName);
            params.put("userName", user.getUsername());
            params.put("REPORT_CONNECTION", c);

            URL url = this.getClass().getClassLoader().getResource("reports/qrPrint");
            String path = url.toURI().toString();//getPath() + "/";
            params.put("SUBREPORT_DIR", path);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            InputStream areportstream1 = null;
            if (depId == 0) {
                params.put("query", "AND dep.DEP_ID=" + department);
            } else {
                params.put("query", "AND dep.DEP_ID=" + depId);
            }
            areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/qrPrint/QR_CODE.jasper");

            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);
            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("loadData/{date}/{noDays}/{categoryType}/{userBranch}")
    @ResponseBody
    public List<ReminderPage> loadData(@PathVariable("date") String date, @PathVariable("noDays") int noDays, @PathVariable("categoryType") int categoryType, @PathVariable("userBranch") int branch) {
        List<ReminderPage> dueDetails = new ArrayList<ReminderPage>();
        List<MaintenanceInsurance> dueDetailsInsurance = new ArrayList<MaintenanceInsurance>();
        List<MaintenanceWarranty> dueDetailsWarranty = new ArrayList<MaintenanceWarranty>();
        List<MaintenanceServiceAgreements> dueDetailsServiceAgreements = new ArrayList<MaintenanceServiceAgreements>();
        List<MaintenanceLeaseAsset> dueDetailsLeaseAsset = new ArrayList<MaintenanceLeaseAsset>();
        try {
            if (categoryType == 1) {
                dueDetailsInsurance = maintenanceInsuranceService.loadData(simpleDateFormat.parse(date), noDays, branch);

                for (int i = 0; i < dueDetailsInsurance.size(); i++) {
                    ReminderPage reminderPage = new ReminderPage();
                    String assetCode = assetService.getAssetCodes(dueDetailsInsurance.get(i).getAssetId());
                    reminderPage.setAssetCode(assetCode);
                    reminderPage.setDateFrom(dueDetailsInsurance.get(i).getInsurancePeriodFrom());
                    reminderPage.setDateTo(dueDetailsInsurance.get(i).getInsurancePeriodTo());
                    reminderPage.setValue(dueDetailsInsurance.get(i).getInsuranceValue());
                    reminderPage.setTransactionNo(dueDetailsInsurance.get(i).getTransactionNo());
                    dueDetails.add(reminderPage);
                }
            } else if (categoryType == 2) {
                dueDetailsWarranty = maintenanceWarrantyService.loadData(simpleDateFormat.parse(date), noDays, branch);
                for (int i = 0; i < dueDetailsWarranty.size(); i++) {
                    ReminderPage reminderPage = new ReminderPage();
                    String assetCode = assetService.getAssetCodes(dueDetailsWarranty.get(i).getAssetId());
                    reminderPage.setAssetCode(assetCode);
                    reminderPage.setDateFrom(dueDetailsWarranty.get(i).getWarrantyPeriodFrom());
                    reminderPage.setDateTo(dueDetailsWarranty.get(i).getWarrantyPeriodTo());
                    reminderPage.setTransactionNo(dueDetailsWarranty.get(i).getTransactionNo());
                    dueDetails.add(reminderPage);
                }

            } else if (categoryType == 3) {
                dueDetailsServiceAgreements = maintenanceServiceAgreementService.loadData(simpleDateFormat.parse(date), noDays, branch);
                for (int i = 0; i < dueDetailsServiceAgreements.size(); i++) {
                    ReminderPage reminderPage = new ReminderPage();
                    String assetCode = assetService.getAssetCodes(dueDetailsServiceAgreements.get(i).getAssetId());
                    reminderPage.setAssetCode(assetCode);
                    reminderPage.setDateFrom(dueDetailsServiceAgreements.get(i).getAgreeFrom());
                    reminderPage.setDateTo(dueDetailsServiceAgreements.get(i).getAgreeTo());
                    reminderPage.setTransactionNo(dueDetailsServiceAgreements.get(i).getTransactionNo());
                    reminderPage.setValue(dueDetailsServiceAgreements.get(i).getAgreeCost());
                    dueDetails.add(reminderPage);
                }

            } else if (categoryType == 4) {
                dueDetailsLeaseAsset = maintenanceLeaseAssetService.loadData(simpleDateFormat.parse(date), noDays, branch);
                leaseAssetRemindersService.deleteAll();
                for (int i = 0; i < dueDetailsLeaseAsset.size(); i++) {

                    ReminderPage reminderPage = new ReminderPage();
                    String assetCode = assetService.getAssetCodes(dueDetailsLeaseAsset.get(i).getAssetId());
                    reminderPage.setAssetCode(assetCode);
                    reminderPage.setDateFrom(dueDetailsLeaseAsset.get(i).getLeasePeriodFrom());
                    reminderPage.setDateTo(dueDetailsLeaseAsset.get(i).getLeasePremiumTo());
                    reminderPage.setTransactionNo(dueDetailsLeaseAsset.get(i).getTransactionNo());
                    reminderPage.setValue(dueDetailsLeaseAsset.get(i).getLeasePremium());
                    dueDetails.add(reminderPage);

//                    Update lease_asset_reminders table for getting lease asset reminder report

                    LeaseAssetReminders leaseAssetReminders = new LeaseAssetReminders();
                    leaseAssetReminders.setId(i + 1);
                    leaseAssetReminders.setAssetId(dueDetailsLeaseAsset.get(i).getAssetId());
                    leaseAssetReminders.setTransactionNo(dueDetailsLeaseAsset.get(i).getTransactionNo());
                    leaseAssetReminders.setPremium(dueDetailsLeaseAsset.get(i).getLeasePremium());
                    leaseAssetReminders.setPremiumDate(dueDetailsLeaseAsset.get(i).getLeasePremiumTo());

                    leaseAssetRemindersService.saveUpdateLeaseAssetReminders(leaseAssetReminders);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dueDetails;
    }

    @RequestMapping("genarateReminder")
    public void genarateReminder(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");

        String date1 = request.getParameter("date1");
        int noDays = Integer.parseInt(request.getParameter("noDays"));
        int categoryType = Integer.parseInt(request.getParameter("categoryType"));

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(simpleDateFormat.parse(date1));
            cal.add(Calendar.DATE, noDays);
            Date dateSecond = cal.getTime();
            int premiumDate = cal.get(Calendar.DATE);
            Date dateFirst = simpleDateFormat.parse(date1);
            User user = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            Connection c = new DBFacade().connect();
            ServletOutputStream outStream = response.getOutputStream();
            Map<String, Object> params = new HashMap<String, Object>();

            InputStream areportstream1 = null;

            params.put("CompanyName", companyName);
            params.put("DateFirst", dateFirst);
            params.put("DateSecond", dateSecond);
            params.put("No_Days", noDays);
            params.put("PremiumDate", premiumDate);
            params.put("userName", user.getUsername());


            params.put("REPORT_CONNECTION", c);

            URL urllogo = this.getClass().getClassLoader().getResource("reports/Images/somro-logo.jpg");
            String logopath = urllogo.toURI().toString();//getPath() + "/";
            params.put("LogoPath", logopath);

            int depId = Integer.parseInt(String.valueOf(session.getAttribute("userBranch")));
            if (depId == 0) {
                params.put("query", " ");
            } else {
                String query = "AND dep.DEP_ID=" + depId;
                params.put("query", query);
            }

            if (categoryType == 1) {
                areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/reminder/reminder_insurance.jasper");

            } else if (categoryType == 2) {
                areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/reminder/reminder_warranty_new.jasper");
            } else if (categoryType == 3) {
                areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/reminder/service_agreement_new.jasper");
            } else if (categoryType == 4) {
                areportstream1 = this.getClass().getClassLoader().getResourceAsStream("reports/reminder/reminder_lease_asset_new.jasper");
            }
            JasperRunManager.runReportToPdfStream(areportstream1, outStream, params, c);

            c.close();
            c = null;
            params = null;
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("getMaintenanceTransaction/{transactionType}/{userBranch}")
    @ResponseBody
    public List<Asset> getMaintenanceTransaction(@PathVariable("transactionType") int transactionType, @PathVariable("userBranch") int branch) {
        if (transactionType == 1) { // Insurance
            return assetService.getAssetInsuranceList(branch);
        } else if (transactionType == 2) { // Warranty
            return assetService.getAssetWarantyList(branch);
        } else if (transactionType == 3) {//Service Agreement
            return assetService.getAssetServiceAgreementList(branch);
        } else if (transactionType == 4) {//Modification
            return assetService.getAssetModificationList(branch);
        } else if (transactionType == 5) {//Maintenance Data
            return assetService.getAssetMaintenanceDataList(branch);
        } else if (transactionType == 6) {//Running data
            return assetService.getAssetRunningDataList(branch);
        } else if (transactionType == 7) {//Lease Asset
            return assetService.getAssetLeaseAssetList(branch);
        }
        return null;
    }
}


