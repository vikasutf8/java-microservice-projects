package com.SpringBoot.RestfullApi.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Field("street")
    private String street;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

    @Field("postalCode")
    private String postalCode;

    @Field("country")
    private String country;
}
