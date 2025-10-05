package com.Learn.internatWorking;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.status" ,havingValue = "stripe")
public class StripeService implements PaymentService{
    @Override
    public String pay() {
        String paymentt ="this is Usa stripe";
                return paymentt;
    }
}
