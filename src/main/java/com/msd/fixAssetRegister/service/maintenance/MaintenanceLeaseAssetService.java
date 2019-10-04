package com.msd.fixAssetRegister.service.maintenance;


import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.repository.MaintenanceLeaseAssetRepository;
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
public class MaintenanceLeaseAssetService {


    @Autowired
    MaintenanceLeaseAssetRepository maintenanceLeaseAssetRepository;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public MaintenanceLeaseAsset saveUpdateMaintenanceLeaseAsset(MaintenanceLeaseAsset maintenanceLeaseAsset) {
        return maintenanceLeaseAssetRepository.save(maintenanceLeaseAsset);
    }

    @Transactional
    public int generateTransactionNo() {
        int rowCount = maintenanceLeaseAssetRepository.getCount();
        int maxNoInt;
        if (rowCount == 0) {
            maxNoInt = 0;
        } else {
            List<String> numbers = maintenanceLeaseAssetRepository.getMaxTransactionNo();
            String maxNoString = numbers.get(0);
            maxNoInt = Integer.parseInt(maxNoString);
        }
        return ++maxNoInt;
    }

    @Transactional
    public List<MaintenanceLeaseAsset> getTransactionNoList(int assetId) {
        return maintenanceLeaseAssetRepository.getTransactionNoList(assetId);

    }


    @Transactional
    public MaintenanceLeaseAsset getLeaseAssetDetails(int transactionNo) {
        return maintenanceLeaseAssetRepository.getLeaseAssetDetails(transactionNo);
    }

    @Transactional
    public MaintenanceLeaseAsset findById(int leaseId) {
        return maintenanceLeaseAssetRepository.findById(leaseId).get();
    }

    @Transactional
    public List<MaintenanceLeaseAsset> loadData(Date parseDate, int noDays, int branch) {

        Calendar c = Calendar.getInstance();
        c.setTime(parseDate);

        int fromDate = c.get(Calendar.DATE);
        int fromMonth = c.get(Calendar.MONTH) + 1;
        int fromYear = c.get(Calendar.YEAR);

        c.add(Calendar.DATE, noDays);
        Date lastDate = c.getTime();


        Date date1 = lastDate;

        int toDate = c.get(Calendar.DATE);
        int toMonth = c.get(Calendar.MONTH) + 1;
        int toYear = c.get(Calendar.YEAR);

        int tempYear = fromYear;
        int tempMonth = fromMonth;
        int tempDate = fromDate;
        String premiumDateWithoutSpace = "";
        List<MaintenanceLeaseAsset> dueAsset = new ArrayList<MaintenanceLeaseAsset>();
        List<MaintenanceLeaseAsset> returnDueAsset = new ArrayList<MaintenanceLeaseAsset>();
        if (branch == 0) {
            dueAsset = maintenanceLeaseAssetRepository.loadDueAsset(parseDate);
        } else {
            dueAsset = maintenanceLeaseAssetRepository.loadDueAsset(parseDate, branch);
        }

        for (int i = 0; i < dueAsset.size(); i++) {
            tempYear = fromYear;
            tempMonth = fromMonth;
            tempDate = fromDate;
            int count=1;

            premiumDateWithoutSpace = dueAsset.get(i).getLeasePremiumDate().trim();
            int premiumDate = Integer.parseInt(premiumDateWithoutSpace.substring(0, 2));
            while (tempYear <= toYear) {
                while (tempMonth <= 12) {
                    if(count==1){
                    if (premiumDate < fromDate) {
                        tempMonth++;
                        count++;
                    }
                    }
                    String tempMonthString = Integer.toString(tempMonth);
                    String tempDateString = Integer.toString(premiumDate);

                    if (tempMonth < 10) {
                        tempMonthString = "0" + tempMonthString;
                    }
                    if (premiumDate < 10) {
                        tempDateString = "0" + tempDateString;
                    }
                    String sDate1 = tempYear + "-" + tempMonthString + "-" + tempDateString;
                    try {
                        date1 = simpleDateFormat.parse(sDate1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    if (date1.compareTo(parseDate) > 0 && date1.compareTo(lastDate) < 0) {
                        if(date1.compareTo(dueAsset.get(i).getLeasePremiumTo())<0) {
                            MaintenanceLeaseAsset maintenanceLeaseAsset = new MaintenanceLeaseAsset();
                            maintenanceLeaseAsset.setLeasePremiumTo(date1);
                            maintenanceLeaseAsset.setLeasePremium(dueAsset.get(i).getLeasePremium());
                            maintenanceLeaseAsset.setLeaseID(dueAsset.get(i).getLeaseID());
                            maintenanceLeaseAsset.setLeaseType(dueAsset.get(i).getLeaseType());
                            maintenanceLeaseAsset.setTransactionNo(dueAsset.get(i).getTransactionNo());
                            maintenanceLeaseAsset.setLeaseTot(dueAsset.get(i).getLeaseTot());
                            maintenanceLeaseAsset.setActionTime(dueAsset.get(i).getActionTime());
                            maintenanceLeaseAsset.setUserId(dueAsset.get(i).getUserId());
                            maintenanceLeaseAsset.setLeasePeriodFrom(dueAsset.get(i).getLeasePeriodFrom());
                            maintenanceLeaseAsset.setContactPerson(dueAsset.get(i).getContactPerson());
                            maintenanceLeaseAsset.setLeaseAddress(dueAsset.get(i).getLeaseAddress());
                            maintenanceLeaseAsset.setLeaseAgreNo(dueAsset.get(i).getLeaseAgreNo());
                            maintenanceLeaseAsset.setAssetId(dueAsset.get(i).getAssetId());
                            returnDueAsset.add(maintenanceLeaseAsset);
                        }
                    }
                    if (tempMonth == toMonth && tempYear == toYear) {
                        tempMonth = 13;
                    } else {
                        tempMonth++;
                    }
                }
                tempYear++;
                tempMonth = 1;
            }
        }
        return returnDueAsset;
    }
}

