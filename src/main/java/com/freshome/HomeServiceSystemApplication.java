package com.freshome;

import com.freshome.entity.dto.customer.CustomerCreateDTO;
import com.freshome.entity.dto.expert.ExpertCreatDTO;
import com.freshome.entity.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.service.CreditService;
import com.freshome.service.CustomerService;
import com.freshome.service.ExpertService;
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
		ExpertService expertService = spring.getBean(ExpertService.class);

//		try{
//			expertService.createExpert(new ExpertCreatDTO(
//					"m",null,null,null,LocalDateTime.now(),
//					null,null,null,null,null
//			));
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//		try{
//			expertService.updateExpert(new ExpertUpdateDTO(
//					4L, "aa",null,null,null, null,
//					null,null,null,null
//			));
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}

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
			var res = customerService.createCustomer(new CustomerCreateDTO(
					"m", "y", null, null, LocalDateTime.now(),
					UserStatus.PENDING_APPROVAL, null
			));
			System.out.println(res);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
