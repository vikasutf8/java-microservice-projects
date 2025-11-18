package com.paypal.Reward_Service.Repository;

import com.paypal.Reward_Service.Enitity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward,Long > {

    List<Reward> findByUserId(Long UserId);

    Boolean existsByTransactionId(Long TransactionId);

}
