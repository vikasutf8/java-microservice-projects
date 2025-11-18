package com.paypal.user_service.dto;

public class CreateWalletClient {

    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private String currency;


    public Long getUserId() {
        return userId;
    }

    public String getCurrency() {
        return currency;
    }
}
