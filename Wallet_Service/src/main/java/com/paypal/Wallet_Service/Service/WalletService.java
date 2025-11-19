package com.paypal.Wallet_Service.Service;

import com.paypal.Wallet_Service.Dto.*;
import com.paypal.Wallet_Service.Enitity.Wallet;

public interface WalletService {

   public WalletResponse createWallet(CreateWalletRequest wallet);

   public WalletResponse credit(CreditResponse request);

    public WalletResponse debit(DebitResponse request);


    public WalletResponse getWallet(Long userId);

    public HoldResponse placeHold(HoldRequest request);

    public WalletResponse captureHold(CaptureRequest request);

    public HoldResponse releaseHold(String holdReference);
}
