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

import com.msd.fixAssetRegister.model.Book;
import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.service.BookService;
import com.msd.fixAssetRegister.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController  {

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Value("${application.companyName}")
    private String companyName;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;


    @RequestMapping("bookRegistration")
    public ModelAndView loadBookRegistration(Model model,HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        } catch (Exception e) {
            logger.error( "Error occurred while calling the loadBookRegistration Method : " + e.getMessage() );
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("bookRegister-library");

    }

    @RequestMapping( value = "saveBook" , method = RequestMethod.POST)
    public ModelAndView  saveBook(@ModelAttribute("bookForm") Book book, Model model, BindingResult brequest, HttpServletRequest request) {
        Boolean isSave = false;

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                Book book1 = bookService.saveUpdatBook(book);//done
                if (book != null) {
                    isSave = true;
                }

            }
        } catch (Exception e) {
            logger.error( "Error occurred while calling the saveBook Method : " + e.getMessage() );
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("bookRegister-library");
    }


}
