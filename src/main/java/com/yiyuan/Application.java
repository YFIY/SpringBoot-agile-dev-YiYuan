package com.yiyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 入口
 * @author MoLi
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.yiyuan.dao"})//配置需要扫描的Dao包
@EnableCaching//启用缓存
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
