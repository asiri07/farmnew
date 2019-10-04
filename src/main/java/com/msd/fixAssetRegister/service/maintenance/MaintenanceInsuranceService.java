package com.msd.fixAssetRegister.service.maintenance;

import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.repository.MaintenanceInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class MaintenanceInsuranceService {


    @Autowired
    MaintenanceInsuranceRepository maintenanceInsuranceRepository;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public MaintenanceInsurance saveUpdateMaintenanceInsurance(MaintenanceInsurance maintenanceInsurance) {
        return maintenanceInsuranceRepository.save(maintenanceInsurance);
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount =maintenanceInsuranceRepository.getCount();
        int maxNoInt;
        if(rowCount==0){
            maxNoInt=0;
        }else{
            List<String> numbers = maintenanceInsuranceRepository.getMaxTransactionNo();
            String maxNoString=numbers.get(0);

            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }

    @Transactional
    public List<MaintenanceInsurance> getTransactionNoList(int assetId) {
        return maintenanceInsuranceRepository.getTransactionNoList(assetId);

    }

    @Transactional
    public MaintenanceInsurance getInsuranceDetails(int transactionNo) {
        MaintenanceInsurance maintenanceInsurance = maintenanceInsuranceRepository.getInsuranceDetails(transactionNo);
        if(maintenanceInsurance!=null){
            maintenanceInsurance.setInsuredValueDisplay(String.format("%1$,.2f", maintenanceInsurance.getInsuranceValue()));
            maintenanceInsurance.setPremiumDisplay(String.format("%1$,.2f", maintenanceInsurance.getPremium()));
        }
        return maintenanceInsurance;
    }
    @Transactional
    public MaintenanceInsurance findById(int insuranceId) {
        return maintenanceInsuranceRepository.findById(insuranceId).get();
    }

    @Transactional
    public List<MaintenanceInsurance> loadData(Date parse, int noDays, int branch){

        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        c.add(Calendar.DATE,noDays);
        Date date =c.getTime();

        if(branch == 0) {
            return maintenanceInsuranceRepository.loadData(parse, date);
        }else{
            return maintenanceInsuranceRepository.loadData(parse, date,branch);
        }
    }
    @Transactional
    public Date loadToDate(Date date, int noMonths) throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,noMonths);
        Date dateReturn =c.getTime();


        return dateReturn;
}}
