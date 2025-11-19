package com.paypal.Wallet_Service.Enitity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "wallets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId") // userId must be unique
        })
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long userId;

    @NotNull
    @Column(nullable = false, length = 3)
    private String currency = "INR";

    @NotNull
    @Column(nullable = false)
    private Long balance = 0L;

    @NotNull
    @Column(nullable = false)
    private Long availableBalance = 0L;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}