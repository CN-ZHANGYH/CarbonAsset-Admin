package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println(" 数字碳链V2.0 开发版   \n" +
                " ____ __________ _     \n" +
                "/ ___|__  /_   _| |    \n" +
                "\\___ \\ / /  | | | |    \n" +
                " ___) / /_  | | | |___ \n" +
                "|____/____| |_| |_____|\n");
    }
}
