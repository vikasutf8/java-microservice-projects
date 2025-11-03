package com.SpringBoot.RestfullApi.Dto;

import com.SpringBoot.RestfullApi.Entity.Enum.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountBloodTypeResponseEnitity {
    private BloodType bloodType;
    private Long count ;
}
