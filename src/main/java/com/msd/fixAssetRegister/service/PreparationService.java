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
package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.controller.PreparationController;
import com.msd.fixAssetRegister.model.*;
import com.msd.fixAssetRegister.model.form.PreparationMassage;
import com.msd.fixAssetRegister.repository.AssetRepository;
import com.msd.fixAssetRegister.repository.CurrencyRepository;
import com.msd.fixAssetRegister.repository.PreparationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PreparationService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyRateService currencyRateService;

    @Autowired
    PreparationRepository preparationRepository;

    @Autowired
    AssetRepository assetRepository;

    private static final Logger log = LoggerFactory.getLogger(PreparationService.class);
    @Transactional
    public PreparationMassage addPreparation(List<Asset> assets, Date fromDate, Date toDate, User user, int currencyType) {
        PreparationMassage addPreparation = new PreparationMassage();
        for (Asset asset : assets) {
            log.info("currency :"+asset.getCurrencyType());
            if (asset.getCurrencyType() == currencyType) {
             //    log.info("start asset id:"+asset.getAsCode()+ "started time :"+new Date());
                 int status = createPreparation(asset, fromDate, toDate, user, currencyType, 1);
                 addPreparation.setStatus(status);
                 log.info("byee "+asset.getAsCode()+ "end time :"+new Date());
            } else {
                log.info("check point");
                CurrencyRate currencyRate = currencyRateService.findRateByDate(asset.getPurDate(), asset.getCurrencyType(), currencyType);
                log.info("currency Exchange rate:"+currencyRate.getExchangeRate());
                if (currencyRate != null) {
                    int status = createPreparation(asset, fromDate, toDate, user, currencyType, currencyRate.getExchangeRate());
                    addPreparation.setStatus(status);
                } else {
                    addPreparation.setStatus(2);
                    addPreparation.setAssetCode(asset.getAsCode());
                    Currency currencyFrom = currencyRepository.findById(asset.getCurrencyType()).get();
                    Currency currencyTo = currencyRepository.findById(currencyType).get();
                    addPreparation.setMassage(asset.getAsCode() + ", Date : "+asset.getPurDate()+" Currency : " + currencyFrom.getCurrencyCode() + " To " + currencyTo.getCurrencyCode() + " exchange rate is not found");
                    break;
                }
            }
        }
        return addPreparation;
    }

    /**
     * @param asset
     * @param fromDate
     * @param toDate
     * @param user
     */
    private int createPreparation(Asset asset, Date fromDate, Date toDate, User user, int currencyType, double currencyRate) {
        int status=0;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            ReportPreparationData preparationData = new ReportPreparationData();
            preparationData.setAssetCode(asset.getAsCode());
            preparationData.setAnalysisCode(asset.getAnalysisCode());
            preparationData.setAuthPerson(asset.getAuthPerson());
            preparationData.setPurchasedDate(asset.getPurDate());
            preparationData.setRegistedDate(asset.getRegDate());

            LocationMaster currentLocation = asset.getLocationMaster();
            preparationData.setLocationCode(currentLocation.getLocCode());
            preparationData.setSectionCode(currentLocation.getSectionMaster().getSecCode());
            preparationData.setDepartmentCode(currentLocation.getSectionMaster().getDepartmentMaster().getDepCode());
            preparationData.setCompanyCode(currentLocation.getSectionMaster().getDepartmentMaster().getCompanyMaster().getComCode());

            preparationData.setDetailCat(asset.getAssetCatergoryDetail().getDcatCode());
            preparationData.setSubCat(asset.getAssetCatergoryDetail().getAssetCatergorySub().getScatCode());
            preparationData.setMainCat(asset.getAssetCatergoryDetail().getAssetCatergorySub().getAssetCatergoryMain().getMcatCode());

            if (asset.getAsDamage() && isActiveDamage(asset, fromDate, toDate)) {
                preparationData.setIsDamage(true);
                preparationData.setDamageDate(asset.getDamages().get(asset.getDamages().size() - 1).getDate());
                preparationData.setNoOfDamage(asset.getDamages().size());
            }

            Double impValue = 0.00;
            if (asset.getAssetImprovements().size() > 0) {
                preparationData.setIsImprovement(true);
                preparationData.setImprovementDate(asset.getAssetImprovements().get(asset.getAssetImprovements().size() - 1).getImpDate());
                for (int j = 0; j < asset.getAssetImprovements().size(); j++) {
                    AssetImprovement improvement = asset.getAssetImprovements().get(j);
                    if (isActiveImproment(improvement, fromDate, toDate)) {
                        impValue = +improvement.getValue();
                    }
                }
            }

            Double depRateAccunting = asset.getAssetCatergoryDetail().getDepRateAccount();
            Double depRateIncomeTax = asset.getAssetCatergoryDetail().getDepRateIncomeTax();

            preparationData.setDepreciationRate(depRateAccunting);
            preparationData.setTaxRate(depRateIncomeTax);

            Double assetValue = asset.getUnitPrice();
            Double totalValue = assetValue + impValue;// inpromvent  issue
            preparationData.setAssetValue(assetValue*currencyRate);// ADD *currencyRate for  exchnage rate from  asiri
            preparationData.setValueAfterImprovement(totalValue*currencyRate);// ADD *currencyRate for  exchnage rate from asiri
            //calculate until dispossal date- Disp
            //Double dipRate = (depRateAccunting / 100);
            //Double dipRatePerDay = dipRate / 365;
            long lifeTime = generateLifeTime(depRateAccunting);

            if (asset.getAsDisposed() && isActiveDisposal(asset, fromDate, toDate)) {
                preparationData.setIsDisposal(true);
                preparationData.setDisposalDate(asset.getDisposals().get(0).getDpDate());
                preparationData.setDisposalAmount(asset.getDisposals().get(0).getDp_value());


                Date purDate = asset.getPurDate();
                long upToDisposalDate = incrementDate(preparationData.getDisposalDate()).getTime() - purDate.getTime();
                long diffUpToDisposalDate = upToDisposalDate / (24 * 60 * 60 * 1000);
                if (diffUpToDisposalDate > lifeTime) {
                    diffUpToDisposalDate = lifeTime;
                }
              //  log.info("totalValue:  "+totalValue);
              //  log.info("lifeTime:  "+lifeTime);
              //  log.info("diffUpToDisposalDate:  "+diffUpToDisposalDate);
                Double upToDisposalDispValue = (totalValue / lifeTime) * diffUpToDisposalDate;
            //    log.info("upToDisposalDispValue:  "+upToDisposalDispValue);
                try {
                    preparationData.setDispValueDisposal(new BigDecimal(upToDisposalDispValue).setScale(2, RoundingMode.HALF_UP).doubleValue());
                }catch (Exception e){
                    preparationData.setDispValueDisposal(0);
                }
            }
            if (asset.getAsTransfer()) {
                preparationData.setIsTransfer(true);
                if (asset.getTransfers().size() > 0) {
                    preparationData.setTransferDate(asset.getTransfers().get(asset.getTransfers().size() - 1).getTfDate());
                    preparationData.setNoOfTransfers(asset.getTransfers().size());
                }
            }
            if (depRateAccunting > 0.00) {

                preparationData.setLifeTime(lifeTime + "");

                Date purDate = asset.getPurDate();

                long upToFromDate = 0;
                long upToFrom_to = 0;
                // if Asset is disposal genarate fromdate and todate
                if (asset.getAsDisposed()  && isActiveDisposal(asset, fromDate, toDate)) {

                    if (fromDate.compareTo(preparationData.getDisposalDate()) > 0 ) {
                        upToFromDate = preparationData.getDisposalDate().getTime() - purDate.getTime();
                        upToFrom_to = incrementDate(toDate).getTime() - fromDate.getTime();
                    } else {
                        if (fromDate.after(purDate)) {
                            upToFromDate = fromDate.getTime() - purDate.getTime();
                        }
                        if (toDate.compareTo(preparationData.getDisposalDate()) >= 0) {
                            if (purDate.after(fromDate)) {
                                upToFrom_to = incrementDate(preparationData.getDisposalDate()).getTime() - purDate.getTime();
                            } else {
                                upToFrom_to = incrementDate(preparationData.getDisposalDate()).getTime() - fromDate.getTime();
                            }
                        } else {
                            if (purDate.after(fromDate)) {
                                upToFrom_to = incrementDate(toDate).getTime() - purDate.getTime();
                            } else {
                                upToFrom_to = incrementDate(toDate).getTime() - fromDate.getTime();
                            }
                        }
                    }
                } else {
                    if (purDate.after(fromDate)) {
                        upToFrom_to = incrementDate(toDate).getTime() - purDate.getTime();
                    } else {
                        upToFromDate = fromDate.getTime() - purDate.getTime();
                        upToFrom_to = incrementDate(toDate).getTime() - fromDate.getTime();
                    }
                }
                long diffDaysUpToFromday = upToFromDate / (24 * 60 * 60 * 1000);
                long diffDaysUpTo_Today = 0;
                Double fromDateValue = 0.00;
                Double toDateValue = 0.00;
                Double fromDateValueDisp = 0.00;
                Double toDateValueDisp = 0.00;

                if (diffDaysUpToFromday >= lifeTime) {
                    diffDaysUpToFromday = lifeTime;
                }
                Double bfDispValue = (totalValue / lifeTime) * diffDaysUpToFromday;
                if (bfDispValue > 0.00) {
                    preparationData.setBfDispValue(new BigDecimal(bfDispValue).setScale(2, RoundingMode.HALF_UP).doubleValue());
                } else {
                    preparationData.setBfDispValue(0.00);
                }
                diffDaysUpTo_Today = upToFrom_to / (24 * 60 * 60 * 1000);

                if (diffDaysUpToFromday > 0) {

                    if (purDate.compareTo(fromDate) != 0) {
                        fromDateValueDisp = (totalValue / lifeTime) * diffDaysUpToFromday;
                    }
                    fromDateValue = totalValue - fromDateValueDisp;
                    if (fromDateValue < 0.00) {
                        fromDateValue = 0.00;
                    }
                }
                long remainingLife = lifeTime - diffDaysUpToFromday;
                if (remainingLife > diffDaysUpTo_Today) {
                    toDateValueDisp = (totalValue / lifeTime) * diffDaysUpTo_Today;
                    toDateValue = totalValue - (fromDateValueDisp + toDateValueDisp);
                    if (toDateValue < 0.00) {
                        toDateValue = 0.00;
                    }
                } else {
                    toDateValueDisp = (totalValue / lifeTime) * remainingLife;
                    toDateValue = totalValue - (fromDateValueDisp + toDateValueDisp);
                    log.info("toDateValue:"+toDateValue);
                    if (toDateValue < 0.00) {
                        toDateValue = 0.00;
                    }
                }
              //  log.info("code::"+asset.getAsCode());
             //   log.info("totalValue:"+totalValue);
             //   log.info("lifeTime:"+lifeTime);
            //    log.info("remainingLife:"+remainingLife);
            //    log.info("toDateValue:"+toDateValue);
                preparationData.setCurrencyType(currencyType);
                preparationData.setFromDateValue(new BigDecimal(fromDateValue * currencyRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
                preparationData.setFromDateDispValue(new BigDecimal(fromDateValueDisp * currencyRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
                preparationData.setToDateValue(new BigDecimal(toDateValue * currencyRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
                preparationData.setToDateDispValue(new BigDecimal(toDateValueDisp * currencyRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (asset.getAsDisposed()) {
                    preparationData.setCurrentValue(0.00);
                } else {
                    preparationData.setCurrentValue(new BigDecimal(toDateValue * currencyRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
                }

            } else {
                preparationData.setBfDispValue(totalValue);
                preparationData.setFromDateValue(totalValue);
                preparationData.setToDateValue(totalValue);
                preparationData.setCurrentValue(totalValue);
                preparationData.setFromDateDispValue(0.00);
                preparationData.setToDateTaxDispValue(0.00);
            }
            preparationData.setLifeTime(asset.getLifeTime());

//        if (depRateIncomeTax > 0.00 && !asset.getAsDisposed()) {
//
//            Double dipRate = (depRateIncomeTax / 100);
//            Double dipRatePerDay = dipRate / 365;
//
//            Date purDate = asset.getPurDate();
//            long upToFromDate = fromDate.getTime() - purDate.getTime();
//            long upToFrom_to;
//            if (purDate.after(fromDate)) {
//                upToFrom_to = toDate.getTime() - purDate.getTime();
//            } else {
//                upToFrom_to = toDate.getTime() - fromDate.getTime();
//            }
//
//            long diffDaysUpToFromday = upToFromDate / (24 * 60 * 60 * 1000);
//            diffDaysUpToFromday += 1;
//            long diffDaysUpTo_Today = upToFrom_to / (24 * 60 * 60 * 1000);
//            diffDaysUpTo_Today += 1;
//
//            Double fromDateValue = 0.00;
//            Double toDateValue = 0.00;
//            Double fromDateValueDisp = 0.00;
//            Double toDateValueDisp = 0.00;
//            if (diffDaysUpToFromday > 0) {
//                fromDateValueDisp = totalValue * diffDaysUpToFromday * dipRatePerDay;
//                fromDateValue = totalValue - fromDateValueDisp;
//                if (fromDateValue < 0.00) {
//                    fromDateValue = 0.00;
//                }
//            }
//            if (diffDaysUpTo_Today > 0) {
//                toDateValueDisp = totalValue * diffDaysUpTo_Today * dipRatePerDay;
//                toDateValue = totalValue - (fromDateValueDisp + toDateValueDisp);
//                if (toDateValue < 0.00) {
//                    toDateValue = 0.00;
//                }
//            }
//            preparationData.setFromDateTaxValue(new BigDecimal(fromDateValue).setScale(2, RoundingMode.HALF_UP).doubleValue());
//            preparationData.setFromDateTaxDispValue(new BigDecimal(fromDateValueDisp).setScale(2, RoundingMode.HALF_UP).doubleValue());
//            preparationData.setToDateTaxValue(new BigDecimal(toDateValue).setScale(2, RoundingMode.HALF_UP).doubleValue());
//            preparationData.setToDateTaxDispValue(new BigDecimal(toDateValueDisp).setScale(2, RoundingMode.HALF_UP).doubleValue());
//            preparationData.setTaxCurrntValue(new BigDecimal(toDateValue).setScale(2, RoundingMode.HALF_UP).doubleValue());
//
//        } else {
//            preparationData.setFromDateTaxValue(totalValue);
//            preparationData.setToDateTaxValue(totalValue);
//            preparationData.setTaxCurrntValue(totalValue);
//            preparationData.setFromDateTaxDispValue(0.00);
//            preparationData.setToDateTaxDispValue(0.00);
//        }
            preparationData.setLedgerCode(asset.getLedgerCode());
            preparationData.setStartDate(fromDate);
            preparationData.setEndDate(toDate);
            preparationData.setUserId(user.getUserId());
            preparationData.setActionTime(date);
            preparationRepository.save(preparationData);
            status = 1;
        }catch (Exception e){
            log.error("Exception "+e);
        }
        return status;
    }


    /**
     * @param asset
     * @return
     */
    private boolean isActiveDisposal(Asset asset, Date fromDate, Date toDate) {
        if (asset.getDisposals().get(0).getDpDate() != null) {
            Date disposalDate = asset.getDisposals().get(0).getDpDate();
            if (disposalDate.after(fromDate) && disposalDate.before(toDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param asset
     * @param fromDate
     * @param toDate
     * @return
     */
    private boolean isActiveDamage(Asset asset, Date fromDate, Date toDate) {
        if (asset.getDamages().size() > 0) {
            Date damageDate = asset.getDamages().get(asset.getDamages().size() - 1).getDate();
            if (damageDate.after(fromDate) && damageDate.before(toDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param improvement
     * @param fromDate
     * @param toDate
     * @return
     */
    private boolean isActiveImproment(AssetImprovement improvement, Date fromDate, Date toDate) {
        if (improvement.getImpDate() != null) {
            if (improvement.getImpDate().after(fromDate) && improvement.getImpDate().before(toDate)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param depRateAccunting
     * @return
     */
    private long generateLifeTime(Double depRateAccunting) {
        System.out.println(depRateAccunting.intValue()+"depRateAccunting.intValue()");
        try {
            int year = (100 / depRateAccunting.intValue());
            log.info("check point 2 :"+year * 365);
            return year * 365;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * @param date
     * @return
     */
    public Date incrementDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }


    @Transactional
    public ReportPreparationData getPreparationData() {
        return preparationRepository.findAll().get(1);
    }


    public void clearPreparationData() {
        preparationRepository.deleteReportPreparationData();
    }


    public List<Asset> getPreparationAssets(Date toDate) {
        return assetRepository.findByPurDate(toDate);
    }

    public List<ReportPreparationData> findAll() {
        return preparationRepository.findAll();
    }
}
