package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}