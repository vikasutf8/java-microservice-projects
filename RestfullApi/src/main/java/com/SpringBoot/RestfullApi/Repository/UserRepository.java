package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Dto.Enum.AuthProviderType;
import com.SpringBoot.RestfullApi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}
