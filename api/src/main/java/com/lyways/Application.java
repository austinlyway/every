package com.lyways;


import com.lyways.bizcommon.business.dao.BaseDAO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author: austin
 * @createDate: 10/25/17
 */
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages={"com.lyways"})
@MapperScan(basePackages={"com.lyways.business.*.dao", "com.lyways.tool.jdbc.adapter.mybatis"})
//,markerInterface= BaseDAO.class, annotationClass= SequenceGen.class
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
