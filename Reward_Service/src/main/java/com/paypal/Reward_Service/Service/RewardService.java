package com.paypal.Reward_Service.Service;

import com.paypal.Reward_Service.Enitity.Reward;

import java.util.List;

public interface RewardService {

    Reward sendReward(Reward reward);
    List<Reward> getRewardbyUserId(Long userId);
}
