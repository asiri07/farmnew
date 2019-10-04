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
import com.msd.fixAssetRegister.service.AJobDefineService;
import com.msd.fixAssetRegister.service.DepartmentService;
import com.msd.fixAssetRegister.service.UserService;
import com.msd.fixAssetRegister.service.UserTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserTypeService userTypeService;
    @Autowired
    UserService userService;
    @Autowired
    AJobDefineService jobDefineService;
    @Autowired
    DepartmentService departmentService;
    @Value("${application.companyName}")
    private String companyName;

    @RequestMapping("userType")
    public ModelAndView loadUserType(Model model, HttpServletRequest request) {
        List<UserType> userTypeList = new ArrayList<UserType>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            userTypeList = userTypeService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadUserType Method : " + e.getMessage());
        }
        model.addAttribute("userType", new UserType());
        model.addAttribute("userTypes", userTypeList);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("addUserType-option");

    }

    @RequestMapping("newUser")
    public ModelAndView loadNewUser(Model model, HttpServletRequest request) {
        List<UserType> userTypeList = new ArrayList<UserType>();
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        List<User> users = new ArrayList<User>();
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            userTypeList = userTypeService.findAll();
            users = userService.findAll();
            departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadNewUser Method : " + e.getMessage());
        }
        model.addAttribute("users", users);
        model.addAttribute("department", departmentMasters);
        model.addAttribute("userTypes", userTypeList);
        model.addAttribute("user", user);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("createUser-option");

    }

    @RequestMapping(value = "saveUserType", method = RequestMethod.POST)
    public ModelAndView saveUserType(@ModelAttribute("userType") UserType userType, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<UserType> userTypeList = new ArrayList<UserType>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);

                UserType userType1 = userTypeService.saveUpdateUserTypeService(userType);
                if (userType1 != null) {
                    isSave = true;
                }
            }
            userTypeList = userTypeService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveMainCatergory Method : " + e.getMessage());
            isSave = false;
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("userTypes", userTypeList);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("addUserType-option");
    }

    @RequestMapping("editUserType/{userTypeId}")
    public ModelAndView editUserType(@PathVariable("userTypeId") int userTypeId, Model model, HttpServletRequest request) {
        List<UserType> userTypeList = new ArrayList<UserType>();
        UserType userType = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            userType = userTypeService.findById(userTypeId);
            userTypeList = userTypeService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the editUserType Method : " + e.getMessage());
        }
        model.addAttribute("userType", userType);
        model.addAttribute("userTypes", userTypeList);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("addUserType-option");
    }

    @RequestMapping("checkUserName/{userName}")
    @ResponseBody
    public Boolean checkUserName(@ModelAttribute("userName") String userName, Model model) {
        Boolean isUser = true;
        try {
            User user = userService.getUser(userName);
            if (user != null) {
                isUser = false;
            }
        } catch (Exception e) {
        }
        return isUser;
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("userForm") User newUser, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        List<User> users = new ArrayList<User>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodePassword = passwordEncoder.encode(newUser.getPassword());
                newUser.setPassword(encodePassword);
                newUser.setActionTime(date);
                newUser.setCreatedUser(user.getUserId());
                System.out.println(user.getUserId());
                if (newUser.getIndividual() == 1) {
                    newUser.setDepId(0);
                }

                User user1 = userService.saveUpdateUser(newUser);//done
                if (user1 != null) {
                    isSave = true;
                }

//                String msg = userService.add(newUser);
//                if (msg.equals("Update Successful.")) {
//                    isSave = true;
//                }
                List<JobDefine> defineList = new ArrayList<JobDefine>();
                if (user.getIndividual() == 1) {
                    defineList = userService.findJobList(user.getUserId());
                } else {
                    defineList = userService.findBranchJobList(user.getDepId());
                }

                model.addAttribute("jobList", defineList);
            }
            users = userService.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveUser Method : " + e.getMessage());
            isSave = false;
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("users", users);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("createUser-option");
    }

    @RequestMapping("editUser/{userId}")
    public ModelAndView editUser(@PathVariable("userId") int userId, Model model, HttpServletRequest request) {
        List<UserType> userTypeList = new ArrayList<UserType>();
        List<User> users = new ArrayList<User>();
        User user = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user1 = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                user = userService.findById(userId);
                userTypeList = userTypeService.findAll();
                users = userService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the editUser Method : " + e.getMessage());
        }

        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("userTypes", userTypeList);
        model.addAttribute("isEdit", 1);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("createUser-option");

    }

    @RequestMapping("userJobs")
    public ModelAndView loadUserJobDefine(Model model, HttpServletRequest request) {
        List<DepartmentMaster> departmentMasters = new ArrayList<DepartmentMaster>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                List<User> users = userService.findIndividualUser();
                model.addAttribute("users", users);
                departmentMasters = departmentService.findAll(Integer.parseInt(String.valueOf(session.getAttribute("userBranch"))));
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadUserType Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("department", departmentMasters);
        return new ModelAndView("userJobDefine-option");

    }

    @RequestMapping(value = "loadUseJobList", method = RequestMethod.POST)
    public ModelAndView loadUseJobList(@ModelAttribute("userForm") User newUser, Model model, HttpServletRequest request) {
        List<JobDefine> jobList = new ArrayList<JobDefine>();
        List<JobDefine> jobDefine = new ArrayList<JobDefine>();
        int branchId = 0;
        int userId = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
            if (newUser.getUserId() > 0) {
                jobDefine = userService.findJobList(newUser.getUserId());
                branchId = 0;
                userId = newUser.getUserId();
            } else {
                jobDefine = userService.findBranchJobList(newUser.getDepId());
                userId = 0;
                branchId = newUser.getDepId();
            }


            jobList = userService.getJobList();
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadUseJobList Method : " + e.getMessage());

        }
        model.addAttribute("jobList", jobList);
        model.addAttribute("jobDefine", jobDefine);
        model.addAttribute("userId", userId);
        model.addAttribute("branchId", branchId);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("userJobList-option");
    }

    @RequestMapping(value = "saveJob", method = RequestMethod.POST)
    @ResponseBody
    public Boolean saveJob(@RequestBody List<AJobDefine> aJobDefine, Model model, HttpServletRequest request) {
        Boolean isSave = false;
        try {
            if (aJobDefine.get(0).getRef_User_Type() > 0) {
                jobDefineService.deleteJobs(aJobDefine.get(0).getRef_User_Type());
            } else {
                jobDefineService.deleteJobsbyBranch(aJobDefine.get(0).getRer_Branch_Id());
            }
//            HttpSession session = request.getSession();
//            (session.getAttribute("userBranch"));
//            User user = userService.findById(aJobDefine.get(0).getRef_User_Type());
//            boolean isIndividual = user.isIndividual();
            jobDefineService.saveAll(aJobDefine);

            // from thr screen need to check that is individual or branch job define
            // id its individual DEP_ID need to = 0
            // else its Branch ISINDIVIDUAL need to = 0

            //update the branch
            isSave = true;
        } catch (Exception e) {
            logger.error("Error occurred while calling the saveUser Method : " + e.getMessage());
            isSave = false;
        }
        return isSave;
    }

    @RequestMapping(value = "checkHigherUserLogin", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkUserName(@ModelAttribute("checkHigherUserLoginForm") User user, Model model) {
        Boolean isUser = false;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User users = userService.getUser(user.getUsername());
            if (users != null) {
                if (users.getUserType().getUserType().equals("admin")) {
                    if (passwordEncoder.matches(user.getPassword(), users.getPassword())) {
                        isUser = true;
                    } else {
                        isUser = false;
                    }
                } else {
                    isUser = false;
                }
            } else {
                isUser = false;
            }
        } catch (Exception e) {

        }
        return isUser;
    }


}

