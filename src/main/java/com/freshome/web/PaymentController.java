package com.freshome.web;

import com.freshome.dto.CardInfoDTO;
import com.freshome.exception.InvalidCaptchaException;
import com.freshome.exception.PaymentSessionExpiredException;
import com.freshome.service.PaymentService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;

@RestController
@RequestMapping("/v1/payment")
@Validated
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final Validator validator;

    @GetMapping("/order_price/{orderId}")
    public ResponseEntity<Double> getOrderPaymentAmount(
            @PathVariable Long orderId,
            HttpSession session
    ){
        if (session.getAttribute("paymentStartTime") == null) {
            session.setAttribute("paymentStartTime", System.currentTimeMillis());
        }
        return ResponseEntity.ok(
                paymentService.getOrderPaymentInfo(orderId).paymentAmount());
    }

    @PostMapping("/pay_with_card/{orderId}")
    public ResponseEntity<Double> payFromCard(
            @PathVariable Long orderId,
            @RequestBody CardInfoDTO cardInfoDTO,
            @RequestParam String captchaInput,
            HttpSession session
    ) throws MethodArgumentNotValidException, NoSuchMethodException {

        Long paymentStartTime = (Long) session.getAttribute("paymentStartTime");
        if (paymentStartTime == null || System.currentTimeMillis() - paymentStartTime > 30 * 1000) {
            session.removeAttribute("paymentStartTime");
            throw new PaymentSessionExpiredException();
        }
        BindingResult bindingResult = new BeanPropertyBindingResult(cardInfoDTO, "cardInfoDTO");
        validator.validate(cardInfoDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            Method method = this.getClass().getMethod(
                    "payFromCard", Long.class, CardInfoDTO.class, String.class, HttpSession.class
            );
            MethodParameter methodParameter = new MethodParameter(method, 1);
            throw new MethodArgumentNotValidException(methodParameter, bindingResult);
        }
        String storedCaptcha = (String) session.getAttribute("captcha");
        paymentService.validateCaptcha(storedCaptcha, captchaInput);

        Double result = paymentService.payFromCard(orderId);
        session.removeAttribute("paymentStartTime");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/pay_with_credit/{orderId}")
    public ResponseEntity<Double> payFromCredit(
            @PathVariable Long orderId,
            @RequestParam String captchaInput,
            HttpSession session
    ) {
        String storedCaptcha = (String) session.getAttribute("captcha");
        paymentService.validateCaptcha(storedCaptcha, captchaInput);
        return ResponseEntity.ok(
                paymentService.payFromCredit(orderId));
    }

    @GetMapping("/captcha")
    public void getCaptcha(
            HttpServletResponse response, HttpSession session)
            throws IOException, FontFormatException {
        SpecCaptcha captcha = new SpecCaptcha(160, 50, 5);

        captcha.setFont(Captcha.FONT_9);

        session.setAttribute("captcha", captcha.text());

        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        captcha.out(out);
        out.flush();
        out.close();
    }
}
