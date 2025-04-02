package com.freshome;

import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.credit.CreditUpdateDTO;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.order.OrderCreateDTO;
import com.freshome.dto.order.OrderUpdateDTO;
import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.repository.ReviewRepository;
import com.freshome.service.*;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
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

//        CustomerService customerService = spring.getBean(CustomerService.class);
//        CreditService creditService = spring.getBean(CreditService.class);
        ExpertService expertService = spring.getBean(ExpertService.class);
//        OrderService orderService = spring.getBean(OrderService.class);
//        ReviewService reviewService = spring.getBean(ReviewService.class);
//        ReviewRepository rrepo = spring.getBean(ReviewRepository.class);
//        OfferService offerService = spring.getBean(OfferService.class);


        System.out.println(expertService.findExpertById(2L));
        expertService.findAllExperts()
                .forEach(System.out::println);

//        rrepo.findAll();

//        try{
//            orderService.createOrder(new OrderCreateDTO(
//                    10L, "asdf", LocalDateTime.now(), null,
//                    null,null,null,null, OrderStatus.WAITING_FOR_EXPERT_OFFERS, 15L, 4L, 2L
//            ));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

//        try {
//            CustomerUpdateDTO c = CustomerUpdateDTO.builder()
//                    .id(15L)
//                    .firstname("qwe")
//                    .lastname("")
//                    .email("asdfg")
//                    .status(null)
//                    .build();
//            customerService.updateCustomer(c);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        try{
//            var o = OrderUpdateDTO.builder()
//                    .id(1L)
//                    .street("new street")
//                    .orderExecutionDateTime(LocalDateTime.now().plusDays(4))
//                    .status(OrderStatus.STARTED)
//                    .build();
//            orderService.updateOrder(o);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

//		try{
//			customerService.changePassword(16L, "1234", "1234");
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}

//		SingularAttribute<?, ?> c = Customer_.firstname;
//		System.out.println(c.getJavaType());
//		System.out.println(c);
//		System.out.println(c.getName());
//
//		List<SingularAttribute<?, ?>> fields = Arrays.asList(Expert_.firstname);
//		List<Operator> operators = Arrays.asList(Operator.CONTAINS);
//		List<String> values = Arrays.asList("a");
//
//		expertService.searchExpert(fields,operators, values, null)
//				.forEach(System.out::println);

//		expertService.searchExpert(fields, operators, values,null)
//				.forEach(System.out::println);

//		expertService.findOptionalExpertById(4L).get().getSubServices()
//				.stream().map(SubService::getName)
//				.toList()
//				.forEach(System.out::println);

//		try{
//			expertService.createExpert(new ExpertCreatDTO(
//					null,"y","aabb@gmail.coom","a1234aSeffn"
//                    , UserStatus.PENDING_APPROVAL,
//                    "0936",null
//			));
//		}catch (Exception e){
//            System.out.println(Arrays.toString(e.getStackTrace()));
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
//					"mm", "yy", "ffg@sff.com", "a12Sad@tt", LocalDateTime.now(),
//					UserStatus.NEW, null
//			));
//			System.out.println(res);
//		}catch (Exception e){
//			System.out.println(e.getMessage() + e.getCause());
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
