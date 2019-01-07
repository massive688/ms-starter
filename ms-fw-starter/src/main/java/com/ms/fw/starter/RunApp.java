package com.ms.fw.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Import;

//import com.ms.dbatis.spring.dynamic.config.MsDynamicDataSourceRegister;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan("com.ms.*")
public class RunApp {
    public static void main( String[] args ) {
    	SpringApplication.run(RunApp.class, args);
    }
}
/**

com.ms.dbatis.spring.dynamic.config.MsMyBatisMapperScannerConfig,\

*/