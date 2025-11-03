package com.SpringBoot.RestfullApi.Entity;

import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 100)
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

//   oneto one --head docker --docker
    @OneToOne()
    @JoinColumn()
    private Doctor headDocker; //having head_docker_id


//    manay to many


//    private List<Doctor>
//    having a jointalble -IMPORTANRT --created an new table automatic
// owming side  where relations is defined as manytomany -- update by department talbe not docker talbe
    @ManyToMany
    @JoinTable(name = "docker-having-department-having-docker", joinColumns =@JoinColumn(name = "dept_id"),
    inverseJoinColumns =  @JoinColumn(name = "doc_id"))
    private Set<Doctor> doctors =new HashSet<>();
}
