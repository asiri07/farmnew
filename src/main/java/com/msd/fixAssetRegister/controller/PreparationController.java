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

import com.msd.fixAssetRegister.model.Asset;
import com.msd.fixAssetRegister.model.Currency;
import com.msd.fixAssetRegister.model.ReportPreparationData;
import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.model.form.PreparationMassage;
import com.msd.fixAssetRegister.service.CurrencyService;
import com.msd.fixAssetRegister.service.PreparationDetailCatService;
import com.msd.fixAssetRegister.service.PreparationService;
import com.msd.fixAssetRegister.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/preparation")
public class PreparationController {

    private static final Logger logger = LoggerFactory.getLogger(PreparationController.class);

    @Value("${application.companyName}")
    private String companyName;

    @Autowired
    PreparationService preparationService;

    @Autowired
    PreparationDetailCatService preparationDetailCatService;

    @Autowired
    UserService userService;

    @Autowired
    CurrencyService currencyService;


    @RequestMapping("reportPreparation")
    public ModelAndView reportPreparation(Model model,HttpServletRequest request) {
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                currencyTypes = currencyService.findAll();
            }
        }catch (Exception e) {
            logger.error( "Error occurred while calling the saveMainCatergory Method : " + e.getMessage() );
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("reportPreparation-option");

    }


    private static <Asset> Collection<List<Asset>> partition(List<Asset> list, int size) {
        final AtomicInteger counter = new AtomicInteger(0);
        return list.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size))
                .values();
    }

    @RequestMapping(value = "doPreparation", method = RequestMethod.POST)
    public ModelAndView doPreparation(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, @RequestParam("currencyType") int currencyType, HttpServletRequest request, Model model) {
        logger.info("fromDate: "+fromDate);
        logger.info("toDate: "+toDate);
        logger.info("currencyType: "+currencyType);
        Boolean isDo = false;
        Boolean isEmpty = false;
        List<Asset> assets = new ArrayList<Asset>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        final List<PreparationMassage> preparationMassage = new ArrayList<>();
        List<ReportPreparationData> reportPreparationData = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                final User user = (User) session.getAttribute("user");
                Boolean isDo1 = false;
                preparationService.clearPreparationData();
                assets = preparationService.getPreparationAssets(toDate);
                List<List<Asset>> assetGroup = new ArrayList<>();
                if (assets.isEmpty()) {
                    isEmpty = true;
                }
                logger.info("size: " +assets.size());
                if (assets.size() > 25000) {
                    assetGroup = partition(assets, assets.size() / (assets.size() / 25000)).stream().collect(toList());
                }else if (assets.size() > 1000) {
                    assetGroup = partition(assets, assets.size() / (assets.size() / 1000)).stream().collect(toList());
                } else if (assets.size() > 100) {
                    assetGroup = partition(assets, assets.size() / (assets.size() / 100)).stream().collect(toList());
                }else if (assets.size() > 50) {
                    assetGroup = partition(assets, assets.size() / (assets.size() / 50)).stream().collect(toList());
                }else {
                    assetGroup.add(assets);
                }
                for (List<Asset> list : assetGroup) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            logger.info("");
                            preparationMassage.add(preparationService.addPreparation(list, fromDate, toDate, user,currencyType));
                        }
                    });
                    thread.start();
                    thread.join();

                }
                    if(preparationMassage.get(0).getStatus() == 1) {
                        isDo1 = true;
                    } else if(preparationMassage.get(0).getStatus() == 2) {
                        isDo1 = false;
                    }


                if(isDo1) {
                    Boolean isDo2 = preparationDetailCatService.getDetailCatBalance();
                    if(isDo2) {
                        isDo = true;
                        preparationMassage.get(0).setMassage("success");
                    }else {
                        preparationMassage.get(0).setMassage("Report preparation Added Fail !!");
                    }
                }
                if (isEmpty) {
                    isDo = true;
                    preparationMassage.get(0).setMassage("No Asset to Preparation");
                }
                List<JobDefine> defineList = userService.findJobList(user.getUserType().getUserTypeId());
                currencyTypes = currencyService.findAll();
                model.addAttribute("jobList", defineList);
            }
            reportPreparationData = preparationService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error( "Error occurred while calling the doPreparation Method : " + e.getMessage() );
        }
        // model.addAttribute("preparation", reportPreparationData);
        model.addAttribute("status", isDo);
        model.addAttribute("massage", preparationMassage.get(0).getMassage());
        model.addAttribute("assetCount", reportPreparationData.size());
        model.addAttribute("pageType", 1);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("reportPreparation-option");

    }

}


