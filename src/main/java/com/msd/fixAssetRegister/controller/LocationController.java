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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/location")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    @Autowired
    CityService cityService;
    @Autowired
    BuildingService buildingService;
    @Autowired
    FloorService floorService;
    @Autowired
    RoomService roomService;
    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;
    @Autowired
    BuildingHistoryService buildingHistoryService;
    @Autowired
    CityHistoryService cityHistoryService;
    @Autowired
    FloorHistoryService floorHistoryService;
    @Autowired
    RoomHistoryService roomHistoryService;

    @Value("${application.companyName}")
    private String companyName;

    @RequestMapping("city")
    public ModelAndView loadCity(Model model, HttpServletRequest request) {
        List<City> citys = new ArrayList<City>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            citys = cityService.findAll();

        } catch (Exception e) {
            logger.error("Error occurred while calling the loadCity Method : " + e.getMessage());
        }
        model.addAttribute("cityList", citys);
        model.addAttribute("city", new City());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("city-location");
    }

    @RequestMapping("building")
    public ModelAndView loadBuilding(Model model, HttpServletRequest request) {
        List<Building> buildings = new ArrayList<Building>();
        List<City> cities = new ArrayList<City>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            buildings = buildingService.findAll();
            cities = cityService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("building", new Building());
        model.addAttribute("buildings", buildings);
        model.addAttribute("citys", cities);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("building-location");
    }

    @RequestMapping("floor")
    public ModelAndView loadFloor(Model model, HttpServletRequest request) {
        List<City> citys = new ArrayList<City>();
        List<Floor> floors = new ArrayList<Floor>();
        List<Building> buildings = new ArrayList<Building>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            citys = cityService.findAll();
            floors = floorService.findAll();
            buildings = buildingService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadFlow Method : " + e.getMessage());
        }
        model.addAttribute("citys", citys);
        model.addAttribute("floor", new Floor());
        model.addAttribute("floors", floors);
        model.addAttribute("buildings", buildings);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("floor-location");
    }


    @RequestMapping(value = "saveCity", method = RequestMethod.POST)
    public ModelAndView saveCity(City city, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<City> citys = new ArrayList<City>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                city.setUserId(user.getUserId());
                city.setActionTime(date);
                city.setCityCode(city.getCityCode().toUpperCase());
                if (city.getCityId() > 0) {
                    City cityOld = cityService.findById(city.getCityId());
                    cityHistoryService.updateHistory(cityOld, 0, user.getUserId());
                }

                City city1 = cityService.saveUpdateCity(city);
                if (city1 != null) {
                    isSave = true;
                }
                citys = cityService.findAll();
            }

        } catch (Exception e) {
            logger.error("Error occurred while calling the saveCity Method : " + e.getMessage());
        }
        model.addAttribute("cityList", citys);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("city", new City());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("city-location");
    }

    @RequestMapping("checkCityCode/{cityCode}")
    @ResponseBody
    public Boolean checkCompanyCode(@PathVariable("cityCode") String cityCode) {
        Boolean isCheck = true;
        try {
            City city = cityService.getCityByCode(cityCode);
            if (city != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkCompanyCode Method : " + e.getMessage());
        }
        return isCheck;
    }

    @RequestMapping("editCity/{cityId}")
    public ModelAndView editCompany(@PathVariable("cityId") int cityId, Model model, HttpServletRequest request) {
        List<City> cities = new ArrayList<City>();
        City city = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine>defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            cities = cityService.findAll();
            city = cityService.findById(cityId);
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("cityList", cities);
        model.addAttribute("city", city);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("city-location");

    }

    @RequestMapping(value = "deleteCity/{cityId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteCompany(@PathVariable("cityId") int cityId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<Building> buildings = buildingService.findBuildings(cityId);
                if (buildings.size() == 0) {
                    City cityOld = cityService.findById(cityId);
                    cityHistoryService.updateHistory(cityOld, 1, user.getUserId());
                    isDelete = cityService.deleteCity(cityId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "saveBuilding", method = RequestMethod.POST)
    public ModelAndView saveBuilding(Building building, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<Building> buildings = new ArrayList<Building>();
        List<City> cities = new ArrayList<City>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                building.setUserId(user.getUserId());
                building.setActionTime(date);
                building.setBuildingCode(building.getBuildingCode().toUpperCase());
                if (building.getId() > 0) {
                    Building buildingOld = buildingService.findById(building.getId());
                    buildingHistoryService.updateHistory(buildingOld, 0, user.getUserId());
                }

                buildings = buildingService.findAll();
                cities = cityService.findAll();
//                int cId= building.getCity().getCityId();

                isSave = buildingService.saveUpdateBuilding(building);
            }



        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
        }
        model.addAttribute("buildings", buildings);
        model.addAttribute("citys", cities);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("building", new Building());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("building-location");
    }

    @RequestMapping("checkBuildingCode/{buildingCode}/{cityId}")
    @ResponseBody
    public Boolean checkBuildingCode(@PathVariable("buildingCode") String buildingCode, @PathVariable("cityId") int cityId) {
        Boolean isCheck = true;
        try {
            Building building = buildingService.getBuildingByCode(buildingCode, cityId);
            if (building != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isCheck;
    }

    @RequestMapping("editBuilding/{buildingId}")
    public ModelAndView editBuilding(@PathVariable("buildingId") int buildingId, Model model, HttpServletRequest request) {
        List<Building> buildings = new ArrayList<Building>();
        int ff = buildingId;
        Building building = null;
        List<City> cities = new ArrayList<City>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            buildings = buildingService.findAll();
            building = buildingService.findById(buildingId);
            cities = cityService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the editBuilding Method : " + e.getMessage());
        }
        model.addAttribute("buildings", buildings);
        model.addAttribute("building", building);
        model.addAttribute("citys", cities);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("building-location");

    }

    @RequestMapping(value = "deleteBuilding/{buildingId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteBuilding(@PathVariable("buildingId") int buildingId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<Floor> floors = floorService.getFlowsByBuildingId(buildingId);
                if (floors.size() == 0) {
                    Building buildingOld = buildingService.findById(buildingId);
                    buildingHistoryService.updateHistory(buildingOld, 1, user.getUserId());
                    buildingService.delete(buildingId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the deleteBuilding Method : " + e.getMessage());
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "getBuildingByCity/{cityId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getBuildingByCity(@PathVariable("cityId") int cityId) {
        List<String> buildingCodes = new ArrayList<String>();
        try {
            List<Building> buildings = buildingService.getBuildingByCity(cityId);
            for (Iterator<Building> iterator = buildings.iterator(); iterator.hasNext(); ) {
                Building building = iterator.next();
                buildingCodes.add(building.getId() + "-" + building.getBuildingCode() + "-" + building.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildingCodes;
    }

    @RequestMapping(value = "saveFloor", method = RequestMethod.POST)
    public ModelAndView saveFloor(Floor floor, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<Floor> floors = new ArrayList<Floor>();
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                floor.setUserId(user.getUserId());
                floor.setActionTime(date);
                floor.setFloorCode(floor.getFloorCode().toUpperCase());
                if (floor.getId() > 0) {
                    Floor floorOld = floorService.findById(floor.getId());
                    floorHistoryService.updateHistory(floorOld, 0, user.getUserId());
                }

                floors = floorService.findAll();

                Floor floor1 = floorService.saveUpdateFloor(floor);
                if (floor1 != null) {
                    isSave = true;
                }
            }

            cities = cityService.findAll();
            buildings = buildingService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveBuilding Method : " + e.getMessage());
        } finally {
            List<JobDefine> defineList = userService.findJobList(user.getUserType().getUserTypeId());
            model.addAttribute("jobList", defineList);
        }
        model.addAttribute("floors", floors);
        model.addAttribute("citys", cities);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("floor", new Floor());
        model.addAttribute("buildings", buildings);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("floor-location");
    }

    @RequestMapping("editFloor/{floorId}")
    public ModelAndView editFloor(@PathVariable("floorId") int floorId, Model model, HttpServletRequest request) {
        List<Floor> floors = new ArrayList<Floor>();
        Floor floor = null;
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            floors = floorService.findAll();
            floor = floorService.findById(floorId);
            cities = cityService.findAll();
            buildings = buildingService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the editFlow Method : " + e.getMessage());
        }
        model.addAttribute("floors", floors);
        model.addAttribute("buildings", buildings);
        model.addAttribute("floor", floor);
        model.addAttribute("citys", cities);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isEdit", 1);
        return new ModelAndView("floor-location");
    }

    @RequestMapping(value = "deleteFloor/{floorId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteFloor(@PathVariable("floorId") int floorId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<Room> room = roomService.getRoomByFloorId(floorId);
                if (room.size() == 0) {
                    Floor flowOld = floorService.findById(floorId);
                    floorHistoryService.updateHistory(flowOld, 1, user.getUserId());
                    floorService.delete(floorId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping("checkFloorCode/{floorCode}/{cityId}/{buildingId}")
    @ResponseBody
    public Boolean checkFloorCode(@PathVariable("floorCode") String floorCode, @PathVariable("cityId") int cityId, @PathVariable("buildingId") int buildingId) {
        Boolean isCheck = true;
        try {
            List<Floor> floors = floorService.getFloorByCodes(floorCode, cityId, buildingId);
            if (floors.size() > 0) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkFloorCode Method : " + e.getMessage());
        }
        return isCheck;
    }

    @RequestMapping("room")
    public ModelAndView loadRoom(Model model, HttpServletRequest request) {
        List<Floor> floors = new ArrayList<Floor>();
        List<City> citys = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        List<Room> rooms = new ArrayList<Room>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            floors = floorService.findAll();
            citys = cityService.findAll();
            buildings = buildingService.findAll();
            rooms = roomService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadRoom Method : " + e.getMessage());
        }
        model.addAttribute("citys", citys);
        model.addAttribute("rooms", rooms);
        model.addAttribute("floors", floors);
        model.addAttribute("buildings", buildings);
        model.addAttribute("room", new Room());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("room-location");
    }


    @RequestMapping(value = "saveRoom", method = RequestMethod.POST)
    public ModelAndView saveRoom(Room room, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<Floor> floors = new ArrayList<Floor>();
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        List<Room> rooms = new ArrayList<Room>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine>defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                room.setRoomCode(room.getRoomCode().toUpperCase());
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                room.setUserId(user.getUserId());
                room.setActionTime(date);
                if (room.getId() > 0) {
                    Room roomOld = roomService.findById(room.getId()).get();
                    roomHistoryService.updateHistory(roomOld, 0, user.getUserId());
                }

                floors = floorService.findAll();
                Room room1 = roomService.saveUpdateRoom(room);
                if (room1 != null) {
                    isSave = true;
                }
            }

            cities = cityService.findAll();
            buildings = buildingService.findAll();
            rooms = roomService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveRoom Method : " + e.getMessage());
        }
        model.addAttribute("citys", cities);
        model.addAttribute("buildings", buildings);
        model.addAttribute("rooms", rooms);
        model.addAttribute("floors", floors);
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("room", new Room());
        model.addAttribute("companyName", companyName);
        return new ModelAndView("room-location");
    }

    @RequestMapping("checkRoomCode/{roomCode}/{cityId}/{buildingId}/{floorId}")
    @ResponseBody
    public Boolean checkRoomCode(@PathVariable("roomCode") String roomCode, @PathVariable("cityId") int cityId, @PathVariable("buildingId") int buildingId, @PathVariable("floorId") int floorId) {
        Boolean isCheck = true;
        try {
            Room room = roomService.getRoomByCodes(roomCode, cityId, buildingId, floorId);
            if (room != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @RequestMapping("editRoom/{roomId}")
    public ModelAndView editRoom(@PathVariable("roomId") int roomId, Model model, HttpServletRequest request) {
        List<Floor> floors = new ArrayList<Floor>();
        List<City> cities = new ArrayList<City>();
        List<Building> buildings = new ArrayList<Building>();
        List<Room> rooms = new ArrayList<Room>();
        Room room = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            floors = floorService.findAll();
            cities = cityService.findAll();
            buildings = buildingService.findAll();
            rooms = roomService.findAll();
            room = roomService.findById(roomId).get();
        } catch (Exception e) {
            logger.error("Error occurred while calling the editRoom Method : " + e.getMessage());
        }
        model.addAttribute("floors", floors);
        model.addAttribute("rooms", rooms);
        model.addAttribute("citys", cities);
        model.addAttribute("isEdit", 1);
        model.addAttribute("room", room);
        model.addAttribute("buildings", buildings);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("room-location");
    }

    @RequestMapping(value = "getFlowByBuilding/{buildingId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getFlowByBuilding(@PathVariable("buildingId") int buildingId) {
        List<String> floorCodes = new ArrayList<String>();
        try {
            List<Floor> floors = floorService.getFlowByBuilding(buildingId);
            for (Iterator<Floor> iterator = floors.iterator(); iterator.hasNext(); ) {
                Floor floor = iterator.next();
                floorCodes.add(floor.getId() + "-" + floor.getFloorCode() + "-" + floor.getDescription());
            }
        } catch (Exception e) {
        }
        return floorCodes;
    }

    @RequestMapping(value = "deleteRoom/{roomId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteRoom(@PathVariable("roomId") int roomId, HttpServletRequest request) {
        int isDelete = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<LocationMaster> locationMasters = locationService.getLocationByRoom(roomId);
                if (locationMasters.size() == 0) {
                    Room companyOld = roomService.findById(roomId).get();
                    roomHistoryService.updateHistory(companyOld, 1, user.getUserId());
                    roomService.delete(roomId);
                    isDelete = 1;
                } else {
                    isDelete = 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping(value = "getRoomByFlow/{floorId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getRoomByFloor(@PathVariable("floorId") int floorId) {
        List<String> roomCodes = new ArrayList<String>();
        try {
            List<Room> rooms = roomService.getRoomByFlow(floorId);
            for (Iterator<Room> iterator = rooms.iterator(); iterator.hasNext(); ) {
                Room room = iterator.next();
                roomCodes.add(room.getId() + "-" + room.getRoomCode() + "-" + room.getDescription());
            }
        } catch (Exception e) {
        }
        return roomCodes;
    }
}
