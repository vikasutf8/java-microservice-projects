package com.paypal.Reward_Service.Service;

import com.paypal.Reward_Service.Enitity.Reward;
import com.paypal.Reward_Service.Repository.RewardRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Data
public class RewardServiceImpl implements RewardService{

    private final RewardRepository rewardRepository;

    @Override
    public Reward sendReward(Reward reward) {
        reward.setSentAt(LocalDateTime.now());
        return rewardRepository.save(reward);
    }

    @Override
    public List<Reward> getRewardbyUserId(Long userId) {
        return rewardRepository.findByUserId(userId);
    }
}
