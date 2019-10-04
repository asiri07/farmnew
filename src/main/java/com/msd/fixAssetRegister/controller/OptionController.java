package com.msd.fixAssetRegister.controller;

import com.msd.fixAssetRegister.model.Currency;
import com.msd.fixAssetRegister.model.CurrencyRate;
import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.service.AssetService;
import com.msd.fixAssetRegister.service.CurrencyRateService;
import com.msd.fixAssetRegister.service.CurrencyService;
import com.msd.fixAssetRegister.service.UserService;
import com.msd.fixAssetRegister.util.SqlUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("option")
public class OptionController {

    @Autowired
    UserService userService;

    @Autowired
    CurrencyRateService currencyRateService;

    @Autowired
    SqlUtil sqlUtil;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    AssetService assetService;

    private static final Logger logger = LoggerFactory.getLogger(OptionController.class);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Value("${application.companyName}")
    private String companyName;

    @RequestMapping("dataBackup")
    public ModelAndView loadDataBackup(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadDataBackup Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("dataBackup-option");

    }

    @RequestMapping("currency")
    public ModelAndView loadCurrency(Model model, HttpServletRequest request) {
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                currencyTypes = currencyService.findAll();
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadCurrency Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("currency", new Currency());
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("currency-option");
    }

    @RequestMapping(value = "saveCurrency", method = RequestMethod.POST)
    public ModelAndView saveCurrency(@ModelAttribute("currencyForm") Currency currency, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        List<Currency> currencyTypes = new ArrayList<Currency>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Currency catergoryAnalysis = currencyService.saveUpdateCurrency(currency); //done
                if (catergoryAnalysis != null) {
                    isSave = true;
                }
                currencyTypes = currencyService.findAll();

            }
        } catch (Exception e) {
            logger.error( "Error occurred while calling the saveCurrency Method : " + e.getMessage() );
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("currency", new Currency());
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("currency-option");
    }

    @RequestMapping("editCurrency/{currencyId}")
    public ModelAndView editCurrency(@PathVariable("currencyId") int currencyId, Model model, HttpServletRequest request) {
        List<Currency> currencyTypes = new ArrayList<Currency>();
        Currency currency = null;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                currencyTypes = currencyService.findAll();
                currency = currencyService.findById(currencyId);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the editCurrency Method : " + e.getMessage());
        }
        model.addAttribute("isEdit", 1);
        model.addAttribute("currency", currency);
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        return new ModelAndView("currency-option");
    }

    @RequestMapping(value = "deleteCurrency/{currencyId}", method = RequestMethod.GET)
    public @ResponseBody
    int deleteCurrency(@PathVariable("currencyId") int currencyId) {
        int isDelete = 0;
        try {
            isDelete = currencyService.deleteCurrecy(currencyId);
        } catch (Exception e) {
            isDelete = 0;
        }
        return isDelete;
    }

    @RequestMapping("checkCurrencyCode/{currencyCode}")
    @ResponseBody
    public Boolean checkCurrencyCode(@PathVariable("currencyCode") String currencyCode) {
        Boolean isCheck = true;
        try {
            Currency currency = currencyService.getCurrencyByCode(currencyCode);
            if (currency != null) {
                isCheck = false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the checkCurrencyCode Method : " + e.getMessage());
        }
        return isCheck;
    }


    @RequestMapping("currencyRate")
    public ModelAndView loadCurrencyRate(Model model, HttpServletRequest request) {
        List<Currency> currencyTypes = new ArrayList<Currency>();
        List<CurrencyRate> currencyRates = new ArrayList<CurrencyRate>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                currencyTypes = currencyService.findAll();
                currencyRates = currencyRateService.findCurrencyRateByDate(new Date());
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadCurrency Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyRate", new CurrencyRate());
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("currencyRates", currencyRates);
        return new ModelAndView("currencyRate-option");
    }

    @RequestMapping("getRateByDate/{date}")
    @ResponseBody public  List<Object[]> getRateByDate(@PathVariable("date") String date) {
        List<CurrencyRate> currencyRates = new ArrayList<CurrencyRate>();
        List<Currency> currencyTypes = new ArrayList<Currency>();
        List<Object[]> rates = new ArrayList<>();

        if(StringUtils.isNotEmpty(date)) {
            try {
                Date day = simpleDateFormat.parse(date);
                currencyRates = currencyRateService.findCurrencyRateByDate(day);
                currencyTypes = currencyService.findAll();
               // rate =  new Object[currencyRates.size()][4];
                int i=0;
                for (CurrencyRate currencyRate: currencyRates) {
                    Object[] rate =new Object[4];
                     for (Currency currency: currencyTypes) {
                        if(currencyRate.getCurrencyFrom() == currency.getCurrencyId()) {
                            rate[0] = currency.getCurrencyCode();
                        }
                        if(currencyRate.getCurrencyTo() == currency.getCurrencyId()) {
                            rate[1] = currency.getCurrencyCode();
                        }
                    }
                    rate[2]=currencyRate.getDate();
                    rate[3]=currencyRate.getExchangeRate();
                    rates.add(rate);
                    i++;
                }
            }catch (Exception e) {
                logger.error("Error occurred while calling the getRateByDate Method : " + e.getMessage());
            }

        }
        return rates;
    }

    @RequestMapping(value = "saveCurrencyRate", method = RequestMethod.POST)
    public ModelAndView saveCurrencyRate(@ModelAttribute("currencyRateForm") CurrencyRate currencyRate, HttpServletRequest request, Model model) {
        Boolean isSave = false;
        List<Currency> currencyTypes = new ArrayList<Currency>();
        List<CurrencyRate> currencyRates = new ArrayList<CurrencyRate>();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                model.addAttribute("jobList", defineList);
                currencyRate.setUserId(user.getUserId());
                currencyRate.setActionTime(date);
                String msg = currencyRateService.saveCurrencyRate(currencyRate);
                currencyTypes = currencyService.findAll();
                currencyRates = currencyRateService.findCurrencyRateByDate(new Date());
                if (msg.equals("Update Successful.")) {
                    isSave = true;
                }
            }
        } catch (Exception e) {
            logger.error( "Error occurred while calling the saveCurrency Method : " + e.getMessage() );
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        model.addAttribute("currencyRate", new CurrencyRate());
        model.addAttribute("companyName", companyName);
        model.addAttribute("currencyTypes", currencyTypes);
        model.addAttribute("currencyRates", currencyRates);
        return new ModelAndView("currencyRate-option");
    }


    @RequestMapping("generateBackup")
    @ResponseBody
    public String generateBackup() {
        return sqlUtil.Backupdbtosql();
    }

    @RequestMapping("qrGenerator")
    public ModelAndView loadQr(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }
        } catch (Exception e) {
            logger.error("Error occurred while calling the loadCurrency Method : " + e.getMessage());
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("qrGenerator-option");
    }

    @RequestMapping("generateQrCode")
    public ModelAndView generateQrCode(HttpServletRequest request,Model model, HttpServletResponse response) {
        Boolean isSave = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
                isSave = assetService.saveQrPath();
            }
        }catch(Exception e){
            logger.error("Error occurred while calling the saveImprovement Method : " + e.getMessage());
        }
        model.addAttribute("status", isSave);
        model.addAttribute("pageType", 1);
        return new ModelAndView("qrGenerator-option");
    }

}
