package com.SpringBoot.RestfullApi;

import com.SpringBoot.RestfullApi.Entity.Appointment;
import com.SpringBoot.RestfullApi.Entity.Insurance;
import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Service.AppointmentService;
import com.SpringBoot.RestfullApi.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testAppointment(){
//        Appointment appointment = Appointment.builder()
//                .(LocalDateTime.of(2025, 11, 1, 14, 0))
//                .range("Cancer")
//                .build();
//
//
//        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
//        System.out.println(newAppointment);
//
//        appointmentService.reassignAppointmentToAontherDocter(newAppointment.getId(), 3L);

    }
}
