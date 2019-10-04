package com.msd.fixAssetRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.msd.fixAssetRegister")
public class FixedAssetRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FixedAssetRegisterApplication.class,args);
    }
    
}
