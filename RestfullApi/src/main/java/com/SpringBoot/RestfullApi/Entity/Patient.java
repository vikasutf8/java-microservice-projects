package com.SpringBoot.RestfullApi.Entity;


import com.SpringBoot.RestfullApi.Entity.Enum.BloodType;
import com.SpringBoot.RestfullApi.Entity.Enum.GenderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "patients_table",
uniqueConstraints = {
        @UniqueConstraint(name = "unique_patient_email" , columnNames = {"email"}),
        @UniqueConstraint(name = "unique_patient_dob" , columnNames = {"birthDate"})
},
indexes = {
        @Index(name = "idx_patient_dob",columnList = "birthDate")
})
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "fullName")
    private String name;

    @ToString.Exclude
    private LocalDate birthDate;

    private String email;

    // @Enumerated(EnumType.STRING) ensures the enum name (e.g., "MALE") is stored in the database instead of its numeric index (which is error-prone).
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

}
