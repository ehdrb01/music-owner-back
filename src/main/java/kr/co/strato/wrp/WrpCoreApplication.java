package kr.co.strato.wrp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:config.properties")
public class WrpCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WrpCoreApplication.class, args);
	}

}
