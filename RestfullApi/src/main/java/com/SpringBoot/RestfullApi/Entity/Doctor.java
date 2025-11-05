package com.SpringBoot.RestfullApi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100)
    private String specialization;

    @Column(nullable = false,unique = true, length = 50)
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    //    bidirectional
    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments =new HashSet<>();


    @OneToOne()
    @MapsId()  // mapoing of user id == docker id  same
    private User user;

}
