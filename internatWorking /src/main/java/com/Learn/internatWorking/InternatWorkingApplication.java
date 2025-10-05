package com.Learn.internatWorking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternatWorkingApplication implements CommandLineRunner {

//    @Autowired
//    private RazorPayPaymentService razorPayPaymentService;
//
//    @Autowired
//    private  StripeService stripeService;
        @Autowired
        private PaymentService paymentService;

//    public InternatWorkingApplication(RazorPayPaymentService razorPayPaymentService) {
//        this.razorPayPaymentService =
//                razorPayPaymentService;
//
//    }

    public static void main(String[] args) {

        SpringApplication.run(InternatWorkingApplication.class, args);
    }

    public void run(String... args) throws Exception {

//        String pay = razorPayPaymentService.pay();
//        System.out.println(pay);
//        String pays = stripeService.pay();
//        System.out.println(pays);
        String pay = paymentService.pay();
        System.out.println(pay+ "method");

    }
}


//    private  RazorPayPaymentService paymentService =new RazorPayPaymentService();
//tight coupling as object defined  : that wrong
// as whati want automatice dicide config which service use ??
//use that bean