package com.upworkgeorgia.UpWorkGeorgia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class UpWorkGeorgiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpWorkGeorgiaApplication.class, args);
	}

}
