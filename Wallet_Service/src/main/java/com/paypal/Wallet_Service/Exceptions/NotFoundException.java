package com.paypal.Wallet_Service.Exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mgs){
        super(mgs);
    }
}
