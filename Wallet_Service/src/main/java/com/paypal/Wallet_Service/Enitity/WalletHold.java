package com.paypal.Wallet_Service.Enitity;

import com.paypal.Wallet_Service.Enitity.Enum.WalletStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "wallet_holds",uniqueConstraints = {
        @UniqueConstraint(columnNames = "holdReference") // userId must be unique
})
public class WalletHold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(optional = false)
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

    @Column(nullable = false)
    private String holdReference; // unique id of each hold

    @Column(nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalletStatus status; // Active,capture,release
//
//    active --> capture  on success paytment
//            --> release on failure

    private LocalDateTime createAt =LocalDateTime.now();

    private LocalDateTime expireAt; // hold/active as TTL
}
