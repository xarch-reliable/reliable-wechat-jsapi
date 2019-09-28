package org.xarch.reliable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReliableClearCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliableClearCenterApplication.class, args);
	}

}
