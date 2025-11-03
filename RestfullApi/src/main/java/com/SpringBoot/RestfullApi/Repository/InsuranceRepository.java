package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}