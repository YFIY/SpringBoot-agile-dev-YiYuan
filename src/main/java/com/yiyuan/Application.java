package com.yiyuan;

import com.yiyuan.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 入口
 * @author MoLi
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.yiyuan.dao"})//配置需要扫描的Dao包
@EnableCaching//启用缓存
@Slf4j//日志
public class Application {


	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}

	public static void main(String[] args) throws UnknownHostException {
		//SpringApplication.run(Application.class, args);

		ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");
		log.info("\n----------------------------------------------------------\n\t" +
				"Application YiYuan is running! Access URLs:\n\t" +
				"Local: \t\thttp://localhost:" + port + path + "/\n\t" +
				"External: \thttp://" + ip + ":" + port + path + "/\n\t" +
				"swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
				"Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
				"----------------------------------------------------------");

	}

}
