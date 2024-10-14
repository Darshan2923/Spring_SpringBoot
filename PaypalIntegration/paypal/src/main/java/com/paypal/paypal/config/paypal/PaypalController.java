package com.paypal.paypal.config.paypal;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    private final PaypalService paypalService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment() {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(10.0, "INR", "paypal", "sale", "Payment description",
                    cancelUrl, successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            // TODO: handle exception
            log.error("Error occured ::", e);
        }
        return new RedirectView("/payment/error");
    }

}
