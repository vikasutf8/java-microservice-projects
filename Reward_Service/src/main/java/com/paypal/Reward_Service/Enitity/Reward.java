package com.paypal.Reward_Service.Enitity;


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
@Table(name = "reward")
public class Reward {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double points;

    private LocalDateTime sentAt;


    @Column(unique = true,nullable = false)
    private Long transactionId;


}
