package com.freshome;

import com.freshome.entity.Credit;
import com.freshome.entity.Customer;
import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import com.freshome.entity.dto.CustomerCreateDTO;
import com.freshome.entity.dto.CustomerUpdateDto;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.repository.CreditRepository;
import com.freshome.service.CreditService;
import com.freshome.service.CustomerService;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "com.freshome.*")
public class HomeServiceSystemApplication {

	public static void main(String[] args) {
		var spring = SpringApplication.run(HomeServiceSystemApplication.class, args);
//
//		LocalDateTime a = LocalDateTime.of(2020, 12, 1, 12,30);
//		Expert e = Expert.builder()
//				.build();
//		Expert expert = new Expert();
//		Order order = Order.builder()
//				.build();
//		Credit c1 = spring.getBean(CreditRepository.class)
//				.findById(1L).get();
//		Credit c2 = spring.getBean(CreditRepository.class)
//				.findById(1L).get();
//		System.out.println(c1);
//		System.out.println(c2);
//		System.out.println(c1.equals(c2));

		CustomerService customerService = spring.getBean(CustomerService.class);
		CreditService creditService = spring .getBean(CreditService.class);

//		System.out.println(customerService.findCustomerById(2L).credit());

//		try {
//			System.out.println(customerService.findCustomerById(1L));
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//		try{
//			creditService.deleteCredit(2L);
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}

		try {
			customerService.createCustomer(new CustomerCreateDTO(
					"m", "y", null, null, LocalDateTime.now(),
					UserStatus.PENDING_APPROVAL, null, null
			));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
