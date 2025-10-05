package com.Learn.internatWorking;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.status",havingValue = "razorpay")
public class RazorPayPaymentService implements  PaymentService {
    @Override
    public  String pay(){
        String payment= "RazorPay payment";
        System.out.println(payment+"fkadshfkajsdjf;las");
                return payment;
    }
}
