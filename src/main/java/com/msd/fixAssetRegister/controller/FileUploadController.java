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


import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.model.form.JobDefine;
import com.msd.fixAssetRegister.model.form.TransferForm;
import com.msd.fixAssetRegister.reportManager.DBFacade;
import com.msd.fixAssetRegister.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Value("${application.companyName}")
    private String companyName;

    @Autowired
    MainCategoryService mainCategoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    DetailCategoryService detailCategoryService;

    @Autowired
    TransferService transferService;

    @Autowired
    UserService userService;

    public static DBFacade db = new DBFacade();
    Connection connection = null;
    Statement statement = null;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);


    @RequestMapping("uploadFile")
    public ModelAndView uploadFile(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine> defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

        }catch (Exception e) {
            logger.error( "Error occurred while calling the uploadFile Method : " + e.getMessage() );
        }
        model.addAttribute("companyName", companyName);
        return new ModelAndView("uploadFile-upload");
    }

    @RequestMapping(value = "uploadXML", method = RequestMethod.POST)
    public ModelAndView uploadMainCat(@RequestParam("type") int type, @RequestParam("file") MultipartFile file, Model model,HttpServletRequest request) {
        Boolean isUpload = false;
        int count = 0;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                List<JobDefine>defineList = (List<JobDefine>) session.getAttribute("jobList");
                model.addAttribute("jobList", defineList);
            }

            List sheet = new ArrayList();
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            FileInputStream fis = new FileInputStream(convFile);
            HSSFWorkbook my_xls_workbook = new HSSFWorkbook(fis); //Read the Excel Workbook in a instance object
            HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); //This will read the sheet for us into another object
            Iterator<Row> rowIterator = my_worksheet.iterator(); // Create iterator object
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                Iterator cells = row.cellIterator();
                List data = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }
                sheet.add(data);
            }
            if (type == 1) {
                String sql1 = "DELETE FROM asset_catergory_main";
                connection = db.connect();
                statement = connection.createStatement();
                statement.executeUpdate(sql1);
                count = mainCategoryService.uploadXMLMainCat(sheet);
            } else if (type == 2) {
                count = uploadSubCatData(sheet);
            } else if (type == 3) {
                count = uploadDetailCatData(sheet);
            } else if (type == 4) {
                count = uploadAssetDetails(sheet);
            } else if (type == 5) {
                count = uploadTranferData(sheet);
            }
            if (count > 0) {
                isUpload = true;
            }
        } catch (Exception e) {
            logger.error( "Error occurred while calling the uploadMainCat Method : " + e.getMessage() );
        }
        model.addAttribute("status", isUpload);
        model.addAttribute("pageType", 1);
        model.addAttribute("count", count);
        model.addAttribute("companyName", companyName);
        return new ModelAndView("uploadFile-upload");
    }


    private int uploadTranferData(List sheet) throws ParseException, SQLException {
        int k = 0;
        String sqldel = "DELETE FROM transfer";
        connection = db.connect();
        statement = connection.createStatement();
        statement.executeUpdate(sqldel);

        for (int i = 1; i < sheet.size(); i++) {
            List list = (List) sheet.get(i);
            Object myObj[] = new Object[list.size()];
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
                if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        myObj[j] = cell.getDateCellValue();
                    } else {
                        myObj[j] = cell.getNumericCellValue();
                    }
                } else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                    myObj[j] = cell.getStringCellValue();
                }

            }
            String d1 = "", d2 = "", d3 = "", d4 = "", d5 = "", d6 = "", d7 = "", d8 = "", d9 = "", d10 = "", d11 = "",
                    d12 = "", d13 = "", d14 = "", d15 = "", d16 = "";

            Integer assetId = 0;
            Integer userId = 0;
            Integer type = 0;

            java.util.Date date = null;

            if (myObj[0] != null) {
                d1 = myObj[0].toString();
                Double code = Double.parseDouble(d1);
                assetId = code.intValue();
            }
            if (myObj[1] != null) {
                d2 = myObj[1].toString();
            }
            if (myObj[2] != null) {
                d3 = createPlainString(myObj[2].toString());
            }
            if (myObj[3] != null) {
                d4 = createPlainString(myObj[3].toString());
            }
            if (myObj[4] != null) {
                d5 = myObj[4].toString();
            }
            if (myObj[5] != null) {
                d6 = myObj[5].toString();
            }
            if (myObj[6] != null) {
                d7 = myObj[6].toString();
            }
            if (myObj[7] != null) {
                d8 = createPlainString(myObj[7].toString());
            }
            if (myObj[8] != null) {
                d9 = createPlainString(myObj[8].toString());
            }
            if (myObj[9] != null) {
                d10 = myObj[9].toString();
            }
            if (myObj[10] != null) {
                d11 = myObj[10].toString();
            }
            if (myObj[11] != null) {
                d12 = myObj[11].toString();
                if (d12.equals("NULL")) {
                    date = new java.util.Date();
                } else {
                    date = formatter.parse(d12);
                }
            }
            if (myObj[12] != null) {
                d13 = myObj[12].toString();
            }
            if (myObj[13] != null) {
                d14 = myObj[13].toString();
            }
            if (myObj[14] != null) {
                d15 = myObj[14].toString();
                Double code = Double.parseDouble(d15);
                type = code.intValue();
            }
            if (myObj[15] != null) {
                d16 = myObj[15].toString();
                Double code = Double.parseDouble(d16);
                userId = code.intValue();
            }

            TransferForm transferForm = new TransferForm();
            transferForm.setAssertId(assetId);
            transferForm.setDate(date);
            transferForm.setFromDep(d3);
            transferForm.setFromSec(d4);
            transferForm.setFromLoc(d5);
            transferForm.setFromEmpNo(d6);
            transferForm.setToDep(d8);
            transferForm.setToSec(d9);
            transferForm.setToLoc(d10);
            transferForm.setToEmpNo(d11);
            transferForm.setUserId(userId);
            transferForm.setType(type);
            transferForm.setIssuedTo(d13);
            transferForm.setComments(d14);
            transferService.saveAssetTransfer(transferForm);
            k++;
        }
        return k;
    }

    private String createPlainString(String s) {
        if (s.indexOf(".") >= 0) {
            Double code = Double.parseDouble(s);
            s = code.intValue() + "";
        }
        return s;
    }


    private int uploadAssetDetails(List sheet) throws Exception {
        int k = 0;

        try {
            String sqldel = "DELETE FROM asset";
            String sqldel2 = "DELETE FROM asset_location_details";
            connection = db.connect();
            statement = connection.createStatement();
            statement.executeUpdate(sqldel2);
            statement.executeUpdate(sqldel);

            for (int i = 1; i < sheet.size(); i++) {
                int count =i;
                System.out.println("------------>>------" + count);

                logger.warn("FAR - Row No : " + i);
                List list = (List) sheet.get(i);
                Object myObj[] = new Object[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    HSSFCell cell = (HSSFCell) list.get(j);
                    if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            myObj[j] = cell.getDateCellValue();
                        } else {
                            myObj[j] = cell.getNumericCellValue();
                            if (myObj[j] != null) {
                                myObj[j] = Double.parseDouble(myObj[j].toString());}

                        }
                    } else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                        myObj[j] = cell.getStringCellValue();
                    }


                }

                String d1 = "", d2 = "", d3 = "", d4 = "", d5 = "", d6 = "", d7 = "", d8 = "", d9 = "", d10 = "", d11 = "", d12 = "",
                        d13 = "", d14 = "", d15 = "", d16 = "", d17 = "", d18 = "", d19 = "", d20 = "", d21 = "", d22 = "", d23 = "",
                        d24 = "", d25 = "", d26 = "", d27 = "", d28 = "", d29 = "", d30 = "", d31 = "", d32 = "", d33 = "", d34 = "",
                        d35 = "", d36 = "", d37 = "",d38 = "", d39 = "", d40 = "", d41 = "", d42 = "", d43 = "", d44 = "",d45="",d46="";

                Date regDate = null;
                Date purDate = null;
                Date actionTime = null;



                if (myObj[0] != null) {
                    d1 = myObj[0].toString();
                    Double dble = Double.parseDouble(d1);
                    d1 = dble.intValue() + "";
                }
                if (myObj[1] != null) {
                    d2 = myObj[1].toString();
                }
                if (myObj[2] != null) {
                    d3 = myObj[2].toString();
                }
                if (myObj[3] != null) {
                    d4 = myObj[3].toString();
                    if (d4.equals("null")) {
                        java.util.Date date = new java.util.Date();
                        regDate = new Date(date.getTime());
                    } else {
                        java.util.Date date = formatter.parse(d4);
                        regDate = new Date(date.getTime());
                    }
                }

                if (myObj[4] != null) {
                    d5 = myObj[4].toString();
                }
                if (myObj[5] != null) {
                    d6 = myObj[5].toString();
                    Double dble = Double.parseDouble(d6);
                    d6 = dble.intValue() + "";
                }
                if (myObj[6] != null) {
                    d7 = myObj[6].toString();
                    Double dble = Double.parseDouble(d7);
                    d7 = dble.intValue() + "";
                }
                if (myObj[7] != null) {
                    d8 = myObj[7].toString();
                    Double dble = Double.parseDouble(d8);
                    d8 = dble.intValue() + "";
                }
                if (myObj[8] != null) {
                    d9 = myObj[8].toString();
                    Double dble = Double.parseDouble(d9);
                    d9 = dble.intValue() + "";
                }
                if (myObj[9] != null) {
                    d10 = myObj[9].toString();
                    Double dble = Double.parseDouble(d10);
                    d10 = dble.intValue() + "";
                }
                if (myObj[10] != null) {
                    d11 = myObj[10].toString();
                    Double dble = Double.parseDouble(d11);
                    d11 = dble.intValue() + "";
                }
                if (myObj[11] != null) {
                    d12 = myObj[11].toString();
                    Double dble = Double.parseDouble(d12);
                    d12 = dble.intValue() + "";
                }
                if (myObj[12] != null) {
                    d13 = myObj[12].toString();
                }
                if (myObj[13] != null) {
                    d14 = myObj[13].toString();
                }

                if (myObj[14] != null) {
                    d15 = myObj[14].toString();
                    if (d15.equals("null")) {
                        java.util.Date date = new java.util.Date();
                        purDate = new Date(date.getTime());
                    } else {
                        java.util.Date date = formatter.parse(d15);
                        purDate = new Date(date.getTime());
                    }
                }
                if (myObj[15] != null) {
                    d16 = myObj[15].toString();
                    Double dble = Double.parseDouble(d16);
                    d16 = dble.intValue() + "";
                }
                if (myObj[16] != null) {
                    d17 = myObj[16].toString();
                    Double dble = Double.parseDouble(d17);
                    d17 = dble.intValue() + "";

                }
                if (myObj[17] != null) {
                    d18 = myObj[17].toString();

                }
                if (myObj[18] != null) {
                    d19 = myObj[18].toString();
                }
                if (myObj[19] != null) {
                    d20 = myObj[19].toString();


                }
                if (myObj[20] != null) {
                    d21 = myObj[20].toString();
                }
                if (myObj[21] != null) {
                    d22 = myObj[21].toString();
                    Double dble = Double.parseDouble(d22);
                    d22 = dble.intValue() + "";
                }
                if (myObj[22] != null) {
                    d23 = myObj[22].toString();
                }
                if (myObj[23] != null) {
                    d24 = myObj[23].toString();
                }
                if (myObj[24] != null) {
                    d25 = myObj[24].toString();
                }
                if (myObj[25] != null) {
                    d26 = myObj[25].toString();
                    Double dble = Double.parseDouble(d26);
                    d26 = dble.intValue() + "";

                }
                if (myObj[26] != null) {
                    d27 = myObj[26].toString();
                    Double dble = Double.parseDouble(d27);
                    d27 = dble.intValue() + "";
                }
                if (myObj[27] != null) {
                    d28 = myObj[27].toString();
                    Double dble = Double.parseDouble(d28);
                    d28 = dble.intValue() + "";
                }
                if (myObj[28] != null) {
                    d29 = myObj[28].toString();
                    if (d29.equals("NULL")) {
                        java.util.Date date = new java.util.Date();
                        actionTime = new Date(date.getTime());
                    } else {
                        java.util.Date date = formatter.parse(d29);
                        actionTime = new Date(date.getTime());
                    }
                }
                if (myObj[29] != null) {
                    d30 = myObj[29].toString();
                }
                if (myObj[30] != null) {
                    d31 = myObj[30].toString();
                }
                if (myObj[31] != null) {
                    d32 = myObj[31].toString();
                }
                if (myObj[32] != null) {
                    d33 = myObj[32].toString();
                }

                if (myObj[33] != null) {
                    d34 = myObj[33].toString();
                }
                if (myObj[34] != null) {
                    d35 = myObj[34].toString();
                }

                if (myObj[35] != null) {
                    d36 = myObj[35].toString();
                }

                if (myObj[36] != null) {
                    d37 = myObj[36].toString();
                }

                if (myObj[37] != null) {
                    d38 = myObj[37].toString();
                }

                if (myObj[38] != null) {
                    d39 = myObj[38].toString();
                }
                if (myObj[39] != null) {
                    d40 = myObj[39].toString();
                }
                if (myObj[40] != null) {
                    d41 = myObj[40].toString();

                }
                if (myObj[41] != null) {
                    d42 = myObj[41].toString();
                    Double dble = Double.parseDouble(d42);
                    d42 = dble.intValue() + "";
                }
                if (myObj[42] != null) {
                    d43 = myObj[42].toString();

                }
                if (myObj[43] != null) {
                    d44 = myObj[43].toString();
                    Double dble = Double.parseDouble(d44);
                    d44 = dble.intValue() + "";
                }
                if (myObj[44] != null) {
                    d45 = myObj[44].toString();

                }
                if (myObj[45] != null) {
                    d46 = myObj[45].toString();

                }

                int dCatId = 0;
                int locationId = 0;

                Double code = Double.parseDouble(d46);
                dCatId = code.intValue();

                Double code2 = Double.parseDouble(d44);
                locationId = code2.intValue();


                String sql3 = "INSERT INTO asset VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement psmt = (PreparedStatement) db.psmt(sql3);
                psmt.setInt(1, Integer.parseInt(d1));
                psmt.setString(2, d2);
                psmt.setString(3, d3);
                psmt.setDate(4, regDate);
                psmt.setString(5, d5);
                psmt.setInt(6, Integer.parseInt(d6));
                psmt.setInt(7, Integer.parseInt(d7));
                psmt.setInt(8, Integer.parseInt(d8));
                psmt.setInt(9, Integer.parseInt(d9));
                psmt.setInt(10, Integer.parseInt(d10));
                psmt.setInt(11, Integer.parseInt(d11));
                psmt.setInt(12, Integer.parseInt(d12));
                psmt.setString(13, d13);
                psmt.setString(14, d14);
                psmt.setDate(15, purDate);
                psmt.setInt(16, Integer.parseInt(d16));
                psmt.setInt(17,  Integer.parseInt(d17));
                if (d18.equals(" ")) {
                    psmt.setDouble(18, Double.parseDouble("0.00"));
                } else {
                    psmt.setDouble(18, Double.parseDouble(d18));
                }

                if (d19.equals(" ")) {
                    psmt.setDouble(19, Double.parseDouble("0.00"));
                } else {
                    psmt.setDouble(19, Double.parseDouble(d19));
                }
                psmt.setString(20, d20);
                psmt.setString(21, d21);
                psmt.setInt(22, Integer.parseInt(d22));
                psmt.setString(23, d23);
                psmt.setString(24, d24);
                psmt.setString(25, d25);
                psmt.setInt(26, Integer.parseInt(d26));
                psmt.setInt(27, Integer.parseInt(d27));
                psmt.setInt(28, Integer.parseInt(d28));
                psmt.setDate(29, actionTime);
                psmt.setString(30, d30);
                psmt.setString(31, d31);
                psmt.setString(32, d32);
                psmt.setString(33, d33);
                psmt.setString(34, d34);
                psmt.setString(35, d35);
                psmt.setString(36, d36);
                psmt.setString(37,d37);
                psmt.setString(38, d38);
                psmt.setString(39, d39);
                psmt.setString(40, d40);
                psmt.setString(41, d41);
                psmt.setInt(42, Integer.parseInt(d42));
                psmt.setString(43, d43);
                psmt.setInt(44, Integer.parseInt(d44));
                psmt.setString(45, d45);

                psmt.executeUpdate();

                // update location detals

                String sql4 = "INSERT INTO asset_location_details VALUES (?,?,?,?,?,?)";
                PreparedStatement psmt2 = (PreparedStatement) db.psmt(sql4);
                psmt2.setInt(1, i);
                psmt2.setInt(2, Integer.parseInt(d1));
                psmt2.setInt(3, locationId);
                psmt2.setInt(4, 1);
                psmt2.setInt(5, Integer.parseInt(d28));
                psmt2.setDate(6, actionTime);
                psmt2.executeUpdate();
                k++;

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("FAR Error :" + e);
            throw e;

        }
        return k;
    }

    private int uploadDetailCatData(List sheet) throws Exception {
        int k = 0;
        String sqldel = "DELETE FROM asset_catergory_detail";
        connection = db.connect();
        statement = connection.createStatement();
        statement.executeUpdate(sqldel);


        for (int i = 1; i < sheet.size(); i++) {
            List list = (List) sheet.get(i);
            Object myObj[] = new Object[list.size()];
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
                if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        myObj[j] = cell.getDateCellValue();
                    } else {
                        myObj[j] = cell.getNumericCellValue();
                        if (myObj[j] != null) {
                            Double aDouble = Double.parseDouble(myObj[j].toString());
                            myObj[j] = aDouble.intValue();
                        }

                    }
                } else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                    myObj[j] = cell.getStringCellValue();
                }


            }

            String d1 = "";
            String d2 = "";
            String d3 = "";
            String d4 = "";
            String d5 = "";
            String d6 = "";
            String d7 = "";
            String d8 = "";
            String d9 = "";
            String d10 = "";


            if (myObj[0] != null) {
                d1 = myObj[0].toString();
            }
            if (myObj[1] != null) {
                d2 = myObj[1].toString();
            }
            if (myObj[2] != null) {
                d3 = myObj[2].toString();
            }
            if (myObj[3] != null) {
                d4 = myObj[3].toString();
            }
            if (myObj[4] != null) {
                d5 = myObj[4].toString();
            }
            if (myObj[5] != null) {
                d6 = myObj[5].toString();
            }
            if (myObj[6] != null) {
                d7 = myObj[6].toString();
            }
            if (myObj[7] != null) {
                d8 = myObj[7].toString();
            }
            if (myObj[8] != null) {
                d9 = myObj[8].toString();
            }

            int maincatId = 0;
            int subcatId = 0;
            ResultSet rs1;
            ResultSet rs2;

            if (!d9.equals("")) {
               // Double mainCode = Double.parseDouble(d9);
                String sql1 = "select MCAT_ID from asset_catergory_main where MCAT_CODE='" + d9 + "'";
                rs1 = (ResultSet) db.fetch(sql1);
                while (rs1.next()) {
                    maincatId = rs1.getInt("MCAT_ID");
                }
            }

            if (!d4.equals("")) {
                String sql2 = "select SCAT_ID from asset_catergory_sub where SCAT_CODE='" + d4 + "' AND MCAT_ID='" + maincatId + "'";
                rs2 = (ResultSet) db.fetch(sql2);
                while (rs2.next()) {
                    subcatId = rs2.getInt("SCAT_ID");
                }
                if (subcatId > 0) {
                    String sql3 = "INSERT INTO asset_catergory_detail (DCAT_ID,DCAT_CODE,DCAT_DES,SCAT_ID,DEP_RATE_ACCOUNT,DEP_RATE_INCOME_TAX,USER_ID) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement psmt = (PreparedStatement) db.psmt(sql3);
                    psmt.setInt(1, i);
                    psmt.setString(2, d2);
                    psmt.setString(3, d3);
                    psmt.setInt(4, subcatId);
                    psmt.setString(5, d5);
                    psmt.setString(6, d6);
                    psmt.setInt(7, Integer.parseInt(d7));
                    //psmt.setDate(6, actionTime);
                    psmt.executeUpdate();
                    k++;
                }
            }
        }
        return k;

    }

    private int uploadSubCatData(List sheet) throws Exception {
        int k = 0;
        String sql1 = "DELETE FROM asset_catergory_sub";
        connection = db.connect();
        statement = connection.createStatement();
        statement.executeUpdate(sql1);

        for (int i = 1; i < sheet.size(); i++) {
            List list = (List) sheet.get(i);
            Object myObj[] = new Object[list.size()];
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
                if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        myObj[j] = cell.getDateCellValue();
                    } else {
                        myObj[j] = cell.getNumericCellValue();
                        if (myObj[j] != null) {
                            Double aDouble = Double.parseDouble(myObj[j].toString());
                            myObj[j] = aDouble.intValue();
                        }

                    }
                } else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                    myObj[j] = cell.getStringCellValue();
                }


            }

            String d1 = "";
            String d2 = "";
            String d3 = "";
            String d4 = "";
            String d5 = "";
            String d6 = "";


            if (myObj[0] != null) {
                d1 = myObj[0].toString();
            }
            if (myObj[1] != null) {
                d2 = myObj[1].toString();
            }
            if (myObj[2] != null) {
                d3 = myObj[2].toString();
            }
            if (myObj[3] != null) {
                d4 = myObj[3].toString();
            }
            if (myObj[4] != null) {
                d5 = myObj[4].toString();
            }
            Date actionTime = null;
            if (myObj[5] != null) {
                d6 = myObj[5].toString();
//                    Date date = (Date)formatter.parse(d6);
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(date);
//                    String formatedDate = cal.get(Calendar.YEAR) +  "-" + (cal.get(Calendar.MONTH) + 1) + "-" +  cal.get(Calendar.DATE);
//                    java.util.Date dd = simpleDateFormat.parse(formatedDate);
//                    actionTime = java.sql.Date.valueOf(String.valueOf(dd));
            }


            int macatId = 0;
            ResultSet rs = null;

            if (!d3.equals("")) {
                if (StringUtils.isNumeric(d3)) {
                    Double code = Double.parseDouble(d3);
                    Integer codeint = code.intValue();
                    d3 = codeint.toString();
                    if (d3.length() == 1) {
                        d3 = "0" + d3;
                    }
                }

                String sqlMain = "select MCAT_ID from asset_catergory_main where MCAT_CODE='" + d3 + "'";
                rs = (ResultSet) db.fetch(sqlMain);
                while (rs.next()) {
                    macatId = rs.getInt("MCAT_ID");
                }

                String sql2 = "INSERT INTO asset_catergory_sub (SCAT_ID,SCAT_CODE,MCAT_ID,SCAT_DES,USER_ID) VALUES (?,?,?,?,?)";
                PreparedStatement psmt = (PreparedStatement) db.psmt(sql2);
                psmt.setInt(1, i);
                if (d2.length() == 1) {
                    d2 = "0" + d2;
                }
                psmt.setString(2, d2);
                psmt.setInt(3, macatId);
                psmt.setString(4, d4);
                psmt.setInt(5, Integer.parseInt(d5));
                //psmt.setDate(6, actionTime);
                psmt.executeUpdate();
                k++;
            }
        }
        return k;
    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            // s is a valid integer
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            logger.error( "Error occurred while calling the isInteger Method : " + ex.getMessage() );
        }

        return isValidInteger;
    }

}
