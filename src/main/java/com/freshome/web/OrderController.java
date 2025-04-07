package com.freshome.web;

import com.freshome.dto.order.OrderCreateDTO;
import com.freshome.dto.order.OrderResponseDTO;
import com.freshome.dto.order.OrderSearchDTO;
import com.freshome.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
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

    @GetMapping("/find/all/by_customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersByCustomerId (
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok(
                orderService.findAllByCustomerId(customerId)
        );
    }

    @GetMapping("/find/all/by_expert/{expertId}")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersByExpertId (
            @PathVariable Long expertId
    ){
        return ResponseEntity.ok(
                orderService.findAllByExpertId(expertId)
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

    @PostMapping("/search")
    public ResponseEntity<List<OrderResponseDTO>> searchOrders (
            @RequestBody OrderSearchDTO searchDTO
    ){
        return ResponseEntity.ok(
                orderService.searchOrder(searchDTO)
        );
    }

    @PutMapping("/update/choose_expert")
    public ResponseEntity<OrderResponseDTO> chooseExpert (
            @RequestParam Long orderId,
            @RequestParam Long expertId
    ){
        return ResponseEntity.ok(
                orderService.chooseExpertForOrder(orderId, expertId)
        );
    }

    @PutMapping("/update/start/{orderId}")
    public ResponseEntity<OrderResponseDTO> startOrder (
            @PathVariable Long orderId
    ){
        return ResponseEntity.ok(
                orderService.startOrder(orderId)
        );
    }

    @PutMapping("/update/execute/{orderId}")
    public ResponseEntity<OrderResponseDTO> executeOrder (
            @PathVariable Long orderId
    ){
        return ResponseEntity.ok(
                orderService.executeOrder(orderId)
        );
    }
}
