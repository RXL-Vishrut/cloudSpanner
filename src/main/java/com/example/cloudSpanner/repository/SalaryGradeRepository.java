package com.example.cloudSpanner.repository;

import com.example.cloudSpanner.domain.SalaryGrade;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryGradeRepository extends SpannerRepository<SalaryGrade, Long> {
}
