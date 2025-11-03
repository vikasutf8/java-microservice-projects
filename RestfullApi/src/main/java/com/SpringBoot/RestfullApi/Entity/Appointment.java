package com.SpringBoot.RestfullApi.Entity;


import com.SpringBoot.RestfullApi.Entity.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;
    @Column(length = 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
//many is this table -- one is other table
    @ManyToOne()
    @JoinColumn(name = "appointment_patient_id",nullable = false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "appointed_docker_id",nullable = false)
    private Doctor doctor;



}

//patient takes many appointment --but a single appointment is having a single patient many to one
// how to choose owning side specially one to many or many one
// which table doesn't meant of without other table iis owing side // dependent table is owming talbe
// without patient ... no value of appointment so appointment is owing . side -- handle fk