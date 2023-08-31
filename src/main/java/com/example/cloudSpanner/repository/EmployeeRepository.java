package com.example.cloudSpanner.repository;

import com.example.cloudSpanner.domain.Employee;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends SpannerRepository<Employee, Long> {
}
