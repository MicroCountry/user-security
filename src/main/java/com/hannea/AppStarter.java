package com.hannea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * Class
 *
 * @author wgm
 * @date 2018/03/16
 */
@EnableTransactionManagement
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.hannea")
public class AppStarter
{
    private static Logger logger = LoggerFactory.getLogger(AppStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
        logger.info("ufu-admin started");
    }
}
