package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}