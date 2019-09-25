package org.xarch.reliable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages="xarch.reliable.util")
public class ReliableWechatPayIdManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliableWechatPayIdManagerApplication.class, args);
	}

}
