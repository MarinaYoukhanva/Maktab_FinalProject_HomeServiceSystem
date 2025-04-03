package com.freshome.web;

import com.freshome.dto.order.OrderCreateDTO;
import com.freshome.dto.order.OrderResponseDTO;
import com.freshome.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder (
            @Valid @RequestBody OrderCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                orderService.createOrder(createDTO));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById (
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                orderService.findOrderById(id)
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrders () {
        return ResponseEntity.ok(
                orderService.findAllOrders()
        );
    }

    @GetMapping("/find/all/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersByCustomerId (
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok(
                orderService.findAllByCustomerId(customerId)
        );
    }

    @GetMapping("/find/all/by_sub_services")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersBySubServices (
//            @RequestParam(required = false) List<Long> subServiceIds
            @RequestParam List<Long> subServiceIds
    ) {
        return ResponseEntity.ok(
                orderService.findAllBySubServiceIds(subServiceIds)
        );
    }
}
