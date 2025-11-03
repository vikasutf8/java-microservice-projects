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
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "patients_table",
uniqueConstraints = {
//        @UniqueConstraint(name = "unique_patient_email" , columnNames = {"email"}),
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


//    this is colume is joincolcum
//    we wnat to delete insurance on delete patient CascadeRemove + only remove insurance not delete patient -- orphan remove
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "insurance_id" ,nullable = true)
    private Insurance insurance;


//    having appointment
//    bidirectional -- JPA know --db not
//    cascading - on patient delete --appointment delte- should meant  no patient no appointment
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Appointment> appointments;

//    fetch type -eager /lazy -- issue on sout()  fetch = FetchType.EAGER avoid in db
//    fetch = FetchType.EAGER bydefulat in onetoone mapping

//    here- patient A -- all detail 1
//              -  at this patient A haivng N appointment so alos find them
//    toatl N+1  query call


//    solution 1 -- donot populate lazy  and dto tostring.exclude
//    sotultuon 2 - custom query -- not findall create  a single query that fetch all recored

}


// CASCADING  -- DATA DOMAIN
// appointmentis child
// patient is parent





//Use CascadeType.PERSIST when you want create child entities to save automatically with the parent.
//Use CascadeType.MERGE when you want update child entities to save automatically with the parent.
//Use CascadeType.REMOVE to delete all related children when the parent is deleted.
//
//Use CascadeType.ALL when you want full lifecycle propagation.
//
//Be cautious with CascadeType.REMOVE — it can delete data unintentionally.



//JPA orphanRemoval
//
//
//orphanRemoval = true automatically deletes child entities when they are no longer linked to the parent.
//
//
//Works with both @OneToMany and @OneToOne relationships.
//
//
//        For @OneToMany → triggers when an item is removed from the list or the list is replaced/cleared.
//
//
//        For @OneToOne → triggers when the reference is set to null or replaced with a new entity.
//
//
//Orphaned entities are deleted automatically during flush or commit — no manual remove() needed.
//
//
//Different from CascadeType.REMOVE:
//
//
//CascadeType.REMOVE → deletes child when parent is deleted.
//
//
//orphanRemoval = true → deletes child when unlinked, even if parent remains.
//
//
//
//
//Best used when a child entity has no meaning without its parent (e.g., Appointment without Doctor).
//

