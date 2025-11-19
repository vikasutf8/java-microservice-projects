package com.paypal.Wallet_Service.Service;

import com.paypal.Wallet_Service.Dto.*;
import com.paypal.Wallet_Service.Enitity.Enum.WalletStatus;
import com.paypal.Wallet_Service.Enitity.Transaction;
import com.paypal.Wallet_Service.Enitity.Wallet;
import com.paypal.Wallet_Service.Enitity.WalletHold;
import com.paypal.Wallet_Service.Exceptions.InSufficientException;
import com.paypal.Wallet_Service.Repository.TransactionRepository;
import com.paypal.Wallet_Service.Repository.WalletHoldRepository;
import com.paypal.Wallet_Service.Repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;
    private  final WalletHoldRepository walletHoldRepository;
    private final TransactionRepository transactionRepository;


    @Override
    @Transactional
    public WalletResponse createWallet(CreateWalletRequest request) {
        Wallet wallet =new Wallet(request.getUserId(), request.getCurrency());
        Wallet saved =walletRepository.save(wallet);

        return new WalletResponse(
                saved.getId(),
                saved.getUserId(),
                saved.getCurrency(),
                saved.getBalance(),
                saved.getAvailableBalance(),
                saved.getCreatedAt()
        );

    }

    @Override
    @Transactional
    public WalletResponse credit(CreditResponse request) {
       System.out.printf("Request: userId=%d, currency=%s, amount=%d%n",
        request.getUserId(),
        request.getCurrency(),
        request.getAmount()
);

        Wallet wallet =walletRepository.findByUserIdAndCurrency(request.getUserId(),"INR").orElseThrow();


        wallet.setBalance(wallet.getBalance() +request.getAmount());
        wallet.setAvailableBalance(wallet.getAvailableBalance()+ request.getAmount());
        Wallet saved =walletRepository.save(wallet);

        Long amount =request.getAmount();

        transactionRepository.save(
                new Transaction(wallet.getId(),"CREDIT",amount,"SUCCESS") // !! WRONG
        );
        System.out.printf("Wallet Updated: id=%d, userId=%d, currency=%s, balance=%d, available=%d%n",
                saved.getId(),
                saved.getUserId(),
                saved.getCurrency(),
                saved.getBalance(),
                saved.getAvailableBalance()
        );


        return new WalletResponse(
                saved.getId(),
                saved.getUserId(),
                saved.getCurrency(),
                saved.getBalance(),
                saved.getAvailableBalance(),
                saved.getCreatedAt()
        );

    }

    @Override
    @Transactional
    public WalletResponse debit(DebitResponse request) {

            System.out.printf("Request: userId=%d, currency=%s, amount=%d%n",
                    request.getUserId(),
                    request.getCurrency(),
                    request.getAmount()
            );

            Wallet wallet =walletRepository.findByUserIdAndCurrency(request.getUserId(),"INR").orElseThrow();

            if(wallet.getAvailableBalance() <request.getAmount())throw new InSufficientException("Not enoght balance");

        wallet.setBalance(wallet.getBalance() -request.getAmount());
        wallet.setAvailableBalance(wallet.getAvailableBalance()- request.getAmount());
        Wallet saved =walletRepository.save(wallet);

        System.out.printf("Wallet Updated: id=%d, userId=%d, currency=%s, balance=%d, available=%d%n",
                saved.getId(),
                saved.getUserId(),
                saved.getCurrency(),
                saved.getBalance(),
                saved.getAvailableBalance()
        );


        return new WalletResponse(
                saved.getId(),
                saved.getUserId(),
                saved.getCurrency(),
                saved.getBalance(),
                saved.getAvailableBalance(),
                saved.getCreatedAt()
        );

    }

    @Override
    @Transactional
    public WalletResponse getWallet(Long userId) {

        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow();

         return new WalletResponse(
                wallet.getId(),
                wallet.getUserId(),
                wallet.getCurrency(),
                wallet.getBalance(),
                wallet.getAvailableBalance(),
                wallet.getCreatedAt()
        );

    }

    @Override
    @Transactional
    public HoldResponse placeHold(HoldRequest request) {
        System.out.printf("Request: userId=%d, currency=%s, amount=%d%n",
                request.getUserId(),
                request.getCurrency(),
                request.getAmount()
        );

        Wallet wallet =walletRepository.findByUserIdAndCurrency(request.getUserId(),"INR").orElseThrow();
        if(wallet.getAvailableBalance() <request.getAmount())throw new InSufficientException("Not enoght balance");

        wallet.setAvailableBalance(wallet.getAvailableBalance()- request.getAmount());

        WalletHold walletHold =new WalletHold();

        walletHold.setWallet(wallet);
        walletHold.setAmount(request.getAmount());
        walletHold.setHoldReference("Hold-"+ System.currentTimeMillis());
        walletHold.setStatus(WalletStatus.ACTIVE);

        walletRepository.save(wallet);
        walletHoldRepository.save(walletHold);


        return new HoldResponse(
                walletHold.getId(),
                walletHold.getWallet().getId(),
                walletHold.getHoldReference(),
                walletHold.getAmount(),
                walletHold.getStatus(),
                walletHold.getCreateAt(),
                walletHold.getExpireAt()

        );

    }

    @Override
    @Transactional
    public WalletResponse captureHold(CaptureRequest request) {
        WalletHold walletHold =walletHoldRepository.findByHoldReference(request.getHoldReference()).orElseThrow();

        if(!WalletStatus.ACTIVE.equals(walletHold.getStatus())){
            throw new IllegalStateException("Holed is not active");
        }


        Wallet wallet = walletHold.getWallet();
        wallet.setAvailableBalance(wallet.getAvailableBalance()- walletHold.getAmount());

        walletHold.setStatus(WalletStatus.CAPTURE);

        walletHoldRepository.save(walletHold);
        walletRepository.save(wallet);


        return new WalletResponse(
                wallet.getId(),
                wallet.getUserId(),
                wallet.getCurrency(),
                wallet.getBalance(),
                wallet.getAvailableBalance(),
                wallet.getCreatedAt()
        );

    }

    @Override
    @Transactional
    public HoldResponse releaseHold(String holdReference) {
        WalletHold walletHold =walletHoldRepository.findByHoldReference(holdReference).orElseThrow();
        if(!WalletStatus.ACTIVE.equals(walletHold.getStatus())){
            throw new IllegalStateException("Holed is not active");
        }
        Wallet wallet = walletHold.getWallet();
        wallet.setAvailableBalance(wallet.getAvailableBalance()- walletHold.getAmount());

        walletHold.setStatus(WalletStatus.RELEASE);

        walletHoldRepository.save(walletHold);
        walletRepository.save(wallet);
        return new HoldResponse(
                walletHold.getId(),
                walletHold.getWallet().getId(),
                walletHold.getHoldReference(),
                walletHold.getAmount(),
                walletHold.getStatus(),
                walletHold.getCreateAt(),
                walletHold.getExpireAt()

        );
    }


}
