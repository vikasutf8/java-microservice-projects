package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}