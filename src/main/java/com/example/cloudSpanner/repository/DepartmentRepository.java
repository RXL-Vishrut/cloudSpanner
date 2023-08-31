package com.example.cloudSpanner.repository;

import com.example.cloudSpanner.domain.Department;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends SpannerRepository<Department, Long> {
}
