package com.${packageName}.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.${packageName}.rest.*",
	"com.${packageName}.service.child.impl",
	"com.${packageName}.service.major.impl",
	"com.${packageName}.service.poly.impl",
	"com.${packageName}.service.individualization.impl",
	"com.${packageName}.service.reference.impl",
	"com.${packageName}.translate"})
@MapperScan({
	"com.${packageName}.mapper",
	"com.${packageName}.mapper.*"})
public class AppConfiguration {
} 

