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

import com.msd.fixAssetRegister.model.Incometax;
import com.msd.fixAssetRegister.repository.IncomeTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncomeTaxService {


    @Autowired
    IncomeTaxRepository incomeTaxRepository;

    public Incometax saveUpdateTax(Incometax incometax) {
        return incomeTaxRepository.save(incometax);
    }
    @Transactional
    public List<Incometax> findAll(){
        return incomeTaxRepository.findAll();
    }

    @Transactional
    public Incometax findById(int impId) {
        return incomeTaxRepository.findById(impId).get();
    }

    @Transactional
    public void delete(int taxId) {
        incomeTaxRepository.deleteById(taxId);
    }
}
