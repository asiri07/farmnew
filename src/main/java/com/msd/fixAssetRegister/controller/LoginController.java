package com.msd.fixAssetRegister.controller;

import com.msd.fixAssetRegister.model.AssetCatergoryMain;
import com.msd.fixAssetRegister.model.CustomUserDetails;
import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.model.UserType;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.service.MainCategoryService;
import com.msd.fixAssetRegister.service.UserService;
import com.msd.fixAssetRegister.service.UserTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @Autowired
    MainCategoryService mainCategoryService;
    @Value("${application.companyName}")
    private String companyName;

    @RequestMapping("/")
    String login(HttpServletRequest request, Model model) {
        try {
            logger.info("check point 01");
            HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                String userName = session.getAttribute("username").toString();
                User user = userService.findByUsername(userName);
                if (user != null) {
                    session.setAttribute("userType", user.getUserType().getUserType());
                }
                session.setAttribute("user", user);

                // get the user is individual or branch user
                int isIndividual = user.getIndividual();
                session.setAttribute("isIndividual", isIndividual);
                int branchId = user.getDepId();
                session.setAttribute("userBranch", branchId);

                List<JobDefine> defineList = null;
                if (isIndividual == 1) {// check user is individual or branch user
                    //get the job list using user id
                    defineList = userService.findJobList(user.getUserId());
                } else {
                    //get the job list using branch id
                    defineList = userService.findBranchJobList(branchId);
                }

                List<AssetCatergoryMain> assetCatergoryMain = mainCategoryService.findAll();
                session.setAttribute("jobList", defineList);
                session.setAttribute("companyName", companyName);
                model.addAttribute("branchId", branchId);
                model.addAttribute("jobList", defineList);
                model.addAttribute("mainCatergory", assetCatergoryMain);
                model.addAttribute("companyName", companyName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while calling the login Method : " + e.getMessage());
        }
        return "home-tile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        logger.info("check point 02");
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("/", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("message", "Logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }

}
