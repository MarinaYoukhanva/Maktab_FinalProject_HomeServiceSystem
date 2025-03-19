package com.freshome;

import com.freshome.entity.*;
import com.freshome.entity.dto.customer.CustomerCreateDTO;
import com.freshome.entity.dto.expert.ExpertCreatDTO;
import com.freshome.entity.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.dto.order.OrderCreateDTO;
import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.service.CreditService;
import com.freshome.service.CustomerService;
import com.freshome.service.ExpertService;
import com.freshome.service.OrderService;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		OrderService orderService = spring.getBean(OrderService.class);

//		SingularAttribute<?, ?> c = Customer_.firstname;
//		System.out.println(c.getJavaType());
//		System.out.println(c);
//		System.out.println(c.getName());
//
		List<SingularAttribute<?, ?>> fields = Arrays.asList(Expert_.firstname, Expert_.lastname);
		List<Operator> operators = Arrays.asList(Operator.CONTAINS, Operator.ENDS_WITH);
		List<String> values = Arrays.asList("w", "e");

		expertService.searchExpert(fields,operators, values, "cle")
				.forEach(System.out::println);

//		customerService.searchCustomer(fields, operators, values)
//				.forEach(System.out::println);

//		expertService.findOptionalExpertById(4L).get().getSubServices()
//				.stream().map(SubService::getName)
//				.toList()
//				.forEach(System.out::println);

//		try{
//			expertService.createExpert(new ExpertCreatDTO(
//					"m",null,null,null,LocalDateTime.now(),
//					null,null,null,null
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

//		try {
//			var res = customerService.createCustomer(new CustomerCreateDTO(
//					"m", "y", null, null, LocalDateTime.now(),
//					UserStatus.PENDING_APPROVAL, null
//			));
//			System.out.println(res);
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}

//		try{
//			var res = orderService.createOrder(new OrderCreateDTO(
//					200L, "aa", LocalDateTime.now(), null,
//					new Address("a", "b", "c", 10), OrderStatus.WAITING_FOR_EXPERT_OFFERS,
//					5L,4L,2L)
//			);
//			System.out.println(res);
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//		System.out.println(customerService.findCustomerById(2L));
//		System.out.println(customerService.findOptionalCustomerById(2L).orElse(null));
	}

}
