package com.yiyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 入口
 * @Author MoLi
 */
@SpringBootApplication
//配置需要扫描的Dao包
@MapperScan(basePackages = {"com.yiyuan.dao"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
