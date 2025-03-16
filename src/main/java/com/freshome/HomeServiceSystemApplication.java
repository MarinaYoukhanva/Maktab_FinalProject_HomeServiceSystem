package com.freshome;

import com.freshome.entity.Customer;
import com.freshome.entity.Expert;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HomeServiceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeServiceSystemApplication.class, args);

		LocalDateTime a = LocalDateTime.of(2020, 12, 1, 12,30);
		Expert e = Expert.builder()
				.build();
		Expert expert = new Expert();
	}

}
