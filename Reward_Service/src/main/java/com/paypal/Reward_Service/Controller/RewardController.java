package com.paypal.Reward_Service.Controller;


import com.paypal.Reward_Service.Enitity.Reward;
import com.paypal.Reward_Service.Repository.RewardRepository;
import com.paypal.Reward_Service.Service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rewards/")
@RequiredArgsConstructor
public class RewardController {
    private final RewardRepository rewardRepository;
    private final RewardService rewardService;

    @GetMapping("user/{userId}")
    public List<Reward> getRewardsByUser(Long userId){
        return rewardService.getRewardbyUserId(userId);
    }

    @GetMapping()
    public List<Reward> getAllReward(){
        return rewardRepository.findAll();
    }
}
