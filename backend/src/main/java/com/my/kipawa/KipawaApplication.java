package com.my.kipawa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication

public class KipawaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KipawaApplication.class, args);
	}

}
