/*
 *
 *       Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *       *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *         This software product contains information which is proprietary to
 *          and considered a trade secret MsSoftIT Solution .
 *          It is expressly agreed that it shall not be reproduced in whole or part,
 *          disclosed, divulged or otherwise made available to any third party directly
 *          or indirectly.  Reproduction of this product for any purpose is prohibited
 *          without written authorisation from the The MsSoftIT Solution
 *          All Rights Reserved.
 *
 *          E-Mail mssoftit@gmail.com
 *          URL : mssoftit.lk
 *          Created By : Mahendra Sri Dayarathna
 *
 */
package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AMaintab;
import com.msd.fixAssetRegister.model.ASubtab;
import com.msd.fixAssetRegister.model.ASubtabSub;
import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    MainTabRepository mainTabRepository;

    @Autowired
    SubTabRepository subTabRepository;

    @Autowired
    SubSubTabRepository subSubTabRepository;

    @Autowired
    JobDefineRepository jobDefineRepository;


    @Transactional
    public User findById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findIndividualUser(){
        return userRepository.findIndividualUser();
    }

    @Transactional
    public List<JobDefine> findJobList(int userId) {
        List<JobDefine> defineList = new ArrayList<>();

        List<Integer> jobDefineList = jobDefineRepository.findByMainTabList(userId);

        for (Integer mainTab : jobDefineList) {
            AMaintab maintab = mainTabRepository.getOne(mainTab);
            if (mainTab != null) {
                JobDefine jobDefine = new JobDefine();
                jobDefine.setMainTabId(maintab.getMain_Tab_ID());
                jobDefine.setMainTabName(maintab.getMain_Tab_Name());
                jobDefine.setMainCode(maintab.getMain_Tab_Code());
                jobDefine.setRefPage(maintab.getRef_Page());
                jobDefine.setSubList(getSubTabs(maintab.getMain_Tab_ID(), userId));
                defineList.add(jobDefine);
            }
        }
        return defineList;
    }

    private List<ASubtab> getSubTabs(int main_tab_id, int userId) {
        List<ASubtab> aSubtabs = new ArrayList<>();
        List<Integer> subTabs = jobDefineRepository.findBySubTabList(main_tab_id,userId);
        for (Integer subTab : subTabs) {
            ASubtab aSubtab = subTabRepository.getOne(subTab);
            if (aSubtab != null) {
                ASubtab newSub = new ASubtab();
                newSub.setSub_Tab_No(aSubtab.getSub_Tab_No());
                newSub.setSub_Tab_Name(aSubtab.getSub_Tab_Name());
                newSub.setSub_Tab_Code(aSubtab.getSub_Tab_Code());
                newSub.setRefPage(aSubtab.getRefPage());
                newSub.setSubtabSubList(getSubTabSub(newSub.getSub_Tab_No(), main_tab_id, userId));
                aSubtabs.add(newSub);
            }
        }
        return aSubtabs;
    }

    private List<ASubtabSub> getSubTabSub(int sub_tab_no, int main_tab_id, int userId) {
        List<ASubtabSub> aSubtabSubs = new ArrayList<>();

        List<Integer> subTabsubs = jobDefineRepository.findBySubTabSubList(sub_tab_no,main_tab_id,userId);
        for (Integer subTabSub : subTabsubs) {
            ASubtabSub subtabSub = subSubTabRepository.getOne(subTabSub);
            if (subtabSub != null) {
                ASubtabSub newSubtabSub = new ASubtabSub();
                newSubtabSub.setSub_Tab_Sub_ID(subtabSub.getSub_Tab_Sub_ID());
                newSubtabSub.setSub_Tab_Sub_Name(subtabSub.getSub_Tab_Sub_Name());
                newSubtabSub.setSub_Tab_Sub_Code(subtabSub.getSub_Tab_Sub_Code());
                newSubtabSub.setRefPage(subtabSub.getRefPage());
                aSubtabSubs.add(newSubtabSub);
            }
        }
        return aSubtabSubs;
    }



    @Transactional
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName).get();
    }

    @Transactional
    public User saveUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getUser(String userName) {
        return userRepository.findByUsername(userName).get();
    }

    @Transactional
    public List<JobDefine> getJobList() {
        List<JobDefine> jobList = new ArrayList<>();
        List<AMaintab> maintabs = mainTabRepository.findMainTabs();
        for (AMaintab maintab : maintabs) {
            JobDefine jobDefine = new JobDefine();
            jobDefine.setMainTabId(maintab.getMain_Tab_ID());
            jobDefine.setMainTabName(maintab.getMain_Tab_Name());
            jobDefine.setSubList(getSubList(maintab.getMain_Tab_ID()));
            jobList.add(jobDefine);
        }
        return jobList;
    }

    private List<ASubtab> getSubList(int main_tab_id) {
        List<ASubtab> subtabs = new ArrayList<>();
        List<ASubtab> subs = subTabRepository.findSubTabs(main_tab_id);
        for (ASubtab subtab : subs) {
            ASubtab subtab1 = new ASubtab();
            subtab1.setSub_Tab_No(subtab.getSub_Tab_No());
            subtab1.setSub_Tab_Name(subtab.getSub_Tab_Name());
            subtab1.setSubtabSubList(getSubTabSub(main_tab_id, subtab.getSub_Tab_No()));
            subtabs.add(subtab1);
        }
        return subtabs;
    }

    private List<ASubtabSub> getSubTabSub(int main_tab_id, int sub_tab_no) {
        return subSubTabRepository.findSubSubTabs(main_tab_id, sub_tab_no);
    }


    //Search wise Branch
    @Transactional
    public List<JobDefine> findBranchJobList(int branchId) {
        List<JobDefine> defineList = new ArrayList<>();

        List<Integer> jobDefineList = jobDefineRepository.findByBranchMainTabList(branchId);

        for (Integer mainTab : jobDefineList) {
            AMaintab maintab = mainTabRepository.getOne(mainTab);
            if (mainTab != null) {
                JobDefine jobDefine = new JobDefine();
                jobDefine.setMainTabId(maintab.getMain_Tab_ID());
                jobDefine.setMainTabName(maintab.getMain_Tab_Name());
                jobDefine.setMainCode(maintab.getMain_Tab_Code());
                jobDefine.setRefPage(maintab.getRef_Page());
                jobDefine.setSubList(getBranchSubTabs(maintab.getMain_Tab_ID(), branchId));
                defineList.add(jobDefine);
            }
        }
        return defineList;
    }

    private List<ASubtab> getBranchSubTabs(int main_tab_id, int branchId) {
        List<ASubtab> aSubtabs = new ArrayList<>();
        List<Integer> subTabs = jobDefineRepository.findByBranchSubTabList(main_tab_id,branchId);
        for (Integer subTab : subTabs) {
            ASubtab aSubtab = subTabRepository.getOne(subTab);
            if (aSubtab != null) {
                ASubtab newSub = new ASubtab();
                newSub.setSub_Tab_No(aSubtab.getSub_Tab_No());
                newSub.setSub_Tab_Name(aSubtab.getSub_Tab_Name());
                newSub.setSub_Tab_Code(aSubtab.getSub_Tab_Code());
                newSub.setRefPage(aSubtab.getRefPage());
                newSub.setSubtabSubList(getBranchSubTabSub(newSub.getSub_Tab_No(), main_tab_id, branchId));
                aSubtabs.add(newSub);
            }
        }
        return aSubtabs;
    }

    private List<ASubtabSub> getBranchSubTabSub(int sub_tab_no, int main_tab_id, int branchId) {
        List<ASubtabSub> aSubtabSubs = new ArrayList<>();

        List<Integer> subTabsubs = jobDefineRepository.findByBranchSubTabSubList(sub_tab_no,main_tab_id,branchId);
        for (Integer subTabSub : subTabsubs) {
            ASubtabSub subtabSub = subSubTabRepository.getOne(subTabSub);
            if (subtabSub != null) {
                ASubtabSub newSubtabSub = new ASubtabSub();
                newSubtabSub.setSub_Tab_Sub_ID(subtabSub.getSub_Tab_Sub_ID());
                newSubtabSub.setSub_Tab_Sub_Name(subtabSub.getSub_Tab_Sub_Name());
                newSubtabSub.setSub_Tab_Sub_Code(subtabSub.getSub_Tab_Sub_Code());
                newSubtabSub.setRefPage(subtabSub.getRefPage());
                aSubtabSubs.add(newSubtabSub);
            }
        }
        return aSubtabSubs;
    }

}
