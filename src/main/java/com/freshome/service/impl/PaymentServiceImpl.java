package com.freshome.service.impl;

import com.freshome.dto.credit.CreditChargeOrWithdrawDTO;
import com.freshome.dto.payment.PaymentDTO;
import com.freshome.entity.Credit;
import com.freshome.entity.Offer;
import com.freshome.entity.Order;
import com.freshome.entity.enumeration.OrderStatus;
import com.freshome.exception.*;
import com.freshome.service.CreditService;
import com.freshome.service.OrderService;
import com.freshome.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderService orderService;
    private final CreditService creditService;


    @Override
    @Transactional(readOnly = true)
    public PaymentDTO getOrderPaymentInfo(Long orderId) {
        Order order = orderService.findOptionalOrderById(orderId)
                .orElseThrow(() -> new NotFoundException(Order.class, orderId));
        if (order.getStatus() == OrderStatus.PAID)
            throw new PaidOrderException();
        if (order.getStatus() != OrderStatus.COMPLETED)
            throw new NotCompletedOrderException();

        Offer selectedOffer = order.getOffers().stream().filter(
                        o -> o.getExpert().getId().equals(order.getExpert().getId())
                ).findFirst()
                .orElseThrow(()-> new NotFoundException(
                        "The expert doesn't have an offer for this order! "));

        Double paymentAmount = Double.valueOf(selectedOffer.getSuggestedPriceByExpert());

        return new PaymentDTO(paymentAmount, order);
    }

    @Override
    @Transactional
    public Double payFromCredit(Long orderId) {
        PaymentDTO paymentDto = getOrderPaymentInfo(orderId);

        Credit customerCredit = paymentDto.order().getCustomer().getCredit();
        Credit expertCredit = paymentDto.order().getExpert().getCredit();

        creditService.withdrawFromCredit(
                new CreditChargeOrWithdrawDTO(
                        customerCredit.getId(), paymentDto.paymentAmount()));
        creditService.chargeCredit(
                new CreditChargeOrWithdrawDTO(
                        expertCredit.getId(), paymentDto.paymentAmount() * 0.7));
        paymentDto.order().setStatus(OrderStatus.PAID);

        return paymentDto.paymentAmount();
    }

    @Override
    @Transactional
    public Double payFromCard(Long orderId) {
        PaymentDTO paymentDto = getOrderPaymentInfo(orderId);

        Credit expertCredit = paymentDto.order().getExpert().getCredit();

        creditService.chargeCredit(
                new CreditChargeOrWithdrawDTO(
                        expertCredit.getId(), paymentDto.paymentAmount() * 0.7));
        paymentDto.order().setStatus(OrderStatus.PAID);

        return paymentDto.paymentAmount();
    }

    @Override
    public void validateCaptcha(String storedCaptcha, String captchaInput){
        if (storedCaptcha == null || !storedCaptcha.equalsIgnoreCase(captchaInput))
            throw new InvalidCaptchaException();
    }
}
