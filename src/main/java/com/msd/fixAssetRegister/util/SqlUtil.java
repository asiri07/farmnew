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

package com.msd.fixAssetRegister.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


@Component
public class SqlUtil {

    private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    Properties prop = new Properties();
    InputStream input = null;

    public String Backupdbtosql() {
        String file = "";

        try {
            String fileName = "application.properties";
            input = getClass().getClassLoader().getResourceAsStream(fileName);
            prop.load(input);

            String username = prop.getProperty("spring.datasource.username");
            String password = prop.getProperty("spring.datasource.password");
            String database = prop.getProperty("db.database");
            String host = prop.getProperty("db.host");
            String port = prop.getProperty("db.port");
            String mysqlPath = prop.getProperty("db.mysqlPath");
            String dumpPath = prop.getProperty("db.dumpPath");

            String date = simpleDateFormat.format(new Date());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String time = timestamp.getTime() + "";


            File f1 = new File(dumpPath);
            if (!f1.exists()) {
                f1.mkdir();
            }
            String savePath = database + "-" + date + "-" + time + ".sql";


            //String executeCmd = "mysqldump -u" + username + " -p" + password + " --database " + database + " -r " + savePath;
            String batchCommand = "";

            //Backup with database
            batchCommand = mysqlPath + " -h " + host + " --port " + port + " -u " + username + " --password=" + password + " --add-drop-database -B " + database + " -r \"" + dumpPath + "" + savePath + "\"";


            Process runtimeProcess = Runtime.getRuntime().exec(batchCommand);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                file = dumpPath.replaceAll("\\\\", "") + "/" + savePath;
                logger.info("Backup Complete");
            } else {
                logger.info("Backup Failure");
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            logger.error("Can not Create Database Backup : " + ex.getMessage());
        }
        return file;
    }

}
