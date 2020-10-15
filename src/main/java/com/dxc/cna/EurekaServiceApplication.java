package com.dxc.cna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.springframework.cloud.netflix.hystrix.dashboard.*;

@SpringBootApplication
@EnableEurekaServer
//@EnableAdminServer
@EnableHystrixDashboard
//@EnableConfigServer
//@EnableCircuitBreaker
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}
}

