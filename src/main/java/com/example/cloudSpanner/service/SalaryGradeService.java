package com.example.cloudSpanner.service;

import com.example.cloudSpanner.domain.SalaryGrade;
import com.example.cloudSpanner.dto.SalaryGradeDto;
import com.example.cloudSpanner.mapper.DomainDtoMapper;
import com.example.cloudSpanner.repository.SalaryGradeRepository;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryGradeService {

    @Autowired
    private SalaryGradeRepository salaryGradeRepository;

    @Autowired
    private SpannerTemplate spannerTemplate;

    public void addSalaryGrade(SalaryGradeDto salaryGradeDtoDto){
        SalaryGrade salGrade = DomainDtoMapper.getSalaryGrade(salaryGradeDtoDto);
        spannerTemplate.insert(salGrade);
    }
}