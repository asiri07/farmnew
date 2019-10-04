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
import com.msd.fixAssetRegister.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/allocation")
public class AllocationController {

    private static final Logger logger = LoggerFactory.getLogger(AllocationController.class);
    @Autowired
    CompanyService companyService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    LocationService locationService;
    @Autowired
    SectionService sectionService;
    @Autowired
    CityService cityService;
    @Autowired
    BuildingService buildingService;
    @Autowired
    FloorService flowService;
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;
    @Autowired
    CompanyHistoryService companyHistoryService;
    @Autowired
    DepartmentHistoryService departmentHistoryService;
    @Autowired
    LocationHistoryService locationHistoryService;
    @Autowired
    SectionHistoryService sectionHistoryService;

    @Value("${application.companyName}")
    private String companyName;

    @RequestMapping("company")
    public ModelAndView loadCompany(Model model, HttpServletRequest request) {
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                companyMasters = companyService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadCompany Method : " + e.getMessage());
        }
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("company", new CompanyMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("company-allocation");

    }

    @RequestMapping(value = "saveCompany", method = RequestMethod.POST)
    public ModelAndView saveCompany(@ModelAttribute("companyForm") CompanyMaster companyMaster, Model model, HttpServletRequest request) {
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                companyMaster.setComCode(companyMaster.getComCode().toUpperCase());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                companyMaster.setUserId(user.getUserId());
                companyMaster.setActionTime(date);
                if (companyMaster.getComId() > 0) {
                    CompanyMaster companyMasterOld = companyService.findById(companyMaster.getComId());
                    companyHistoryService.updateHistory(companyMasterOld, 0, user.getUserId());//done
                }
                CompanyMaster companyMaster1 = companyService.saveUpdateCompany(companyMaster);//done
                if (companyMaster1 != null) {
                    isSave = true;
                }
                companyMasters = companyService.findAll();

            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveCompany Method : " + e.getMessage());
            isSave = false;
        }
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("company", new CompanyMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("company-allocation");

    }

    @RequestMapping("checkCompanyCode/{comCode}")
    @ResponseBody
    public Boolean checkCompanyCode(@PathVariable("comCode") String comCode) {
        Boolean isCheck = true;
        try {
            CompanyMaster companyMaster = companyService.getCompanyByCode(comCode);
            if (companyMaster != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkCompanyCode Method : " + e.getMessage());
        }
        return isCheck;
    }

    @RequestMapping("checkDepartmentCode/{depCode}/{comId}")
    @ResponseBody
    public Boolean checkDepartmentCode(@PathVariable("depCode") String depCode, @PathVariable("comId") int comId) {
        Boolean isCheck = true;
        try {
            DepartmentMaster departmentMaster = departmentService.getDepartmentByCode(depCode, comId);
            if (departmentMaster != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkDepartmentCode Method : " + e.getMessage());
        }
        return isCheck;
    }

    @RequestMapping("checkLocationCode/{locCode}/{secId}")
    @ResponseBody
    public Boolean checkLocationCode(@PathVariable("locCode") String locCode, @PathVariable("secId") int secId) {
        Boolean isCheck = true;
        try {
            LocationMaster locationMaster = locationService.getLocationByCode(locCode, secId);
            if (locationMaster != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkLocationCode Method : " + e.getMessage());
        }
        return isCheck;
    }

    @RequestMapping("checkSectionCode/{secCode}/{depaId}")
    @ResponseBody
    public Boolean checkSectionCode(@PathVariable("secCode") String secCode, @PathVariable("depaId") int depaId) {
        Boolean isCheck = true;
        try {
            SectionMaster sectionMaster = sectionService.getSectionByCode(secCode, depaId);
            if (sectionMaster != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isCheck;
    }


    @RequestMapping("editCompany/{camId}")
    public ModelAndView editCompany(@PathVariable("camId") int camId, Model model, HttpServletRequest request) {
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        CompanyMaster companyMaster = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                companyMasters = companyService.findAll();
                companyMaster = companyService.findById(camId);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the editCompany Method : " + e.getMessage());
        }
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("company", companyMaster);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("company-allocation");

    }

    @RequestMapping("department")
    public ModelAndView loadDepartment(Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            companyMasters = companyService.findAll();

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDepartment Method : " + e.getMessage());
        }
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("department", new DepartmentMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("department-allocation");

    }

    @RequestMapping("saveDepartment")
    public ModelAndView saveDepartment(@ModelAttribute("departmentForm") DepartmentMaster department, Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine>defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                department.setDepCode(department.getDepCode().toUpperCase());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                department.setUserId(user.getUserId());
                department.setActionTime(date);
                if (department.getDepId() > 0) {
                    DepartmentMaster departmentMasterOld = departmentService.findById(department.getDepId());
                    departmentHistoryService.updateHistory(departmentMasterOld, 0, user.getUserId());//done
                }
                DepartmentMaster departmentMaster = departmentService.saveDepartment(department);//done
                if (departmentMaster != null) {
                    isSave = true;
                }
                departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                companyMasters = companyService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveDepartment Method : " + e.getMessage());
            isSave = false;
        }
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("department", new DepartmentMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("department-allocation");
    }

    @RequestMapping("editDepartment/{depId}")
    public ModelAndView editDepartment(@PathVariable("depId") int depId, Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        DepartmentMaster departmentMaster = null;
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            companyMasters = companyService.findAll();
            departmentMaster = departmentService.findById(depId);

        } catch (Exception e) {
            logger.error("Error occurred while calling the editDepartment Method : " + e.getMessage());
        }
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("department", departmentMaster);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("department-allocation");

    }

    @RequestMapping("location")
    public ModelAndView loadLocation(Model model, HttpServletRequest request) {
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        List<Floor> flows = new ArrayList<Floor>();
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        List<Room> rooms = new ArrayList<Room>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("jobList") != null) {
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            companyMasters = companyService.findAll();
            cities = cityService.findAll();
            buildings = buildingService.findAll();
            flows = flowService.findAll();
            rooms = roomService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadLocation Method : " + e.getMessage());
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("location", new LocationMaster());
        model.addAttribute("flows", flows);
        model.addAttribute("rooms", rooms);
        model.addAttribute("citys", cities);
        ;
        model.addAttribute("buildings", buildings);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("location-allocation");


    }

    @RequestMapping(value = "saveLocation", method = RequestMethod.POST)
    public ModelAndView saveLocation(@ModelAttribute("locationForm") LocationMaster locationMaster, Model model, HttpServletRequest request) {
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        List<Floor> flows = new ArrayList<Floor>();
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        List<Room> rooms = new ArrayList<Room>();
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                locationMaster.setLocCode(locationMaster.getLocCode().toUpperCase());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                locationMaster.setUserId(user.getUserId());
                locationMaster.setActionTime(date);
                if (locationMaster.getLocId() > 0) {
                    LocationMaster locationMasterOld = locationService.findById(locationMaster.getLocId());
                    locationHistoryService.updateHistory(locationMasterOld, 0, user.getUserId());//done
                }
                LocationMaster locationMaster1 = locationService.saveUpdateLocationMaster(locationMaster);//done
                if (locationMaster1 != null) {
                    isSave = true;
                }
                locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                companyMasters = companyService.findAll();
                cities = cityService.findAll();
                buildings = buildingService.findAll();
                flows = flowService.findAll();
                rooms = roomService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveLocation Method : " + e.getMessage());
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("location", new LocationMaster());
        model.addAttribute("flows", flows);
        model.addAttribute("rooms", rooms);
        model.addAttribute("citys", cities);
        model.addAttribute("buildings", buildings);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("location-allocation");
    }

    @RequestMapping("editLocation/{locId}")
    public ModelAndView editLocation(@PathVariable("locId") int locId, Model model, HttpServletRequest request) {
        List<LocationMaster> locationMasters = new ArrayList<LocationMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        LocationMaster locationMaster = null;
        List<Floor> flows = new ArrayList<Floor>();
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        List<Room> rooms = new ArrayList<Room>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                locationMasters = locationService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                companyMasters = companyService.findAll();
                locationMaster = locationService.findById(locId);
                cities = cityService.findAll();
                buildings = buildingService.findAll();
                flows = flowService.findAll();
                rooms = roomService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the editLocation Method : " + e.getMessage());
        }
        model.addAttribute("locations", locationMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("location", locationMaster);
        model.addAttribute("flows", flows);
        model.addAttribute("rooms", rooms);
        model.addAttribute("citys", cities);
        model.addAttribute("buildings", buildings);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("location-allocation");

    }


    @RequestMapping(value = "getLocationSerial/{roomId}", method = RequestMethod.GET)
    public @ResponseBody
    String getLocationSerial(@PathVariable("roomId") int roomId) {
        return genarateLocationSerialCode(roomId);
    }

    private String genarateLocationSerialCode(int roomId) {
        String serialNo = "";
        int maxChar = 3;
        try {
            String maxNo = locationService.getMaxSerialNo(roomId, maxChar);
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
            // TODO: handle exception
        }
        return serialNo;

    }

    @RequestMapping("section")
    public ModelAndView loadSection(Model model, HttpServletRequest request) {
        List<SectionMaster> sectionMasters = new ArrayList<SectionMaster>();
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            sectionMasters = sectionService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            companyMasters = companyService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadSection Method : " + e.getMessage());
        }
        model.addAttribute("sections", sectionMasters);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("section", new SectionMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("section-allocation");

    }

    @RequestMapping(value = "saveSection", method = RequestMethod.POST)
    public ModelAndView saveSection(@ModelAttribute("sectionForm") SectionMaster sectionMaster, Model model, HttpServletRequest request) {
        List<SectionMaster> sectionMasters = new ArrayList<SectionMaster>();
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine>defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                sectionMaster.setSecCode(sectionMaster.getSecCode().toUpperCase());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                sectionMaster.setUserId(user.getUserId());
                sectionMaster.setActionTime(date);
                if (sectionMaster.getSecId() > 0) {
                    SectionMaster sectionMasterOld = sectionService.findById(sectionMaster.getSecId());
                    sectionHistoryService.updateHistory(sectionMasterOld, 0, user.getUserId());//done
                }
                SectionMaster sectionMaster1 = sectionService.saveUpdateSection(sectionMaster);//done
                if (sectionMaster1 != null) {
                    isSave = true;
                }
//                String msg = sectionService.add(sectionMaster);
                sectionMasters = sectionService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
                companyMasters = companyService.findAll();
//                if (msg.equals("Update Successful.")) {
//                    isSave = true;
//                }
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadSection Method : " + e.getMessage());
        }
        model.addAttribute("sections", sectionMasters);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("section", new SectionMaster());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("section-allocation");
    }

    @RequestMapping("editSection/{secId}")
    public ModelAndView editSection(@PathVariable("secId") int secId, Model model, HttpServletRequest request) {
        List<SectionMaster> sectionMasters = new ArrayList<SectionMaster>();
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<CompanyMaster> companyMasters = new ArrayList<CompanyMaster>();
        SectionMaster sectionMaster = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            sectionMasters = sectionService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            companyMasters = companyService.findAll();
            sectionMaster = sectionService.findById(secId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the editSection Method : " + e.getMessage());
        }
        model.addAttribute("sections", sectionMasters);
        model.addAttribute("departments", departmentMasters);
        model.addAttribute("companyList", companyMasters);
        model.addAttribute("section", sectionMaster);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("section-allocation");

    }

    @RequestMapping(value = "getDepartments/{comId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getDepartments(@PathVariable("comId") int comId) {
        List<String> departmentCodes = new ArrayList<String>();
        try {

            List<DepartmentMaster> departmentMasters = departmentService.getDepartmentsByComId(comId);
            for (Iterator<DepartmentMaster> iterator = departmentMasters.iterator(); iterator.hasNext(); ) {
                DepartmentMaster departmentMaster = iterator.next();
                departmentCodes.add(departmentMaster.getDepId() + "-" + departmentMaster.getDepCode() + "-" + departmentMaster.getDepDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getDepartments Method : " + e.getMessage());
        }
        return departmentCodes;
    }


    @RequestMapping(value = "getSections/{depId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getSections(@PathVariable("depId") int depId) {
        List<String> sectionCodes = new ArrayList<String>();
        try {

            List<SectionMaster> sectionMasters = sectionService.getSectionBydepId(depId);

            for (Iterator<SectionMaster> iterator = sectionMasters.iterator(); iterator.hasNext(); ) {
                SectionMaster sectionMaster = iterator.next();
                sectionCodes.add(sectionMaster.getSecId() + "-" + sectionMaster.getSecCode() + "-" + sectionMaster.getSecDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getSections Method : " + e.getMessage());
        }
        return sectionCodes;
    }


    @RequestMapping(value = "getLocations/{secId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getLocations(@PathVariable("secId") int secId) {
        List<String> locationCodes = new ArrayList<String>();
        try {

            List<LocationMaster> locationMasters = locationService.getLocationDetailsBySecId(secId);

            for (Iterator<LocationMaster> iterator = locationMasters.iterator(); iterator.hasNext(); ) {
                LocationMaster locationMaster = iterator.next();
                locationCodes.add(locationMaster.getLocId() + "-" + locationMaster.getLocCode() + "-" + locationMaster.getLocDes());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the getLocations Method : " + e.getMessage());
        }
        return locationCodes;
    }

    @RequestMapping(value = "deleteCompany/{comId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteDetailCategory(@PathVariable("comId") int comId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<DepartmentMaster> departmentMasters = departmentService.findDepartmentByComId(comId);
                if (departmentMasters.size() == 0) {
                    CompanyMaster companyOld = companyService.findById(comId);
                    companyHistoryService.updateHistory(companyOld, 1, user.getUserId());
                    isDelete = companyService.deleteCompany(comId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }


    @RequestMapping(value = "deleteDepartment/{depatId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteDepartment(@PathVariable("depatId") int depatId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<SectionMaster> sectionMasters = sectionService.getSectionBydepId(depatId);
                if (sectionMasters.size() == 0) {
                    DepartmentMaster departmentOld = departmentService.findById(depatId);
                    departmentHistoryService.updateHistory(departmentOld, 1, user.getUserId());
                    isDelete = departmentService.deleteDepartment(depatId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "deleteSection/{sectionId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteSection(@PathVariable("sectionId") int sectionId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<LocationMaster> locationMasters = locationService.getLocationBySectionId(sectionId);
                if (locationMasters.size() == 0) {
                    SectionMaster sectionMasterOld = sectionService.findById(sectionId);
                    sectionHistoryService.updateHistory(sectionMasterOld, 1, user.getUserId());
                    isDelete = sectionService.deleteSection(sectionId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "deleteLocation/{locationId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteLocation(@PathVariable("locationId") int locationId, HttpServletRequest request) {
        int isDelete=0 ;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                //List<AssetLocationDetail> assetLocationDetails = as.getAssetByLocation(locationId);
                if (locationId > 0) {
//                    LocationMaster companyOld = locationService.findById(locationId);
                    LocationMaster locationMasterOld = locationService.findById(locationId);
                    locationHistoryService.updateHistory(locationMasterOld, 1, user.getUserId());//done
//                    companyHistoryService.updateHistory(companyOld, 1, user.getUserId());
                    isDelete = locationService.deleteLocation(locationId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }
}