package com.ms.fw.starter.rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Aman {
	
	@NotBlank(message="名字不能为空")
	String name;
	@NotNull(message="年纪不能为空")
	int age;
}
