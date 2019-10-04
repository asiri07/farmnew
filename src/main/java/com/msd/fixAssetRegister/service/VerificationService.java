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

import com.msd.fixAssetRegister.model.PhysicalVerification;
import com.msd.fixAssetRegister.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VerificationService {


    @Autowired
    VerificationRepository verificationRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Transactional
    public String saveVerification(PhysicalVerification verification) {
        String[] detailCodes = verification.getDetailCode().split(",");
        String[] detailCodeIds = verification.getDetailId().split(",");
        String[] physicalBalances = verification.getDetailBalance().split(",");
        String[] description = verification.getDescription().split(",");
        verificationRepository.removeTodayVerifications(verification.getDate());
        for (int i = 0; i < detailCodes.length; i++) {
            PhysicalVerification physicalVerification = new PhysicalVerification();
            physicalVerification.setLocationId(verification.getLocationId());
            physicalVerification.setDetailCode(detailCodes[i]);
            physicalVerification.setDetailId(detailCodeIds[i]);
            physicalVerification.setDescription(description[i]);
            physicalVerification.setBalance(Double.parseDouble(physicalBalances[i]));
            physicalVerification.setDate(verification.getDate());
            physicalVerification.setUserId(verification.getUserId());
            physicalVerification.setActionTime(verification.getActionTime());
            verificationRepository.save(physicalVerification);
        }
        return "Update Successful.";
    }

    @Transactional
    public List<PhysicalVerification> loadVerification(Date parse,int locationId) {
        return verificationRepository.findByDate(parse,locationId);
    }
}
